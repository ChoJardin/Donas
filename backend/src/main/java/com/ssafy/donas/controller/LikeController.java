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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.Like;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.request.LikeRequest;
import com.ssafy.donas.response.SearchResponse;
import com.ssafy.donas.service.AlarmService;
import com.ssafy.donas.service.ArticleService;
import com.ssafy.donas.service.LikeService;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
	@Autowired
	LikeService likeService;

	@Autowired
	UserService userService;

	@Autowired
	ArticleService articleService;
	
	@Autowired
	AlarmService alarmService;
	
	@PostMapping
	@ApiOperation(value = "좋아요 누르기")
	public Object saveLike(@RequestBody LikeRequest like) {
		if (!userService.checkId(like.getUserId()) || !articleService.checkArticle(like.getArticleId()))
			return new ResponseEntity<>("좋아요 누르기",HttpStatus.NOT_FOUND);
		
		if(likeService.checkLike (like.getUserId(),like.getArticleId())!=-1)
			return new ResponseEntity<>("이미 누름",HttpStatus.NOT_FOUND);	
	
		User sendUser = userService.getUser(like.getUserId());
		Article article = articleService.getArticleById(like.getArticleId());
		User receivedUser = article.getUser();
		likeService.addLike(receivedUser, article);
		if(!alarmService.addAlarm(receivedUser,sendUser.getNickname(),article.getId(),sendUser.getNickname()+"님이 "+"\""+article.getQuest().getTitle()+"\""+"퀘스트의 게시물을 좋아합니다.", LocalDateTime.now().plusHours(9)))
			return HttpStatus.CONFLICT;
		return HttpStatus.OK;
	}

	@DeleteMapping
	@ApiOperation(value = "좋아요 취소")
	public Object deleteLike(@RequestParam long articleId, @RequestParam long userId) {
		long likeId = likeService.checkLike(userId, articleId);
		
		if (likeId == -1)
			return HttpStatus.NOT_FOUND;

		likeService.delete(likeId);
		// 게시물의 좋아요 불러오기
		return HttpStatus.OK;
	}

	@GetMapping("/{articleId}")
	@ApiOperation(value = "게시물 당 좋아요 누른 유저 목록")
	public Object getLikeByUser(@PathVariable long articleId) {
		if (!articleService.checkArticle(articleId))
			return new ResponseEntity<>("좋아요 누른 유저 목록",HttpStatus.NOT_FOUND);
		
		List<Like> likes = likeService.getLikes(articleId);
		final List<SearchResponse> result = new ArrayList<>();

		for (Like like : likes) {
			SearchResponse response = new SearchResponse();
			response.id = like.getUser().getId();
			response.nickname = like.getUser().getNickname();
			response.picture = like.getUser().getPicture();
			response.description = like.getUser().getDescription();
			result.add(response);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
