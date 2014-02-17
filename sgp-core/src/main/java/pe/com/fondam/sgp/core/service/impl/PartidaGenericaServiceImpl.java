package pe.com.fondam.sgp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.PartidaGenericaDAO;
import pe.com.fondam.sgp.core.domain.PartidaGenerica;
import pe.com.fondam.sgp.core.service.PartidaGenericaService;

@Service
public class PartidaGenericaServiceImpl implements PartidaGenericaService {

	//**********************  inyecciones  *************************//
	@Resource
	PartidaGenericaDAO partidaGenericaDAO;
	
	//**********************  inyecciones  *************************//
	@Override
	public PartidaGenerica findPartidaGenericaById(Integer partidaGenericaID) {
		
		return partidaGenericaDAO.findPartidaGenericaById(partidaGenericaID);
	}

}
