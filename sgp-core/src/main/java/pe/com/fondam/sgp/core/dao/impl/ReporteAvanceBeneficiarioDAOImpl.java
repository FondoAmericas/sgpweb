package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.ReporteAvanceBeneficiarioDAO;
import pe.com.fondam.sgp.core.domain.ReporteAvanceBeneficiario;

@Repository
public class ReporteAvanceBeneficiarioDAOImpl extends JpaBaseDAOImpl implements
		ReporteAvanceBeneficiarioDAO {
	
	/************************* inyecciones ********************************/
	@Autowired
	public ReporteAvanceBeneficiarioDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

		
	/************************* metodos ********************************/
	@Override
	public List<ReporteAvanceBeneficiario> findReporteAvanceBeneficiarioXBeneficiariosPorResultadoId(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}


	@Override
	public void saveReporteAvanceBeneficiario(
			ReporteAvanceBeneficiario reporteAvanceBeneficiario) {
		super.save(reporteAvanceBeneficiario);
	}


	@Override
	public List<ReporteAvanceBeneficiario> findReporteAvanceBeneficiarioXConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}


	@Override
	public ReporteAvanceBeneficiario updateReporteAvanceBeneficiario(
			ReporteAvanceBeneficiario reporteAvanceBeneficiario) {
		return super.update(reporteAvanceBeneficiario);
		
	}
	
	public List<ReporteAvanceBeneficiario> findReporteAvanceBeneficiarioByBeneficiariosPorResultadoId(Integer beneficiariosPorResultadoID) {
		String queryString = "from ReporteAvanceBeneficiario where beneficiariosPorResultado.beneficiariosPorResultadoID = ? ";
		Object[] params = new Object[1];
		params[0] = beneficiariosPorResultadoID;
		return super.find(queryString, params);
	}


	@Override
	public ReporteAvanceBeneficiario findReporteAvanceBeneficiarioById(
			Integer reporteAvanceBeneficiarioID) {
		
		return super.findById(ReporteAvanceBeneficiario.class, reporteAvanceBeneficiarioID);
	}


	@Override
	public void deleteReporteAvanceBeneficiario(
			ReporteAvanceBeneficiario reporteAvanceBeneficiario) {
		super.delete(reporteAvanceBeneficiario);
		
	}
	
	
	
	
	
}
