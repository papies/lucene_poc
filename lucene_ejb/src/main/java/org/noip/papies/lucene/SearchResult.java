package org.noip.papies.lucene;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "searchResult")
public class SearchResult {

	private String path;
	private String title;
	
	public SearchResult(String path, String title) {
		super();
		this.path = path;
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public String getTitle() {
		return title;
	}

	
}
