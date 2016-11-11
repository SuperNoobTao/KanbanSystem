package test.cc.superliar.service; 

import cc.superliar.KanbanApplication;
import cc.superliar.entity.Admin;
import cc.superliar.service.AdminService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/** 
* AdminServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>十一月 8, 2016</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringApplicationConfiguration(classes = KanbanApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
public class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: find(String name) 
* 
*/ 
@Test
public void testFind() throws Exception { 
//TODO: Test goes here...

    Admin admin = adminService.find("1");
    System.out.println(admin);
} 


} 
