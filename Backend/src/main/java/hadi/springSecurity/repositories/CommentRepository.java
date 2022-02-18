package hadi.springSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.models.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>
{

}
