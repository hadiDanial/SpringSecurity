package hadi.springSecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.models.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>
{
	Optional<Authority> findByAuthorityIdentifier(String authorityIdentifier);
	boolean existsByAuthorityIdentifier(String authorityIdentifier);
}
