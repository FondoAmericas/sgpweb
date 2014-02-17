package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.InformeFinalDAO;
import pe.com.fondam.sgp.core.domain.InformeFinal;

@Repository
public class InformeFinalDAOImpl extends JpaBaseDAOImpl implements InformeFinalDAO {

	//*********** inyecciones *********************//
	@Autowired
	public InformeFinalDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	//*********** metodos *********************//
	@Override
	public void saveInformeFinal(InformeFinal informeFinal) {
		super.save(informeFinal);
		
	}

	@Override
	public InformeFinal updateInformeFinal(InformeFinal informeFinal) {
		return super.update(informeFinal);
	}

	@Override
	public void deleteInformeFinal(InformeFinal informeFinal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InformeFinal findInformeFinalById(Integer id) {
		return super.findById(InformeFinal.class, id);
	}

	@Override
	public List<InformeFinal> findInformeFinalByConsulta(String consulta, Object[] params) {
		return super.find(consulta, params);
	}

	

}
