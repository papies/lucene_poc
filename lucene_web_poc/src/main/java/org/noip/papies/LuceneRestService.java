package org.noip.papies;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.lucene.index.SlowCompositeReaderWrapper;
import org.apache.lucene.index.Terms;

@Path("/lucene")
public class LuceneRestService {

	@POST
	@Path("index")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
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
	
	@POST
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SearchResults query(SearchRequest searchRequest) {
		LuceneIndexer luceneIndexer = LuceneIndexer.getInstance();
		SearchResult[] results = luceneIndexer.searchIndex(searchRequest.getQuery());
		return new SearchResults(results);
    }
	
	@POST
    @Path("/hint")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String[] hint(SearchRequest searchRequest) {
		LuceneIndexer luceneIndexer = LuceneIndexer.getInstance();
		String[] terms = luceneIndexer.getTerms();
		return terms;
    }
	
	@POST
    @Path("/terms")
    @Produces(MediaType.APPLICATION_JSON)
    public String[] terms() {
		LuceneIndexer luceneIndexer = LuceneIndexer.getInstance();
		String[] terms = luceneIndexer.getTerms();
		return terms;
    }


}
