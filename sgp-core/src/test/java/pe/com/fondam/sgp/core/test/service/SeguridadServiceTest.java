package pe.com.fondam.sgp.core.test.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.domain.FuncionalidadPerfil;
import pe.com.fondam.sgp.core.domain.PerfilUsuario;
import pe.com.fondam.sgp.core.domain.Usuario;
import pe.com.fondam.sgp.core.service.SeguridadService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:pe/com/fondam/sgp/core/resources/application-context.xml" })
public class SeguridadServiceTest {

	@Resource
	private SeguridadService services;

	public SeguridadService getServices() {
		return services;
	}

	public void setServices(SeguridadService services) {
		this.services = services;
	}

	@Test
	public void testObtenerAlmenosUnaOferta() {
		List<PerfilUsuario> todos = services.listAll();
		assertTrue(todos.size() > 0);
		int longitud = todos.size();
		System.out.println("longitud" + " " + longitud);
	}

	@Test
	public void autenticateUsuario() {
		String login = "dme";
		String password = "dme";
		Integer perfil = 1;
		Usuario usuarios = this.getServices().autenticateUsuario(login,
				password, perfil);
		Assert.assertNotNull(usuarios);
	}
	
	@Test
	public void testMostarListadoMenu() {

		PerfilUsuario perfilusuario = new PerfilUsuario();
		DatoUsuario datoUsuario = new DatoUsuario();
		Usuario usuario = new Usuario();
		
		datoUsuario.setDatoUsuarioID(1);
		perfilusuario.setPerfilUsuarioID(1);
		perfilusuario.setPrefijo("DME");
		usuario.setLogin("hhumala");
		usuario.setPassword("humala");
		usuario.setDatoUsuario(datoUsuario);
		usuario.setPerfilUsuario(perfilusuario);
		usuario.setUsuarioID(1);
		List<FuncionalidadPerfil>  mostrar = services.menusPorPerfil(usuario.getPerfilUsuario().getPerfilUsuarioID());
		System.out.println("seguridadServiceTest"+"probando el test:  "+mostrar.size());
	}
}