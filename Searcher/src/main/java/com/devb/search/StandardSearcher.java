package com.devb.search;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.lucene.analysis.Analyzer;
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

/**
 * @author DevbNJ
 *
 */
public class StandardSearcher implements Searcher {
	private ServletContext servletContext;

	private Map<String, SearchResult> contents 
			= new HashMap<String, SearchResult>();
	public JSONArray ja = new JSONArray();
	private String id;

	public StandardSearcher(String id, ServletContext sc, Boolean json) {
		this.id = id;
		this.servletContext = sc;
		callSearch(json);
	}

	/*
	 * @return contents
	 */
	/* (non-Javadoc)
	 * @see com.devb.search.Searcher#getModel()
	 */
	@Override
	public Map<String, SearchResult> getModel() {
		return contents;
	}

	/*
	 * @return the search
	 */
	private void callSearch(boolean j) {
		System.out.println("Servlet Ctx " + servletContext.getRealPath("/"));
		String indexPath = servletContext.getRealPath("/") + "/index/";
		String docsPath = servletContext.getRealPath("/") + "/docs/";

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
			analyzer = new StandardAnalyzer();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		QueryParser parser = new QueryParser(field, analyzer);
		String line = null;
		Query query = null;

		try {
			line = this.id;

			line = line.trim();
			if (line.length() == 0) {
				return;
			}
			query = parser.parse(line);
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
						contents.put(String.valueOf(i), ns);
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
