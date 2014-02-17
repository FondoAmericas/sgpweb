package pe.com.fondam.sgp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DatoUsuarioDAO;
import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.service.DatoUsuarioService;

@Service
public class DatoUsuarioServiceImpl implements DatoUsuarioService {

	//*************** inyecciones *********************//
	@Resource
	DatoUsuarioDAO datoUsuarioDAO;
	
	//*************** metodos *********************//
	@Override
	public DatoUsuario findDatoUsuarioById(Integer datoUsuarioID) {
		return datoUsuarioDAO.findDatoUsuarioById(datoUsuarioID);
	}

}
