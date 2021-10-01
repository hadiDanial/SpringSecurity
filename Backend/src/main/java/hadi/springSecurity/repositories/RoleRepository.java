package hadi.springSecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.models.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{
	Optional<Role> findByRoleIdentifier(String roleIdentifier);
	boolean existsByRoleIdentifier(String roleIdentifier);
}
