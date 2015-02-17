/**
 * SearchResult.java 
 * For the Book - Fragmented Covenants
 * Author Dev B, Copyright 2015 Dev B
 * This software is distributed under the terms 
 * of the Open Source Apache v2.0 license
 * This program is distributed in the hope that it will be useful
 * The author Makes No Warranties, Express OR Implied
 * Please do not remove the copyright notice
 */
package com.devb.search.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author DevbNJ
 *
 */
@XmlRootElement
public class SearchResult {
	private String query;
	private String docPath;
	private String lineNum;
	private String contents;
	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}
	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	/**
	 * @return the docPath
	 */
	public String getDocPath() {
		return docPath;
	}
	/**
	 * @param docPath the docPath to set
	 */
	public void setDocPath(String docPath) {
		this.docPath = docPath; 
	}
	/**
	 * @return the lineNum
	 */
	public String getLineNum() {
		return lineNum;
	}
	/**
	 * @param lineNum the lineNum to set
	 */
	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}
	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
}
