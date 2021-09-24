package com.ais.pojos;

import java.util.List;

/**
 * 
 * POJO Class for Purchase Order data for Manhattan
 *
 */

public class PurchaseOrderMA {
		
	private String puchaseOrderId;
	private String purchaseOrderStatus;
	private String organization;
	private String facilityId;
	private List<PurchaseOrderLine> purchaseOrderLines;
	
	
	public String getPuchaseOrderId() {
		return puchaseOrderId;
	}
	public void setPuchaseOrderId(String puchaseOrderId) {
		this.puchaseOrderId = puchaseOrderId;
	}
	public String getPurchaseOrderStatus() {
		return purchaseOrderStatus;
	}
	public void setPurchaseOrderStatus(String purchaseOrderStatus) {
		this.purchaseOrderStatus = purchaseOrderStatus;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	public List<PurchaseOrderLine> getPurchaseOrderLines() {
		return purchaseOrderLines;
	}
	public void setPurchaseOrderLines(List<PurchaseOrderLine> purchaseOrderLines) {
		this.purchaseOrderLines = purchaseOrderLines;
	}


}
