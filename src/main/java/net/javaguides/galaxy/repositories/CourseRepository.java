package net.javaguides.galaxy.repositories;

import net.javaguides.galaxy.entities.Courses;
import net.javaguides.galaxy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Courses,Integer> {
    public List<Courses> findByGroupsId(Integer groupId);

}
