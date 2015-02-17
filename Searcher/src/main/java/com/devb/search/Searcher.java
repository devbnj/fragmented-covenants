package com.devb.search;

import java.util.Map;

import com.devb.search.model.SearchResult;

public interface Searcher {

	/*
	 * @return contents
	 */
	public abstract Map<String, SearchResult> getModel();

}