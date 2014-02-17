package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.PropuestaTransferenciaDAO;
import pe.com.fondam.sgp.core.domain.PropuestaTransferencia;

@Repository
public class PropuestaTransferenciaDAOImpl extends JpaBaseDAOImpl implements PropuestaTransferenciaDAO {

	//**************** inyecciones *********************//
	@Autowired
	public PropuestaTransferenciaDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	
	//**************** metodos *********************//
	@Override
	public void savePropuestaTransferencia(
			PropuestaTransferencia propuestaTransferencia) {
		super.save(propuestaTransferencia);
		
	}

	@Override
	public PropuestaTransferencia updatePropuestaTransferencia(
			PropuestaTransferencia propuestaTransferencia) {
		return super.update(propuestaTransferencia);
	}

	@Override
	public void deletePropuestaTransferencia(
			PropuestaTransferencia propuestaTransferencia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PropuestaTransferencia findPropuestaTransferenciaById(Integer id) {
		return super.findById(PropuestaTransferencia.class, id);
	}

	@Override
	public List<PropuestaTransferencia> findPropuestaTransferenciaByConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}


}
