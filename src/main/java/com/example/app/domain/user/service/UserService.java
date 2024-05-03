package com.example.app.domain.user.service;

import java.util.List;
import java.util.Map;

import com.example.app.domain.user.dto.BussinessMan;
import com.example.app.domain.user.dto.Session;
import com.example.app.domain.user.dto.User;

public interface UserService {

	// 일반 유저 회원가입
	Map<String, Object> signUp(User user) throws Exception;

	// 사업자 유저 회원가입
	Map<String, Object> signUpBussinessMan(User user, String bussinessManId) throws Exception;

	// 로그인
	Map<String, Object> login(String id, String pw) throws Exception;

	// 로그아웃
	Map<String, Object> logout(int sessionId) throws Exception;

	// 일반 회원정보 조회
	Map<String, Object> userSelect(String userId, String pw) throws Exception;

	// 사업자 회원정보 조회
	Map<String, Object> bussniessManSelect(String userId, String pw) throws Exception;

	// 회원탈퇴
	Map<String, Object> deleteUser(String id, String pw) throws Exception;

	// 회원정보 수정
	Map<String, Object> updateUser(String id, String pw, User newUser) throws Exception;

	// 세션정보 가져오기
	Session getSession(int sessionId) throws Exception;

	// 일반 유저정보 가져오기
	User getUser(String username) throws Exception;
	
	// DB 세션비우기
	boolean deleteAllSession() throws Exception;

//	// 현재 접속중인 세션 Id list 리턴
	List<Integer> getSessionIdList();
}