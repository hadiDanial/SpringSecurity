package hadi.springSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	

}
