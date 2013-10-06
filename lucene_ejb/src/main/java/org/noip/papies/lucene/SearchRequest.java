package org.noip.papies.lucene;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "searchRequest")
public class SearchRequest {

	String query;
	
	public SearchRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
	
}
