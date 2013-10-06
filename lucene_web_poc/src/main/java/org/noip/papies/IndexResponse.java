package org.noip.papies;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name="indexResponse")
@XmlType(propOrder={"success", "errorDescription"})
public class IndexResponse {

	private boolean success;
	private String errorDescription;
	
	public IndexResponse() {
		// TODO Auto-generated constructor stub
	}

	public IndexResponse(boolean success, String errorDescription) {
		super();
		this.success = success;
		this.errorDescription = errorDescription;
	}

	@XmlElement(name="success")
	public boolean isSuccess() {
		return success;
	}
	@XmlElement(name="errorDescription")
	public String getErrorDescription() {
		return errorDescription;
	}
	
	
}
