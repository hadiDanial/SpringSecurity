package hadi.springSecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.models.entities.User;


public interface UserRepository extends JpaRepository<User,Long>
{
	Optional<User> findUserByUsername(String username);
	boolean existsByUsername(String username);
}
