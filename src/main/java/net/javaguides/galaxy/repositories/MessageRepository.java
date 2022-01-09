package net.javaguides.galaxy.repositories;

import java.util.List;

import net.javaguides.galaxy.entities.Grade;
import net.javaguides.galaxy.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import net.javaguides.galaxy.entities.Message;
import net.javaguides.galaxy.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Caio Fernando
 */
public interface MessageRepository extends JpaRepository<Message,Integer>
{

    List<Message> findByUser(User user);
    List<Message> findByMessageTask(Tasks task);

    @Query("select content from Message content where post_id = :postId and user_id = :userId")
    public  List<Message> findByPostIdAndUserId(@Param("postId") Integer postId,
                                                 @Param("userId") Integer userId);
}