package com.chihuo.api.component.event;

import java.io.Serializable;
import java.util.Date;

public class DomainEvent implements Serializable {
	
	private static final long serialVersionUID = 8590980085263347400L;
	private String id;
	private Date timestamp;
	private String topic;
	private String source;
	private String data;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
}
