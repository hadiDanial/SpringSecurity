package hadi.springSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.models.entities.ProfileImage;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long>
{

}
