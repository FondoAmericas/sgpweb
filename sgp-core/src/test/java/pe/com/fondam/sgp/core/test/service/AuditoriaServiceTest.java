package pe.com.fondam.sgp.core.test.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.domain.Perfil;
import pe.com.fondam.sgp.core.service.AuditoriaService;

@Transactional
public class AuditoriaServiceTest extends AbstractBaseUnitTest {

	@Resource
	private AuditoriaService auditoriaService;

	public AuditoriaService getAuditoriaService() {
		return auditoriaService;
	}

	public void setAuditoriaService(AuditoriaService auditoriaService) {
		this.auditoriaService = auditoriaService;
	}

	@Test
	public void grabarEstadoTablas() throws Exception {
		Perfil perfil = new Perfil();

		Integer datoUsuarioID = 1;
		Integer estadoID = 1;
		String nombreClase = perfil.getClass().getName();
		Integer valorId = 1999;

		getAuditoriaService().grabarEstadoTablas(datoUsuarioID, estadoID,
				nombreClase, valorId);
	}
}
