package chojniak.marcin.testysb.testy;

import chojniak.marcin.testysb.ApplicationException;
import chojniak.marcin.testysb.users.User;
import chojniak.marcin.testysb.users.UserRepository;
import chojniak.marcin.testysb.users.UserTest;
import chojniak.marcin.testysb.users.UserTestRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DummyService {

    private UserRepository userRepository;

    private UserTestRepository userTestRepository;

    public DummyService(UserRepository userRepository, UserTestRepository userTestRepository) {
        this.userRepository = userRepository;
        this.userTestRepository = userTestRepository;
    }

    @Transactional
    public User doSomething() {
        User newUser = new User();
        UserTest newUserTest = new UserTest();
        newUser.setEmail("dummy@email.com");
        newUserTest.setUser(newUser);
        newUserTest.setScore(100);

        userRepository.save(newUser);
        userTestRepository.save(newUserTest);

        throw new ApplicationException();
    }
}
