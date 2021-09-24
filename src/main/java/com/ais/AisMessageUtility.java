package com.ais;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

/**
 * AIS Message utility . This class contains different function to work with messages received from GLS
 * Example : splitting of messages , building messages 
 *
 */
public class AisMessageUtility {
		
	
	public static Map<String, List<String>> transactionSplitter(String blobReference){
		
		Map<String, List<String>> splitterMap = null; 
		 List<String> lines = new ArrayList<String>();
		
		try
		{
			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(AisConstants.storageConnectionString);
			//System.out.println("connection established");
			// Create the blob client.
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
			//System.out.println("client created"+blobClient.getStorageUri());
			// Retrieve reference to a previously created container.
			CloudBlobContainer container = blobClient.getContainerReference(AisConstants.DC_FOLDER);
			
			 CloudBlob blob = container.getBlockBlobReference(blobReference);
	            InputStream input =  blob.openInputStream();
	            InputStreamReader inr = new InputStreamReader(input, "UTF-8");
	            lines = IOUtils.readLines(inr);
	           
	            //System.out.println("No of Lines - >" + lines.size());	            
	           // System.out.println(lines.get(1));
	           // System.out.println("download success");	
	            
	           // Process Lines 	            
	            splitterMap = FileProcessor.groupMessage(lines);                                		
		}
		catch (Exception e)
		{
			// Output the stack trace.
			e.printStackTrace();
		}
		
		return splitterMap;
	}
	
	public static String buildMessage(List<String> list) {		
		
		StringBuilder stbr = new StringBuilder();
		
		for(String linedetail : list) {
			
			stbr.append(linedetail);
		//	stbr.append(System.lineSeparator());	
			stbr.append(AisConstants.MESSAGE_DELIMITER);			
		}		

		return stbr.toString();
	}
		
}
