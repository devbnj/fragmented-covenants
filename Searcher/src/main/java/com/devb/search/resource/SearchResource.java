package com.devb.search.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.devb.search.GoodTimes;
import com.devb.search.IndicIndexer;
import com.devb.search.StandardIndexer;
import com.devb.search.TestData;
import com.devb.search.model.SearchResult;
import com.devb.search.model.SearchResultDemo;

//Will map the resource to the URL searcher
/**
 * @author DevbNJ
 *
 */
@Path("/searcher")
public class SearchResource {

	  // Allows to insert contextual objects into the class, 
	  // ex: ServletContext, Request, Response, UriInfo
	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;
	  @Context
	  ServletContext sc;
	  
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public List<SearchResult> getSearcherText() {
	    List<SearchResult> searcher = new ArrayList<SearchResult>();
	    TestData td = new TestData();
	    searcher.addAll(td.getModel().values());
	    return searcher; 
	  }
	  
	  // Return the list of searcher for applications
	  @GET
	  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	  public List<SearchResult> getSearcherXml() {
	    List<SearchResult> searcher = new ArrayList<SearchResult>();
	    TestData td = new TestData();
	    searcher.addAll(td.getModel().values());
	    return searcher; 
	  }
	  
	  

	  // indexes the documents
	  @GET
	  @Path("index")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getIndex() {
	    new StandardIndexer(sc);
	    return "Indexing completed";
	  }
	  
	  // indexes the indic documents
	  @GET
	  @Path("indexindic")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getIndexIndic() {
	    new IndicIndexer(sc);
	    return "Indic Indexing completed";
	  }

	  @GET
	  @Path("isgoodtime")
	  @Produces(MediaType.APPLICATION_JSON)
	  public String isGoodTime() {
		  GoodTimes gt = new GoodTimes(true);
		  return gt.jo.toString();
	  }
	  
	  
	  // Defines that the next path parameter after searcher is
	  // treated as a parameter and passed to the SearcherResource
	  /*
	   * @calls Finder
	   */
	  @Path("{searcher}")
	  public Finder getSearchResults(@PathParam("searcher") String id) {
	    return new Finder(uriInfo, request, sc, id);
	  }

}
