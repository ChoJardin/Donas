package com.ssafy.donas.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.donas.domain.Token;
import com.ssafy.donas.domain.User;
import com.ssafy.donas.repository.TokenRepo;

@Service
@Transactional
public class PushService {
	
	@Autowired
	TokenRepo tokenRepo;
	
	@Autowired
	FcmService fcmService;
	
	public void searchReceivedUser(User selectUser, String content, LocalDateTime sendTIme) {
		List<Token> tokens = tokenRepo.findTokenByUser_id(selectUser.getId());
		// 로그인되어 있는 기기가 존재 -> 푸시 알림 
		if(tokens.size()!=0) {		
			for (Token token : tokens) {
				try {
					fcmService.sendMessageTo(token.getToken(), "title이 뭘 뜻하는지 몰람", content);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
