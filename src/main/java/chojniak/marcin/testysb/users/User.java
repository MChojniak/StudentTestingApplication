package chojniak.marcin.testysb.users;

import chojniak.marcin.testysb.users.groups.Group;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "USERS")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Boolean admin = false;
    @Column
    private Boolean accountNonExpired = true;
    @Column
    private Boolean accountNonLocked = true;
    @Column
    private Boolean credentialsNonExpired = true;
    @Column
    private Boolean enabled = true;
    @ManyToMany(mappedBy = "users")
    private Set<Group> groups = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserTest> userTests = new HashSet<>();

    private transient List<GrantedAuthority> grantedAuthorities;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserQuestion> userQuestion = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (grantedAuthorities == null)
            if (admin)
                this.grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
            else
                this.grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        return grantedAuthorities;
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public User setAdmin(Boolean admin) {
        this.admin = admin;
        return this;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public User setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public User setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public User setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public User setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<UserTest> getUserTests() {
        return userTests;
    }

    public void setUserTests(Set<UserTest> userTests) {
        this.userTests = userTests;
    }

    public Set<UserQuestion> getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(Set<UserQuestion> userQuestion) {
        this.userQuestion = userQuestion;
    }
}
