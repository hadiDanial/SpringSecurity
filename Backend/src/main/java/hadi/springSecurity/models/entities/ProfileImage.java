package hadi.springSecurity.models.entities;

import java.time.Instant;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Profile_Images")
@DiscriminatorValue("P")
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



	public ProfileImage(byte[] content, String name, UserProfile profile)
	{
		super(content, name);
		this.profile = profile;
	}

	public ProfileImage(long id, byte[] content, String name, Instant uploadDate, UserProfile profile)
	{
		super(id, content, name, uploadDate);
		this.profile = profile;
	}

	public UserProfile getProfile()
	{
		return profile;
	}
}
