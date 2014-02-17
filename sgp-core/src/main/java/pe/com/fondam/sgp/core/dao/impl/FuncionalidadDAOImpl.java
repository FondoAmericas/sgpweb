package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.FuncionalidadDAO;
import pe.com.fondam.sgp.core.dao.PerfilUsuarioDAO;
import pe.com.fondam.sgp.core.domain.Funcionalidad;
import pe.com.fondam.sgp.core.domain.FuncionalidadPerfil;
import pe.com.fondam.sgp.core.domain.PerfilUsuario;
import pe.com.fondam.sgp.core.domain.Usuario;

@Repository
public class FuncionalidadDAOImpl extends JpaBaseDAOImpl implements
		FuncionalidadDAO {

	protected final Log logger = LogFactory.getLog(UsuarioDAOImpl.class);
	@Autowired
	PerfilUsuarioDAO perfilUsuarioDAO;

	@Autowired
	public FuncionalidadDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	public List<Funcionalidad> findByfuncionalidadByUsuario(Usuario usuario) {

		PerfilUsuario perfilUsuarioID = usuario.getPerfilUsuario();
		Integer perfilUsuario = perfilUsuarioID.getPerfilUsuarioID();
	
		PerfilUsuario perfilusuario = perfilUsuarioDAO.findPerfilUsuarioById(perfilUsuario);
		Integer  perfilusu= perfilusuario.getPerfilUsuarioID();

		String queryString = "from FuncionalidadPerfil where funcionalidadPerfilID = ? ";
		Object[] params = new Object[1];
		params[0] = perfilusu;

		logger.info("estamos en FuncionalidadDAOImpl");
		List<FuncionalidadPerfil> funcionalidadperfil=super.find(queryString, params);
        Integer funcionlidadPeril  =  funcionalidadperfil.get(0).getFuncionalidadPerfilID();
        String queryString1 = "from Funcionalidad where funcionalidadID = ? ";
        Object[] param = new Object[1];
		param[0] = funcionlidadPeril;
	    return super.find(queryString1, param);

	}
}
