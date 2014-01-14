package com.worlk.service.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @RunWith(Suite.class) 集合测试
 * @Suite.SuiteClasses({CompanyServImplTest.class}) 集合，如果有多个类只
 * 需要用逗号隔开就可以，这个类可以用于service层测试，与spring无关
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({CompanyServImplTest.class})
public class ServAllTests {
}
