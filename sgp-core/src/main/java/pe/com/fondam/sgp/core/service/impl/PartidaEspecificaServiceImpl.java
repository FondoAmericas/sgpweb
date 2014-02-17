package pe.com.fondam.sgp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.PartidaEspecificaDAO;
import pe.com.fondam.sgp.core.domain.PartidaEspecifica;
import pe.com.fondam.sgp.core.service.PartidaEspecificaService;

@Service
public class PartidaEspecificaServiceImpl implements PartidaEspecificaService {

	//*************** inyecciones **********************//
	@Resource
	PartidaEspecificaDAO partidaEspecificaDAO;
	
	//*************** metodos **********************//
	@Override
	public PartidaEspecifica findPartidaEspecificaById(
			Integer partidaEspecificaID) {
		
		return partidaEspecificaDAO.findPartidaEspecificaById(partidaEspecificaID);
	}

}
