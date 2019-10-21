package chojniak.marcin.testysb.users;

import chojniak.marcin.testysb.users.groups.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(String firstName, String lastName, String email, String password) {
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        return userRepository.save(newUser);
    }


    public boolean checkIfEmailExist(String email) {
        User found = userRepository.findByEmail(email);
        if (found != null)
            return true;
        return false;
    }

    public Set<User> listUsers() {
        return userRepository.findAll();
    }

    public User findByEmail(String email){
        logger.info("Wywoluje funkcje findByEmail dla maila: {}", email);
        User user = userRepository.findByEmail(email);
        user.getGroups().size();
        user.getUserTests().size();
        return user;
    }

    public Set<User> findAllNotAdmins() {
        Set<User> users =  userRepository.findAll();
        users = users.stream().filter(u-> !u.getAdmin()).collect(Collectors.toSet());
        return users;
    }

    public Set<User> findAllUsersButGroup(Group group) {
        Set<User> users = findAllNotAdmins();
        users = users.stream().filter(u-> !u.getGroups().contains(group)).collect(Collectors.toSet());
        return users;
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).get();
    }
}
