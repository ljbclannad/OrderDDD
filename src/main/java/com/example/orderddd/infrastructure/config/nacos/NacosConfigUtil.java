package com.example.orderddd.infrastructure.config.nacos;

import java.util.Properties;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NacosConfigUtil {

    @Value("${nacos.discovery.server-addr}")
    private String serverAddr;

    /**
     * 获取配置
     * 
     * @throws NacosException
     */
    public void getConfig(String dataId, String group) throws NacosException {
        try {
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, 5000);
            log.info(content);
            log.info("获取配置成功");
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加监听器
     * 
     * @throws NacosException
     */
    public void addListener(String dataId, String group, long timeoutMs) throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("namespace", "public");
        ConfigService configService = NacosFactory.createConfigService(properties);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("recieve1: {}", configInfo);
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }

    /**
     * 删除监听器
     * 
     * @throws NacosException
     */
    public void deleteListener(String dataId, String group) throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        configService.removeListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("recieve2: {}", configInfo);
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }

    /**
     * 发布配置
     * 
     * @throws NacosException
     */
    public void publishConfig(String dataId, String group, String content, String type) throws NacosException {
        try {
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            // 直接使用publishConfig进行配置发布时，可能存在不同进程间并发的配置覆盖问题，此时可以使用带Compare-And-Swap（CAS）的发布配置API，来保证不会此类情形。
            configService.publishConfigCas(dataId, group, content, type);
            log.info("发布配置成功");
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除配置
     * 
     * @throws NacosException
     */
    public void deleteConfig(String dataId, String group) throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        configService.removeConfig(dataId, group);
        log.info("删除配置成功");
    }

    /**
     * 获取配置并监听
     * 
     * @throws NacosException
     */
    public void getConfigAndSignListener(String dataId, String group) {
        try {
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfigAndSignListener(dataId, group, 5000, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("recieve1:" + configInfo);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
            log.info(content);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}
