package com.ais.pojos;


/**
 * 
 * POJO Class for Store Order Line for Manhattan Active
 *
 */

public class StoreOrderLine {
	
	private String originalOrderId;
	private String itemId;
	private String orderedQuantity;
	
	
	public String getOriginalOrderId() {
		return originalOrderId;
	}
	public void setOriginalOrderId(String originalOrderId) {
		this.originalOrderId = originalOrderId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getOrderedQuantity() {
		return orderedQuantity;
	}
	public void setOrderedQuantity(String orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}
	
	
}
