package com.ais;

/**
 * This class have different functions to send messages to respective service bus queues depending on Transaction types 
 * 
 */

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

public class ServiceBusMessageSender {
	
	public static void main(String[] args) {
		
		sendPoMessage("Testing ..");		
	}

	public static void sendPoMessage(String message)
	{
	    // create a Service Bus Sender client for the queue 
	    ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
	            .connectionString(AisConstants.connectionStringPO)
	            .sender()
	            .queueName(AisConstants.queueNamePO)
	            .buildClient();

	    // send one message to the queue
	    senderClient.sendMessage(new ServiceBusMessage(message));
	    //System.out.println("Sent messages to the queue: " + AisConstants.queueNamePO);        
	}
	
	
	public static void sendSoMessage(String message)
	{
	    // create a Service Bus Sender client for the queue 
	    ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
	            .connectionString(AisConstants.connectionStringSO)
	            .sender()
	            .queueName(AisConstants.queueNameSO)
	            .buildClient();

	    // send one message to the queue
	    senderClient.sendMessage(new ServiceBusMessage(message));
	    //System.out.println("Sent messages to the queue: " + AisConstants.queueNameSO);        
	}
		
}
