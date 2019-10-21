package chojniak.marcin.testysb.users.groups;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
    Group findByName(String name);
    Set<Group> findAll();
}
