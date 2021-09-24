package com.ais;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * File Processor Class to read file from Storage and process 
 *
 */
public class FileProcessor {	
			
	public static String processFile(String fileName) {
		
		ResponseObject responseObject = new ResponseObject();
		  String transactionTypes = "";  
		  String responseMessage = "";
		  
		  //Split the transactions and then
		  // Send message to respective queues 
		  
		  try {
		  
			  Map<String, List<String>> splitterMap = AisMessageUtility.transactionSplitter(fileName);		  
			  responseObject.setNumberOfTransactions(splitterMap.size());
			  
			  // Use Splitter Map to process the transactions further 		  
			  for (Map.Entry<String, List<String>> e : splitterMap.entrySet()) {
					
					String splitterKey = e.getKey();
					String transactionKey = splitterKey.split("-")[1];				
					transactionTypes = transactionTypes + transactionKey + "-";				
					//System.out.println("Transaction Key ->" + transactionKey);
					
					String splittedStringMessage = AisMessageUtility.buildMessage(e.getValue());
					
					if(AisConstants.PURCHASE_ORDER_TX.equalsIgnoreCase(transactionKey)) {													
																								
							//Send untransformed PO Data to Queue
							ServiceBusMessageSender.sendPoMessage(splittedStringMessage);						
							//System.out.println(" Sent data to PO Message Queue");							
												 							
					}else if(AisConstants.STORE_ORDER_TX.equalsIgnoreCase(transactionKey)) {							
																
							//Send untransformed SO Data to Queue
							ServiceBusMessageSender.sendSoMessage(splittedStringMessage);						
							//System.out.println(" Sent data to SO Message Queue");													
											
					}						
		           // System.out.println("Key: " + e.getKey()  + " Value: " + e.getValue());						
				}		  
		  
			  responseObject.setStatus("Success");
			  responseObject.setTransactionTypes(transactionTypes);	  
			  
			  responseMessage = new ObjectMapper().writeValueAsString(responseObject);
					  
		  }catch(Exception ex) {						
				//ex.printStackTrace();
				responseObject.setStatus("Failure");
			}	
		  
		  return   responseMessage;
	}
	
	
	public static Map<String, List<String>> groupMessage(List<String> lines) {
		
		Map<String, List<String>> splitterMap = new HashMap<String, List<String>>();
		
		 for(String line : lines) {	
			 
        	// System.out.println(line);		        	 
        	 String splitterKey1 = line.split("\\|")[0];	
        	 String splitterKey2 = line.split("\\|")[6];
        	 String finalKey = splitterKey1 + "-" + splitterKey2;
        	// System.out.println(splitterKey);    	        	 
        	 // [0161554519, list<string>]
        	 
        	 if(splitterMap.containsKey(finalKey)) {
        		 
        		List<String> messageList = (List<String>)splitterMap.get(finalKey);
        		messageList.add(line);
        		 
        	 }else {
        		 
        		 List<String> messageList = new ArrayList<String>();
        		 messageList.add(line);
        		 splitterMap.put(finalKey, messageList);        		 
        	 }
        	 
        	// break;
         }
		 		 
		//System.out.println(" Total No of Transaction Messages -> " + splitterMap.size());	
		
		return splitterMap;		
	}

}