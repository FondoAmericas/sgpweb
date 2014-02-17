package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.InformeFinalBienDAO;
import pe.com.fondam.sgp.core.domain.InformeFinalBien;

@Repository
public class InformeFinalBienDAOImpl extends JpaBaseDAOImpl implements
		InformeFinalBienDAO {

	//******************* inyecciones **********************//
	@Autowired
	public InformeFinalBienDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	
	//******************* metodos **********************//
	@Override
	public List<InformeFinalBien> findInformeFinalBienByConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}


	@Override
	public InformeFinalBien updateInformeFinalBien(
			InformeFinalBien informeFinalBienSave) {

		return super.update(informeFinalBienSave);
	}


	@Override
	public void deleteInformeFinalBien(InformeFinalBien informeFinalBien) {
		super.delete(informeFinalBien);
		
	}
	
	
	
}
