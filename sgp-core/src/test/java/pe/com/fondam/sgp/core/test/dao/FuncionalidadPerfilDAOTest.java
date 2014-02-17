package pe.com.fondam.sgp.core.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.fondam.sgp.core.dao.FuncionalidadPerfilDAO;
import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.domain.FuncionalidadPerfil;
import pe.com.fondam.sgp.core.domain.PerfilUsuario;
import pe.com.fondam.sgp.core.domain.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:pe/com/fondam/sgp/core/resources/application-context.xml" })
public class FuncionalidadPerfilDAOTest {
	
	@Autowired
	private FuncionalidadPerfilDAO funcionalidadPerfilDAO;
	
	PerfilUsuario perfilusuario = new PerfilUsuario();
	DatoUsuario datoUsuario = new DatoUsuario();
	Usuario usuario = new Usuario();
	FuncionalidadPerfil funcionalidadperfil = new FuncionalidadPerfil();	
	

	@Test
	public void obtenerFuncionalidad() {
	   /* datoUsuario.setDatoUsuarioID(1);
		perfilusuario.setPerfilUsuarioID(1);
		perfilusuario.setPrefijo("DME");
		usuario.setLogin("hhumala");
		usuario.setPassword("humala");
		usuario.setDatoUsuario(datoUsuario);
		usuario.setPerfilUsuario(perfilusuario);
		usuario.setUsuarioID(1);*/
		//funcionalidadperfil.setFuncionalidadPerfilID(1);
		Integer numero =1;
		try {
		List<FuncionalidadPerfil> funcionaPerfil = funcionalidadPerfilDAO.findFuncionalidadPerfilByPerfilUsuarioId(numero);
		System.out.println("funcionaPerfil.size() = " + funcionaPerfil.size());
		}catch (Exception e) {
			System.out.println("ERRORRRR ::==="  + e);
		}
}
	}