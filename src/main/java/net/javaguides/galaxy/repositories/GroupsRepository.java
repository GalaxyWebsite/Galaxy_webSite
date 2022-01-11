package net.javaguides.galaxy.repositories;

import net.javaguides.galaxy.entities.Grade;
import net.javaguides.galaxy.entities.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Groups,Integer> {
    public Groups findByUsersId(Integer userId);



}
