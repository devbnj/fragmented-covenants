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
