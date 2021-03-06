package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TablaEspecificaDAO;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;


@Repository
public class TablaEspecificaDAOImpl extends JpaBaseDAOImpl implements
		TablaEspecificaDAO {

	@Autowired
	public TablaEspecificaDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public TablaEspecifica findTablaEspecificaById(Integer tablaEspecificaID) {
		return super.findById(TablaEspecifica.class, tablaEspecificaID);
	}
	
	@Override
	public List<TablaEspecifica> findTablaEspecificasByTablaGeneralId(
			Integer tablaGeneralID) {
		String queryString = "from TablaEspecifica where estadoEliminado = 1 and tablaGeneral.tablaGeneralID = ?";
		Object[] params = new Object[1];
		params[0] = tablaGeneralID;
		return super.find(queryString, params);
	}

	@Override
	public List<TablaEspecifica> findTablaEspecificaByConsulta(String consulta,
			Object[] params) {
		return super.find(consulta, params);
	}
	
	@Override
	public List<TablaEspecifica> findTablaEspecificaByConsultaSinParametros(String consulta) {
		return super.find(consulta);
	}

}
