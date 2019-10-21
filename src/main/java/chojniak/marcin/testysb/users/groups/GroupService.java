package chojniak.marcin.testysb.users.groups;

import chojniak.marcin.testysb.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupService {
    private Logger logger = LoggerFactory.getLogger(GroupService.class);
    private GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group addGroup(String name){
        Group group = new Group();
        group.setName(name);

        group =  groupRepository.save(group);
        group.getUsers().size();
        return group;
    }


    public Group findGroupByName(String name){
        logger.info("Wywoluje funkcje findGroupByName dla name: {}", name);
        Group group = groupRepository.findByName(name);
        group.getUsers().size();
        return groupRepository.findByName(name);
    }


    public Set<User> findAllStudents(String name){
        return findGroupByName(name).getUsers();
    }

    public Set<User> addUserToGroup(User user, String groupName){
        Group group = findGroupByName(groupName);
        group.getUsers().add(user);
        user.getGroups().add(group);
        groupRepository.save(group);
        return group.getUsers();
    }
    public Set<User> deleteFromGroup(User user, Group group){
    Set<User> users = group.getUsers();
    users = users.stream().filter(u-> u.getEmail()!=user.getEmail()).collect(Collectors.toSet());
    group.setUsers(users);
    groupRepository.save(group);
    return users;
}



    public Set<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    public Group findGroupById(Long grupaid) {
        return groupRepository.findById(grupaid).get();
    }
}
