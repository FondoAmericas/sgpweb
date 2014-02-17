package pe.com.fondam.sgp.core.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.fondam.sgp.core.dao.PerfilUsuarioDAO;
import pe.com.fondam.sgp.core.domain.PerfilUsuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:pe/com/fondam/sgp/core/resources/application-context.xml"})
public class PerfilUsuarioDAOTest  {

	@Autowired
	private PerfilUsuarioDAO perfilUsuario;
	
	@Test
	public void obtenerListaRecetas(){
		List<PerfilUsuario>  perfiles;
		perfiles=perfilUsuario.findPerfilUsuario();
		for(PerfilUsuario m:perfiles){
			System.out.println(m.getDescripcionPerfil());
			System.out.println(m.getPerfilUsuarioID());
			System.out.println(m.getPrefijo());
		}
			System.out.print("estamos probando"+perfilUsuario.findPerfilUsuario());
		
	}
	
}