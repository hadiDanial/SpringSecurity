package hadi.springSecurity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hadi.springSecurity.models.entities.Comment;
import hadi.springSecurity.models.entities.Post;
import hadi.springSecurity.models.entities.User;
import hadi.springSecurity.models.entities.UserProfile;
import hadi.springSecurity.repositories.CommentRepository;
import hadi.springSecurity.repositories.PostRepository;
import hadi.springSecurity.repositories.UserProfileRepository;

@Service
public class PostService
{
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final UserProfileRepository userProfileRepository;

	@Autowired
	public PostService(CommentRepository commentRepository, PostRepository postRepository,
			UserProfileRepository userProfileRepository)
	{
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.userProfileRepository = userProfileRepository;
	}

	public boolean createNewPost(String title, String text)
	{
		try
		{
			User user = AuthenticationUserMatcher.getAuthenticatedUser();
			UserProfile profile = userProfileRepository.getById(user.getId());
			Post post = new Post(title, text, text, profile);
			Post savedPost = postRepository.save(post);
			profile.addPost(savedPost);
			userProfileRepository.save(profile);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	public List<Post> getAllPosts()
	{
		return postRepository.findAll();
	}
	
	public List<Post> getUserPosts(long userId){
		return userProfileRepository.findById(userId).get().getPosts();
	}

	public Post getPostById(long postId)
	{
		Optional<Post> post = postRepository.findById(postId);
		if (post.isPresent())
			return post.get();
		else
			return null;
	}

	public Post getPostByTitle(String title)
	{
		Optional<Post> post = postRepository.findByUnderscoredTitle(title);
		if (post.isPresent())
			return post.get();
		else
			return null;
	}

	public boolean addCommentToPost(String title, String text, String postId)
	{
		try
		{
			UserProfile profile = AuthenticationUserMatcher.getAuthenticatedUser().getProfile();
			Comment comment = new Comment(title, text, text, profile);
			Long id = Long.parseLong(postId);
			Post post = postRepository.getById(id);
			comment.setPost(post);
			comment = commentRepository.save(comment);
			post.addComment(comment);
			profile.addComment(comment);
			postRepository.save(post);
			userProfileRepository.save(profile);
			return true;			
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

	public List<Comment> getPostComments(Long postId)
	{
		try
		{
			Post post = postRepository.getById(postId);
			return post.getComments();			
		} catch (Exception e)
		{
			return null;
		}
	}

}
