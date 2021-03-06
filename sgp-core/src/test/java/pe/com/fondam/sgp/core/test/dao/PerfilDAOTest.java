package pe.com.fondam.sgp.core.test.dao;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.dao.PerfilDAO;
import pe.com.fondam.sgp.core.dao.TmpPerfilDAO;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Perfil;
import pe.com.fondam.sgp.core.domain.TmpPerfil;

@Transactional
public class PerfilDAOTest extends AbstractBaseUnitTest {
	@Resource
	PerfilDAO perfilDAO; 
	@Resource
	TmpPerfilDAO tmpPerfilDAO; 
	@Resource
	DatoProyectoDAO datoProyectoDAO; 
	
	public PerfilDAO getPerfilDAO() {
		return perfilDAO;
	}

	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

	@Test
	public void savePerfil() {
		TmpPerfil tmpPerfil=tmpPerfilDAO.findTmpPerfilByTmpDatoProyectoID(1);
		DatoProyecto datoProyecto=datoProyectoDAO.findDatoProyectoById(18);
		Perfil perfil=new Perfil();
		perfil.setDatoProyecto(datoProyecto);
		perfil.setDuracionMeses(tmpPerfil.getDuracionMeses());
		perfil.setMontoContrapartida(tmpPerfil.getMontoContrapartida());
		perfil.setMontoSolicitadoFondam(tmpPerfil.getMontoSolicitadoFondam());
		

		this.getPerfilDAO().savePerfil(perfil);

		logger.info("perfila.id = " +perfil.getPerfilID());
		Assert.assertNotNull(perfil.getPerfilID());


	}
}
