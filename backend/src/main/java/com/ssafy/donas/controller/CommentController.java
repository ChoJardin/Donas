package com.ssafy.donas.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.Comment;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.request.AddCommentRequest;
import com.ssafy.donas.request.UpdateCommentRequest;
import com.ssafy.donas.response.CommentResponse;
import com.ssafy.donas.service.AlarmService;
import com.ssafy.donas.service.ArticleService;
import com.ssafy.donas.service.CommentService;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@Autowired
	UserService userService;

	@Autowired
	ArticleService articleService;
	
	@Autowired
	AlarmService alarmService;

	@PostMapping
	@ApiOperation(value = "댓글 달기")
	public Object addComment(@RequestBody AddCommentRequest comment) {
		if (!articleService.checkArticle(comment.getArticleId()) || !userService.checkId(comment.getUserId()))
			return HttpStatus.NOT_FOUND;
		
		if ("".equals(comment.getContent()))
			return HttpStatus.NO_CONTENT;
		
		User sendUser = userService.getUser(comment.getUserId());
		Article article = articleService.getArticleById(comment.getArticleId());
		User receivedUser = article.getUser();
		commentService.add(article, comment.getUserId(), comment.getContent());
		if(!alarmService.addAlarm(receivedUser,sendUser.getNickname(),article.getId(),sendUser.getNickname()+"님이 "+"\""+article.getQuest().getTitle()+"\""+"퀘스트의 게시물에 댓글을 남겼습니다.", LocalDateTime.now().plusHours(9)))
			return HttpStatus.CONFLICT;
		return HttpStatus.OK;
	}

	@PatchMapping
	@ApiOperation(value = "댓글 수정")
	public Object updateComment(@RequestBody UpdateCommentRequest comment) {
		if (!commentService.checkComment(comment.getCommentId()))
			return HttpStatus.NOT_FOUND;
		
		if ("".equals(comment.getContent()))
			return HttpStatus.NO_CONTENT;
		
		commentService.update(comment.getCommentId(), comment.getContent());
		return HttpStatus.OK;
	}

	@DeleteMapping
	@ApiOperation(value = "댓글 삭제")
	public Object deleteComment(@RequestParam long commentId) {
		if (!commentService.checkComment(commentId))
			return HttpStatus.NOT_FOUND;
		
		commentService.delete(commentId);
		return HttpStatus.OK;
	}

	@GetMapping("/{articleId}")
	@ApiOperation(value = "게시물 당 댓글목록")
	public Object getCommnetByArticle(@PathVariable long articleId) {
		if (!articleService.checkArticle(articleId))
			return HttpStatus.NOT_FOUND;
		
		List<Comment> comments = commentService.getComments(articleService.getArticleById(articleId));
		final List<CommentResponse> result = new ArrayList<>();

		for (Comment comment : comments) {
			CommentResponse res = new CommentResponse();
			res.userId = comment.getUser().getId();
			res.picture = comment.getUser().getPicture();
			res.nickname = comment.getUser().getNickname();
			res.commentId = comment.getId();
			res.content = comment.getContent();
			res.createdAt = comment.getCreatedAt();
			res.updatedAt = comment.getUpdatedAt();
			result.add(res);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
