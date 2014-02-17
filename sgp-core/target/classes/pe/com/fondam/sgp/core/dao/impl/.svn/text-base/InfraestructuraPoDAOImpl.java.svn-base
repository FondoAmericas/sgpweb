package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.InfraestructuraPoDAO;
import pe.com.fondam.sgp.core.domain.InfraestructuraPo;
/**
*
* 
*/
@Repository
public class InfraestructuraPoDAOImpl  extends JpaBaseDAOImpl implements InfraestructuraPoDAO {

	//************  inyecciones  ****************//
	@Autowired
	public InfraestructuraPoDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	
	//************  metodos ****************//
	@Override
	public void saveInfraestructuraPo(InfraestructuraPo infraestructuraPo) {
		super.save(infraestructuraPo);
		
	}

	@Override
	public InfraestructuraPo updateInfraestructuraPo(
			InfraestructuraPo infraestructuraPo) {
		return super.update(infraestructuraPo);
	}

	@Override
	public void deleteInfraestructuraPo(InfraestructuraPo infraestructuraPo) {
		super.delete(infraestructuraPo);
		
	}

	@Override
	public InfraestructuraPo findInfraestructuraPoById(Integer id) {
		return super.findById(InfraestructuraPo.class, id);
	}

	@Override
	public List<InfraestructuraPo> findInfraestructuraPo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InfraestructuraPo> findInfraestructuraPoByConsulta(String consulta,
			Object[] params) {
		return super.find(consulta, params);
	}

}
