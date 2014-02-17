package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.FuncionalidadDAO;
import pe.com.fondam.sgp.core.dao.FuncionalidadPerfilDAO;
import pe.com.fondam.sgp.core.dao.PerfilUsuarioDAO;
import pe.com.fondam.sgp.core.dao.UsuarioDAO;
import pe.com.fondam.sgp.core.domain.FuncionalidadPerfil;
import pe.com.fondam.sgp.core.domain.PerfilUsuario;
import pe.com.fondam.sgp.core.domain.Usuario;
import pe.com.fondam.sgp.core.service.SeguridadService;

@Service("seguridadServiceImpl")
public class SeguridadServiceImpl implements SeguridadService {

	protected final Log logger = LogFactory.getLog(SeguridadServiceImpl.class);

	@Resource
	private PerfilUsuarioDAO perfilusuarioDAO;

	@Resource
	private FuncionalidadDAO funcionalidadDAO;

	@Resource
	private UsuarioDAO usuarioDAO;

	@Resource
	private FuncionalidadPerfilDAO funcionalidadPerfilDAO;
	
	public FuncionalidadDAO getFuncionalidadDAO() {
		return funcionalidadDAO;
	}

	public void setFuncionalidadDAO(FuncionalidadDAO funcionalidadDAO) {
		this.funcionalidadDAO = funcionalidadDAO;
	}

	public PerfilUsuarioDAO getPerfilusuarioDAO() {
		return perfilusuarioDAO;
	}

	public void setPerfilusuarioDAO(PerfilUsuarioDAO perfilusuarioDAO) {
		this.perfilusuarioDAO = perfilusuarioDAO;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public List<PerfilUsuario> listAll() {
		return perfilusuarioDAO.findPerfilUsuario();
	}

	@Override
	public Usuario autenticateUsuario(String login, String password, Integer perfil) {
		logger.info("metodo de autenticate usuario  inicio");
		logger.info("SeguridadServiceImpl-->autenticateUsuario");
		Usuario user = this
				.getUsuarioDAO()
				.findUsuarioByLoginAndPasswordAndPerfil(login, password, perfil);
		if (user != null) {
			logger.info("Valor del usuario cuando es difernte del vacio ::::=="
					+ user);
		} else {
			logger.info("Valor del usuario  cuando es null  ::::" + user);
		}
		logger.info("metodo de autenticate usuario  FINAL  DEL METODO");
		return user;

	}

	@Override
	public List<FuncionalidadPerfil> menusPorPerfil(Integer perfilUsuarioId) {
		return funcionalidadPerfilDAO.findFuncionalidadPerfilByPerfilUsuarioId(perfilUsuarioId);
	}

	@Override
	public List<PerfilUsuario> listPerfilUsuariobyDMEbyDAFIandDT() {
		
		logger.info("se usa para el filtro de list evluador en la instalacion de comite tecnico");
		return perfilusuarioDAO.listPerfilUsuariobyDMEbyDAFIandDT();
		
		
	}
	
	
}
