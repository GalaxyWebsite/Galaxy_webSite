package net.javaguides.galaxy.repositories;

import net.javaguides.galaxy.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Courses,Integer> {
}
