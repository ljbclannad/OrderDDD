package com.example.orderddd.infrastructure.config.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

@Component
public class NacosServiceUtil {

    @Value("${nacos.discovery.server-addr}")
    private String serverAddr;

    /**
     * 注册实例
     * 
     * @throws NacosException
     */
    public void registerInstance(String serviceName, String groupName, String ip, int port) throws NacosException {
        Instance instance = new Instance();
        instance.setIp(ip);
        instance.setPort(port);
        instance.setClusterName("DEFAULT");
        NacosFactory.createNamingService(serverAddr).registerInstance(serviceName, groupName, instance);
    }

    /**
     * 注销实例
     * 
     * @throws NacosException
     */
    public void deregisterInstance(String serviceName, String groupName, String ip, int port) throws NacosException {
        NamingService naming = NamingFactory.createNamingService(System.getProperty("serveAddr"));
        naming.deregisterInstance(serviceName, groupName, ip, port);
    }
}
