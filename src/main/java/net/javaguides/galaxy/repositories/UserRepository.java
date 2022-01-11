package net.javaguides.galaxy.repositories;

import net.javaguides.galaxy.entities.Groups;
import net.javaguides.galaxy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Caio Fernando
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    
    Optional<User> findByEmail(String email);

//    public User findByGroupsId(Integer groupId);

    public List<User> findByGroupsId(Integer groupId);

}