package chojniak.marcin.testysb.testy;


import chojniak.marcin.testysb.testy.question.Question;
import chojniak.marcin.testysb.users.User;
import chojniak.marcin.testysb.users.UserTest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String testName;
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private Set<UserTest> userTest = new HashSet<>();

    public Test(String testName) {
        this.testName = testName;
    }

    public Test() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<UserTest> getUserTest() {
        return userTest;
    }

    public void setUserTest(Set<UserTest> userTest) {
        this.userTest = userTest;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testName='" + testName + '\'' +
                '}';
    }
}
