package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.IndicadorMarcoLogicoDAO;
import pe.com.fondam.sgp.core.dao.TablaEspecificaDAO;
import pe.com.fondam.sgp.core.domain.IndicadorMarcoLogico;
import pe.com.fondam.sgp.core.service.IndicadorMarcoLogicoService;

@Service
public class IndicadorMarcoLogicoServiceImpl implements
		IndicadorMarcoLogicoService {

	//************  inyecciones  ******************//
	@Resource
	IndicadorMarcoLogicoDAO indicadorMarcoLogicoDAO;

	@Resource
	TablaEspecificaDAO tablaEspecificaDAO;
	
	//************  metodos  ******************//
	@Override
	public List<IndicadorMarcoLogico> findIndicadorMarcoLogicoByIdMarcoLogico(
			Integer marcoLogicoID) {
		String queryString = "from IndicadorMarcoLogico where marcoLogico.marcoLogicoID=?";
		Object[] params = new Object[1];
		params[0] = marcoLogicoID;
		return llenaListIndicadorMarcoLogico(indicadorMarcoLogicoDAO.findIndicadorMarcoLogicoByConsulta(queryString,params));
	}

	@Override
	public IndicadorMarcoLogico updateIndicadorMarcoLogico(
			IndicadorMarcoLogico indicadorMarcoLogico) {
		return indicadorMarcoLogicoDAO.updateIndicadorMarcoLogico(indicadorMarcoLogico);
		
	}
	
	@Override
	public IndicadorMarcoLogico findIndicadorMarcoLogicoById(
			Integer indicadorMarcoLogicoId) {
		return indicadorMarcoLogicoDAO.findIndicadorMarcoLogicoById(indicadorMarcoLogicoId);
		
	}

	@Override
	public void deleteIndicadorMarcoLogico(Integer indicadorMarcoLogicoId) {
		IndicadorMarcoLogico indicadorMarcoLogico= indicadorMarcoLogicoDAO.findIndicadorMarcoLogicoById(indicadorMarcoLogicoId);
		indicadorMarcoLogicoDAO.deleteIndicadorMarcoLogico(indicadorMarcoLogico);
	}
	
	
	//************  metodos privados ******************//
	private List<IndicadorMarcoLogico> llenaListIndicadorMarcoLogico(
			List<IndicadorMarcoLogico> listIndicadorMarcoLogico) {

		for (IndicadorMarcoLogico indicadorMarcoLogico : listIndicadorMarcoLogico) {
			indicadorMarcoLogico.setSituacionActualDescripcion(tablaEspecificaDAO.findTablaEspecificaById(indicadorMarcoLogico.getUnidadMedida()).getDescripcionCabecera());
		}
		return listIndicadorMarcoLogico;
	}

	
}
