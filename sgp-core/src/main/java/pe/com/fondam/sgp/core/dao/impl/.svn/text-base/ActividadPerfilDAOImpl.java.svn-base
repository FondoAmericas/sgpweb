package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.ActividadPerfilDAO;
import pe.com.fondam.sgp.core.domain.ActividadPerfil;

@Repository
public class ActividadPerfilDAOImpl extends JpaBaseDAOImpl implements
		ActividadPerfilDAO {

	//**************** inyecciones ****************//
	@Autowired
	public ActividadPerfilDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	//**************metodos ********//
	
	@Override
	public void saveActividadPerfil(ActividadPerfil actividadPerfil) {
		super.save(actividadPerfil);

	}

	@Override
	public ActividadPerfil updateActividadPerfil(ActividadPerfil actividadPerfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteActividadPerfil(ActividadPerfil actividadPerfil) {
		// TODO Auto-generated method stub

	}

	@Override
	public ActividadPerfil findActividadPerfilById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActividadPerfil> findActividadPerfil() {
		// TODO Auto-generated method stub
		return null;
	}

}
