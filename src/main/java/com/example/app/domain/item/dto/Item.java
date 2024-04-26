package com.example.app.domain.item.dto;

import java.util.Date;

public class Item {
	private int itemId;
	private String bussinessManId;
	private String itemName;
	private String itemType;
	private int itemPrice;
	private int itemCount;
	private Date itemManufacturingDate;
	
	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(String bussinessManId, String itemName, String itemType, int itemPrice, int itemCount,
			Date itemManufacturingDate) {
		super();
		this.bussinessManId = bussinessManId;
		this.itemName = itemName;
		this.itemType = itemType;
		this.itemPrice = itemPrice;
		this.itemCount = itemCount;
		this.itemManufacturingDate = itemManufacturingDate;
	}
	
	public Item(int itemId, String bussinessManId, String itemName, String itemType, int itemPrice, int itemCount,
			Date itemManufacturingDate) {
		super();
		this.itemId = itemId;
		this.bussinessManId = bussinessManId;
		this.itemName = itemName;
		this.itemType = itemType;
		this.itemPrice = itemPrice;
		this.itemCount = itemCount;
		this.itemManufacturingDate = itemManufacturingDate;
	}

	public Item(String itemName, String itemType, int itemPrice, int itemCount, Date itemManufacturingDate) {
		super();
		this.itemName = itemName;
		this.itemType = itemType;
		this.itemPrice = itemPrice;
		this.itemCount = itemCount;
		this.itemManufacturingDate = itemManufacturingDate;
	}
	
	

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", bussinessManId=" + bussinessManId + ", itemName=" + itemName
				+ ", itemType=" + itemType + ", itemPrice=" + itemPrice + ", itemCount=" + itemCount
				+ ", itemManufacturingDate=" + itemManufacturingDate + "]";
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getBussinessManId() {
		return bussinessManId;
	}

	public void setBussinessManId(String bussinessManId) {
		this.bussinessManId = bussinessManId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public Date getItemManufacturingDate() {
		return itemManufacturingDate;
	}

	public void setItemManufacturingDate(Date itemManufacturingDate) {
		this.itemManufacturingDate = itemManufacturingDate;
	}
}
