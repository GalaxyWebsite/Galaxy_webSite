package net.javaguides.galaxy.repositories;

import net.javaguides.galaxy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Caio Fernando
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    
    Optional<User> findByEmail(String email);
    
}