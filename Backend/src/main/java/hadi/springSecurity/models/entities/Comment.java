package hadi.springSecurity.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Comments")
public class Comment extends Content
{
	@ManyToOne(fetch = FetchType.EAGER)
	private Post post;

	@ManyToOne
	private Comment replyTo;
	@OneToMany(mappedBy = "replyTo")
	private List<Comment> replies;

	public Comment()
	{
	}

	public Comment(String title, String text, String markdown, UserProfile profile)
	{
		super(title, text, markdown, profile);
	}

	public Comment(String title, String text, String markdown, UserProfile profile, Post post, Comment replyTo, List<Comment> replies)
	{
		super(title, text, markdown, profile);
		this.post = post;
		this.replyTo = replyTo;
		this.replies = replies;
	}

	public Post getPost()
	{
		return post;
	}

	public Comment getReplyTo()
	{
		return replyTo;
	}

	public void setReplyTo(Comment replyTo)
	{
		this.replyTo = replyTo;
	}

	public List<Comment> getReplies()
	{
		return replies;
	}

	public void setReplies(List<Comment> replies)
	{
		this.replies = replies;
	}

	public void setPost(Post post)
	{
		this.post = post;
	}
	
	public void addToRepliesList(Comment reply)
	{
		if(replies == null)
			replies = new ArrayList<Comment>();
		this.replies.add(reply);
		reply.setReplyTo(this);
	}
}
