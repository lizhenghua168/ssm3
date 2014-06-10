package com.worlk.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * Controller测试基类，这里与service层的区别是多一个@WebAppConfiguration注解
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration({"classpath:spring-config.xml", "classpath:spring-servlet.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public abstract class AbstractTestCase {

    @Autowired
    private WebApplicationContext wac;

    //private MockMvc mockMvc;

    @Before
    public void setUp()
    {
        //this.mockMvc = webAppContextSetup(this.wac).build();
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /*public MockMvc getMockMvc() {
        return mockMvc;
    }

    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }*/
}
