package net.javaguides.galaxy.repositories;

import net.javaguides.galaxy.entities.Grade;
import net.javaguides.galaxy.entities.TaskDelivery;
import net.javaguides.galaxy.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskDeliveryRepository extends JpaRepository<TaskDelivery,Integer> {
    public List<TaskDelivery> findByUserTasksId(Integer id);
    @Query("select body from TaskDelivery body where id = :id and student_id = :studentId")
    public  TaskDelivery findByIdAndStudentId(@Param("studentId") Integer studentId,
                                                 @Param("id") Integer id);

}
