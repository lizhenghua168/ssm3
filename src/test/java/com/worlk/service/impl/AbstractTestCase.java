package com.worlk.service.impl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ContextConfiguration(locations = "classpath:spring-config.xml")导入配置文件。这时候，我们可以看出之前使用applicationContext.xml文件作为系统总控文件的好处！ 当然，Spring-Test的这个配置只认classpath，很无奈，我必须拷贝这些文件到根目录！
 * @RunWith(SpringJUnit4ClassRunner.class)SpringJUnit支持，由此引入Spring-Test框架支持！
 * @Transactional这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！
 * @TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），同时指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！
 AbstractTransactionalDataSourceSpringContextTests要想构建这一系列的无污染纯绿色事务测试框架就必须找到这个基类！
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback =true)
public abstract class AbstractTestCase {
}
