package com.ais;

/**
 * 
 * AIS Constant Definition
 *
 */
public interface AisConstants {
	
	
	public static final String PURCHASE_ORDER_TX = "00001";
	public static final String STORE_ORDER_TX = "00004";
	
	public static final String MESSAGE_DELIMITER = "@@@";
 
	public static String connectionStringPO = "Endpoint=sb://aisservicebusnamespacemanhattan.servicebus.windows.net/;SharedAccessKeyName=mgPolicyAisPoQUnprocessed;SharedAccessKey=kWWxhCqesCh54Ne2Esx0JjP92s9S/QuWHvuFEIMQEJM=;EntityPath=aispoqueue-unprocessed";
	
	//public static String connectionStringPO = "Endpoint=sb://aisservicebusasda.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=tOMfTIwJciaeM8je//kJKz7Y1Zi4ZwLwM/PSoM413VM="; 	
	public static String queueNamePO = "aispoqueue-unprocessed";  
	
	public static String connectionStringSO = "Endpoint=sb://aisservicebusnamespacemanhattan.servicebus.windows.net/;SharedAccessKeyName=mgPolicyAisSoQUnprocessed;SharedAccessKey=Q7xp8uL58LL1ZrANqoxklrrwVfh+6dGx9TL/V6a33YQ=;EntityPath=aissoqueue-unprocessed";
	public static String queueNameSO = "aissoqueue-unprocessed"; 
	
	public static final String storageConnectionString =
			"DefaultEndpointsProtocol=https;AccountName=asdamapocstorage;AccountKey=yfF/2zvQyGviVI5+H6TIz+cZ+Z5sTC6hBiqyT08dHwxQpvDLEmR60Gqi1q0DJFh0F1otIczH+Vzim6Nwy6vXMA==;EndpointSuffix=core.windows.net";
	
	public static String queueNamePoProcessed = "aispoqueue-processed"; 
	public static String queueNameSoProcessed = "aissoqueue-processed";
	
	public static final String DC_FOLDER = "dc-unread";
}
