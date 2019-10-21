package chojniak.marcin.testysb.controlers.uzytkownik;

import chojniak.marcin.testysb.users.UserQuestion;

import java.util.Set;

public class UserPanelForm {
    Set<UserQuestion> userQuestionSet;

    public Set<UserQuestion> getUserQuestionSet() {
        return userQuestionSet;
    }

    public UserPanelForm setUserQuestionSet(Set<UserQuestion> userQuestionSet) {
        this.userQuestionSet = userQuestionSet;
        return this;
    }
}
