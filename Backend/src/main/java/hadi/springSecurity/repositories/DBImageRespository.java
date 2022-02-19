package hadi.springSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.models.entities.DBImage;

public interface DBImageRespository extends JpaRepository<DBImage, Long>
{

}
