package chojniak.marcin.testysb.registration;

import javax.validation.constraints.*;

public class RegistrationForm {

    @Size(min = 3, max = 50)
    private String firstName;
    @Size(min = 3, max = 50)
    private String lastName;
    @Email
    @NotEmpty
    @Size(min = 3, max = 100)
    private String email;
    @Pattern(regexp = "^(?=.*[A-Za-zęóąśłżźćńĘÓĄŚŁŻŹĆŃ])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&ęóąśłżźćńĘÓĄŚŁŻŹĆŃ]{8,25}$")
    private String password;
    private String passwordConfirmation;

    public boolean passwordConfirmationCorrect() {
        return (passwordConfirmation != null && !passwordConfirmation.isEmpty()) ? passwordConfirmation.equals(password) : false ;
    }

    public String getFirstName() {
        return firstName;
    }

    public RegistrationForm setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public RegistrationForm setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegistrationForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegistrationForm setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public RegistrationForm setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
        return this;
    }
}
