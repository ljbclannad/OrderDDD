package com.example.orderddd.nacos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.nacos.api.exception.NacosException;
import com.example.orderddd.infrastructure.config.nacos.NacosServiceUtil;

@SpringBootTest
public class NacosServiceUtilTest {

    @Autowired
    private NacosServiceUtil nacosServiceUtil;

    @Test
    public void testRegisterInstance() {
        try {
            nacosServiceUtil.registerInstance("orderDDD", "DEFAULT_GROUP", "127.0.0.1", 8081);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

}
