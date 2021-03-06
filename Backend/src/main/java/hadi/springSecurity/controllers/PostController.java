package hadi.springSecurity.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hadi.springSecurity.models.entities.Post;
import hadi.springSecurity.services.PostService;

@RestController
@RequestMapping(path = "/posts")
public class PostController
{
	private final PostService postService;

	@Autowired
	public PostController(PostService postService)
	{
		super();
		this.postService = postService;
	}

	@PostMapping(path = "newPost")
	public ResponseEntity<Boolean> createNewPost(@RequestBody Map<String, String> postData)
	{
		boolean res = postService.createNewPost(postData.get("title"), postData.get("text"));
		if (res)
			return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
		else
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(path = "getPost/{postId}")
	public ResponseEntity<Post> getPost(@PathVariable long postId)
	{
		Post post = postService.getPostById(postId);
		return (post == null) ? ResponseEntity.notFound().build():
								new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@GetMapping(path = "getUserPosts/{userId}")
	public ResponseEntity<List<Post>> getUserPosts(@PathVariable long userId)
	{
		List<Post> posts = postService.getUserPosts(userId);
		return (posts == null || posts.size() == 0) ? ResponseEntity.notFound().build():
			new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping(path = "getAllPosts")
	public ResponseEntity<List<Post>> getAllPosts()
	{
		List<Post> posts = postService.getAllPosts();
		return (posts == null) ? ResponseEntity.notFound().build():
			new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping(path = "getPostById/{postId}")
	public ResponseEntity<Post> getPostById(@PathVariable long postId)
	{
		Post post = postService.getPostById(postId);
		return (post == null) ? ResponseEntity.notFound().build():
			new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@GetMapping(path = "getPostByTitle")
	public ResponseEntity<Post> getPostByTitle(@RequestParam String title)
	{
		Post post  = postService.getPostByTitle(title);
		return (post == null) ? ResponseEntity.notFound().build():
			new ResponseEntity<Post>(post, HttpStatus.OK);
	}
}
