package chojniak.marcin.testysb.testy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {
    Set<Test> findAll();
}
