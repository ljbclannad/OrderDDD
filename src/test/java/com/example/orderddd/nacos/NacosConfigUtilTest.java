package com.example.orderddd.nacos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.nacos.api.exception.NacosException;
import com.example.orderddd.infrastructure.config.nacos.NacosConfigUtil;

@SpringBootTest
public class NacosConfigUtilTest {

    @Autowired
    private NacosConfigUtil nacosConfigUtil;

    @Value("${nacos.config.data-id}")
    private String dataId;

    @Value("${nacos.config.group}")
    private String group;

    @Test
    public void testGetConfig() {
        try {
            nacosConfigUtil.getConfig(dataId, group);
        } catch (NacosException e) {
            e.printStackTrace(); // 处理异常
        }
    }

    @Test
    public void testAddListener() {
        try {
            nacosConfigUtil.addListener(dataId, group, 5000);
        } catch (NacosException e) {
            e.printStackTrace(); // 处理异常
        }
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace(); // 处理异常
            }
        }
    }

    @Test
    public void testDeleteListener() {
        try {
            nacosConfigUtil.deleteListener(dataId, group);
        } catch (NacosException e) {
            e.printStackTrace(); // 处理异常
        }
    }

    @Test
    public void testPublishConfig() {
        try {
            nacosConfigUtil.publishConfig(dataId, group,
                    "app:\n  name: my-application\n  version: 1.0.2\n  description: A sample application using Nacos and Spring Boot",
                    "yaml");
        } catch (NacosException e) {
            e.printStackTrace(); // 处理异常
        }
    }

    @Test
    public void testDeleteConfig() {
        try {
            nacosConfigUtil.deleteConfig(dataId, group);
        } catch (NacosException e) {
            e.printStackTrace(); // 处理异常
        }
    }
}
