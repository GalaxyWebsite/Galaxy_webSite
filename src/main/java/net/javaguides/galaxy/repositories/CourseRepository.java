package net.javaguides.galaxy.repositories;

import net.javaguides.galaxy.entities.Courses;
import net.javaguides.galaxy.entities.Grade;
import net.javaguides.galaxy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Courses,Integer> {
    public List<Courses> findByGroupsId(Integer groupId);
    @Query("select subject from Courses subject where id = :id and groups_id = :groupId")
    public Courses findByIdAndGroupId(@Param("id") Integer id,
                                        @Param("groupId") Integer groupId);
}
