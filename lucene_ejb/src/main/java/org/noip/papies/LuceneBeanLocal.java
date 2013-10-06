package org.noip.papies;
import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.noip.papies.lucene.IndexRequest;
import org.noip.papies.lucene.IndexResponse;
import org.noip.papies.lucene.SearchRequest;
import org.noip.papies.lucene.SearchResults;

@Local
@Path("/lucene")
public interface LuceneBeanLocal {


	@POST
	@Path("index")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public IndexResponse index(IndexRequest indexRequest);
	
	@POST
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SearchResults query(SearchRequest searchRequest);
	
	@POST
    @Path("/hint")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String[] hint(SearchRequest searchRequest);
    
	@POST
    @Path("/terms")
    @Produces(MediaType.APPLICATION_JSON)
    public String[] terms();
	
}
