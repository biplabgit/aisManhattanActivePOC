package com.ais.pojos;

import java.util.List;

/**
 * 
 * POJO Class for Store Order for Manhattan Active
 *
 */
public class StoreOrderMA {
	
	private String orginialOrderID;
	private String destinationFacilityIDl;
	private String deliveryDate;
	
	private List<StoreOrderLine> storeOrderLines;

	public String getOrginialOrderID() {
		return orginialOrderID;
	}

	public void setOrginialOrderID(String orginialOrderID) {
		this.orginialOrderID = orginialOrderID;
	}

	public String getDestinationFacilityIDl() {
		return destinationFacilityIDl;
	}

	public void setDestinationFacilityIDl(String destinationFacilityIDl) {
		this.destinationFacilityIDl = destinationFacilityIDl;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public List<StoreOrderLine> getStoreOrderLines() {
		return storeOrderLines;
	}

	public void setStoreOrderLines(List<StoreOrderLine> storeOrderLines) {
		this.storeOrderLines = storeOrderLines;
	}
		

}
