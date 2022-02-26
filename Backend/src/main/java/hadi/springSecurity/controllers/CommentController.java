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
import org.springframework.web.bind.annotation.RestController;

import hadi.springSecurity.models.entities.Comment;
import hadi.springSecurity.services.PostService;
import hadi.springSecurity.services.UserService;

@RestController
@RequestMapping(path = "/comments")
public class CommentController
{
	private final PostService postService;
	private final UserService userService;

	@Autowired
	public CommentController(PostService postService, UserService userService)
	{
		super();
		this.postService = postService;
		this.userService = userService;
	}

	@GetMapping(path = "getUserComments/{userId}")
	public ResponseEntity<List<Comment>> getUserComments(@PathVariable Long userId)
	{
		List<Comment> comments = userService.getUserComments(userId);
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}

	@GetMapping(path = "getPostComments/{postId}")
	public ResponseEntity<List<Comment>> getPostComments(@PathVariable Long postId)
	{
		List<Comment> comments = postService.getPostComments(postId);
		if(comments == null)
			return ResponseEntity.notFound().build();
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}

	@PostMapping(path = "addCommentToPost")
	public ResponseEntity<Boolean> addCommentToPost(@RequestBody Map<String, String> map)
	{
		boolean res = postService.addCommentToPost(map.get("title"), map.get("text"));
		return new ResponseEntity<Boolean>(res, HttpStatus.OK);
	}
}
