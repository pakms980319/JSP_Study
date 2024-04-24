package com.example.app.domain.user.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.app.domain.common.CommonDao;
import com.example.app.domain.user.dto.BussinessMan;

public class BussinessManDaoImpl extends CommonDao implements BussinessManDao {

	private static BussinessManDao instance;
	
	public static BussinessManDao getInstance() throws Exception {
		if (instance == null)
			instance = new BussinessManDaoImpl();
		return instance;
	}
	
	private BussinessManDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public BussinessMan select(String id) throws Exception {
		pstmt = conn.prepareStatement("SELECT * FROM BussinessMan WHERE userId = ?");
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		
		BussinessMan bussinessMan = null;
		if (rs != null) {
			rs.next();
			bussinessMan = new BussinessMan(
					rs.getString(1),
					rs.getString(2)
				);
		}
		
		freeConnection(pstmt, rs);
		
		return bussinessMan;
	}
	
	

	@Override
	public BussinessMan select2(String bussinessManId) throws Exception {
		pstmt = conn.prepareStatement("SELECT * FROM BussinessMan WHERE bussinessManId = ?");
		pstmt.setString(1, bussinessManId);
		rs = pstmt.executeQuery();
		
		BussinessMan bussinessMan = null;
		if (rs != null && rs.next()) {
			bussinessMan = new BussinessMan(
					rs.getString(1),
					rs.getString(2)
				);
		}
		
		freeConnection(pstmt, rs);
		
		return bussinessMan;
	}

	@Override
	public List<BussinessMan> selectAll() throws Exception {
		List<BussinessMan> list = null;
		BussinessMan bussinessMan = null;
		
		pstmt = conn.prepareStatement("SELECT * FROM BussinessMan");
		rs = pstmt.executeQuery();
		
		list = new ArrayList<BussinessMan>();
		if (rs != null) {
			while(rs.next()) {
				bussinessMan = new BussinessMan(
						rs.getString(1),
						rs.getString(2)
					);
				list.add(bussinessMan);
			}
		}
		
		freeConnection(pstmt, rs);
		
		return list;
	}

	@Override
	public boolean insert(BussinessMan dto) throws Exception {
		pstmt = conn.prepareStatement("INSERT INTO BussinessMan VALUES(?, ?)");
		pstmt.setString(1, dto.getBussinessManId());
		pstmt.setString(2, dto.getUserId());
		
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		
		return result > 0;
	}

	@Override
	public boolean update(String id, BussinessMan dto) throws Exception {
		pstmt = conn.prepareStatement("UPDATE BussinessMan SET bussinessManId = ? WHERE userId = ?");
		pstmt.setString(1, dto.getBussinessManId());
		pstmt.setString(2, id);
		
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		
		return result > 0;
	}

	@Override
	public boolean delete(String id) throws Exception {
		pstmt = conn.prepareStatement("DELETE FROM BussinessMan WHERE userId = ?");
		pstmt.setString(1, id);
		
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		
		return result > 0;
	}
}
