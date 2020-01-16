package chojniak.marcin.testysb.testy.question;

import chojniak.marcin.testysb.testy.Test;
import chojniak.marcin.testysb.testy.answers.Answers;
import chojniak.marcin.testysb.users.UserQuestion;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String questionContent;
    @Column(unique = false)
    private int questionValue;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answers> answers = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "TEST_ID")
    private Test test = null;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<UserQuestion> userQuestion = new HashSet<>();
    @Transient
    private Long temp_id;


    public Question(String questionContent, int questionValue) {
        this.questionContent = questionContent;
        this.questionValue = questionValue;
    }

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public int getQuestionValue() {
        return questionValue;
    }

    public void setQuestionValue(int questionValue) {
        this.questionValue = questionValue;
    }

    public Set<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answers> answers) {
        this.answers = answers;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Set<UserQuestion> getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(Set<UserQuestion> userQuestion) {
        this.userQuestion = userQuestion;
    }

    public Long getTemp_id() {
        return temp_id;
    }

    public void setTemp_id(Long temp_id) {
        this.temp_id = temp_id;
    }
}
