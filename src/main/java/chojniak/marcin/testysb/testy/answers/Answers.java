package chojniak.marcin.testysb.testy.answers;

import chojniak.marcin.testysb.testy.question.Question;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String answerContent;
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;
    @Column
    private Boolean correct = false;
    @Transient
    private Long tempId;

    public Answers(String answerContent, boolean correct) {
        this.answerContent = answerContent;
        this.correct = correct;
    }

    public Answers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }
}