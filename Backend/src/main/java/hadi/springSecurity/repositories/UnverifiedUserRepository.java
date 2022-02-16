package hadi.springSecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.models.entities.UnverifiedUser;

public interface UnverifiedUserRepository extends JpaRepository<UnverifiedUser,Integer> 
{
	Optional<UnverifiedUser> findUnverifiedUserByUsername(String username);
	Optional<UnverifiedUser> findUnverifiedUserByVerificationUUID(String verificationUUID);
}
