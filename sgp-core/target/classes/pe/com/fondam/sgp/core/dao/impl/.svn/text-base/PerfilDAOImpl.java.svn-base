package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.PerfilDAO;
import pe.com.fondam.sgp.core.domain.Perfil;

/**
 * 
 * @author Zolanch Távara
 */
@Repository
public class PerfilDAOImpl extends JpaBaseDAOImpl implements PerfilDAO {

	@Autowired
	public PerfilDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void savePerfil(Perfil perfil) {
		super.save(perfil);

	}

	@Override
	public Perfil updatePerfil(Perfil perfil) {
		
		return super.update(perfil);
	}

	@Override
	public void deletePerfil(Perfil perfil) {
		super.delete(perfil);

	}

	@Override
	public Perfil findPerfilById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Perfil> findPerfil() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Perfil findPerfilByDatoProyectoID(int proyectoID) {
		
		String queryString = "from Perfil where datoProyecto.datoProyectoID=?" ;
		Object[] params = new Object[1];
		params[0] =proyectoID;
		List<Perfil> lista=super.find(queryString, params);
		if (lista!=null) {
			return lista.get(0);
		}
		
		
		return null;
	}

}
