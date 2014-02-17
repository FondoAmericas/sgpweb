package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TmpMetaPerfilDAO;
import pe.com.fondam.sgp.core.domain.TmpMetaPerfil;

@Repository
public class TmpMetaPerfilDAOImpl extends JpaBaseDAOImpl implements TmpMetaPerfilDAO{

	@Autowired
	public TmpMetaPerfilDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	public void saveTmpMetaPerfil(TmpMetaPerfil tmpMetaPerfil) {
		super.save(tmpMetaPerfil);

	}

	public TmpMetaPerfil updateTmpMetaPerfil(TmpMetaPerfil tmpMetaPerfil) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void deleteTmpMetaPerfil(TmpMetaPerfil tmpMetaPerfil) {
		super.delete(tmpMetaPerfil);

	}


	public TmpMetaPerfil findTmpMetaPerfilById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<TmpMetaPerfil> findTmpMetaPerfil() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TmpMetaPerfil> findTmpMetaPerfilByTmpActividadPerfilID(Integer tmpActividadPerfilID) {
		String queryString1 = "from TmpMetaPerfil where tMPActividadPerfil.tMPActividadPerfilID= ? ";
		Object[] params1 = new Object[1];
		params1[0] = tmpActividadPerfilID;

		return super.find(queryString1, params1);
	}
}
