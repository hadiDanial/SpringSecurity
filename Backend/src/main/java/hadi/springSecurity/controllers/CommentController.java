package hadi.springSecurity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
