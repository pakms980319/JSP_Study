package com.example.app.domain.item.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.app.domain.common.CommonDao;
import com.example.app.domain.item.dto.Criteria;
import com.example.app.domain.item.dto.Item;
import com.example.app.domain.item.dto.PageDto;

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
		pstmt = conn.prepareStatement("select * from item where itemId=?");
		pstmt.setInt(1, id);
		rs =  pstmt.executeQuery();
		
		Item dto = null;
		if(rs!=null)
		{
				rs.next();
				dto = new Item();
				dto.setItemId(rs.getInt(1));
				dto.setBussinessManId(rs.getString(2));
				dto.setItemName(rs.getString(3));
				dto.setItemType(rs.getString(4));
				dto.setItemPrice(rs.getInt(5));
				dto.setItemCount(rs.getInt(6));
				dto.setItemManufacturingDate(rs.getDate(7));		
		}	
		
		freeConnection(pstmt,rs);
		return dto;
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
		pstmt = conn.prepareStatement(
				"UPDATE ITEM SET itemName = ?, itemType = ?, itemPrice = ?, itemCount = ?, itemManufacturingDate = ? WHERE itemId = ? AND bussinessManId = ?");
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
		if (rs != null) {
			list = new ArrayList<Item>();
			while (rs.next()) {
				dto = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getDate(7));
				list.add(dto);
			}
		}

		freeConnection(pstmt, rs);

		return list;
	}

	@Override
	public int count() throws Exception {

		pstmt = conn.prepareStatement("select count(*) from item");
		rs = pstmt.executeQuery();
		int count = -1;
		if (rs != null && rs.next())
			count = rs.getInt(1);
		freeConnection(pstmt, rs);

		return count;
	}

	// 07
	@Override
	public int count(Criteria criteria) throws Exception {

		System.out.println("count func type : " + criteria.getType());
		pstmt = conn.prepareStatement(
				"select count(*) from item where " + criteria.getType() + " like '%" + criteria.getKeyword() + "%'");
		rs = pstmt.executeQuery();
		int count = -1;
		if (rs != null && rs.next())
			count = rs.getInt(1);
		freeConnection(pstmt, rs);

		return count;
	}

	@Override
	public List<Item> Select(PageDto pageDto, int offset) throws SQLException {

		pstmt = conn.prepareStatement("select * from item order by itemId desc limit ?,?");
		pstmt.setInt(1, offset); // 시작 offset
		pstmt.setInt(2, pageDto.getCriteria().getAmount());

		rs = pstmt.executeQuery();

		List<Item> list = null;
		Item dto = null;
		if (rs != null) {
			list = new ArrayList<Item>();
			while (rs.next()) {
				dto = new Item();
				dto.setItemId(rs.getInt(1));
				dto.setBussinessManId(rs.getString(2));
				dto.setItemName(rs.getString(3));
				dto.setItemType(rs.getString(4));
				dto.setItemPrice(rs.getInt(5));
				dto.setItemCount(rs.getInt(6));
				dto.setItemManufacturingDate(rs.getDate(7));
				list.add(dto);
			}
		}

		freeConnection(pstmt, rs);
		return list;

	}

	@Override
	public List<Item> Select(PageDto pageDto, int offset, String type, String keyword) throws SQLException {

		pstmt = conn.prepareStatement(
				"select * from item where " + type + " like '%" + keyword + "%' order by itemId desc limit ?,?");

		pstmt.setInt(1, offset); // 시작 offset
		pstmt.setInt(2, pageDto.getCriteria().getAmount());

		rs = pstmt.executeQuery();

		List<Item> list = null;
		Item dto = null;
		if (rs != null) {
			list = new ArrayList<Item>();
			while (rs.next()) {
				dto = new Item();
				dto.setItemId(rs.getInt(1));
				dto.setBussinessManId(rs.getString(2));
				dto.setItemName(rs.getString(3));
				dto.setItemType(rs.getString(4));
				dto.setItemPrice(rs.getInt(5));
				dto.setItemCount(rs.getInt(6));
				dto.setItemManufacturingDate(rs.getDate(7));
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
		if (rs != null) {
			list = new ArrayList<Item>();
			while (rs.next()) {
				dto = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getDate(7));
				list.add(dto);
			}
		}

		freeConnection(pstmt, rs);

		return list;
	}
}
