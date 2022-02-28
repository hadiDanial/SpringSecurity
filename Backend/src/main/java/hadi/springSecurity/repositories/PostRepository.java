package hadi.springSecurity.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.models.entities.Post;


public interface PostRepository extends JpaRepository<Post, Long>
{
	Optional<Post> findByTitle(String title);
	Optional<Post> findByUnderscoredTitle(String title);
	List<Post> findAllByUnderscoredTitle(String title);
}
