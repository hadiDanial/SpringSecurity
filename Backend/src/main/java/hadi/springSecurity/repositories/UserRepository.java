package hadi.springSecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.beans.entities.User;


public interface UserRepository  extends JpaRepository<User,Long>
{
	Optional<User> findUserByUsername(String username);
}
