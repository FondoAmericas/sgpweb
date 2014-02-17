package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.ReporteAvanceBeneficiarioDAO;
import pe.com.fondam.sgp.core.domain.ReporteAvanceBeneficiario;
import pe.com.fondam.sgp.core.service.ReporteAvanceBeneficiarioService;

@Service
public class ReporteAvanceBeneficiarioServiceImpl implements
		ReporteAvanceBeneficiarioService {

	/****************** inyecciones ************************/
	@Resource
	ReporteAvanceBeneficiarioDAO reporteAvanceBeneficiarioDAO;
	
	/****************** metodos ************************/

	@Override
	public List<ReporteAvanceBeneficiario> findReporteAvanceBeneficiarioXBeneficiariosPorResultadoId(
			Integer beneficiariosPorResultadoID) {
		
		String consulta =" from ReporteAvanceBeneficiario where beneficiariosPorResultado.beneficiariosPorResultadoID = ? ";
		
		Object[] params = new Object[1];
		params[0]=beneficiariosPorResultadoID;
		
		return reporteAvanceBeneficiarioDAO.findReporteAvanceBeneficiarioXBeneficiariosPorResultadoId(consulta,params);
	}

	@Override
	public void saveReporteAvanceBeneficiario(
			ReporteAvanceBeneficiario reporteAvanceBeneficiario) {

		String consulta =" from ReporteAvanceBeneficiario where reporteAvance.reporteAvanceID = ? and beneficiariosPorResultado.beneficiariosPorResultadoID = ? ";
		Object[] params= new Object[2];
		params[0]= reporteAvanceBeneficiario.getReporteAvance().getReporteAvanceID();
		params[1]=reporteAvanceBeneficiario.getBeneficiariosPorResultado().getBeneficiariosPorResultadoID();

		List<ReporteAvanceBeneficiario> listReporteAvanceBeneficiarioTemp= reporteAvanceBeneficiarioDAO.findReporteAvanceBeneficiarioXConsulta(consulta,params);
		if(listReporteAvanceBeneficiarioTemp.size()==0){
			reporteAvanceBeneficiarioDAO.saveReporteAvanceBeneficiario(reporteAvanceBeneficiario);		
		}else{
			ReporteAvanceBeneficiario reporteAvanceBeneficiarioTemp=listReporteAvanceBeneficiarioTemp.get(0); 
			reporteAvanceBeneficiarioTemp.setCantidadLograda(reporteAvanceBeneficiario.getCantidadLograda());
			reporteAvanceBeneficiarioDAO.updateReporteAvanceBeneficiario(reporteAvanceBeneficiarioTemp);

		}
	}

	@Override
	public ReporteAvanceBeneficiario updateReporteAvanceBeneficiario(
			ReporteAvanceBeneficiario reporteAvanceBeneficiario) {
		
		return reporteAvanceBeneficiarioDAO.updateReporteAvanceBeneficiario(reporteAvanceBeneficiario);
	}

	@Override
	public void deleteReporteAvanceBeneficiario(
			Integer reporteAvanceBeneficiarioID) {
		ReporteAvanceBeneficiario reporteAvanceBeneficiario = reporteAvanceBeneficiarioDAO.findReporteAvanceBeneficiarioById(reporteAvanceBeneficiarioID);
		reporteAvanceBeneficiarioDAO.deleteReporteAvanceBeneficiario(reporteAvanceBeneficiario);
		
	}
}
