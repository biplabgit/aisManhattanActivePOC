package com.ais.processor;

import java.util.Arrays;
import java.util.List;

import com.ais.AisConstants;
import com.ais.pojos.PurchaseOrderMA;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.ServiceBusQueueOutput;
import com.microsoft.azure.functions.annotation.ServiceBusQueueTrigger;

/**
 * Azure Functions with Service Bus Trigger. This function is for sending Purchase Order data from untransformed queue 
 * to transformed queue after data conversion
 */
public class AisPOTransformFunction {
    /**
     * This function will be invoked when a new message is received at the Service Bus Queue.
     */
    @FunctionName("ServiceBusPoProcess")
    
    public void run(
            @ServiceBusQueueTrigger(name = "message", queueName = AisConstants.queueNamePO, connection = "sbConnection") String messageRequest,
            
            final ExecutionContext context,
            @ServiceBusQueueOutput(name = "msg", queueName = AisConstants.queueNamePoProcessed, connection = "sbConnection") final OutputBinding<String> result
    ) {
    	
        context.getLogger().info("Service Bus Queue trigger function for PO Processing started ........");
        context.getLogger().info(messageRequest);
        
        //Create Processing Logic, get the message split by separator , build po , covert to json , put to output queue        
        //split the message.
        
        if (null!=  messageRequest) {
        	
        	List<String> glsPOLines = Arrays.asList(messageRequest.split(AisConstants.MESSAGE_DELIMITER));
        	PurchaseOrderMA poMA = AisMhDataBuilder.buildPoData(glsPOLines);
        	//ObjectMapper mapper = new ObjectMapper();
        	
        	try {
				
				//String convertedData = mapper.writeValueAsString(poMA);
        		String convertedData = new Gson().toJson(poMA);
				//Send Data to Queue
				 result.setValue(convertedData);
				 context.getLogger().info("Transformed PO Message sent to PO Processed queue.");
				 context.getLogger().info("Service Bus Queue trigger function for PO Processing ended ........");
				 
				} /*
					 * catch (JsonProcessingException e1) { context.getLogger().
					 * info("Json Processing Exception while transforming PO data" + e1.toString());
					 * }
					 */ catch (Exception ex) {						
				context.getLogger().info("Processing Exception while transforming PO data" + ex.toString());
			}							
        }                    
    }
}
