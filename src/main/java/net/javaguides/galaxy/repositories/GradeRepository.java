package net.javaguides.galaxy.repositories;

import net.javaguides.galaxy.entities.Grade;
import net.javaguides.galaxy.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Integer> {
    public List<Grade> findByTasksId(Integer id);
    public List<Grade> findByUserId(Integer id);
    public List<Grade> findByCoursesId(Integer id);

    @Query("select content from Grade content where user_id = :userId and courses_id = :coursesId")
     public  List<Grade> findByUserIdAndCoursesId(@Param("coursesId") Integer coursesId,
                                   @Param("userId") Integer userId);

    @Query("select content from Grade content where id = :id and user_id = :userId and tasks_id = :tasksId")
    public  Grade findByUserIdAndCoursesId(@Param("userId") Integer userId,
                                           @Param("id") Integer id,
                                           @Param("tasksId") Integer tasksId);

    @Query("select content from Grade content where user_id = :userId and tasks_id = :tasksId")
    public  Grade findByUserIdAndTasksId(@Param("userId") Integer userId,
                                           @Param("tasksId") Integer tasksId);



}
