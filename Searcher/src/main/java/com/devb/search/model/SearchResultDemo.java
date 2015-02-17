/**
 * SearchResultDemo.java 
 * For the Book - Fragmented Covenants
 * Author Dev B, Copyright 2015 Dev B
 * This software is distributed under the terms 
 * of the Open Source Apache v2.0 license
 * This program is distributed in the hope that it will be useful
 * The author Makes No Warranties, Express OR Implied
 * Please do not remove the copyright notice
 */
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
