package net.javaguides.galaxy.repositories;

import net.javaguides.galaxy.entities.TaskDelivery;
import net.javaguides.galaxy.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDeliveryRepository extends JpaRepository<TaskDelivery,Integer> {
    public List<TaskDelivery> findByUserTasksId(Integer id);

}
