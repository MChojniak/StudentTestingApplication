package chojniak.marcin.testysb.users;

import chojniak.marcin.testysb.testy.Test;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "TEST_ID")
    private Test test;
    @Column
    private Integer score;
    @Column
    @Temporal(TemporalType.TIME)
    private Date testTime;
    @Column
    private Boolean testPassed = false;
    @Column
    private Boolean testAvailable = true;

    @OneToMany(mappedBy = "userTest", cascade = CascadeType.ALL)
    private Set<UserQuestion> userQuestions = new HashSet<>();

    public UserTest() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getTestAvailable() {
        return testAvailable;
    }

    public void setTestAvailable(Boolean testAvailable) {
        this.testAvailable = testAvailable;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public Boolean getTestPassed() {
        return testPassed;
    }

    public void setTestPassed(Boolean testPassed) {
        this.testPassed = testPassed;
    }

    public Set<UserQuestion> getUserQuestions() {
        return userQuestions;
    }

    public void setUserQuestions(Set<UserQuestion> userQuestions) {
        this.userQuestions = userQuestions;
    }

}
