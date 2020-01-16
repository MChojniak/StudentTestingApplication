//package chojniak.marcin.testysb;
//
//import chojniak.marcin.testysb.users.groups.Group;
//import chojniak.marcin.testysb.users.groups.GroupService;
//import chojniak.marcin.testysb.users.User;
//import chojniak.marcin.testysb.users.UserService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest
//                        .WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//public class GroupServiceTest {
//    @Autowired
//    private GroupService groupService;
//    @Autowired
//    private UserService userService;
//
//    @Before
//    public void initDb(){
//        {
//            userService.registerNewUser(
//                    "Marysia",
//                    "Skrzypczyk",
//                    "marysia@gmail.com",
//                    "maslo");
//        }
//        {
//            userService.registerNewUser(
//                    "Robert",
//                    "Kubica",
//                    "kubica@gmail.com",
//                    "maslo");
//        }
//        {
//            groupService.addGroup("GRUPA_1");
//        }
//        {
//            groupService.addGroup("GRUPA_2");
//        }
//    }
//
//    @Test
//    public void groupsFunctionalityTesting(){
//
//    }
//}
