package org.noip.papies;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "searchResults")
public class SearchResults {

	private SearchResult[] results;
	
	public SearchResults(SearchResult[] results) {
		super();
		this.results = results;
	}
	public SearchResult[] getResults() {
		return results;
	}

	
}
