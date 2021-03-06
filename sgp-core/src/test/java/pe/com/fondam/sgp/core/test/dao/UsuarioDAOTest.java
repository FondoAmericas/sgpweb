package pe.com.fondam.sgp.core.test.dao;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.UsuarioDAO;
import pe.com.fondam.sgp.core.domain.Usuario;

@Transactional
public class UsuarioDAOTest extends AbstractBaseUnitTest {

	@Resource
	private UsuarioDAO usuarioDAO;

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public void findUsuarioByLoginAndPasswordAndPerfil() {
		String login = "dme";
		String password = "dme";
		Integer perfil = 1;
		Usuario usuarios = this
				.getUsuarioDAO()
				.findUsuarioByLoginAndPasswordAndPerfil(login, password, perfil);

		Assert.assertNotNull(usuarios);
	}

	@Test
public void deleteUsuario() {
	Usuario u=usuarioDAO.findUsuarioById(15);
	usuarioDAO.deleteUsuario(u);
	logger.info("usuario.id delete = " + u.getUsuarioID());
	Assert.assertNotNull(u.getUsuarioID());
}
}
