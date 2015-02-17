package com.devb.search.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DevbNJ
 *
 */
public enum SearchResultDemo {
	instance;

	  private Map<String, SearchResult> contents = new HashMap<String, SearchResult>();
	  
	  private SearchResultDemo() {
	    SearchResult ns = new SearchResult();
		ns.setDocPath("C:/One");
		ns.setLineNum("100");
		ns.setContents("Lopsum dom pom 1");
		contents.put("+sun +moon", ns);

		ns = new SearchResult();
		ns.setDocPath("C:/Two");
		ns.setLineNum("110");
		ns.setContents("Lopsum dom pom 2");
		contents.put("+sun -moon", ns);
	  }
	  
	  /*
	   * return demo contents
	   */
	  public Map<String, SearchResult> getModel(){
	    return contents;
	  }
	
}
