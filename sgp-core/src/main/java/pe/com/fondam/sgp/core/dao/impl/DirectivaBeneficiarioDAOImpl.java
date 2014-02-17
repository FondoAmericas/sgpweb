package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Controller;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.DirectivaBeneficiarioDAO;
import pe.com.fondam.sgp.core.domain.DirectivaBeneficiario;

@Controller
public class DirectivaBeneficiarioDAOImpl extends JpaBaseDAOImpl implements DirectivaBeneficiarioDAO {

	//************ inyecciones *************//
	@Autowired
	public DirectivaBeneficiarioDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	//************ metodos *************//
	@Override
	public void saveDirectivaBeneficiario(
			DirectivaBeneficiario directivaBeneficiario) {

		super.save(directivaBeneficiario);
	}

	@Override
	public DirectivaBeneficiario updateDirectivaBeneficiario(
			DirectivaBeneficiario directivaBeneficiario) {
		return super.update(directivaBeneficiario);
	}

	@Override
	public void deleteDirectivaBeneficiario(
			DirectivaBeneficiario directivaBeneficiario) {
		super.delete(directivaBeneficiario);
		
	}

	@Override
	public DirectivaBeneficiario findDirectivaBeneficiarioById(Integer id) {
		return super.findById(DirectivaBeneficiario.class, id);
	}

	@Override
	public List<DirectivaBeneficiario> findDirectivaBeneficiarioByConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}

}
