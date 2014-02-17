package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TmpPerfilDAO;
import pe.com.fondam.sgp.core.domain.TmpPerfil;

@Repository
public class TmpPerfilDAOImpl extends JpaBaseDAOImpl implements TmpPerfilDAO{

	@Autowired
	public TmpPerfilDAOImpl(	@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	public void saveTmpPerfil(TmpPerfil tmpPerfil) {
		
		super.save(tmpPerfil);

	}

	public TmpPerfil updateTmpPerfil(TmpPerfil tmpPerfil) {
		
		return super.update(tmpPerfil);
	
	}

	
	public void deleteTmpPerfil(TmpPerfil tmpPerfil) {
		super.delete(tmpPerfil);

	}


	public TmpPerfil findTmpPerfilById(Integer id) {
		
		return super.findById(TmpPerfil.class, id);
	}

	
	public List<TmpPerfil> findTmpPerfil() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TmpPerfil findTmpPerfilByTmpDatoProyectoID(Integer idProyecto) {
		String queryString = "from TmpPerfil where tMPDatoProyecto.tMPDatoProyectoID= ?";
		Object[] params = new Object[1];
		params[0] = idProyecto;
		
		List<TmpPerfil> listPerfil= super.find(queryString,params);
	if (listPerfil!=null && listPerfil.size()!=0) {
		return listPerfil.get(0);
	}
		return null;
	}
}
