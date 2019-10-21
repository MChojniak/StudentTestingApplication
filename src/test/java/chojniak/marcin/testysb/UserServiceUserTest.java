package chojniak.marcin.testysb;

import chojniak.marcin.testysb.users.User;
import chojniak.marcin.testysb.users.UserService;
import chojniak.marcin.testysb.users.UserTest;
import chojniak.marcin.testysb.users.UserTestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest
        .WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserServiceUserTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserTestService userTestService;

    @Before
    public void initDB(){
        {
            userService.registerNewUser(
                    "Marysia",
                    "Skrzypczyk",
                    "marysia@gmail.com",
                    "maslo");

            userService.registerNewUser(
                    "Robert",
                    "Kubica",
                    "kubica@gmail.com",
                    "maslo");
        }
        {
            userTestService.addUserTest();
            userTestService.addUserTest();
        }
    }

    @Test
    public void functionalityTest(){
        User user = userService.findByEmail("marysia@gmail.com");
        User user2 = userService.findByEmail("kubica@gmail.com");

        Set<UserTest> userTests = userTestService.addUser(user,(long)1);
        assertThat(userTests.size()).isEqualTo(1);
        UserTest userTest = userTestService.findUserTestById((long)1);
        assertThat(userTest.getUser()).isNotNull();
    }
}
