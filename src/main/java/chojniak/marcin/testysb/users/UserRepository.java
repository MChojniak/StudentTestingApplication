package chojniak.marcin.testysb.users;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {
    Set<User> findAll();

    User findByEmail(String email);
}
