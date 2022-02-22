package hadi.springSecurity.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import hadi.springSecurity.models.entities.DBImage;
import hadi.springSecurity.models.entities.ProfileImage;
import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.models.entities.UserProfile;
import hadi.springSecurity.repositories.DBImageRespository;
import hadi.springSecurity.repositories.ProfileImageRepository;
import hadi.springSecurity.repositories.UserRepository;

@Component
public class ImageService
{
	@Qualifier(value = "DBImageRespository")
	private final DBImageRespository dbImageRepository;
	private final ProfileImageRepository profileImageRepository;
	private final UserRepository userRepository;
	public ImageService(DBImageRespository dbImageRepository, ProfileImageRepository profileImageRepository, UserRepository userRepository)
	{
		super();
		this.dbImageRepository = dbImageRepository;
		this.profileImageRepository = profileImageRepository;
		this.userRepository = userRepository;
	}

	public Long uploadImage(MultipartFile multipartImage)
	{
		try
		{
			DBImage dbImage = new DBImage(multipartImage.getBytes(), multipartImage.getOriginalFilename());
			return dbImageRepository.save(dbImage).getId();
		} catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}

	}

	public ByteArrayResource downloadImage(Long imageId)
	{
		Optional<DBImage> image = dbImageRepository.findById(imageId);
		if (image.isPresent())
		{
			byte[] imageBytes = image.get().getContent();
			return new ByteArrayResource(imageBytes);
		}
		return null;
	}

	public Long uploadProfileImage(MultipartFile multipartImage, Long userId)
	{
		try
		{
			User user = userRepository.findById(userId).get();
			UserProfile profile = user.getProfile();
			ProfileImage oldProfileImage = profile.getProfileImage(); 
			ProfileImage profileImage = new ProfileImage(multipartImage.getBytes(), multipartImage.getOriginalFilename(), profile);
			profile.setProfileImage(profileImage);
			Long id = profileImageRepository.save(profileImage).getId();
			userRepository.save(user);
			if(oldProfileImage != null)
				profileImageRepository.delete(oldProfileImage);
			return id;
		} catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public ByteArrayResource downloadProfileImage(Long userId)
	{
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent())
		{
			UserProfile profile = user.get().getProfile();
			ProfileImage image = profile.getProfileImage();
			if(image == null)
				return null;
			byte[] imageBytes = image.getContent();
			return new ByteArrayResource(imageBytes);
		}
		return null;
	}

}
