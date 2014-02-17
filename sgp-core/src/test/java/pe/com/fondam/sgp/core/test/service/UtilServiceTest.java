package pe.com.fondam.sgp.core.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.service.UtilService;

@Transactional
public class UtilServiceTest extends AbstractBaseUnitTest {

	@Resource
	private UtilService utilService;

	public UtilService getUtilService() {
		return utilService;
	}

	public void setUtilService(UtilService utilService) {
		this.utilService = utilService;
	}

	@Test
	public void listaEstratos() {
		List<TablaEspecifica> listTablaEspecifica = getUtilService()
				.listaEstratos();

		logger.info("listTablaEspecifica.size() = "
				+ listTablaEspecifica.size());
		Assert.assertNotNull(listTablaEspecifica);
	}
	
	
	@Test
	public void listaTipoIndicadorResultado() {
		List<TablaEspecifica> listTablaEspecifica = getUtilService()
				.listaEstratos();

		logger.info("listTablaEspecifica.size() = "
				+ listTablaEspecifica.size());
		Assert.assertNotNull(listTablaEspecifica);
	}

}
