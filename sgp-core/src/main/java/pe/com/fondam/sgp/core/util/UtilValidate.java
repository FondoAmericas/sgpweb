package pe.com.fondam.sgp.core.util;

import java.util.Collection;
import java.util.Map;

public class UtilValidate {

	// private static Logger logger = Logger.getLogger(UtilValidate.class);
	/**
	 * boolean specifying by default whether or not it is okay for a String to
	 * be empty
	 */
	public static final boolean defaultEmptyOK = true;

	public static boolean areEqual(Object obj, Object obj2) {
		if (obj == null) {
			return obj2 == null;
		} else {
			return obj.equals(obj2);
		}
	}

	/**
	 * Check whether an object is empty, will see if it is a String, Map,
	 * Collection, etc.
	 */
	public static boolean isEmpty(Object o) {
		return isObjectEmpty(o);
	}

	/**
	 * Check whether an object is NOT empty, will see if it is a String, Map,
	 * Collection, etc.
	 */
	public static boolean isNotEmpty(Object o) {
		return !isObjectEmpty(o);
	}

	/** Check whether string s is empty. */
	public static boolean isEmpty(String s) {
		return ((s == null) || (s.length() == 0));
	}

	/** Check whether collection c is empty. */
	public static <E> boolean isEmpty(Collection<E> c) {
		return ((c == null) || (c.size() == 0));
	}

	/** Check whether map m is empty. */
	public static <K, E> boolean isEmpty(Map<K, E> m) {
		return ((m == null) || (m.size() == 0));
	}

	/** Check whether charsequence c is empty. */
	public static <E> boolean isEmpty(CharSequence c) {
		return ((c == null) || (c.length() == 0));
	}

	/** Check whether string s is NOT empty. */
	public static boolean isNotEmpty(String s) {
		return ((s != null) && (s.length() > 0));
	}

	/** Check whether collection c is NOT empty. */
	public static <E> boolean isNotEmpty(Collection<E> c) {
		return ((c != null) && (c.size() > 0));
	}

	/** Check whether charsequence c is NOT empty. */
	public static <E> boolean isNotEmpty(CharSequence c) {
		return ((c != null) && (c.length() > 0));
	}

	@SuppressWarnings("unchecked")
	private static boolean isObjectEmpty(Object value) {
		if (value == null) {
			return true;
		}
		if (value instanceof String) {
			return UtilValidate.isEmpty((String) value);
		}
		if (value instanceof Collection) {
			return UtilValidate.isEmpty((Collection<? extends Object>) value);
		}
		if (value instanceof Map) {
			return UtilValidate
					.isEmpty((Map<? extends Object, ? extends Object>) value);
		}
		if (value instanceof CharSequence) {
			return UtilValidate.isEmpty((CharSequence) value);
		}
		// These types would flood the log
		// Number covers: BigDecimal, BigInteger, Byte, Double, Float, Integer,
		// Long, Short
		if (value instanceof Boolean) {
			return false;
		}
		if (value instanceof Number) {
			return false;
		}
		if (value instanceof Character) {
			return false;
		}
		if (value instanceof java.sql.Timestamp) {
			return false;
		}
		return false;
	}

	public static boolean isString(Object obj) {
		return ((obj != null) && (obj instanceof java.lang.String));
	}

	/** Returns true if character c is a digit (0 .. 9). */
	public static boolean isDigit(char c) {
		return Character.isDigit(c);
	}

	public static boolean isInteger(String s) {
		if (isEmpty(s))
			return defaultEmptyOK;
		// Search through string's characters one by one
		// until we find a non-numeric character.
		// When we do, return false; if we don't, return true.
		for (int i = 0; i < s.length(); i++) {
			// Check that current character is number.
			char c = s.charAt(i);

			if (!isDigit(c)) {
				return false;
			}
		}
		// All characters are numbers.
		return true;
	}

	public static boolean isLong(String s) {
		if (isEmpty(s))
			return defaultEmptyOK;
		try {
			Long.parseLong(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
