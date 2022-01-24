package net.javaguides.galaxy.repositories;

import net.javaguides.galaxy.entities.Grade;
import net.javaguides.galaxy.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks,Integer> {
    public List<Tasks> findByGroupsId(Integer groupId);
    public Tasks findByTitle(String title);
    public List<Tasks> findByCoursesId(Integer id);

    @Query("select title from Tasks title where groups_id = :groupsId and courses_id = :coursesId")
    public  List<Tasks> findByCoursesIdAndGroupsId(@Param("coursesId") Integer coursesId,
                                                 @Param("groupsId") Integer groupsId);


}
