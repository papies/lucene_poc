package org.noip.papies;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "indexRequest")
@XmlType(propOrder={"documentPath"})
public class IndexRequest {

	private String documentPath;
	
	public IndexRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public IndexRequest(String documentPath) {
		this.documentPath = documentPath;
	}

	@XmlElement(name="documentPath")
	public String getDocumentPath() {
		return documentPath;
	}
	
}
