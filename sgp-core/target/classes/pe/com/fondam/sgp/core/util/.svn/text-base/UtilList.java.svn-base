package pe.com.fondam.sgp.core.util;

import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ReverseComparator;

public class UtilList {

	@SuppressWarnings("unchecked")
	public static List<?> orderAscList(List<?> collections,
			String orderByAttribute) {
		BeanComparator beanComparator = new BeanComparator(orderByAttribute);
		Collections.sort(collections, beanComparator);
		return collections;
	}

	@SuppressWarnings("unchecked")
	public static List<?> orderDescList(List<?> collections,
			String orderByAttribute) {
		BeanComparator beanComparator = new BeanComparator(orderByAttribute,
				new ReverseComparator(new ComparableComparator()));
		Collections.sort(collections, beanComparator);
		return collections;
	}
}
