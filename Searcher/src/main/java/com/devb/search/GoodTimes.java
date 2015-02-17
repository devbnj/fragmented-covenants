/**
 * GoodTimes.java 
 * For the Book - Fragmented Covenants
 * Author Dev B, Copyright 2015 Dev B
 * This software is distributed under the terms 
 * of the Open Source Apache v2.0 license
 * This program is distributed in the hope that it will be useful
 * The author Makes No Warranties, Express OR Implied
 */

package com.devb.search;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.devb.search.model.GoodTimesResult;

/**
 * @author DevbNJ
 *
 */
public class GoodTimes {

	private Map<String, GoodTimesResult> contents = new HashMap<String, GoodTimesResult>();
	public JSONObject jo = new JSONObject();

	public GoodTimes(boolean json) {
		findGoodTimes(json);
	}

	private void findGoodTimes(boolean j) {
		try {
			GoodTimesResult gtr = new GoodTimesResult();
			if (j) {
				jo.put("ddate", gtr.getDate());
				jo.put("unodeInausp", gtr.getUnodeInausp());
				jo.put("deathBearing", gtr.getDeathBearing());
				jo.put("saturnInausp", gtr.getSaturnInausp());
				jo.put("directionInausp", gtr.getDirectionInausp());
			} else
				contents.put(String.valueOf(0), gtr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Map<String, GoodTimesResult> getContents() {
		return contents;
	}
}
