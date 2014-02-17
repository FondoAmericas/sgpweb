package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.DescripcionEfDAO;
import pe.com.fondam.sgp.core.domain.DescripcionEf;

@Repository
public class DescripcionEfDAOImpl extends JpaBaseDAOImpl implements DescripcionEfDAO {

	//*********** inyecciones *******************//
	@Autowired
	public DescripcionEfDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	//*********** metodos *******************//
	@Override
	public void saveDescripcionEf(DescripcionEf descripcionEf) {
		super.save(descripcionEf);
		
	}

	@Override
	public DescripcionEf updateDescripcionEf(DescripcionEf descripcionEf) {
		return super.update(descripcionEf);
	}

	@Override
	public void deleteDescripcionEf(DescripcionEf descripcionEf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DescripcionEf findDescripcionEfById(Integer id) {
		return super.findById(DescripcionEf.class, id);
	}

	@Override
	public List<DescripcionEf> findDescripcionEfByConsulta(String consulta,
			Object[] params) {
		return super.find(consulta, params);
	}

}
