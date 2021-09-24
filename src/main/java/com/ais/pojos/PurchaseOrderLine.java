package com.ais.pojos;

/**
 * 
 * POJO Class for Purchase Order Line Details 
 *
 */
public class PurchaseOrderLine {
	
	
	private String itemId ;
	private String orderQuantity;
	private String purchaseOrderLineId ;
	private String purchaseOrderId;
	
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getPurchaseOrderLineId() {
		return purchaseOrderLineId;
	}
	public void setPurchaseOrderLineId(String purchaseOrderLineId) {
		this.purchaseOrderLineId = purchaseOrderLineId;
	}
	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	

}
