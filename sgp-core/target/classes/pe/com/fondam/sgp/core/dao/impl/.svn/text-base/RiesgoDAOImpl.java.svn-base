package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.RiesgoDAO;
import pe.com.fondam.sgp.core.domain.Riesgo;
/**
*
* @author Zolanch Távara
*/
@Repository
public class RiesgoDAOImpl extends JpaBaseDAOImpl implements RiesgoDAO {

	@Autowired
	public RiesgoDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}


	@Override
	public void saveRiesgo(Riesgo riesgo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Riesgo> findRiesgoByActividadID(int id) {

		String queryString = "from Riesgo where actividad.actividadID = ? ";
		Object[] params = new Object[1];
		params[0] = id;
		return super.find(queryString, params);
	}

}
