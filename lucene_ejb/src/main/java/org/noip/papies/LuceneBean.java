package org.noip.papies;

import java.io.File;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.noip.papies.lucene.IndexRequest;
import org.noip.papies.lucene.IndexResponse;
import org.noip.papies.lucene.LuceneIndexer;
import org.noip.papies.lucene.LuceneIndexerQueue;
import org.noip.papies.lucene.SearchRequest;
import org.noip.papies.lucene.SearchResult;
import org.noip.papies.lucene.SearchResults;

/**
 * Session Bean implementation class LuceneBean
 */
@Stateless
@LocalBean
public class LuceneBean implements LuceneBeanRemote, LuceneBeanLocal {

    /**
     * Default constructor. 
     */
    public LuceneBean() {
        // TODO Auto-generated constructor stub
    }
    
    public IndexResponse index(IndexRequest indexRequest) {
		LuceneIndexerQueue luceneIndexerQueue = LuceneIndexerQueue.getInstance();
		File documentToIndex = new File(indexRequest.getDocumentPath());
		IndexResponse indexResponse = null;
		if(!documentToIndex.exists()){
			indexResponse = new IndexResponse(false, "Document path doesn't exist");
		}else if(documentToIndex.isDirectory()){
			indexResponse = new IndexResponse(false, "Document path is a directory (must be a file)");
		}else{
			String errorDetails = null;
			boolean queued = luceneIndexerQueue.queueDocument(documentToIndex);
			if(!queued){
				errorDetails = "Generic error";
			}
			indexResponse = new IndexResponse(queued, errorDetails);
		}
		return indexResponse;
    }
	
    public SearchResults query(SearchRequest searchRequest) {
		LuceneIndexer luceneIndexer = LuceneIndexer.getInstance();
		SearchResult[] results = luceneIndexer.searchIndex(searchRequest.getQuery());
		return new SearchResults(results);
    }
	
    public String[] hint(SearchRequest searchRequest) {
		LuceneIndexer luceneIndexer = LuceneIndexer.getInstance();
		String[] terms = luceneIndexer.getTerms();
		return terms;
    }
	
    public String[] terms() {
		LuceneIndexer luceneIndexer = LuceneIndexer.getInstance();
		String[] terms = luceneIndexer.getTerms();
		return terms;
    }

}
