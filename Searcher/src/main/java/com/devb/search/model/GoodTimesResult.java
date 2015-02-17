package com.devb.search.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author DevbNJ
 *
 */
@XmlRootElement
public class GoodTimesResult {
	private String ddate;
	private String unodeInausp;
	private String deathBearing;
	private String saturnInausp;
	private String directionInausp;
	
	public GoodTimesResult() {
		Calendar rn = Calendar.getInstance();
		int hh = rn.get(Calendar.HOUR_OF_DAY);
		int mt = rn.get(Calendar.MINUTE);
		int daywk = rn.get(Calendar.DAY_OF_WEEK);
		ddate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rn.getTime());
		double h1 = hh + (mt / 60);
		String rk = "x";
		String gk = "x";
		String yg = "x";
		String sl = "";
		switch (daywk) {
			case Calendar.SUNDAY: // sunday
				if (h1 >= 16.5 && h1 <= 18) rk = "Y";
				if (h1 >= 15 && h1 < 16.5) gk = "Y";
				if ((h1 >= 12 && h1 <= 13.5) || (h1 >= 18 && h1 <= 19.5)) yg = "Y";
				sl = "W";
			break;
			case Calendar.MONDAY: 
				if (h1 >= 7.5 && h1 <= 9) rk = "Y";
				if (h1 >= 13.5 && h1 < 15) gk = "Y";
				if ((h1 >= 10.5 && h1 <= 12) || (h1 >= 3 && h1 <= 4.5)) yg = "Y";
				sl = "E";
			break;
			case Calendar.TUESDAY: 
				if (h1 >= 15 && h1 <= 16.5) rk = "Y";
				if (h1 >= 12 && h1 < 13.5) gk = "Y";
				if ((h1 >= 9 && h1 <= 10.5) || (h1 >= 1.5 && h1 <= 3)) yg = "Y";
				sl = "N";
				break;
			case Calendar.WEDNESDAY: 
				if (h1 >= 12 && h1 <= 13.5) rk = "Y";
				if (h1 >= 10.5 && h1 < 12) gk = "Y";
				if ((h1 >= 7.5 && h1 <= 9) || (h1 >= 12 && h1 <= 13.5)) yg = "Y";
				sl = "N";
				break;
			case Calendar.THURSDAY: 
				if (h1 >= 13.5 && h1 <= 15) rk = "Y";
				if (h1 >= 9 && h1 < 10.5) gk = "Y";
				if ((h1 >= 6 && h1 <= 7.5) || (h1 >= 22 && h1 <= 24)) yg = "Y";
				sl = "S";
				break;
			case Calendar.FRIDAY: 
				if (h1 >= 10.5 && h1 <= 12) rk = "Y";
				if (h1 >= 7.5 && h1 < 9) gk = "Y";
				if ((h1 >= 15 && h1 <= 16.5) || (h1 >= 19.5 && h1 <= 21)) yg = "Y";
				sl = "W";
				break;
			case Calendar.SATURDAY: 
				if (h1 >= 9 && h1 <= 10.5) rk = "Y";
				if (h1 >= 6 && h1 < 7.5) gk = "Y";
				if ((h1 >= 13.5 && h1 <= 15) || (h1 >= 19.5 && h1 <= 21)) yg = "Y";
				sl = "E";
				break;
			default: break;
		}
		unodeInausp = rk;
		saturnInausp = gk;
		deathBearing = yg;
		directionInausp = sl;
	}
	
	public String getDate() {
		return ddate;
	}
	public void setDate(String ddate) {
		this.ddate = ddate;
	}
	public String getUnodeInausp() {
		return unodeInausp;
	}
	public String getDirectionInausp() {
		return directionInausp;
	}

	public void setDirectionInausp(String directionInausp) {
		this.directionInausp = directionInausp;
	}

	public void setUnodeInausp(String unodeInausp) {
		this.unodeInausp = unodeInausp;
	}
	public String getDeathBearing() {
		return deathBearing;
	}
	public void setDeathBearing(String deathBearing) {
		this.deathBearing = deathBearing;
	}
	public String getSaturnInausp() {
		return saturnInausp;
	}
	public void setSaturnInausp(String saturnInausp) {
		this.saturnInausp = saturnInausp;
	}
}
