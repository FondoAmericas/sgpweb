package pe.com.fondam.sgp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.UsuarioDAO;
import pe.com.fondam.sgp.core.domain.Usuario;
import pe.com.fondam.sgp.core.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	//*********** inyecciones ***************//
	@Resource
	UsuarioDAO usuarioDAO;

	
	//*********** metodos ***************//
	@Override
	public Usuario findUsuarioById(Integer usuarioId) {
		return usuarioDAO.findUsuarioById(usuarioId);
	}
	

	
	
}
