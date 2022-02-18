package hadi.springSecurity.models.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Comments")
public class Comment extends Content
{
	@ManyToOne
	private Post post;

	public Comment()
	{
	}

	public Comment(String title, String text, String markdown, UserProfile profile)
	{
		super(title, text, markdown, profile);
	}

	public Post getPost()
	{
		return post;
	}
}
