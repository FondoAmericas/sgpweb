package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.ApreciacionResultadoDAO;
import pe.com.fondam.sgp.core.domain.ApreciacionResultado;
import pe.com.fondam.sgp.core.service.ApreciacionResultadoService;

@Service
public class ApreciacionResultadoServiceImpl implements
		ApreciacionResultadoService {

	/****** inyecciones *******/
	@Resource
	ApreciacionResultadoDAO apreciacionResultadoDAO;
	
	
	/****** metodos *******/
	@Override
	public List<ApreciacionResultado> findApreciacionResultadoXReporteAvanceId(
			Integer reporteAvanceId) {
		String consulta = " from ApreciacionResultado where reporteAvance.reporteAvanceID = ?";
		
		Object[] params= new Object[1];
		params[0]= reporteAvanceId;
		
		return apreciacionResultadoDAO.findApreciacionResultados(consulta,params);
	}


	@Override
	public ApreciacionResultado findApreciacionResultadoById(
			Integer apreciacionResultadoID) {
		return apreciacionResultadoDAO.findApreciacionResultadoById(apreciacionResultadoID);
	}


	@Override
	public void saveApreciacionResultado(
			ApreciacionResultado apreciacionResultado) {

apreciacionResultadoDAO.saveApreciacionResultado(apreciacionResultado);
		
	}


	@Override
	public ApreciacionResultado updateApreciacionResultado(
			ApreciacionResultado apreciacionResultado) {
		return apreciacionResultadoDAO.updateApreciacionResultado(apreciacionResultado);
	}


	@Override
	public void deleteApreciacionResultado(Integer apreciacionResultadoID) {
		ApreciacionResultado apreciacionResultado= apreciacionResultadoDAO.findApreciacionResultadoById(apreciacionResultadoID);
		apreciacionResultadoDAO.deleteApreciacionResultado(apreciacionResultado);
		
	}

}
