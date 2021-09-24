package com.ais.processor;

import java.util.ArrayList;
import java.util.List;

import com.ais.pojos.PurchaseOrderLine;
import com.ais.pojos.PurchaseOrderMA;
import com.ais.pojos.StoreOrderLine;
import com.ais.pojos.StoreOrderMA;
/**
 * 
 * Data Builder class for transforming GLS data to Manhattan.
 * Field wise mappings are captured in this class.
 *
 */
public class AisMhDataBuilder {
	
public static PurchaseOrderMA buildPoData(List<String> list) {
		
		PurchaseOrderMA poMA = new PurchaseOrderMA();
		List<PurchaseOrderLine> purchaseOrderLines = new ArrayList<PurchaseOrderLine>();
		String purchaseOrderId = "";
		
		for(String linedetail : list) {
			
			if(null != linedetail && "" != linedetail.trim()) {
							
				String subTransactionType = linedetail.split("\\|")[8];			
				if(subTransactionType.equalsIgnoreCase("02000")) {
					
					poMA.setFacilityId(linedetail.split("\\|")[2]);
					poMA.setOrganization(linedetail.split("\\|")[4]);
					purchaseOrderId = linedetail.split("\\|")[13];
					poMA.setPuchaseOrderId(purchaseOrderId);
					
				}else if (subTransactionType.equalsIgnoreCase("02010")) {
					
				}else if (subTransactionType.equalsIgnoreCase("02020")) {
					
					PurchaseOrderLine poLine = new PurchaseOrderLine();
					poLine.setPurchaseOrderId(purchaseOrderId);
					poLine.setOrderQuantity(linedetail.split("\\|")[18]);
					poLine.setPurchaseOrderLineId(linedetail.split("\\|")[14]);
					
					purchaseOrderLines.add(poLine);
					
				}else if (subTransactionType.equalsIgnoreCase("02030")) {
					
				}else if(subTransactionType.equalsIgnoreCase("04000")){				
				}
			}
		}		
		poMA.setPurchaseOrderLines(purchaseOrderLines);
		
		return poMA;		
	}	



public static StoreOrderMA buildSoData(List<String> list) {
	
	StoreOrderMA soMA = new StoreOrderMA();
	List<StoreOrderLine> storeOrderLines = new ArrayList<StoreOrderLine>();
	String orginalOrderId = "";
	
	for(String linedetail : list) {
		
		if(null != linedetail && "" != linedetail.trim()) {
		
			String subTransactionType = linedetail.split("\\|")[8];
			
			if(subTransactionType.equalsIgnoreCase("06000")) {
				
				soMA.setDeliveryDate(linedetail.split("\\|")[14]);
				soMA.setDestinationFacilityIDl(linedetail.split("\\|")[19]);
				orginalOrderId = linedetail.split("\\|")[13];
				soMA.setOrginialOrderID(orginalOrderId);
				
			}else if (subTransactionType.equalsIgnoreCase("06010")) {
				
				StoreOrderLine soLine = new StoreOrderLine();
				soLine.setOriginalOrderId(orginalOrderId);
				soLine.setItemId(linedetail.split("\\|")[19]);
				soLine.setOrderedQuantity(linedetail.split("\\|")[21]);
				
				storeOrderLines.add(soLine);				
			}	
		}		
	}
	
	soMA.setStoreOrderLines(storeOrderLines);		
	return soMA;		
}


}
