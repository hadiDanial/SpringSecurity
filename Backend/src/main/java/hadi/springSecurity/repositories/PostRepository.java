package hadi.springSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.models.entities.Post;


public interface PostRepository extends JpaRepository<Post, Long>
{

}
