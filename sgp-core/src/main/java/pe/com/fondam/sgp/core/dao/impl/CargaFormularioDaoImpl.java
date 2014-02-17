package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.CargaFormularioDao;
import pe.com.fondam.sgp.core.domain.CargaFormulario;

@Repository
public class CargaFormularioDaoImpl extends JpaBaseDAOImpl implements
		CargaFormularioDao {

	//*********** inyecciones *************//
	@Autowired
	public CargaFormularioDaoImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	
	
	//*********** metodos  *************//
	@Override
	public void saveCargaFormulario(CargaFormulario cargaFormulario) {
		super.save(cargaFormulario);
	}

	@Override
	public CargaFormulario updateCargaFormulario(CargaFormulario cargaFormulario) {
		return super.update(cargaFormulario);
	}
	
	@Override
	public List<CargaFormulario> findFormularios() {
		return super.find("from CargaFormulario");
	}



	@Override
	public CargaFormulario findById(Integer formularioId) {
		return super.findById(CargaFormulario.class, formularioId);
	}


}
