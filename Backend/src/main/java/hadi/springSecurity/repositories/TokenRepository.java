package hadi.springSecurity.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.models.entities.Token;

public interface TokenRepository extends JpaRepository<Token, Long>
{
	Optional<Token> findByUsername(String username);

	Optional<Token> findByRefreshToken(String refreshToken);
	Optional<Token> findByAccessToken(String accessToken);
	boolean existsByAccessToken(String accessToken);
	Optional<Token> findByUsernameAndRefreshToken(String username, String refreshToken);

	List<Token> findAllByUsername(String username);
}
