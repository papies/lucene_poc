package org.noip.papies;

import javax.ejb.Remote;

import org.noip.papies.lucene.IndexRequest;
import org.noip.papies.lucene.IndexResponse;
import org.noip.papies.lucene.SearchRequest;
import org.noip.papies.lucene.SearchResults;

@Remote
public interface LuceneBeanRemote {

    public IndexResponse index(IndexRequest indexRequest);
	public SearchResults query(SearchRequest searchRequest);
	public String[] hint(SearchRequest searchRequest);
    public String[] terms();
}
