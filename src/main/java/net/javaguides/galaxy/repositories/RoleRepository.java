package net.javaguides.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.galaxy.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Integer>{
    
}