package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.ApreciacionResultadoDAO;
import pe.com.fondam.sgp.core.domain.ApreciacionResultado;


@Repository
public class ApreciacionResultadoDAOImpl extends JpaBaseDAOImpl implements
		ApreciacionResultadoDAO {

	/********************** inyecciones ****************************/
	@Autowired
	public ApreciacionResultadoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	/********************** metodos ****************************/
	@Override
	public void saveApreciacionResultado(
			ApreciacionResultado apreciacionResultado) {

		super.save(apreciacionResultado);
	}

	@Override
	public ApreciacionResultado updateApreciacionResultado(
			ApreciacionResultado apreciacionResultado) {

		return super.update(apreciacionResultado);
	}

	@Override
	public void deleteActivo(ApreciacionResultado apreciacionResultado) {
		// TODO Auto-generated method stub

	}

	@Override
	public ApreciacionResultado findApreciacionResultadoById(Integer id) {
		return super.findById(ApreciacionResultado.class, id);
	}

	@Override
	public List<ApreciacionResultado> findApreciacionResultados(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}

	@Override
	public void deleteApreciacionResultado(
			ApreciacionResultado apreciacionResultado) {
		super.delete(apreciacionResultado);
		
	}

}
