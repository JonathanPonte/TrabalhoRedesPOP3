package model;

public class Email {

	private int id;
	private String subject;
	private String textBody;
	private String from;
	private String to;
	private String base64;
	private String data;

	public Email() {

	}

	public Email(int id, String subject, String textBody, String from, String to, String base64, String data) {
		super();
		this.id = id;
		this.to = to;
		this.subject = subject;
		this.textBody = textBody;
		this.from = from;
		this.base64 = base64;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTextBody() {
		return textBody;
	}

	public void setTextBody(String textBody) {
		this.textBody = textBody;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}