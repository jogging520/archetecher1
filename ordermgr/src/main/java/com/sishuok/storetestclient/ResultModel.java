package com.sishuok.storetestclient;

public class ResultModel {
	private boolean callResult = false;

	public boolean isCallResult() {
		return callResult;
	}

	public void setCallResult(boolean callResult) {
		this.callResult = callResult;
	}

	@Override
	public String toString() {
		return "ResultModel [callResult=" + callResult + "]";
	}

	
	
}
