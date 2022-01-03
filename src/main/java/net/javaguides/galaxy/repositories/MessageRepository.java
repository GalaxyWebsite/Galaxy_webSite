package net.javaguides.galaxy.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import net.javaguides.galaxy.entities.Message;
import net.javaguides.galaxy.entities.User;

/**
 * @author Caio Fernando
 */
public interface MessageRepository extends JpaRepository<Message,Integer>
{
    
    List<Message> findByUser(User user);    
}