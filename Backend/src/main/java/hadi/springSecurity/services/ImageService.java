package hadi.springSecurity.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import hadi.springSecurity.models.entities.DBImage;
import hadi.springSecurity.repositories.DBImageRespository;
import hadi.springSecurity.repositories.ProfileImageRepository;

@Component
public class ImageService
{
	@Qualifier(value = "DBImageRespository")
	private final DBImageRespository dbImageRepository;
	private final ProfileImageRepository profileImageRepository;
	public ImageService(DBImageRespository dbImageRepository, ProfileImageRepository profileImageRepository)
	{
		super();
		this.dbImageRepository = dbImageRepository;
		this.profileImageRepository = profileImageRepository;
	}
	public Long uploadImage(MultipartFile multipartImage)
	{
		DBImage dbImage = new DBImage();
        dbImage.setName(multipartImage.getName());
        try
		{
			dbImage.setContent(multipartImage.getBytes());
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
		if(image.isPresent())
		{
			byte[] imageBytes = image.get().getContent();
			return new ByteArrayResource(imageBytes);
		}
		return null;
	}
	
	
}
