package com.ctrip.zeus.support;

import com.ctrip.zeus.auth.entity.*;
import com.ctrip.zeus.dal.core.*;
import com.ctrip.zeus.model.entity.*;

/**
 * @author:xingchaowang
 * @date: 3/6/2015.
 */
public class C {


    public static Group toGroup(GroupDo d) {
        return new Group()
                .setAppId(d.getAppId())
                .setName(d.getName())
                .setSsl(d.isSsl())
                .setVersion(d.getVersion());
    }

    public static GroupServer toGroupServer(GroupServerDo d) {
        return new GroupServer()
                .setIp(d.getIp())
                .setHostName(d.getHostName())
                .setFailTimeout(d.getFailTimeout())
                .setMaxFails(d.getMaxFails())
                .setPort(d.getPort())
                .setWeight(d.getWeight());
    }

    public static GroupSlb toGroupSlb(GroupSlbDo d) {
        return new GroupSlb()
                .setGroupName(d.getGroupName())
                .setSlbName(d.getSlbName())
                .setPath(d.getPath())
                .setRewrite(d.getRewrite())
                .setPriority(d.getPriority());
    }

    public static Domain toDomain(SlbDomainDo d) {
        return new Domain()
                .setName(d.getName());
    }

    public static HealthCheck toHealthCheck(GroupHealthCheckDo d) {
        return new HealthCheck()
                .setFails(d.getFails())
                .setIntervals(d.getIntervals())
                .setPasses(d.getPasses())
                .setUri(d.getUri());
    }

    public static LoadBalancingMethod toLoadBalancingMethod(GroupLoadBalancingMethodDo d) {
        return new LoadBalancingMethod()
                .setType(d.getType())
                .setValue(d.getValue());
    }


    public static Slb toSlb(SlbDo d) {
        return new Slb()
                .setName(d.getName())
                .setNginxBin(d.getNginxBin())
                .setNginxConf(d.getNginxConf())
                .setNginxWorkerProcesses(d.getNginxWorkerProcesses())
                .setStatus(d.getStatus())
                .setVersion(d.getVersion());
    }

    public static SlbServer toSlbServer(SlbServerDo d) {
        return new SlbServer()
                .setEnable(d.isEnable())
                .setHostName(d.getHostName())
                .setIp(d.getIp());
    }

    public static Vip toVip(SlbVipDo d) {
        return new Vip()
                .setIp(d.getIp());
    }

    public static VirtualServer toVirtualServer(SlbVirtualServerDo d) {
        return new VirtualServer()
                .setPort(d.getPort())
                .setName(d.getName())
                .setSsl(d.isIsSsl());
    }

    public static Archive toGroupArchive(ArchiveGroupDo d) {
        return new Archive()
                .setName(d.getName())
                .setContent(d.getContent())
                .setVersion(d.getVersion());
    }

    public static Archive toSlbArchive(ArchiveSlbDo d) {
        return new Archive()
                .setName(d.getName())
                .setContent(d.getContent())
                .setVersion(d.getVersion());
    }

    public static Role toRole(AuthRoleDo roleDo){
        return new Role()
                .setRoleName(roleDo.getRoleName())
                .setDescription(roleDo.getDescription());
    }

    public static User toUser(AuthUserDo userDo){
        return new User()
                .setUserName(userDo.getUserName())
                .setDescription(userDo.getDescription());
    }

    public static Resource toResource(AuthResourceDo resourceDo){
        return new Resource()
                .setResourceName(resourceDo.getResourceName())
                .setDescription(resourceDo.getDescription())
                .setResourceType(resourceDo.getResourceType());
    }

    /*Entity to Do*/

    public static GroupDo toGroupDo(Group e) {
        return new GroupDo().setAppId(e.setAppId())
                .setName(e.getName())
                .setSsl(e.isSsl())
                .setVersion(e.getVersion());
    }

    public static GroupServerDo toGroupServerDo(GroupServer e) {
        return new GroupServerDo()
                .setIp(e.getIp())
                .setHostName(e.getHostName())
                .setFailTimeout(e.getFailTimeout())
                .setMaxFails(e.getMaxFails())
                .setPort(e.getPort())
                .setWeight(e.getWeight());
    }

    public static GroupSlbDo toGroupSlbDo(GroupSlb e) {
        return new GroupSlbDo()
                .setGroupName(e.getGroupName())
                .setSlbName(e.getSlbName())
                .setSlbVirtualServerName(e.getVirtualServer().getName())
                .setPath(e.getPath())
                .setRewrite(e.getRewrite())
                .setPriority(e.getPriority() == null ? 0 : e.getPriority().intValue());
    }

    public static SlbDomainDo toSlbDomainDo(Domain e) {
        return new SlbDomainDo()
                .setName(e.getName());
    }

    public static GroupHealthCheckDo toGroupHealthCheckDo(HealthCheck e) {
        return new GroupHealthCheckDo()
                .setUri(e.getUri())
                .setIntervals(e.getIntervals())
                .setFails(e.getFails())
                .setPasses(e.getPasses());
    }

    public static GroupLoadBalancingMethodDo toGroupLoadBalancingMethodDo(LoadBalancingMethod e) {
        return new GroupLoadBalancingMethodDo()
                .setType(e.getType())
                .setValue(e.getValue());
    }

    public static SlbDo toSlbDo(Slb e) {
        return new SlbDo()
                .setName(e.getName())
                .setNginxBin(e.getNginxBin())
                .setNginxConf(e.getNginxConf())
                .setNginxWorkerProcesses(e.getNginxWorkerProcesses())
                .setStatus(e.getStatus())
                .setVersion(e.getVersion());
    }

    public static SlbServerDo toSlbServerDo(SlbServer e) {
        return new SlbServerDo()
                .setHostName(e.getHostName())
                .setIp(e.getIp())
                .setEnable(e.getEnable());
    }

    public static SlbVipDo toSlbVipDo(Vip e) {
        return new SlbVipDo()
                .setIp(e.getIp());
    }

    public static SlbVirtualServerDo toSlbVirtualServerDo(VirtualServer e) {
        return new SlbVirtualServerDo()
                .setPort(e.getPort())
                .setIsSsl(e.isSsl())
                .setName(e.getName());
    }

    public static AuthRoleDo toRoleDo(Role role) {
        return new AuthRoleDo()
                .setDescription(role.getDescription())
                .setRoleName(role.getRoleName());
    }

    public static AuthUserDo toUserDo(User user) {
        return new AuthUserDo()
                .setUserName(user.getUserName())
                .setDescription(user.getDescription());
    }

    public static AuthResourceDo toResourceDo(Resource resource){
        return new AuthResourceDo()
                .setResourceName(resource.getResourceName())
                .setResourceType(resource.getResourceType())
                .setDescription(resource.getDescription());
    }


}
