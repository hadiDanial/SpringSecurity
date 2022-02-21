package hadi.springSecurity.models.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "UserProfiles")
public class UserProfile
{
	@Id
	@Column(name = "user_id")
	private long id;
	private String about;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id")
	private ProfileImage profileImage;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "profile")
	private List<Comment> comments;
	@OneToMany(mappedBy = "profile")
	private List<Post> posts;
	
	public UserProfile()
	{
	}

	public UserProfile(User user)
	{
		super();
		this.user = user;
	}
	public UserProfile(String about, User user)
	{
		super();
		this.about = about;
		this.user = user;
	}

	public UserProfile(String about, ProfileImage profileImage, User user)
	{
		super();
		this.about = about;
		this.profileImage = profileImage;
		this.user = user;
	}

	public String getAbout()
	{
		return about;
	}

	public void setAbout(String about)
	{
		this.about = about;
	}


	public ProfileImage getProfileImage()
	{
		return profileImage;
	}

	public void setProfileImage(ProfileImage profileImage)
	{
		this.profileImage = profileImage;
	}

	public List<Comment> getComments()
	{
		return comments;
	}

	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}
	
	public boolean addComment(Comment comment)
	{
		if(comments.contains(comment))
			return false;
		comments.add(comment);
		return true;
	}
	
	public boolean deleteComment(Comment comment)
	{
		if(comments.contains(comment))
		{
			comments.remove(comment);
			return true;
		}
		return false;
	}

	public List<Post> getPosts()
	{
		return posts;
	}

	public void setPosts(List<Post> posts)
	{
		this.posts = posts;
	}
	
	public boolean addPost(Post post)
	{
		if(posts.contains(post))
			return false;
		posts.add(post);
		return true;
	}
	
	public boolean deletePost(Post post)
	{
		if(posts.contains(post))
		{
			posts.remove(post);
			return true;
		}
		return false;
	}

	public long getId()
	{
		return id;
	}

	public User getUser()
	{
		return user;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProfile other = (UserProfile) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return user.getUsername() + "'s Profile: " + comments.size() + " comments, " + posts.size() + " posts.";
	}

	
	
}
