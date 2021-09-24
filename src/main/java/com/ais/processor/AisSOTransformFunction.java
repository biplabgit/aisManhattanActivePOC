package com.ais.processor;

import java.util.Arrays;
import java.util.List;

import com.ais.AisConstants;
import com.ais.pojos.StoreOrderMA;
import com.google.gson.Gson;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.ServiceBusQueueOutput;
import com.microsoft.azure.functions.annotation.ServiceBusQueueTrigger;

/**
 * Azure Functions with Service Bus Trigger. This function is for sending Store order data from untransformed queue 
 * to transformed queue after data conversion
 */
public class AisSOTransformFunction {
    /**
     * This function will be invoked when a new message is received at the Service Bus Queue.
     */
    @FunctionName("ServiceBusSoProcess")
    
    public void run(
            @ServiceBusQueueTrigger(name = "message", queueName = AisConstants.queueNameSO, connection = "sbConnection") String messageRequest,
            
            final ExecutionContext context,
            @ServiceBusQueueOutput(name = "msg", queueName = AisConstants.queueNameSoProcessed, connection = "sbConnection") final OutputBinding<String> result
    ) {
    	
        context.getLogger().info("Service Bus Queue trigger function for SO Processing started ........");
        context.getLogger().info(messageRequest);
        
        //Create Processing Logic, get the message split by separator , build po , covert to json , put to output queue        
        //split the message.
        
        if (null!=  messageRequest) {
        	
        	List<String> glsSOLines = Arrays.asList(messageRequest.split(AisConstants.MESSAGE_DELIMITER));
        	StoreOrderMA soMA = AisMhDataBuilder.buildSoData(glsSOLines);
        	//ObjectMapper mapper = new ObjectMapper();
        	
        	try {
				
				//String convertedData = mapper.writeValueAsString(poMA);
        		String convertedData = new Gson().toJson(soMA);
				//Send Data to Queue
				 result.setValue(convertedData);
				 context.getLogger().info("Transformed SO Message sent to SO Processed queue.");
				 context.getLogger().info("Service Bus Queue trigger function for SO Processing ended ........");
				 
				} /*
					 * catch (JsonProcessingException e1) { context.getLogger().
					 * info("Json Processing Exception while transforming PO data" + e1.toString());
					 * }
					 */ catch (Exception ex) {						
				context.getLogger().info("Processing Exception while transforming SO data" + ex.toString());
			}							
        }                    
    }
    
    
    
}
