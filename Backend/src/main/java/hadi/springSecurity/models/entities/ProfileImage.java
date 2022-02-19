package hadi.springSecurity.models.entities;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Profile_Images")
public class ProfileImage extends DBImage
{
	@OneToOne(mappedBy = "profileImage")
	protected UserProfile profile;

	public ProfileImage()
	{
	}

	public ProfileImage(UserProfile profile)
	{
		super();
		this.profile = profile;
	}

	public ProfileImage(long id, byte[] content, String date, Instant uploadDate)
	{
		super(id, content, date, uploadDate);

	}

	public UserProfile getProfile()
	{
		return profile;
	}
}
