package pe.com.fondam.sgp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.TipoActividadObligatoriaProgramaDAO;
import pe.com.fondam.sgp.core.domain.TipoActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.service.TipoActividadObligatoriaProgramaService;

@Service
public class TipoActividadObligatoriaProgramaServiceImpl implements
		TipoActividadObligatoriaProgramaService {

	//*************** inyecciones *************************//
	@Resource
	TipoActividadObligatoriaProgramaDAO tipoActividadObligatoriaProgramaDAO;
	
	//*************** metodos *************************//
	@Override
	public TipoActividadObligatoriaPrograma findTipoActividadObligatoriaProgramaById(
			Integer tipoActividadObligatoriaProgramaID) {

		return tipoActividadObligatoriaProgramaDAO.findTipoActividadObligatoriaProgramaById(tipoActividadObligatoriaProgramaID);
	}

}
