/**
 * TestData.java 
 * For the Book - Fragmented Covenants
 * Author Dev B, Copyright 2015 Dev B
 * This software is distributed under the terms 
 * of the Open Source Apache v2.0 license
 * This program is distributed in the hope that it will be useful
 * The author Makes No Warranties, Express OR Implied
 * Please do not remove the copyright notice
 */
package com.devb.search;

import java.util.HashMap;
import java.util.Map;

import com.devb.search.model.SearchResult;

/**
 * @author DevbNJ
 *
 */
public class TestData {

	private Map<String, SearchResult> contentProvider = new HashMap<String, SearchResult>();

	public TestData() {
		SearchResult ns = new SearchResult();
		ns.setQuery("sun AND moon : demo only");
		ns.setDocPath("docs/Bhrigu-Sutras.txt");
		ns.setLineNum("13");
		ns.setContents("For all living beings Sun represents the positive "
				+ "and primal front whereas the Moon represents the negative influence. "
				+ "Sun is constructive and creative, Moon is preserving and formative. "
				+ "Sun is the father, Moon is the mother. "
				+ "The Sun and Moon are considered sovereigns. "
				+ "Jupiter and Venus are ministers. Mercury is the young prince. "
				+ "Mars is the commander-in-chief and Saturn is the servant. "
				+ "Sun is the king and the Moon is the queen. "
				+ "Sun and Moon are also considered luminaries.");
		contentProvider.put("1", ns);

		ns = new SearchResult();
		ns.setQuery("sun AND moon : demo only");
		ns.setDocPath("docs/Brihat-Jataka.txt");
		ns.setLineNum("78");
		ns.setContents("Stanza 6: If Jupiter does not aspect birth and Moon, "
				+ "or if he does not aspect the Sun, in conjunction with the Moon, "
				+ "if the Moon with a malefic combines with the Sun "
				+ "then say certainly the child is born to another person "
				+ "or of adultery.");
		contentProvider.put("2", ns);

	}

	/*
	 * @return contents
	 */
	public Map<String, SearchResult> getModel() {
		return contentProvider;
	}

}
