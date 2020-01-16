package chojniak.marcin.testysb.users;

import chojniak.marcin.testysb.testy.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserTestService {
    private Logger logger = LoggerFactory.getLogger(UserTestService.class);
    private UserTestRepository userTestRepository;

    @Autowired
    public UserTestService(UserTestRepository userTestRepository) {
        this.userTestRepository = userTestRepository;
    }

    public UserTest addUserTest() {
        UserTest userTest = new UserTest();
        return userTestRepository.save(userTest);
    }

    public Set<UserTest> addUserTests(Set<User> users, Test test) {
        Set<UserTest> userTests = new HashSet<>();

        for (User a : users) {
            UserTest ut = new UserTest();
            ut.setUser(a);
            ut.setTest(test);
            userTestRepository.save(ut);
            userTests.add(ut);
        }
        return userTests;
    }

    public UserTest findUserTestById(Long id) {
        UserTest userTest = userTestRepository.findById(id).get();
        userTest.getUserQuestions().size();
        return userTest;
    }

    public UserTest addTest(Test test, Long id) {
        UserTest userTest = findUserTestById(id);
        userTest.setTest(test);
        //test.getUserTest().add(userTest);
        return userTestRepository.save(userTest);
    }

    //TODO this function is returning wrong type of data and needs refactoring
    public Set<UserTest> addUser(User user, Long id) {
        UserTest userTest = findUserTestById(id);
        user.getUserTests().add(userTest);
        userTest.setUser(user);
        userTestRepository.save(userTest);
        return user.getUserTests();
    }

    public void closeUserTest(UserTest userTest, int score, int maxscore) {
        userTest.setTestAvailable(false);
        userTest.setScore(score);

        if (score > maxscore / 2)
            userTest.setTestPassed(true);
        userTestRepository.save(userTest);
    }

    public Set<UserTest> findUserTestsByUser(User user) {
        return userTestRepository.findAllByUser(user);
    }

    public ArrayList<UserTest> findAllUserTests() {
        return userTestRepository.findAll();
    }
}
