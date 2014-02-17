package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.FuenteFinanciadoraDAO;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.service.FuenteFinanciadoraService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class FuenteFinanciadoraServiceImpl implements FuenteFinanciadoraService {

	//*****************  inyecciones  *****************//
	@Resource
	FuenteFinanciadoraDAO fuenteFinanciadoraDAO; 
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	//*********  metodos  *************//
	@Override
	public FuenteFinanciadora findFuenteFinanciadoraById(
			int fuenteFinanciadoraId) {
		return fuenteFinanciadoraDAO.findFuenteFinanciadoraById(fuenteFinanciadoraId);
	}


	@Override
	public List<FuenteFinanciadora> findFuenteFinanciadoraByDatoProyectoId(
			Integer idProyecto) {
		String consulta = " from FuenteFinanciadora where datoProyecto.datoProyectoID = ? ";
		
		Object[] params = new Object[1];
		params[0] = idProyecto;
		
		return fuenteFinanciadoraDAO.findFuenteFinanciadoraByConsulta(consulta, params);
	}


	@Override
	public List<FuenteFinanciadora> llenaFuenteFinanciadoraCompleto(
			List<FuenteFinanciadora> listFuenteFinanciadora) {

		for (FuenteFinanciadora fuenteFinanciadora : listFuenteFinanciadora) {
			fuenteFinanciadora.setDescripcionTipoFuenteFinanciadora(tablaEspecificaService.findTablaEspecificaById(fuenteFinanciadora.getFkIdtablaespTipoFuenteFinanciadora()).getDescripcionCabecera());
			fuenteFinanciadora.setDescripcionTipoMoneda(tablaEspecificaService.findTablaEspecificaById(fuenteFinanciadora.getFkIdtablaespTipoMoneda()).getDescripcionCabecera());
		}
		return listFuenteFinanciadora;
	}


	@Override
	public FuenteFinanciadora findFuenteFinanciadoraByDatoProyectoIdByEjecutor(
			Integer datoProyectoID) {
	String consulta = " from FuenteFinanciadora where datoProyecto.datoProyectoID = ? and defineSiEsEjecutor = 1 ";
		
		Object[] params = new Object[1];
		params[0] = datoProyectoID;
		
		return fuenteFinanciadoraDAO.findFuenteFinanciadoraByConsulta(consulta, params).get(0);
	}

}
