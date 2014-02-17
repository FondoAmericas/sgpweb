package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TablaClaseDAO;
import pe.com.fondam.sgp.core.domain.TablaClase;

@Repository
public class TablaClaseDAOImpl extends JpaBaseDAOImpl implements TablaClaseDAO {

//*********** inyecciones **********************//
	@Autowired
	public TablaClaseDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	//*********** metodos **********************//
	@Override
	public TablaClase findByClaseNombre(String claseNombre) {
		TablaClase tablaClase = null;

		String queryString = "from TablaClase where claseNombre = ?";
		Object[] params = new Object[1];
		params[0] = claseNombre;

		List<TablaClase> listTablaClase = super.find(queryString, params);
		if (!listTablaClase.isEmpty()) {
			tablaClase = listTablaClase.get(0);
		}
		return tablaClase;
	}

	@Override
	public TablaClase findTablaClaseById(Integer tablaClaseId) {
		return super.findById(TablaClase.class, tablaClaseId);
	}

	@Override
	public List<TablaClase> findTablaClase() {
		return super.find("from TablaClase ");
	}

	@Override
	public List<TablaClase> findByConsulta(String consulta, Object[] params) {
		
		return super.find(consulta, params);
	}
}
