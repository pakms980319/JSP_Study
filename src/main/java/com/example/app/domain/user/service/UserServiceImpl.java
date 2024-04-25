package com.example.app.domain.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.user.dao.BussinessManDao;
import com.example.app.domain.user.dao.BussinessManDaoImpl;
import com.example.app.domain.user.dao.SessionDao;
import com.example.app.domain.user.dao.SessionDaoImpl;
import com.example.app.domain.user.dao.UserDao;
import com.example.app.domain.user.dao.UserDaoImpl;
import com.example.app.domain.user.dto.BussinessMan;
import com.example.app.domain.user.dto.Session;
import com.example.app.domain.user.dto.User;


public class UserServiceImpl implements UserService {
	private List<Integer> SessionIdList;
	private UserDao userDao;
	private BussinessManDao bussinessManDao;
	private SessionDao sessionDao;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private ConnectionPool_ByHikari connectionPool;
	
	private static UserService instance;
	public static UserService getInstance() throws Exception {
		if (instance == null)
			instance = new UserServiceImpl();
		return instance;
	}
	
	private UserServiceImpl() throws Exception {
		userDao = UserDaoImpl.getInstance();
		bussinessManDao = BussinessManDaoImpl.getInstance();
		sessionDao = SessionDaoImpl.getInstance();
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		SessionIdList = new ArrayList<Integer>();
		connectionPool = ConnectionPool_ByHikari.getInstance();
		
		List<Session> tmpList = sessionDao.selectAll();
		for (Session dto : tmpList) {
			SessionIdList.add(dto.getSessionId());
		}
	}
	
	// 일반 유저 회원가입
	@Override
	public Map<String, Object> signUp(User user) throws Exception {
		connectionPool.txStart();
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 회원 중 동일한 id 가 있는지 확인하기
		if(userDao.select(user.getUserId()) != null) {
			result.put("response", false);
			result.put("msg", "이미 등록된 Id 입니다.");
			return result;
		}
		
		// 비밀번호 암호화
		String pw = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(pw);
		
		
		boolean isSaved = userDao.insert(user); 
		
		// 회원가입 실패
		if(!isSaved) {
			result.put("response", false);
			result.put("msg", "회원가입에 실패하였습니다. 관리자에게 문의해주세요.");
			return result;
		}
		
		// 회원가입 성공
		result.put("response", true);
		result.put("msg", "회원가입에 성공하였습니다! 감사합니다");
		
		connectionPool.txCommit();
		return result;
	}
	
	// 사업자 유저 회원가입
	@Override
	public Map<String, Object> signUpBussinessMan(User user, String bussinessManId) throws Exception {
		connectionPool.txStart();
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 회원 중 동일한 id 가 있는지 확인하기
		if(userDao.select(user.getUserId()) != null) {
			result.put("response", false);
			result.put("msg", "이미 등록된 Id 입니다.");
			return result;
		}
		
		// 이미 등록된 사업자 번호인지 확인하기
		if(bussinessManDao.select2(bussinessManId) != null) {
			result.put("response", false);
			result.put("msg", "이미 등록된 사업자 번호 입니다.");
			return result;
		}
		
		// 비밀번호 암호화
		String pw = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(pw);
		
		
		BussinessMan bussinessMan = new BussinessMan(bussinessManId, user.getUserId());
		boolean isSaved = userDao.insert(user);
		boolean isSaved2 = bussinessManDao.insert(bussinessMan);
		
		// 회원가입 실패
		if(!isSaved || !isSaved2) {
			result.put("response", false);
			result.put("msg", "회원가입에 실패하였습니다. 관리자에게 문의해주세요.");
			return result;
		}
		
		// 회원가입 성공
		result.put("response", true);
		result.put("msg", "회원가입에 성공하였습니다! 감사합니다");
		
		connectionPool.txCommit();
		return result;
	}
	
	// 로그인
	@Override
	public Map<String, Object> login(String id, String pw) throws Exception {
		connectionPool.txStart();
		Map<String, Object> result = new HashMap<String, Object>();
		
		// user 테이블로부터 동일한 이름의 user 정보를 가져오기
		User savedUser = getUser(id);
		if(savedUser == null) {
			result.put("response", false);
			result.put("msg", "해당 계정은 존재하지 않습니다.");
			return result;
		}
		
		// pw 일치여부 확인
		if (!bCryptPasswordEncoder.matches(pw, savedUser.getPassword())) {
			result.put("response", false);
			result.put("msg", "Password가 일치하지 않습니다.");
			return result;
		}
		
		// 동일한 세션 정보가 있는지 확인
		if (sessionDao.select(id) != null) {
			result.put("response", false);
			result.put("msg", "이미 해당 계정은 로그인한 상태입니다.");
			return result;
		}
		
		// pw 가 일치한다면 session 테이블에 세션 정보 저장
		Session session = new Session(
						savedUser.getUserId(),
						savedUser.getRole()
					);
		if (!sessionDao.insert(session)) {
			result.put("response", false);
			result.put("msg", "로그인 처리중 오류가 발생하였습니다. Session 생성 실패..");
			return result;
		}
		
		// sessionList에 sessionId 값 저장
		Integer idtmp = sessionDao.select(session.getUserId()).getSessionId();
		
		
		result.put("response", true);
		result.put("msg", "로그인 성공!");
		result.put("sessionId", idtmp);
		result.put("session", session);
		SessionIdList.add(idtmp);
		connectionPool.txCommit();
		return result;
	}
	
	// 로그아웃
	@Override
	public Map<String, Object> logout(int sessionId) throws Exception {
		connectionPool.txStart();
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 1 sessionIdList 에 sessionId 가 있는지 확인 후 있다면 삭제
		boolean isExisted = SessionIdList.contains(sessionId);
		if(!isExisted) {
			result.put("response", false);
			result.put("msg", "현재 로그인된 상태가 아닙니다.");
			return result;
		} else {
			SessionIdList.remove(sessionId);
		}
		
		// 2 Session 테이블에서 dto 삭제
		boolean isremoved = sessionDao.delete(sessionId);
		if(!isremoved) {
			result.put("response", false);
			result.put("msg", "시스템 상의 문제로 Session 삭제가 불가합니다. 관리자에게 문의해주세요.");
			return result;
		}
		
		// 로그아웃 성공
		result.put("response", true);
		result.put("msg", "로그아웃 성공!");
		connectionPool.txCommit();
		return result;
	}
	
	// 일반 회원정보 조회
	@Override
	public Map<String, Object> userSelect(String userId, String pw) throws Exception {
		connectionPool.txStart();
		Map<String, Object> result = new HashMap<>();
		User user = userDao.select(userId);
		
		// 회원정보 조회 실패 시나리오
		if (user == null) {
			result.put("response", false);
			result.put("msg", "회원정보 조회에 실패하였습니다. 관리자에게 문의해주세요.");
			return result;
		}
		
		// 패스워드, re 패스워드 불일치 시나리오
		if (!bCryptPasswordEncoder.matches(pw, user.getPassword())) {
			result.put("response", false);
			result.put("msg", "Password가 일치하지 않습니다.");
			return result;
		}
		
		// 회원정보 조회 성공
		result.put("response", true);
		result.put("msg", "회원정보 조회에 성공하였습니다.");
		result.put("user", user);
		
		connectionPool.txCommit();
		return result;
	}
	
	// 사업자 회원정보 조회
	@Override
	public Map<String, Object> bussniessManSelect(String userId, String pw) throws Exception {
		connectionPool.txStart();
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userDao.select(userId);
		BussinessMan bussinessMan = bussinessManDao.select(userId);
		
		// 회원정보 조회 실패 시나리오
		if (user == null || bussinessMan == null) {
			result.put("response", false);
			result.put("msg", "회원정보 조회에 실패하였습니다. 관리자에게 문의해주세요.");
			return result;
		}
		
		// 패스워드, re 패스워드 불일치 시나리오
		if (!bCryptPasswordEncoder.matches(pw, user.getPassword())) {
			result.put("response", false);
			result.put("msg", "Password가 일치하지 않습니다.");
			return result;
		}
		
		// 회원정보 조회 성공
		result.put("response", true);
		result.put("user", user);
		result.put("bussinessMan", bussinessMan);
		result.put("msg", "회원정보 조회 성공");
		
		connectionPool.txCommit();
		return result;
	}
	
	// 회원탈퇴
	@Override
	public Map<String, Object> deleteUser(String id, String pw) throws Exception {
		connectionPool.txStart();
		Map<String, Object> result = new HashMap<String, Object>();
		User savedUser = userDao.select(id);
		
		// 유저 데이터 읽기 실패
		if(savedUser == null) {
			result.put("response", false);
			result.put("msg", "유저 데이터를 읽을 수 없습니다. 관리자에게 문의해주세요.");
			return result;
		}
		
		// 패스워드, re 패스워드 불일치
		if (!bCryptPasswordEncoder.matches(pw, savedUser.getPassword())) {
			result.put("response", false);
			result.put("msg", "Password가 일치하지 않습니다.");
			return result;
		}
		
		// 회원탈퇴 오류 처리
		if (!userDao.delete(id)) {
			result.put("response", false);
			result.put("msg", "회원탈퇴 중 오류가 발생했습니다. 관리자에게 문의해주세요.");
			return result;
		}
		
		// 회원탈퇴 성공
		result.put("response", true);
		result.put("msg", "회원탈퇴에 성공하였습니다. 다음에 또 이용해주세요.");
		
		connectionPool.txCommit();
		return result;
	}
	
	// 회원정보 수정
	@Override
	public Map<String, Object> updateUser(String id, String pw, User newUser) throws Exception {
		connectionPool.txStart();
		Map<String, Object> result = new HashMap<String, Object>();
		User savedUser = userDao.select(id);
		
		// 유저 데이터 읽기 실패
		if(savedUser == null) {
			result.put("response", false);
			result.put("msg", "유저 데이터를 읽을 수 없습니다. 관리자에게 문의해주세요.");
			return result;
		}
		
		// 패스워드, re 패스워드 불일치
		if (!bCryptPasswordEncoder.matches(pw, savedUser.getPassword())) {
			result.put("response", false);
			result.put("msg", "Password가 일치하지 않습니다.");
			return result;
		}
		
		// 비밀번호 암호화
		String rePw = bCryptPasswordEncoder.encode(newUser.getPassword());
		newUser.setPassword(rePw);
		
		// 회원수정 오류 처리
		if (!userDao.update(id, newUser)) {
			result.put("response", false);
			result.put("msg", "회원수정 중 오류가 발생했습니다. 관리자에게 문의해주세요.");
			return result;
		}
		
		// 회원수정 성공
		result.put("response", true);
		result.put("msg", "회원수정에 성공하였습니다.");
		
		connectionPool.txCommit();
		return result;
	}
	
	// 세션정보 가져오기
	@Override
	public Session getSession(int sessionId) throws Exception {
		connectionPool.txStart();
		
		Session session = sessionDao.select(sessionId);
		
		connectionPool.txCommit();
		
		return session;
	}
	
	// 일반 유저정보 가져오기
	@Override
	public User getUser(String username) throws Exception {
		connectionPool.txStart();
		
		User user = userDao.select(username);
		
		connectionPool.txCommit();
		
		return user;
	}
	
	// 현재 접속중인 세션 Id list 리턴
	@Override
	public List<Integer> getSessionIdList() {
		return SessionIdList;
	}
}
