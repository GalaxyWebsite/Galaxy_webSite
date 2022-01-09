package net.javaguides.galaxy.repositories;

import net.javaguides.galaxy.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks,Integer> {
    public Tasks findByUserId(Integer id);
    public List<Tasks> findByCoursesId(Integer id);

}
