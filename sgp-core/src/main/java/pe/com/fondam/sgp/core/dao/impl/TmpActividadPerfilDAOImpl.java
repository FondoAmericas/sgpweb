package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TmpActividadPerfilDAO;
import pe.com.fondam.sgp.core.domain.ActividadPerfil;
import pe.com.fondam.sgp.core.domain.TmpActividadPerfil;

@Repository
public class TmpActividadPerfilDAOImpl extends JpaBaseDAOImpl implements TmpActividadPerfilDAO{

	@Autowired
	public TmpActividadPerfilDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	public void saveTmpActividadPerfil(TmpActividadPerfil tmpActividadPerfil) {
		super.save(tmpActividadPerfil);

	}

	public ActividadPerfil updateTmpActividadPerfil(TmpActividadPerfil tmpActividadPerfil) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void deleteTmpActividadPerfil(TmpActividadPerfil tmpActividadPerfil) {
		super.delete(tmpActividadPerfil);
	}


	public TmpActividadPerfil findTmpActividadPerfilById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<TmpActividadPerfil> findTmpActividadPerfil() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TmpActividadPerfil> findTmpActividadPerfilByTmpPerfilID(Integer tmpPerfilID) {
		String queryString1 = "from TmpActividadPerfil where tMPPerfil.tMPPerfilID= ? ";
		Object[] params1 = new Object[1];
		params1[0] = tmpPerfilID;

		return super.find(queryString1, params1);
	}

	@Override
	public List<TmpActividadPerfil> findTmpActividadPerfilByPerfilID(Integer tmpPerfilID) {
		String queryString1 = "from TmpActividadPerfil where tMPPerfil.tMPPerfilID= ? ";
		Object[] params1 = new Object[1];
		params1[0] = tmpPerfilID;

		return super.find(queryString1, params1);
	}

}
