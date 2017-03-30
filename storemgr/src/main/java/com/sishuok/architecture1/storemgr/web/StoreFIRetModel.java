package com.sishuok.architecture1.storemgr.web;

public class StoreFIRetModel implements java.io.Serializable {
	
	private boolean callResult = false;

	public boolean isCallResult() {
		return callResult;
	}

	public void setCallResult(boolean callResult) {
		this.callResult = callResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (callResult ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoreFIRetModel other = (StoreFIRetModel) obj;
		if (callResult != other.callResult)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StoreFIRetModel [callResult=" + callResult + "]";
	}
	
}
