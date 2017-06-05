package com.hss01248.sptest.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/6/5 0005.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/springmvc.xml",
        "classpath:/spring/applicationContext-dao.xml",
        "classpath:/spring/applicationContext-service.xml",
        "classpath:/spring/applicationContext-transation.xml",
        "classpath:mybatis/SqlMapConfig.xml"})
public class BaseJunitTest {
}
