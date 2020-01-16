package chojniak.marcin.testysb.users;

import chojniak.marcin.testysb.testy.answers.Answers;
import chojniak.marcin.testysb.testy.question.Question;

import javax.persistence.*;

@Entity(name = "user_question")
public class UserQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "USERTEST_ID")
    private UserTest userTest;
    @Column
    private Boolean rightAnswer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_ID")
    private User user;
    @Column(name = "ANSWERED_ID")
    private Long answerId;
    @Transient
    private Answers answerByUser;

    public Answers getAnswerByUser() {
        return answerByUser;
    }

    public void setAnswerByUser(Answers answerByUser) {
        this.answerByUser = answerByUser;
    }

    public UserQuestion() {
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public UserTest getUserTest() {
        return userTest;
    }

    public void setUserTest(UserTest userTest) {
        this.userTest = userTest;
    }

    public Boolean getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }


}
