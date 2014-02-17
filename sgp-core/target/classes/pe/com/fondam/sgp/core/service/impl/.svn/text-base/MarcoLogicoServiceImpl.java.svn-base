package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.dao.MarcoLogicoDAO;
import pe.com.fondam.sgp.core.domain.MarcoLogico;
import pe.com.fondam.sgp.core.service.MarcoLogicoService;

@Service
public class MarcoLogicoServiceImpl implements MarcoLogicoService {

	//*****************  inyecciones  *****************//
	@Resource
	MarcoLogicoDAO marcoLogicoDAO;
	
	@Resource
	DatoProyectoDAO datoProyectoDAO;
	
	
	//*****************  metodos  *****************//
	@Override
	public MarcoLogico findMarcoLogicoByDatoProyectoID(Integer datoProyectoId) {
		String queryString = "from MarcoLogico where datoProyecto.datoProyectoID=?  ";
		
		Object[] params = new Object[1];
		params[0] = datoProyectoId;
		
		List<MarcoLogico> listMarcoLogico=	marcoLogicoDAO.findMarcoLogicoByConsulta(queryString,params);
		MarcoLogico marcoLogico= null;
		if(listMarcoLogico.size()>0){
			marcoLogico=listMarcoLogico.get(0);
		}
		return marcoLogico;
	}


	@Override
	public MarcoLogico saveMarcoLogicoVacio(Integer datoProyectoID) {
			MarcoLogico marcoLogico= new MarcoLogico();
			marcoLogico.setDatoProyecto(datoProyectoDAO.findDatoProyectoById(datoProyectoID));
			marcoLogico.setFinDescrip("Sin Especificacion");
			marcoLogico.setFinSupuesto("Sin Especificacion");
			marcoLogico.setPropositoDescrip("Sin Especificacion");
			marcoLogico.setPropositoSupuesto("Sin Especificacion");
			marcoLogico.setResumenEjecutivo("Sin Especificacion");
		
		return marcoLogicoDAO.updateMarcoLogico(marcoLogico);
		
	}


	@Override
	public MarcoLogico findMarcoLogicoByID(Integer marcoLogicoID) {
		return marcoLogicoDAO.findMarcoLogicoById(marcoLogicoID);
	}


	@Override
	public MarcoLogico updateMarcoLogico(MarcoLogico marcoLogico) {
		return marcoLogicoDAO.updateMarcoLogico(marcoLogico);
	}
}