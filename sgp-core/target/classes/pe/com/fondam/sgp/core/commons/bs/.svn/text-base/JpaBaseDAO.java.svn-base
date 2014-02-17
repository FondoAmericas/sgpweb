package pe.com.fondam.sgp.core.commons.bs;

import java.util.List;

public interface JpaBaseDAO {

	<T> void save(T entity);

	<T> T update(T entity);

	<T> void delete(T entity);

	<T> T findById(Class<T> entityClass, Object id);

	<T> List<T> find(String queryString);

	<T> List<T> find(String queryString, Object[] params);

	<T> T findUniqueResult(String queryString);

	<T> T findUniqueResult(String queryString, Object params[]);

	<T> List<T> findLimitedResult(String queryString, int start, int max);

	<T> List<T> findLimitedResult(String queryString, int start, int max,
			Object params[]);

}