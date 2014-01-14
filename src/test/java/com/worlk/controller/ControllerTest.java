package com.worlk.controller;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by XSF on 14-1-13.
 */
public class ControllerTest extends AbstractTestCase {

    @Test
    public void test() throws Exception
    {
        getMockMvc().perform((get("/user/test")))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
