package com.example.app.domain.user.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.app.domain.common.CommonDao;
import com.example.app.domain.user.dto.Session;

public class SessionDaoImpl extends CommonDao implements SessionDao {
	
	private static SessionDaoImpl instance;
	public static SessionDaoImpl getInstance() throws Exception {
		if (instance == null) {
			instance = new SessionDaoImpl();
		}
		return instance;
	}
	
	private SessionDaoImpl() throws Exception {}
	
	@Override
	public Session select(Integer id) throws Exception {
		pstmt = conn.prepareStatement("SELECT * FROM SESSION WHERE sessionId = ?");
		pstmt.setInt(1, id);
		
		rs = pstmt.executeQuery();
		
		Session dto = null;
		if (rs!=null && rs.next()) {
			dto = new Session(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3)
				);
		}
		
		freeConnection(pstmt, rs);
		
		return dto;
	}
	
	@Override
	public Session select(String userId) throws Exception {
		pstmt = conn.prepareStatement("SELECT * FROM SESSION WHERE userId = ?");
		pstmt.setString(1, userId);
		
		rs = pstmt.executeQuery();
		
		Session dto = null;
		if (rs!=null && rs.next()) {
			dto = new Session(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3)
				);
		}
		
		freeConnection(pstmt, rs);
		
		return dto;
	}

	@Override
	public List<Session> selectAll() throws Exception {
		pstmt = conn.prepareStatement("SELECT * FROM SESSION");
		
		rs = pstmt.executeQuery();
		
		List<Session> list = null;
		Session dto = null;
		
		if (rs!=null) {
			list = new ArrayList<Session>();
			while(rs.next()) {
				dto = new Session(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3)
					);
				list.add(dto);
			}
		}
		
		freeConnection(pstmt, rs);
		
		return list;
	}

	@Override
	public boolean insert(Session dto) throws Exception {
		pstmt = conn.prepareStatement("INSERT INTO SESSION VALUES(null, ?, ?)");
		pstmt.setString(1, dto.getUserId());
		pstmt.setString(2, dto.getRole());
		
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		
		return result > 0;
	}

	@Override
	public boolean update(Integer id, Session dto) throws Exception {
		pstmt = conn.prepareStatement("UPDATE SESSION SET userId = ?, role = ? WHERE sessionId = ?");
		pstmt.setString(1, dto.getUserId());
		pstmt.setString(2, dto.getRole());
		pstmt.setInt(3, dto.getSessionId());
		
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		
		return result > 0;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		pstmt = conn.prepareStatement("DELETE FROM SESSION WHERE sessionId = ?");
		pstmt.setInt(1, id);
		
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		
		return result > 0;
	}

	@Override
	public boolean deleteAll() throws Exception {
		pstmt = conn.prepareStatement("DELETE FROM SESSION");

		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		
		return result > 0;
	}
	
	
}
