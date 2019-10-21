package chojniak.marcin.testysb.users;

import chojniak.marcin.testysb.testy.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserTestRepository extends CrudRepository<UserTest, Long> {
    Set<UserTest> findAllByUser(User user);
}
