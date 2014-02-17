package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.PropuestaTransferenciaBeneficiarioDAO;
import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBeneficiario;

@Repository
public class PropuestaTransferenciaBeneficiarioDAOImpl extends JpaBaseDAOImpl
		implements PropuestaTransferenciaBeneficiarioDAO {
	
	//**************** inyecciones *********************//
	@Autowired
	public PropuestaTransferenciaBeneficiarioDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	//**************** metodos *********************//
	@Override
	public void savePropuestaTransferenciaBeneficiario(
			PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiario) {
		super.save(propuestaTransferenciaBeneficiario);
	}

	@Override
	public PropuestaTransferenciaBeneficiario updatePropuestaTransferenciaBeneficiario(
			PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiario) {
		return super.update(propuestaTransferenciaBeneficiario);
	}

	@Override
	public void deletePropuestaTransferenciaBeneficiario(
			PropuestaTransferenciaBeneficiario propuestaTransferenciaBeneficiario) {
		super.delete(propuestaTransferenciaBeneficiario);
		}

	@Override
	public PropuestaTransferenciaBeneficiario findPropuestaTransferenciaBeneficiarioById(
			Integer id) {
		return super.findById(PropuestaTransferenciaBeneficiario.class, id);
	}

	@Override
	public List<PropuestaTransferenciaBeneficiario> findPropuestaTransferenciaBeneficiarioByConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}

}
