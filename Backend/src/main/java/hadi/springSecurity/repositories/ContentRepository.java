package hadi.springSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.models.entities.Content;


public interface ContentRepository extends JpaRepository<Content, Long>
{

}
