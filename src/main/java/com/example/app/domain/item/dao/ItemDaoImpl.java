package com.example.app.domain.item.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.app.domain.common.CommonDao;
import com.example.app.domain.item.dto.Item;

public class ItemDaoImpl extends CommonDao implements ItemDao {
	
	private static ItemDao instance;
	
	public static ItemDao getInstance() throws Exception {
		if (instance == null)
			instance = new ItemDaoImpl();
		return instance;
	}
	
	private ItemDaoImpl() throws Exception {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Item select(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Item dto) throws Exception {
		pstmt = conn.prepareStatement("INSERT INTO ITEM VALUES(null, ?, ?, ?, ?, ?, ?)");
		
		pstmt.setString(1, dto.getBussinessManId());
		pstmt.setString(2, dto.getItemName());
		pstmt.setString(3, dto.getItemType());
		pstmt.setInt(4, dto.getItemPrice());
		pstmt.setInt(5, dto.getItemCount());
		Date sqlDate = new Date(dto.getItemManufacturingDate().getTime());
		pstmt.setDate(6, sqlDate);
		
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		
		return result > 0;
	}

	@Override
	public boolean update2(Integer id, String bussinessManId, Item dto) throws Exception {
		pstmt = conn.prepareStatement("UPDATE ITEM SET itemName = ?, itemType = ?, itemPrice = ?, itemCount = ?, itemManufacturingDate = ? WHERE itemId = ? AND bussinessManId = ?");
		pstmt.setString(1, dto.getItemName());
		pstmt.setString(2, dto.getItemType());
		pstmt.setInt(3, dto.getItemPrice());
		pstmt.setInt(4, dto.getItemCount());
		Date sqlDate = new Date(dto.getItemManufacturingDate().getTime());
		pstmt.setDate(5, sqlDate);
		pstmt.setInt(6, id);
		pstmt.setString(7, bussinessManId);
		
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		
		return result > 0;
	}
	
	@Override
	public boolean update(Integer id, Item dto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete2(Integer id, String bussinessManId) throws Exception {
		pstmt = conn.prepareStatement("DELETE FROM ITEM WHERE ITEMID = ? AND bussinessManId = ?");
		pstmt.setInt(1, id);
		pstmt.setString(2, bussinessManId);
		
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		
		return result > 0;
	}

	@Override
	public List<Item> select1(String keyword, int offset, int limit) throws Exception {
		pstmt = conn.prepareStatement("SELECT * FROM ITEM WHERE ITEMNAME LIKE ? LIMIT ?, ?");
		
		pstmt.setString(1, "%" + keyword + "%");
		pstmt.setInt(2, offset);
		pstmt.setInt(3, limit);
		
		rs = pstmt.executeQuery();
		
		List<Item> list = null;
		Item dto = null;
		if(rs != null) {
			list = new ArrayList<Item>();
			while(rs.next()) {
				dto = new Item(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getDate(7)
					);
				list.add(dto);
			}
		}
		
		freeConnection(pstmt, rs);
		
		return list;
	}

	@Override
	public List<Item> select2(String bussinessManId, int offset, int limit) throws Exception {
		pstmt = conn.prepareStatement("SELECT * FROM ITEM WHERE bussinessManId = ? LIMIT ?, ?");
		
		pstmt.setString(1, bussinessManId);
		pstmt.setInt(2, offset);
		pstmt.setInt(3, limit);
		
		rs = pstmt.executeQuery();
		
		List<Item> list = null;
		Item dto = null;
		if(rs != null) {
			list = new ArrayList<Item>();
			while(rs.next()) {
				dto = new Item(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getDate(7)
					);
				list.add(dto);
			}
		}
		
		freeConnection(pstmt, rs);
		
		return list;
	}
}
