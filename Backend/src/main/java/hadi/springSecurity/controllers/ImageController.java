package hadi.springSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import hadi.springSecurity.services.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController
{
	private final ImageService imageService;

	@Autowired
	public ImageController(ImageService imageService)
	{
		super();
		this.imageService = imageService;
	}
	
	
	@PostMapping(path = "/uploadImage")
	 public ResponseEntity<Long> uploadImage(@RequestParam MultipartFile multipartImage)
	{
		Long id = imageService.uploadImage(multipartImage);
		if(id == null)
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED);
		return new ResponseEntity<Long>(id, HttpStatus.CREATED);
        
    }
	
	@PostMapping(path = "profile-pictures/uploadImage/{userId}")
	public ResponseEntity<Long> uploadProfileImage(@RequestParam MultipartFile multipartImage, @PathVariable Long userId)
	{
		if(userId == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);		
		Long id = imageService.uploadProfileImage(multipartImage, userId);
		if(id == null)
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED);
		return new ResponseEntity<Long>(id, HttpStatus.CREATED);		
	}
	
	@GetMapping(path = "/{imageId}",produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId)
	{
		ByteArrayResource image = imageService.downloadImage(imageId);
		if(image == null)
		{
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<Resource>(image, HttpStatus.OK);
	}
	@GetMapping(path = "profile-pictures/{userId}",produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<Resource> downloadProfilePicture(@PathVariable Long userId)
	{
		ByteArrayResource image = imageService.downloadProfileImage(userId);
		if(image == null)
		{
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<Resource>(image, HttpStatus.OK);
	}
	
}
