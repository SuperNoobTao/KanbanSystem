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
* @since <pre>ʮһ�� 8, 2016</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit֧�֣��ɴ�����Spring-Test���֧�֣�
@SpringApplicationConfiguration(classes = KanbanApplication.class) // ָ������SpringBoot���̵�Application������
@WebAppConfiguration // ������Web��Ŀ��Junit��Ҫģ��ServletContext�����������Ҫ�����ǵĲ��������@WebAppConfiguration��
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
