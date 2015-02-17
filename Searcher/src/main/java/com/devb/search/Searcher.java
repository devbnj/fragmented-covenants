/**
 * Searcher.java 
 * For the Book - Fragmented Covenants
 * Author Dev B, Copyright 2015 Dev B
 * This software is distributed under the terms 
 * of the Open Source Apache v2.0 license
 * This program is distributed in the hope that it will be useful
 * The author Makes No Warranties, Express OR Implied
 * Please do not remove the copyright notice
 */
package com.devb.search;

import java.util.Map;

import com.devb.search.model.SearchResult;

public interface Searcher {

	/*
	 * @return contents
	 */
	public abstract Map<String, SearchResult> getModel();

}