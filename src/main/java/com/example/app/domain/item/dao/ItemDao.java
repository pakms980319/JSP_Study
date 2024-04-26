package com.example.app.domain.item.dao;

import java.util.List;

import com.example.app.domain.common.Crud;
import com.example.app.domain.item.dto.Item;

public interface ItemDao extends Crud<Item, Integer>{
	List<Item> select1(String keyword, int offset, int limit) throws Exception;
	List<Item> select2(String bussinessManId, int offset, int limit) throws Exception;
	boolean delete2(Integer id, String bussinessManId) throws Exception;
	boolean update2(Integer id, String bussinessManId, Item item) throws Exception;
}
