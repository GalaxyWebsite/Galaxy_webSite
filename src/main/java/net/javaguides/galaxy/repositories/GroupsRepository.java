package net.javaguides.galaxy.repositories;

import net.javaguides.galaxy.entities.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Groups,Integer> {
}
