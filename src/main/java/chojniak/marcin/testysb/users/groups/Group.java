package chojniak.marcin.testysb.users.groups;


import chojniak.marcin.testysb.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="GROUPS")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @Size(min = 4)
    private String name;
    @ManyToMany
    @JoinTable(name = "USER_GROUPS",
            joinColumns = {
                    @JoinColumn(name = "GROUP_ID", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
            }
    )
    private Set<User> users = new HashSet<>();

    public Group() {
    }

    public Group(@NotEmpty @Size(min = 4) String name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    public Group(@NotEmpty @Size(min = 4) String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
