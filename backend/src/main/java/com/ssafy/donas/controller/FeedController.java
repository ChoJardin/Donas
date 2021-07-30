package com.ssafy.donas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.Comment;
import com.ssafy.donas.domain.Like;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.Relay;
import com.ssafy.donas.request.AddArticleRequest;
import com.ssafy.donas.request.AddCommentRequest;
import com.ssafy.donas.request.LikeRequest;
import com.ssafy.donas.request.UpdateArticleRequest;
import com.ssafy.donas.request.UpdateCommentRequest;
import com.ssafy.donas.response.ArticleResponse;
import com.ssafy.donas.response.CommentResponse;
import com.ssafy.donas.response.IdResponse;
import com.ssafy.donas.response.LikeResponse;
import com.ssafy.donas.response.QuestResponse;
import com.ssafy.donas.service.ArticleService;
import com.ssafy.donas.service.CommentService;
import com.ssafy.donas.service.LikeService;
import com.ssafy.donas.service.QuestService;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

	@Autowired
	CommentService commentService;

	@Autowired
	LikeService likeService;

	@Autowired
	UserService userService;

	@Autowired
	ArticleService articleService;

	@Autowired
	QuestService questService;
	
	/*
	 * Article Functions
	 */
	@PostMapping("/article")
	@ApiOperation(value = "게시물 등록")
	public Object addArticle(@RequestBody AddArticleRequest article) {
		if (!userService.checkId(article.getUserId()))
			return HttpStatus.NOT_FOUND;
		if (!questService.checkQuest(article.getQuestId()))
			return HttpStatus.NOT_FOUND;
		if (article.getContent() == null)
			return HttpStatus.NO_CONTENT;

		articleService.add(userService.getUser(article.getUserId()), questService.getQuestById(article.getQuestId()),
				article.getImage(), article.getContent(), article.getType());
		return HttpStatus.OK;
	}
	
	@PutMapping("/article")
	@ApiOperation(value = "게시물 수정")
	public Object updateArticle(@RequestBody UpdateArticleRequest article) {
		if (!articleService.checkArticle(article.getArticleId()))
			return HttpStatus.NOT_FOUND;
		articleService.update(article.getArticleId(), article.getContent());
		return HttpStatus.OK;
	}
	
	//게시물 삭제 기능 추가
	
	@GetMapping("/article/{userId}")
	@ApiOperation(value = "아이디 당 게시물 목록")
	public Object getArticleByUserId(@PathVariable long userId) {
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;
		List<Article> articles = articleService.getArticlesByUser(userService.getUser(userId));
		final List<ArticleResponse> result = new ArrayList<>();

		for (Article article : articles) {
			ArticleResponse res = new ArticleResponse();
			res.id = article.getId();
			res.questId = article.getQuest().getId();
			res.image = article.getImage();
			res.content = article.getContent();
			res.createdAt = article.getCreatedAt();
			res.updatedAt = article.getUpdatedAt();
			res.type = article.getType();
			res.like = article.getLilkes().size();
			res.comment = article.getComments().size();
			result.add(res);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	//ing
	@GetMapping("/article/recent/{userId}")
	@ApiOperation(value = "유저별 팔로잉 중인 유저들의 게시물 목록 최신순 5개")
	public Object getOrderedArticleByUser(@PathVariable long userId) {
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;
		
//		return new ResponseEntity<>(result, HttpStatus.OK);
		return null;
	}
	//추가로 5개 불러오는거 
	
	
	
	
	
	/*
	 * Quest Functions
	 */
	//퀘스트 생성
	//수정삭제..?
	@GetMapping("/quest/{userId}")
	@ApiOperation(value = "퀘스트목록 가져오기")
	public Object getQuestByUser(@PathVariable long userId) {
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;
		List<Quest> quests = questService.getQuestsByUserId(userId);
		List<QuestResponse> result = new ArrayList<>();
		for (Quest quest : quests) {
			QuestResponse res = new QuestResponse();
			res.id = quest.getId();
			result.add(res);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/realy/{userId}")
	@ApiOperation(value = "유저별 참여중인 릴레이 퀘스트 가져오기")
	public Object getRelayByUser(@PathVariable long userId) {
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;
		List<Relay> relays = questService.getRealysByUserId(userId);
		final List<QuestResponse> result = new ArrayList<>();
		for (Relay relay : relays) {
			QuestResponse res = new QuestResponse();
			res.id = relay.getId();
			result.add(res);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	/*
	 * Comment Functions
	 */
	@PostMapping("/comment")
	@ApiOperation(value = "댓글달기")
	public Object addComment(@RequestBody AddCommentRequest comment) {
		if (!articleService.checkArticle(comment.getArticleId()))
			return HttpStatus.NOT_FOUND;
		if (!userService.checkId(comment.getUserId()))
			return HttpStatus.NOT_FOUND;
		if (comment.getContent() == null)
			return HttpStatus.NO_CONTENT;
		commentService.add(articleService.getArticleById(comment.getArticleId()), comment.getUserId(),
				comment.getContent());
		return HttpStatus.OK;
	}

	@PutMapping("/comment")
	@ApiOperation(value = "댓글 수정")
	public Object updateComment(@RequestBody UpdateCommentRequest comment) {
		if (!commentService.checkComment(comment.getCommentId()))
			return HttpStatus.NOT_FOUND;
		if (comment.getContent() == "")
			return HttpStatus.NO_CONTENT;
		commentService.update(comment.getCommentId(), comment.getContent());
		return HttpStatus.OK;
	}

	@DeleteMapping("/comment")
	@ApiOperation(value = "댓글삭제")
	public Object deleteComment(@RequestParam long commentId) {
		if (!commentService.checkComment(commentId))
			return HttpStatus.NOT_FOUND;
		commentService.delete(commentId);
		return HttpStatus.OK;
	}

	@GetMapping("/comment/{articleId}")
	@ApiOperation(value = "게시물 당 댓글목록")
	public Object getCommnetByArticle(@PathVariable long articleId) {

		if (!articleService.checkArticle(articleId))
			return HttpStatus.NOT_FOUND;
		List<Comment> comments = commentService.getComments(articleService.getArticleById(articleId));
		final List<CommentResponse> result = new ArrayList<>();

		for (Comment comment : comments) {
			CommentResponse res = new CommentResponse();
			res.id = comment.getId();
			res.content = comment.getContent();
			res.createdAt = comment.getCreatedAt();
			res.updatedAt = comment.getUpdatedAt();
			result.add(res);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	/*
	 * Like Functions
	 */
	@PostMapping("/like")
	@ApiOperation(value = "좋아요 누르기")
	public Object saveLike(@RequestBody LikeRequest like) {
		if (!userService.checkId(like.getUserId()))
			return HttpStatus.NOT_FOUND;
		if (!articleService.checkArticle(like.getArticleId()))
			return HttpStatus.NOT_FOUND;
		likeService.addLike(like.getUserId(), articleService.getArticleById(like.getArticleId()));
		return HttpStatus.OK;
	}
	
	@DeleteMapping("/like")
	@ApiOperation(value = "좋아요 취소")
	public Object deleteLike(@RequestParam long likeId) {
		if (!likeService.checkLike(likeId))
			return HttpStatus.NOT_FOUND;
		likeService.delete(likeId);
		return HttpStatus.OK;
	}

	@GetMapping("/like/{articleId}")
	@ApiOperation(value = "게시물 당 좋아요 누른 유저 목록")
	public Object getLikeByUser(@PathVariable long articleId) {
		if (!articleService.checkArticle(articleId))
			return HttpStatus.NOT_FOUND;
		List<Like> likes = likeService.getLikes(articleId);
		final LikeResponse result = new LikeResponse();
		List<IdResponse> userIds = new ArrayList<>();

		for (Like like : likes) {
			IdResponse id = new IdResponse();
			id.id = like.getUserId();
			userIds.add(id);
		}

		result.articleId = articleId;
		result.userIds = userIds;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
