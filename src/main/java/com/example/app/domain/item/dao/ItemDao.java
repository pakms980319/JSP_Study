package com.example.app.domain.item.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.app.domain.common.Crud;
import com.example.app.domain.item.dto.Criteria;
import com.example.app.domain.item.dto.Item;
import com.example.app.domain.item.dto.PageDto;

public interface ItemDao extends Crud<Item, Integer>{
	List<Item> select1(String keyword, int offset, int limit) throws Exception;
	List<Item> select2(String bussinessManId, int offset, int limit) throws Exception;
	boolean delete2(Integer id, String bussinessManId) throws Exception;
	boolean update2(Integer id, String bussinessManId, Item item) throws Exception;
	int count() throws Exception;
	int count(Criteria criteria) throws Exception;
	List<Item> Select(PageDto pageDto, int offset) throws SQLException;
	List<Item> Select(PageDto pageDto, int offset, String type, String keyword) throws SQLException;
	int count2(String id) throws Exception;
	int count2(Criteria criteria, String id) throws Exception;
	List<Item> Select2(PageDto pageDto, int offset, String id) throws SQLException;
	List<Item> Select2(PageDto pageDto, int offset, String type, String keyword, String id) throws SQLException;
}
