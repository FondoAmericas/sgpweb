package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.DatoUsuarioDAO;
import pe.com.fondam.sgp.core.domain.DatoUsuario;

/**
 * 
 * @author Zolanch Tavara
 */
@Repository
public class DatoUsuarioDAOImpl extends JpaBaseDAOImpl implements
		DatoUsuarioDAO {
	@Autowired
	public DatoUsuarioDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveDatoUsuario(DatoUsuario datoUsuario) {
		super.save(datoUsuario);

	}

	@Override
	public DatoUsuario updateDatoUsuario(DatoUsuario datoUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDatoUsuario(DatoUsuario datoUsuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public DatoUsuario findDatoUsuarioById(Integer id) {
		return super.findById(DatoUsuario.class, id);
	}

	@Override
	public List<DatoUsuario> findDatoUsuarios() {
		return super.find("from DatoUsuario ");
	}

}
