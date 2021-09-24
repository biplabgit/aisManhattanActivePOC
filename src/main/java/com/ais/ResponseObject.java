package com.ais;

public class ResponseObject {
	
	private String status;
	private int numberOfTransactions;
	private String transactionTypes;
	
			
	public ResponseObject(String status, int numberOfTransactions, String transactionTypes) {
		super();
		this.status = status;
		this.numberOfTransactions = numberOfTransactions;
		this.transactionTypes = transactionTypes;
	}

	public ResponseObject() {
		super();
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}		
	
	public String getTransactionTypes() {
		return transactionTypes;
	}
	public void setTransactionTypes(String transactionTypes) {
		this.transactionTypes = transactionTypes;
	}	
	
	public int getNumberOfTransactions() {
		return numberOfTransactions;
	}

	public void setNumberOfTransactions(int numberOfTransactions) {
		this.numberOfTransactions = numberOfTransactions;
	}	

}
