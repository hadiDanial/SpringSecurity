package hadi.springSecurity.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Post getPostById(long postId)
	{
		Optional<Post> post = postRepository.findById(postId);
		if (post.isPresent())
			return post.get();
		else
			return null;
	}

}
