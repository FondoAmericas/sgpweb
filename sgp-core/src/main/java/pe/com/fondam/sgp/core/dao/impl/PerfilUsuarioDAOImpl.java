package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.PerfilUsuarioDAO;
import pe.com.fondam.sgp.core.domain.PerfilUsuario;

@Repository
public class PerfilUsuarioDAOImpl extends JpaBaseDAOImpl implements
		PerfilUsuarioDAO {

	@Autowired
	public PerfilUsuarioDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void savePerfilUsuario(PerfilUsuario perfilUsuario) {
		this.save(perfilUsuario);

	}

	@Override
	public PerfilUsuario updatePerfilUsuario(PerfilUsuario perfilUsuario) {
		return super.update(perfilUsuario);
	}

	@Override
	public void deletePerfilUsuario(PerfilUsuario perfilUsuario) {
		super.delete(perfilUsuario);

	}

	@Override
	public PerfilUsuario findPerfilUsuarioById(Integer id) {
		return super.findById(PerfilUsuario.class, id);
	}

	@Override
	public List<PerfilUsuario> findPerfilUsuario() {
		return super.find("from PerfilUsuario");
	}

	@Override
	public List<PerfilUsuario> listPerfilUsuariobyDMEbyDAFIandDT() {
		
		return super.find("from PerfilUsuario where perfilUsuarioID in (1,2,3)");
	}

	
}

