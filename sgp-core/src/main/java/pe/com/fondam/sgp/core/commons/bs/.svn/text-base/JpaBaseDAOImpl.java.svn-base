package pe.com.fondam.sgp.core.commons.bs;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.support.JpaDaoSupport;


public class JpaBaseDAOImpl extends JpaDaoSupport implements JpaBaseDAO {

	static Logger logger = Logger.getLogger("JpaBaseDAOImpl");

	public <T> void save(T entity) throws DataAccessException{
		super.getJpaTemplate().persist(entity);
	}

	public <T> T update(T entity) throws DataAccessException {
		return (T) super.getJpaTemplate().merge(entity);
	}

	public <T> void delete(T entity) throws DataAccessException {
		super.getJpaTemplate().remove(entity);
	}

	public <T> T findById(Class<T> entityClass, Object id)
			throws DataAccessException {
		return (T) super.getJpaTemplate().find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(String queryString) throws DataAccessException {
		return (List<T>) super.getJpaTemplate().find(queryString);
	}

	@SuppressWarnings(value = "unchecked")
	public <T> List<T> find(String queryString, Object[] params) {
		return (List<T>) super.getJpaTemplate().find(queryString, params);
	}

	@SuppressWarnings(value = "unchecked")
	public <T> T findUniqueResult(String queryString) {
		return (T) findUniqueResult(queryString, null);
	}

	@SuppressWarnings(value = "unchecked")
	public <T> T findUniqueResult(String queryString, Object[] params) {
		Query query = super.getJpaTemplate().getEntityManagerFactory()
				.createEntityManager().createQuery(queryString);
		applyParamsToQuery(query, params);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (T) query.getSingleResult();
	}

	public <T> List<T> findLimitedResult(String queryString, int start, int max) {
		return findLimitedResult(queryString, start, max, null);
	}

	@SuppressWarnings(value = "unchecked")
	public <T> List<T> findLimitedResult(String queryString, int start,
			int max, Object[] params) {
		Query query = super.getJpaTemplate().getEntityManagerFactory()
				.createEntityManager().createQuery(queryString);
		applyParamsToQuery(query, params);
		query.setFirstResult(start);
		query.setMaxResults(max);
		return (List<T>) query.getResultList();
	}

	private void applyParamsToQuery(Query query, Object params[]) {
		int i;
		if (params == null) {
			return;
		}
		if (params.length <= 0) {
			return;
		}
		for (i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
	}
}