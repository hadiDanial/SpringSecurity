package hadi.springSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.models.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{

}
