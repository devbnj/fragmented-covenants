/**
 * StandardIndexer.java 
 * For the Book - Fragmented Covenants
 * Author Dev B, Copyright 2015 Dev B
 * This software is distributed under the terms 
 * of the Open Source Apache v2.0 license
 * This program is distributed in the hope that it will be useful
 * The author Makes No Warranties, Express OR Implied
 * Please do not remove the copyright notice
 */
package com.devb.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.FSDirectory;

public class StandardIndexer implements Indexer {
	// The servletcontext helps in determining the folder path
	// StandardIndexer will use the folder path as
	private ServletContext servletContext;

	public StandardIndexer(ServletContext sc) {
		this.servletContext = sc;
		makeIndex();
	}

	/* (non-Javadoc)
	 * @see com.devb.search.Indexer#callIndex()
	 */
	@Override
	public void makeIndex() {
		String indexPath = servletContext.getRealPath("/") + "/index/";
		String docsPath = servletContext.getRealPath("/") + "/docs/";
		boolean create = true;

		final File docDir = new File(docsPath);
		if (!docDir.exists() || !docDir.canRead()) {
			System.out
					.println("Document directory '"
							+ docDir.getAbsolutePath()
							+ "' does not exist or is not readable, please check the path\n");
			return;
		}

		Date start = new Date();
		try {
			System.out
					.println("Indexing to directory '" + indexPath + "'...\n");

			org.apache.lucene.store.Directory dir = FSDirectory.open(new File(
					indexPath));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(null, analyzer);

			if (create) {
				iwc.setOpenMode(OpenMode.CREATE);
			} else {
				iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
			}

			IndexWriter writer = new IndexWriter(dir, iwc);
			if (docDir.canRead()) {
				if (docDir.isDirectory()) {
					String[] files = docDir.list();
					if (files != null) {
						for (int i = 0; i < files.length; i++) {
							File file = new File(docDir, files[i]);
							FileReader fr = new FileReader(file);
							BufferedReader br = new BufferedReader(fr);
							String line;
							int lineNumber = 0;
							try {
								while ((line = br.readLine()) != null) {
									Document doc = new Document();
									Field pathField = new StringField("path",
											file.getName(), Field.Store.YES);
									doc.add(pathField);
									TextField nField = new TextField(
											"linenumber", new Integer(
													++lineNumber).toString(),
											Store.YES);
									doc.add(nField);
									TextField field = new TextField("contents",
											line, Store.YES);
									doc.add(field);
									writer.addDocument(doc);
								}
								System.out.println("Adding " + file + "\n");
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								br.close();
								fr.close();
							}
						}
					}
				}
			}

			writer.close();

			Date end = new Date();
			System.out.println((end.getTime() - start.getTime())
					+ " total milliseconds\n");

		} catch (IOException e) {
			System.out.println("Caught a " + e.getClass() + "\n with message: "
					+ e.getMessage());
		}

	}
}
