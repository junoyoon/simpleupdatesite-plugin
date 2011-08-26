/*
 * Copyright 2010 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY. Use is subject to license terms.
 */
package hudson.plugins.simpleupdatesite;

/**
 * Constant
 * 
 * @author JunHo Yoon
 */
public class Constant {
	public static final long MINUTE = 1000 * 60;
	public static final long HOUR = Constant.MINUTE * 60;
	public static final long DATA_RETREIVER_PERIOD = 3 * Constant.HOUR;
	public static final int HTTP_TIMEOUT = 5000;
	public static final String SIMPLE_UPDATESITE_ID = "simpleupdatesite";
	public static final long UPDATESITE_REFRESH_RATE = Constant.HOUR * 3;
}
