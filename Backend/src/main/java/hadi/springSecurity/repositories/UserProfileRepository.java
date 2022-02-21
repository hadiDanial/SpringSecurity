package hadi.springSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hadi.springSecurity.models.entities.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>
{

}
