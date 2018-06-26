package twitterPack;

import java.io.Serializable;

public class RispostaBoolean implements Serializable {

	private static final long serialVersionUID = 3416296735381581388L;
	private boolean error;
	private String message;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
