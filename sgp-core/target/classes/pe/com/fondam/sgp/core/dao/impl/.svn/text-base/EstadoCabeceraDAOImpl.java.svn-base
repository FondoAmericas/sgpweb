package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.EstadoCabeceraDAO;
import pe.com.fondam.sgp.core.domain.EstadoCabecera;

/**
*
* 
*/
@Repository
public class EstadoCabeceraDAOImpl extends JpaBaseDAOImpl implements
		EstadoCabeceraDAO {

	// *********** inyecciones ***********//
	@Autowired
	public EstadoCabeceraDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	// *********** metodos ***********//
	@Override
	public int findPrefijoEstadoCabeceraByID(String prefijo) {
		String queryString = "from EstadoCabecera where prefijoEstado = ?";
		Object[] params = new Object[1];
		params[0] = prefijo;
		List<EstadoCabecera> lista = super.find(queryString, params);

		return lista.get(0).getEstadoCabeceraID();
	}

	@Override
	public void saveEstadoCabecera(EstadoCabecera estadoCabecera) {
		// TODO Auto-generated method stub

	}

	@Override
	public EstadoCabecera findEstadoCabeceraById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EstadoCabecera findEstadoCabeceraByPrefijo(String prefijo) {
		String queryString = "from EstadoCabecera where prefijoEstado = ?";
		Object[] params = new Object[1];
		params[0] = prefijo;
		List<EstadoCabecera> lista = super.find(queryString, params);

		return lista.get(0);
	}
}
