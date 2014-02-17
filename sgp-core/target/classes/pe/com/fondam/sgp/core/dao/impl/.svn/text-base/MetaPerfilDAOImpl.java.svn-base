package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.MetaPerfilDAO;
import pe.com.fondam.sgp.core.domain.MetaPerfil;
/**
*
* @author Zolanch Távara
*/
@Repository
public class MetaPerfilDAOImpl  extends JpaBaseDAOImpl implements MetaPerfilDAO {

	@Autowired
	public MetaPerfilDAOImpl (@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	@Override
	public void saveMetaPerfil(MetaPerfil metaPerfil) {
		super.save(metaPerfil);
		
	}

	@Override
	public MetaPerfil updateMetaPerfil(MetaPerfil metaPerfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMetaPerfil(MetaPerfil metaPerfil) {
	super.delete(metaPerfil);
		
	}

	@Override
	public MetaPerfil findMetaPerfilById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MetaPerfil> findMetaPerfil() {
		// TODO Auto-generated method stub
		return null;
	}

}
