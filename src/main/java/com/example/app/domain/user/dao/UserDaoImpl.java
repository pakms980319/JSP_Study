package com.example.app.domain.user.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.app.domain.common.CommonDao;
import com.example.app.domain.user.dto.User;

public class UserDaoImpl extends CommonDao implements UserDao{
	
	private static UserDao instance;
	public static UserDao getInstance() throws Exception {
		if(instance == null)
			instance = new UserDaoImpl();
		return instance;
	}
	
	private UserDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public User select(String id) throws Exception {
		User user = null;
		
		pstmt = conn.prepareStatement("SELECT * FROM USER WHERE userId = ?");
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
			
		if (rs != null && rs.next()) {
			
			user = new User(
					rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6)
				);
		}
		
		freeConnection(pstmt, rs);
		
		return user;
	}

	@Override
	public List<User> selectAll() throws Exception {
		List<User> list = null;
		User user = null;
				
		pstmt = conn.prepareStatement("SELECT * FROM USER");
		rs = pstmt.executeQuery();
		list = new ArrayList<User>();
		
		if (rs != null) {
			while(rs.next()) {
				user = new User(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)
					);
				list.add(user);
			}
		}
		
		freeConnection(pstmt, rs);
		
		return list;
	}

	@Override
	public boolean insert(User dto) throws Exception {
		int result = -1;
		
		
		pstmt = conn.prepareStatement("INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?)");
		pstmt.setString(1, dto.getUserId());
		pstmt.setString(2, dto.getPassword());
		pstmt.setString(3, dto.getName());
		pstmt.setString(4, dto.getPhoneNumber());
		pstmt.setString(5, dto.getEmail());
		pstmt.setString(6, dto.getRole());
		
		result = pstmt.executeUpdate();			
		
		freeConnection(pstmt);
		
		return result > 0;
	}

	@Override
	public boolean update(String id, User dto) throws Exception {
		int result = -1;
		
		
			
		pstmt = conn.prepareStatement("UPDATE USER SET password=?, name=?, "
				+ "phoneNumber=?, email=?, role=? WHERE userId = ?");
		
		pstmt.setString(1, dto.getPassword());
		pstmt.setString(2, dto.getName());
		pstmt.setString(3, dto.getPhoneNumber());
		pstmt.setString(4, dto.getEmail());
		pstmt.setString(5, dto.getRole());
		pstmt.setString(6, dto.getUserId());
		
		result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		
		return result > 0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		int result = -1;
			
		pstmt = conn.prepareStatement("DELETE FROM USER WHERE userId = ?");
		pstmt.setString(1, id);
		
		result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		
		return result > 0;
	}
}
