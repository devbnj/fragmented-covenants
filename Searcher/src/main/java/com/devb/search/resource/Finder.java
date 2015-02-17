package com.devb.search.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.devb.search.IndicSearcher;
import com.devb.search.Searcher;
import com.devb.search.StandardSearcher;
import com.devb.search.model.SearchResult;

/**
 * @author DevbNJ
 *
 */
public class Finder {

	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;
	  @Context
	  ServletContext sc;
	  String id;
	  
	  /*
	   * constructor
	   */
	  public Finder(UriInfo uriInfo, Request request, 
			  ServletContext sc, String id) {
		    this.uriInfo = uriInfo;
		    this.request = request;
		    this.sc = sc;
		    this.id = id;
	  }
	  
	  //Application integration     
	  @GET
	  @Path("json")
	  @Produces(MediaType.APPLICATION_JSON)
	  public String doSearcher() {
		    StandardSearcher td = new StandardSearcher(id, sc, true);
		    return td.ja.toString(); 
	  }
	  
	  //Application integration     
	  @GET
	  @Path("jsonindic")
	  @Produces(MediaType.APPLICATION_JSON)
	  public String doSearcherIndicJson() {
		    IndicSearcher is = new IndicSearcher(id, sc, true);
		    return is.ja.toString(); 
	  }

	  // for the browser
	  @GET
	  @Path("xml")
	  @Produces(MediaType.APPLICATION_XML)
	  public List<SearchResult> doSearcherHTML() {
		    List<SearchResult> searcher = new ArrayList<SearchResult>();
		    Searcher td = new StandardSearcher(id, sc, false);
		    searcher.addAll(td.getModel().values());
		    return searcher; 
	  }

	  // for the browser
	  @GET
	  @Path("xmlindic")
	  @Produces(MediaType.APPLICATION_XML)
	  public List<SearchResult> doSearcherIndicHtml() {
		    List<SearchResult> searcher = new ArrayList<SearchResult>();
		    Searcher cs = new IndicSearcher(id, sc, false);
		    searcher.addAll(cs.getModel().values());
		    return searcher; 
	  }
	  
}
