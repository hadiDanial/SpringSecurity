package hadi.springSecurity.models.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Posts")
public class Post extends Content
{
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> comments;

	public Post()
	{
	}

	public Post(String title, String text, String markdown, UserProfile profile)
	{
		super(title, text, markdown, profile);
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
}
