package com.ctrip.zeus.restful.resource;

import com.ctrip.zeus.auth.Authorize;
import com.ctrip.zeus.exceptions.NotFoundException;
import com.ctrip.zeus.executor.TaskManager;
import com.ctrip.zeus.lock.DbLockFactory;
import com.ctrip.zeus.lock.DistLock;
import com.ctrip.zeus.model.entity.Archive;
import com.ctrip.zeus.model.entity.Group;
import com.ctrip.zeus.model.entity.GroupVirtualServer;
import com.ctrip.zeus.model.transform.DefaultSaxParser;
import com.ctrip.zeus.restful.message.ResponseHandler;
import com.ctrip.zeus.service.activate.ActivateService;
import com.ctrip.zeus.service.activate.ActiveConfService;
import com.ctrip.zeus.service.build.BuildInfoService;
import com.ctrip.zeus.service.build.BuildService;
import com.ctrip.zeus.service.model.GroupRepository;
import com.ctrip.zeus.service.model.SlbRepository;
import com.ctrip.zeus.service.nginx.NginxService;
import com.ctrip.zeus.service.task.TaskService;
import com.ctrip.zeus.service.task.constant.TaskOpsType;
import com.ctrip.zeus.task.entity.OpsTask;
import com.ctrip.zeus.task.entity.TaskResult;
import com.ctrip.zeus.task.entity.TaskResultList;
import com.ctrip.zeus.util.AssertUtils;
import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fanqq on 2015/6/11.
 */

@Component
@Path("/deactivate")
public class DeactivateResource {
    @Resource
    private ActivateService activateService;
    @Resource
    private NginxService nginxAgentService;
    @Resource
    private BuildInfoService buildInfoService;
    @Resource
    private BuildService buildService;
    @Resource
    private DbLockFactory dbLockFactory;
    @Resource
    private SlbRepository slbRepository;
    @Resource
    private GroupRepository groupRepository;
    @Resource
    private ResponseHandler responseHandler;
    @Resource
    private ActiveConfService activeConfService;
    @Resource
    private TaskManager taskManager;


    private static DynamicIntProperty lockTimeout = DynamicPropertyFactory.getInstance().getIntProperty("lock.timeout", 5000);
    private static DynamicBooleanProperty writable = DynamicPropertyFactory.getInstance().getBooleanProperty("activate.writable", true);

    @GET
    @Path("/group")
    @Authorize(name="deactivate")
    public Response deactivateGroup(@Context HttpServletRequest request,@Context HttpHeaders hh,@QueryParam("groupId") List<Long> groupIds,  @QueryParam("groupName") List<String> groupNames)throws Exception{
        List<Long> _groupIds = new ArrayList<>();
        List<Long> _slbIds = new ArrayList<>();

        if ( groupIds!=null && !groupIds.isEmpty() )
        {
            _groupIds.addAll(groupIds);
        }
        if ( groupNames!=null && !groupNames.isEmpty() )
        {
            for (String groupName : groupNames)
            {
                Group group = groupRepository.get(groupName);
                if (group == null)
                {
                    continue;
                }
                _groupIds.add(group.getId());
            }
        }

        List<OpsTask> tasks = new ArrayList<>();
        for (Long id : _groupIds) {
            Set<Long> slbIds =activeConfService.getSlbIdsByGroupId(id);
            for (Long slbId : slbIds){
                OpsTask task = new OpsTask();
                task.setGroupId(id);
                task.setOpsType(TaskOpsType.DEACTIVATE_GROUP);
                task.setTargetSlbId(slbId);
                tasks.add(task);
            }
        }
        List<Long> taskIds = taskManager.addTask(tasks);
        List<TaskResult> results = taskManager.getResult(taskIds,30000L);

        TaskResultList resultList = new TaskResultList();
        for (TaskResult t : results){
            resultList.addTaskResult(t);
        }
        resultList.setTotal(results.size());
        return responseHandler.handle(resultList,hh.getMediaType());
    }

}
