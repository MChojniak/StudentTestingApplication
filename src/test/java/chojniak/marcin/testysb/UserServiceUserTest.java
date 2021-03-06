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

import java.util.ArrayList;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

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
    public void initDB() {
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

        userTestService.addUserTest();
        userTestService.addUserTest();
    }

    @Test
    public void shouldUserBeAbleToAssignToUserTest() {
        Set<User> allNotAdmins = userService.findAllNotAdmins();
        assertThat(allNotAdmins).isNotNull();
        int usersSize = allNotAdmins.size();
        assertThat(usersSize).isNotNull();

        ArrayList<UserTest> allUserTests = userTestService.findAllUserTests();
        assertThat(allUserTests).isNotNull();
        int userTestsSize = allUserTests.size();
        assertThat(userTestsSize).isNotNull();

        User user = userService.findByEmail("marysia@gmail.com");
        assertThat(user).isNotNull();
        User user2 = userService.findByEmail("kubica@gmail.com");
        assertThat(user2).isNotNull();

        Set<UserTest> userTests = userTestService.addUser(user, allUserTests.get(0).getId());
        assertThat(userTests.size()).isEqualTo(1);
        userTests = userTestService.addUser(user, allUserTests.get(1).getId());
        assertThat(userTests.size()).isEqualTo(2);


        userTests = userTestService.addUser(user2, allUserTests.get(1).getId());
        assertThat(userTests.size()).isEqualTo(1);

        UserTest userTest = userTestService.findUserTestById(allUserTests.get(0).getId());
        assertThat(userTest.getUser()).isNotNull();
        assertThat(userTest.getUser().getEmail()).isEqualToIgnoringCase("marysia@gmail.com");

        userTest = userTestService.findUserTestById(allUserTests.get(1).getId());
        assertThat(userTest.getUser()).isNotNull();
        assertThat(userTest.getUser().getEmail()).isEqualToIgnoringCase("kubica@gmail.com");
    }
}
