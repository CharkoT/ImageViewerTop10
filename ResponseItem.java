package charko.tester01.com.imageviewertop10;

public class ResponseItem {
	int state;
	String title;
	String cause;

	public ResponseItem(int state, String title, String cause) {
		this.state = state;
		this.title = title;
		this.cause = cause;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}
