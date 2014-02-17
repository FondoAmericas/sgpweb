package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.FuncionalidadPerfil;
import pe.com.fondam.sgp.core.domain.PerfilUsuario;
import pe.com.fondam.sgp.core.domain.Usuario;

public interface SeguridadService {
 
	List<PerfilUsuario> listAll();

	Usuario autenticateUsuario(String login, String password, Integer perfil);

	 List<FuncionalidadPerfil> menusPorPerfil(Integer perfilUsuarioId);

	List<PerfilUsuario> listPerfilUsuariobyDMEbyDAFIandDT();
}
