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
import com.ssafy.donas.domain.Follow;
import com.ssafy.donas.domain.Like;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.domain.quest.Personal;
import com.ssafy.donas.domain.quest.Quest;
import com.ssafy.donas.domain.quest.QuestInfo;
import com.ssafy.donas.domain.quest.QuestParticipants;
import com.ssafy.donas.domain.quest.Relay;
import com.ssafy.donas.request.AddArticleRequest;
import com.ssafy.donas.request.AddCommentRequest;
import com.ssafy.donas.request.AddGroupQuestRequest;
import com.ssafy.donas.request.AddPersonalQuestRequest;
import com.ssafy.donas.request.LikeRequest;
import com.ssafy.donas.request.UpdateArticleRequest;
import com.ssafy.donas.request.UpdateCommentRequest;
import com.ssafy.donas.request.UpdateQuestRequest;
import com.ssafy.donas.response.ArticleResponse;
import com.ssafy.donas.response.CommentResponse;
import com.ssafy.donas.response.IdResponse;
import com.ssafy.donas.response.LikeResponse;
import com.ssafy.donas.response.QuestResponse;
import com.ssafy.donas.response.SearchResponse;
import com.ssafy.donas.service.ArticleService;
import com.ssafy.donas.service.CommentService;
import com.ssafy.donas.service.FollowService;
import com.ssafy.donas.service.LikeService;
import com.ssafy.donas.service.QuestParticipantsService;
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
	
	@Autowired
	FollowService followService;

	@Autowired
	QuestParticipantsService questParticipantsService;

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

	@DeleteMapping("/article")
	@ApiOperation(value = "게시물삭제")
	public Object deleteArticle(@RequestParam long articleId) {
		if (!articleService.checkArticle(articleId))
			return HttpStatus.NOT_FOUND;
		articleService.delete(articleId);
		return HttpStatus.OK;
	}

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
			res.like = article.getLikes().size();
			res.comment = article.getComments().size();
			result.add(res);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 퀘스트 별 게시물 목록 가져오기
	@GetMapping("/article/quest/{questId}")
	@ApiOperation(value = "퀘스트 별 게시물 목록 가져오기")
	public Object getArticlesByQuest(@PathVariable long questId) {
		if (!questService.checkQuest(questId))
			return HttpStatus.NOT_FOUND;
		Quest quest = questService.getQuestById(questId);
		List<Article> result = null;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// ing
	@GetMapping("/article/recent/{userId}")
	@ApiOperation(value = "유저별 팔로잉 중인 유저들의 게시물 목록 최신순 5개")
	public Object getOrderedArticleByUser(@PathVariable long userId) {
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;
		User user = userService.getUser(userId);
		List<Follow> followings = user.getFollowers();
		System.out.println("show + " + followings.toString());
		List<Article> result = new ArrayList<>();
		System.out.println("before");
		for (Follow follow : followings) {
			System.out.println(follow.getId());
			System.out.println("test");

		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/*
	 * Quest Functions
	 */
	// 퀘스트 생성
//	@PostMapping("/quest/personal")
//	@ApiOperation(value = "개인퀘스트 생성")
//	public Object addPersonalQuest(@RequestBody AddPersonalQuestRequest quest) {
//		if (!userService.checkId(quest.getUserId()))
//			return HttpStatus.NOT_FOUND;
//		if (quest.getTitle() == "" || quest.getDescription() == "")
//			return HttpStatus.NO_CONTENT;
//		questService.addPersonalQuest(quest.getTitle(), quest.getDescription(), quest.getStartAt(),
//				quest.getFinishAt());
//		return HttpStatus.OK;
//	}

	@PostMapping("/quest/group")
	@ApiOperation(value = "그룹퀘스트 생성")
	public Object addGroupQuest(@RequestBody AddGroupQuestRequest quest) {
		if (!userService.checkId(quest.getUserId()))
			return HttpStatus.NOT_FOUND;
		if (quest.getTitle() == "" || quest.getDescription() == "")
			return HttpStatus.NO_CONTENT;
		List<Long> participant_users = quest.getParticipants();
		List<User> participants = new ArrayList<>();
		for (long p : participant_users) {
			if (!userService.checkId(p))
				return HttpStatus.NOT_FOUND;
			participants.add(userService.getUser(p));
		}
		Quest groupQuest = questService.addGroupQuest(quest.getTitle(), quest.getDescription(),
				quest.getStartAt(), quest.getFinishAt());
		questParticipantsService.addParticipants(userService.getUser(quest.getUserId()), participants, groupQuest);
		return HttpStatus.OK;
	}
	
	@PutMapping("/quest")
	@ApiOperation(value = "퀘스트 수정 (title, description 수정) (공통)")
	public Object updateQuest(@RequestBody UpdateQuestRequest quest) {
		if (!questService.checkQuest(quest.getQuestId()))
			return HttpStatus.NOT_FOUND;
		questService.updateQuest(quest.getQuestId(), quest.getTitle(), quest.getDescription());
		return HttpStatus.OK;
	}

	@DeleteMapping("/quest")
	@ApiOperation(value = "퀘스트 삭제")
	public Object deleteQuest(@RequestParam long questId) {
		System.out.println(questId);
		if(!questService.delete(questId))
			return HttpStatus.NOT_FOUND;
		return HttpStatus.OK;
	}
	
	@GetMapping("/quest/{userId}")
	@ApiOperation(value = "유저 별 참여중인 모든 퀘스트목록 가져오기")
	public Object getQuestByUser(@PathVariable long userId) {
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;

		User user = userService.getUser(userId);
		List<QuestParticipants> questSummaries = user.getMyQuests();

		List<Quest> quests = new ArrayList<>();
		for (QuestParticipants qs : questSummaries)
			quests.add(qs.getQuest());

		List<QuestResponse> result = new ArrayList<>();
		for (Quest quest : quests) {
			QuestResponse res = new QuestResponse();
			res.id = quest.getId();
			res.title = quest.getTitle();
			res.description = quest.getDescription();
			res.startAt = quest.getStartAt();
			res.finishAt = quest.getFinishAt();
			res.picture = quest.getPicture();
			result.add(res);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
//	@GetMapping("/personal/{userId}")
//	@ApiOperation(value = "유저별 참여중인 개인 퀘스트 가져오기")
//	public Object getPersonalByUser(@PathVariable long userId) {
//		if (!userService.checkId(userId))
//			return HttpStatus.NOT_FOUND;
//
//		User user = userService.getUser(userId);
//		List<QuestResponse> result = new ArrayList<>();
//		List<Personal> personals = questService.getPersonalsByUserId(userId);
//		for (Personal p : personals) {
//			QuestResponse qr = new QuestResponse();
//			qr.title = p.getTitle();
//			qr.description = p.getDescription();
//			qr.startAt = p.getStartAt();
//			qr.finishAt = p.getFinishAt();
//			qr.id = p.getId();
//			result.add(qr);
//		}
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}

	@GetMapping("/realy/{userId}")
	@ApiOperation(value = "유저별 참여중인 릴레이 퀘스트 가져오기")
	public Object getRelayByUser(@PathVariable long userId) {
		if (!userService.checkId(userId))
			return HttpStatus.NOT_FOUND;

		List<QuestInfo> quests = questService.getQuestInfoByUserId(userId);

		final List<QuestResponse> result = new ArrayList<>();
		for (QuestInfo quest : quests) {
			if (quest.getType().equals("R")) {
				QuestResponse res = new QuestResponse();
				res.id = quest.getId();
				result.add(res);
			}
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
		likeService.addLike(userService.getUser(like.getUserId()), articleService.getArticleById(like.getArticleId()));
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
