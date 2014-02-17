package pe.com.fondam.sgp.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.com.fondam.sgp.core.constants.FondamConstans;

public class CommonUtilities {

	protected final static Log logger = LogFactory.getLog(CommonUtilities.class);
	
	public static boolean isNotNullOrBlank(Object obj) {
		if (obj != null && obj.toString().trim().length() > 0) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrBlank(Object obj) {
		return !isNotNullOrBlank(obj);
	}
	
	public static String convertNumberToString(Number number) {
		if (number == null) {
			return (String) null;
		}
		return number.toString();
	}
	
	public static Date getDateFormat(String strDate, int inFormat) {
		String strFormat = null;
		if (!isNotNullOrBlank(strDate))
			return null;
		switch (inFormat) {
		case FondamConstans.DATE_FORMAT_DD_MM_YYYY:
			strFormat = FondamConstans.DATEFORMAT1;
			break;
		case FondamConstans.DATE_FORMAT_YYYY_MM_DD:
			strFormat = FondamConstans.DATEFORMAT2;
			break;
		}
		DateFormat formatter = new SimpleDateFormat(strFormat);
		Date dtResult = null;
		try {
			dtResult = (Date) formatter.parse(strDate);
		} catch (Exception e) {
			logger.error("getDateFormat", e);
			dtResult = null;
		}

		return dtResult;
	}
	

	public static String convertDatetoString(Date dtDateVal, int inFormat) {
		String strFormat = null;
		String strTempDate = FondamConstans.EMPTY_STRING;
		try {
			switch (inFormat) {
			case FondamConstans.DATE_FORMAT_DD_MM_YYYY:
				strFormat = FondamConstans.DATEFORMAT1;
				break;
			case FondamConstans.DATE_FORMAT_YYYY_MM_DD:
				strFormat = FondamConstans.DATEFORMAT2;
				break;
			
			case FondamConstans.DATE_FORMAT_DD_MM_YYYY_:
				strFormat = FondamConstans.DATEFORMAT3;
				break;
												
			case FondamConstans.DATE_FORMAT_YYYY_MM_DD_:
				strFormat = FondamConstans.DATEFORMAT4;
				break;
				
			}
			SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
			if (dtDateVal != null)
				strTempDate = sdf.format(dtDateVal);
		} catch (Exception e) {
			logger.error("convertDatetoString", e);
		}
		return strTempDate;
	}
	
	public static String toString(Object obj) {
		if (obj != null) {
			return obj.toString();
		}
		return FondamConstans.EMPTY_STRING;
	}
	
	public static double toDouble(Object obj) {
		double returnValue = 0;
		if (obj != null) {
			try {
				returnValue = Double.parseDouble(obj.toString());
			} catch (Exception e) {
				return returnValue;
			}
		}
		return returnValue;
	}
	
	public static int toInt(Object obj) {
		int returnValue = 0;
		if (obj != null) {
			try {
				returnValue = Integer.parseInt(obj.toString());
			} catch (Exception e) {
				return returnValue;
			}
		}
		return returnValue;
	}
	
	public static Date convertStringtoDate(String strTempDate) {
		SimpleDateFormat objSDF = new SimpleDateFormat(FondamConstans.DATEFORMAT1);
		Date dtNewDate = null;
		try {
			if (strTempDate != null && strTempDate.trim().length() > 0)
				dtNewDate = objSDF.parse(strTempDate);
		} catch (Exception e) {
			logger.error("convertStringtoDate", e);
		}

		return dtNewDate;

	}
	
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}
}
