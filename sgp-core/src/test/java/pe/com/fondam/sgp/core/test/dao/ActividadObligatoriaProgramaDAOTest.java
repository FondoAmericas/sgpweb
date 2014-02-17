package pe.com.fondam.sgp.core.test.dao;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.ActividadObligatoriaProgramaDAO;
import pe.com.fondam.sgp.core.domain.ActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.TipoActividadObligatoriaPrograma;

@Transactional
public class ActividadObligatoriaProgramaDAOTest extends AbstractBaseUnitTest {

	@Resource
	private ActividadObligatoriaProgramaDAO actividadObligatoriaProgramaDAO;




	public ActividadObligatoriaProgramaDAO getActividadObligatoriaProgramaDAO() {
		return actividadObligatoriaProgramaDAO;
	}




	public void setActividadObligatoriaProgramaDAO(
			ActividadObligatoriaProgramaDAO actividadObligatoriaProgramaDAO) {
		this.actividadObligatoriaProgramaDAO = actividadObligatoriaProgramaDAO;
	}




	@Test
	public void saveActividadObligatoriaPrograma() {
		Programa programa=new Programa();
		programa.setProgramaID(1);
		
		TipoActividadObligatoriaPrograma tipoActividadObligatoriaPrograma=new TipoActividadObligatoriaPrograma();
		tipoActividadObligatoriaPrograma.setTipoActividadObligatoriaProgramaID(3);
		ActividadObligatoriaPrograma actividadObligatoriaPrograma=new ActividadObligatoriaPrograma();
		actividadObligatoriaPrograma.setPrograma(programa);
		actividadObligatoriaPrograma.setTipoActividadObligatoriaPrograma(tipoActividadObligatoriaPrograma);

		this.actividadObligatoriaProgramaDAO.saveActividadObligatoriaPrograma(actividadObligatoriaPrograma);
		logger.info("Activo.id = " + actividadObligatoriaPrograma.getActividadObligatoriaProgramaID());
		Assert.assertNotNull(actividadObligatoriaPrograma.getActividadObligatoriaProgramaID());
	System.out.println("SAVEE ActividadObligatoria PROGRAMA");
	}

}
