package com.example.app.domain.item.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.app.domain.common.ConnectionPool_ByHikari;
import com.example.app.domain.item.dao.ItemDao;
import com.example.app.domain.item.dao.ItemDaoImpl;
import com.example.app.domain.item.dto.Criteria;
import com.example.app.domain.item.dto.Item;
import com.example.app.domain.item.dto.PageDto;
import com.example.app.domain.user.dao.BussinessManDao;
import com.example.app.domain.user.dao.BussinessManDaoImpl;

public class ItemServiceImpl implements ItemService {

	private static ItemService instance;
	private BussinessManDao bussinessManDao;
	private ItemDao itemDao;
	private ConnectionPool_ByHikari connectionPool;

	public static ItemService getInstance() throws Exception {
		if (instance == null)
			instance = new ItemServiceImpl();
		return instance;
	}

	private ItemServiceImpl() throws Exception {
		itemDao = ItemDaoImpl.getInstance();
		connectionPool = ConnectionPool_ByHikari.getInstance();
		bussinessManDao = BussinessManDaoImpl.getInstance();
	}

	@Override
	public Item getItem(int itemId) throws Exception {
		Item dto = itemDao.select(itemId);
		return dto;
	}

	@Override
	public Map<String, Object> getAllItems(Criteria criteria) throws Exception {

		connectionPool.txStart(); // TX START

		Map<String, Object> returnValue = new HashMap();

		String type = criteria.getType();
		String keyword = criteria.getKeyword();

		if (type == null || type.isEmpty() || keyword == null || keyword.isEmpty()) {
			int count = itemDao.count();

			System.out.println("getAllItems count : " + count);
			// pageDto생성
			PageDto pageDto = new PageDto(count, criteria);

			// 시작 게시물 번호 구하기(수정) - OFFSET
			int offset = (criteria.getPageno() - 1) * criteria.getAmount();
			System.out.println("getAllItems offset : " + offset);
			List<Item> list = itemDao.Select(pageDto, offset);

			returnValue.put("list", list);
			returnValue.put("pageDto", pageDto);
			System.out.println("getAllItems list : " + list);
		} else // 키워드 조회
		{
			int count = itemDao.count(criteria);
			System.out.println("getAllItems count : " + count);
			// pageDto생성
			PageDto pageDto = new PageDto(count, criteria);

			// 시작 게시물 번호 구하기(수정) - OFFSET
			int offset = (criteria.getPageno() - 1) * criteria.getAmount();
			System.out.println("getAllItems offset : " + offset);
			List<Item> list = itemDao.Select(pageDto, offset, type, keyword);

			returnValue.put("list", list);
			returnValue.put("pageDto", pageDto);
			System.out.println("getAllItems list : " + list);

		}

		connectionPool.txCommit(); // TX END

		return returnValue;
	}

	@Override
	public Map<String, Object> BussinessManItemShow(String userId, int offset, int limit) throws Exception {
		connectionPool.txStart();
		Map<String, Object> result = new HashMap<String, Object>();
		List<Item> list = new ArrayList<Item>();
		String bussinessManId = bussinessManDao.select(userId).getBussinessManId();

		list = itemDao.select2(bussinessManId, offset, limit);

		// 조회된 아이템이 없을 때
		if (list.size() == 0) {
			result.put("response", false);
			result.put("msg", "등록된 상품이 없습니다. 상품을 등록해주세요.");
			return result;
		}

		// 조회 성공
		result.put("response", true);
		result.put("msg", "상품 조회에 성공하였습니다.");
		result.put("itemList", list);

		connectionPool.txCommit();
		return result;
	}

	@Override
	public Map<String, Object> KeywordItemShow(String keyword, int offset, int limit) throws Exception {
		connectionPool.txStart();
		Map<String, Object> result = new HashMap<String, Object>();
		List<Item> list = new ArrayList<Item>();

		list = itemDao.select1(keyword, offset, limit);

		// 조회된 아이템이 없을 때
		if (list.size() == 0) {
			result.put("response", false);
			result.put("msg", "조회할 상품이 없습니다.");
			return result;
		}

		// 조회 성공
		result.put("response", true);
		result.put("msg", "상품 조회에 성공하였습니다.");
		result.put("itemList", list);

		connectionPool.txCommit();
		return result;
	}

	@Override
	public Map<String, Object> ItemInsert(Item item, String userId) throws Exception {
		connectionPool.txStart();
		Map<String, Object> result = new HashMap<String, Object>();
		String bussinessManId = bussinessManDao.select(userId).getBussinessManId();
		item.setBussinessManId(bussinessManId);

		boolean isSaved = itemDao.insert(item);

		// 상품등록 실패
		if (!isSaved) {
			result.put("response", false);
			result.put("msg", "상품등록에 실패하였습니다. 관리자에게 문의해주세요.");
			return result;
		}

		// 상품등록 성공
		result.put("response", true);
		result.put("msg", "상품등록에 성공하였습니다!");

		connectionPool.txCommit();
		return result;
	}

	@Override
	public Map<String, Object> ItemUpdate(int itemId, String userId, Item updateItem) throws Exception {
		connectionPool.txStart();
		Map<String, Object> result = new HashMap<String, Object>();
		String bussinessManId = bussinessManDao.select(userId).getBussinessManId();

		boolean isUpdated = itemDao.update2(itemId, bussinessManId, updateItem);

		// 상품수정 실패
		if (!isUpdated) {
			result.put("response", false);
			result.put("msg", "상품수정에 실패하였습니다. 관리자에게 문의해주세요.");
			return result;
		}

		// 상품등록 성공
		result.put("response", true);
		result.put("msg", "상품수정에 성공하였습니다!");

		connectionPool.txCommit();
		return result;
	}

	@Override
	public Map<String, Object> ItemDelete(int itemId, String userId) throws Exception {
		connectionPool.txStart();
		Map<String, Object> result = new HashMap<String, Object>();
		String bussinessManId = bussinessManDao.select(userId).getBussinessManId();

		boolean isDeleted = itemDao.delete2(itemId, bussinessManId);

		// 상품삭제 실패
		if (!isDeleted) {
			result.put("response", false);
			result.put("msg", "상품삭제에 실패하였습니다. 관리자에게 문의해주세요.");
			return result;
		}

		// 상품삭제 성공
		result.put("response", true);
		result.put("msg", "상품삭제에 성공하였습니다!");

		connectionPool.txCommit();
		return result;
	}

}
