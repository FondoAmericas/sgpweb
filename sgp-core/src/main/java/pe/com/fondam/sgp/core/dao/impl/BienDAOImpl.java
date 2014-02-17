package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.BienDAO;
import pe.com.fondam.sgp.core.domain.Bien;

@Repository
public class BienDAOImpl extends JpaBaseDAOImpl implements BienDAO {

	//*********** inyecciones *************//
	@Autowired
	public BienDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	
	
	//*********** metodos  *************//
	@Override
	public void saveBien(Bien bien) {
		super.save(bien);
	}

	@Override
	public Bien updateBien(Bien bien) {
		return super.update(bien);
	}

	@Override
	public void deleteBien(Bien bien) {
		super.delete(bien);
		
	}

	@Override
	public Bien findBienById(Integer id) {
		return super.findById(Bien.class, id);
	}

	@Override
	public List<Bien> findBienByConsulta(String consulta, Object[] params) {
		return super.find(consulta, params);
	}

}
