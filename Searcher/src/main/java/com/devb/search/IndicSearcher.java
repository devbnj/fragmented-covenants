/**
 * IndicSearcher.java 
 * For the Book - Fragmented Covenants
 * Author Dev B, Copyright 2015 Dev B
 * This software is distributed under the terms 
 * of the Open Source Apache v2.0 license
 * This program is distributed in the hope that it will be useful
 * The author Makes No Warranties, Express OR Implied
 * Please do not remove the copyright notice
 */
package com.devb.search;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.hi.HindiAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.FSDirectory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.devb.search.model.SearchResult;

public class IndicSearcher implements Searcher {

	private ServletContext servletContext;

	private Map<String, SearchResult> contentProvider 
			= new HashMap<String, SearchResult>();
	public JSONArray ja = new JSONArray();
	private String id;

	public IndicSearcher(String id, ServletContext sc, Boolean json) {
		this.id = id;
		this.servletContext = sc;
		callSearch(json);
	}

	@Override
	public Map<String, SearchResult> getModel() {
		return contentProvider;
	}
	/*
	 * @return the search
	 */
	private void callSearch(boolean j) {
		System.out.println("Servlet Ctx " + servletContext.getRealPath("/"));
		String indexPath = servletContext.getRealPath("/") + "/hindex/";
		String docsPath = servletContext.getRealPath("/") + "/hdocs/";

		final File docDir = new File(docsPath);
		if (!docDir.exists() || !docDir.canRead()) {
			System.out
					.println("Document directory '"
							+ docDir.getAbsolutePath()
							+ "' does not exist or is not readable, "
							+ "please check the path\n");
			return;
		}

		IndexReader reader = null;
		IndexSearcher searcher = null;
		Analyzer analyzer = null;
		String field = "contents";

		try {
			reader = DirectoryReader
					.open(FSDirectory.open(new File(indexPath)));
			searcher = new IndexSearcher(reader);
			analyzer = new HindiAnalyzer();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		QueryParser parser = new QueryParser(field, analyzer);
		String /*ByteBuffer*/ line = null;
		Query query = null;

		try {
			// line = Charset.forName("UTF-8").encode(this.id);
			line = this.id;

			// line = line.trim();
			if (line == null) {
				return;
			}
			System.out.println("Hindi StandardSearcher / callSearch Line "+line);
			query = parser.parse(line);
			System.out.println("Hindi StandardSearcher / callSearch Hindi Query "+query);
			final int maxHits = 10;
			ScoreDoc[] hits = searcher.search(query, null, maxHits).scoreDocs;
			try {
				// Iterate through the results:
				for (int i = 0; i < hits.length; i++) {
					Document hitDoc = searcher.doc(hits[i].doc);

					if (j) {
						JSONObject jo = new JSONObject();
						jo.put("query", query.toString(field));
						jo.put("path", hitDoc.get("path"));
						jo.put("line", hitDoc.get("linenumber"));
						jo.put("contents", hitDoc.get("contents"));

						ja.put(jo);
					} else {
						SearchResult ns = new SearchResult();
						ns.setQuery(query.toString(field));
						ns.setDocPath(hitDoc.get("path"));
						ns.setLineNum(hitDoc.get("linenumber"));
						ns.setContents(hitDoc.get("contents"));
						contentProvider.put(String.valueOf(i), ns);
					}
				}
			} catch (Exception ito) {
				ito.printStackTrace();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException ioe) {

		}
	}

}
