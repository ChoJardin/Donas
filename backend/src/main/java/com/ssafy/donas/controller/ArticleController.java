package com.ssafy.donas.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.donas.domain.Article;
import com.ssafy.donas.domain.ArticleInfo;
import com.ssafy.donas.domain.Follow;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.request.AddArticleRequest;
import com.ssafy.donas.request.UpdateArticleRequest;
import com.ssafy.donas.response.ArticleResponse;
import com.ssafy.donas.service.ArticleService;
import com.ssafy.donas.service.QuestService;
import com.ssafy.donas.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
	@Autowired
	UserService userService;

	@Autowired
	ArticleService articleService;
	
	@Autowired
	QuestService questService;
	
	@PostMapping
	@ApiOperation(value = "게시물 등록")
	public Object addArticle(@RequestBody AddArticleRequest article) {
		if (!userService.checkId(article.getUserId()) || !questService.checkQuest(article.getQuestId()))
			return HttpStatus.NOT_FOUND;
		
		if ("".equals(article.getContent()))
			return HttpStatus.NO_CONTENT;

		articleService.add(userService.getUser(article.getUserId()), questService.getQuestById(article.getQuestId()), article.getImage(), article.getContent(), article.getType());
		return HttpStatus.OK;
	}

	@PatchMapping
	@ApiOperation(value = "게시물 수정")
	public Object updateArticle(@RequestBody UpdateArticleRequest article) {
		if (!articleService.checkArticle(article.getArticleId()))
			return HttpStatus.NOT_FOUND;
		
		if ("".equals(article.getContent()))
			return HttpStatus.NO_CONTENT;
		
		articleService.update(article.getArticleId(), article.getContent());
		return HttpStatus.OK;
	}

	// 버그 : entity 못찾음
	@DeleteMapping
	@ApiOperation(value = "게시물 삭제")
	public Object deleteArticle(@RequestParam long articleId) {
		if (!articleService.checkArticle(articleId))
			return HttpStatus.NOT_FOUND;
		
		articleService.delete(articleId);
		return HttpStatus.OK;
	}

//	@GetMapping("/{userId}")
//	@ApiOperation(value = "아이디 당 모든 게시물 목록")
//	public Object getArticleByUserId(@PathVariable long userId) {
//		if (!userService.checkId(userId))
//			return HttpStatus.NOT_FOUND;
//		
//		List<Article> articles = articleService.getArticlesByUser(userService.getUser(userId));
//		final List<ArticleResponse> result = new ArrayList<>();
//
//		for (Article article : articles) {
//			ArticleResponse res = new ArticleResponse();
//			res.articleId = article.getId();
//			res.questId = article.getQuest().getId();
//			res.image = article.getImage();
//			res.content = article.getContent();
//			res.createdAt = article.getCreatedAt();
//			res.updatedAt = article.getUpdatedAt();
//			res.type = article.getType();
//			res.likeCnt = article.getLikes().size();
//			res.commentCnt = article.getComments().size();
//			result.add(res);
//		}
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
	
	@GetMapping("/mine")
	@ApiOperation(value = "퀘스트 종류별 나의 게시물 목록")
	public Object getArticleByType(@RequestParam long user_id, @RequestParam String type) {
		if(!userService.checkId(user_id))
			return HttpStatus.NOT_FOUND;
		List<ArticleResponse> result = new ArrayList<ArticleResponse>();
		List<ArticleInfo> articles = articleService.getArticleInfoByUserAndType(user_id,type,"mine");
		for(ArticleInfo article : articles) {
			ArticleResponse res = new ArticleResponse();
			res.articleId = article.getId();
			res.questId = article.getQuestId();
			res.image = article.getImage();
			res.content = article.getContent();
			res.createdAt = article.getCreatedAt();
			res.updatedAt = article.getUpdatedAt();
			res.type = article.getType();
			res.likeCnt = article.getHeartCnt();
			res.commentCnt = article.getCommentCnt();
			res.questTitle = article.getQuestTitle();
			res.makerName = article.getMakerName();
			res.makerImage = article.getMakerImage();
			res.isLike = article.isLike();
			result.add(res);
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	// 미완성 코드
	@GetMapping("/quest/{questId}")
	@ApiOperation(value = "퀘스트 별 게시물 목록 가져오기")
	public Object getArticlesByQuest(@PathVariable long questId) {
		if (!questService.checkQuest(questId))
			return HttpStatus.NOT_FOUND;
		
		Quest quest = questService.getQuestById(questId);
		List<Article> result = null;
		// 게시글 id도 넘겨주기
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 미완성 코드
	@GetMapping("/recent/{userId}")
	@ApiOperation(value = "유저별 팔로잉 중인 유저들의 게시물 목록 최신순 5개")
	public Object getOrderedArticleByUser(@PathVariable long userId) {
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;
		
		User user = userService.getUser(userId);
		List<Follow> followings = user.getFollower();
		System.out.println("show + " + followings.toString());
		List<Article> result = new ArrayList<>();
		System.out.println("before");
		
		// 게시글 id도 넘겨주기
		for (Follow follow : followings) {
			System.out.println(follow.getId());
			System.out.println("test");
		}
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/following")
	@ApiOperation(value = "팔로잉 중인 유저들의 개인 퀘스트 피드")
	public Object getPersonalQuestFeed(@RequestParam long user_id, @RequestParam String type ) {
		if(!userService.checkId(user_id))
			return HttpStatus.NOT_FOUND;
		
		List<ArticleResponse> result = new ArrayList<ArticleResponse>();
		List<ArticleInfo> articles = articleService.getArticleInfoByUserAndType(user_id,type,"other");
		for(ArticleInfo article : articles) {
			ArticleResponse res = new ArticleResponse();
			res.articleId = article.getId();
			res.questId = article.getQuestId();
			res.image = article.getImage();
			res.content = article.getContent();
			res.createdAt = article.getCreatedAt();
			res.updatedAt = article.getUpdatedAt();
			res.type = article.getType();
			res.likeCnt = article.getHeartCnt();
			res.commentCnt = article.getCommentCnt();
			res.questTitle = article.getQuestTitle();
			res.makerName = article.getMakerName();
			res.makerImage = article.getMakerImage();
			res.isLike = article.isLike();
			result.add(res);
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
}
