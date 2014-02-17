package pe.com.fondam.sgp.core.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:pe/com/fondam/sgp/core/resources/application-context.xml" })
public class TablaEspecificaServiceTest {

	@Resource
	private TablaEspecificaService tablaEspecificaService;



	public TablaEspecificaService getTablaEspecificaService() {
		return tablaEspecificaService;
	}



	public void setTablaEspecificaService(
			TablaEspecificaService tablaEspecificaService) {
		this.tablaEspecificaService = tablaEspecificaService;
	}



	@Test
	public void listTablaEspecificaByTablaGeneralId() {
	
		int n = 2;
		List<TablaEspecifica> tablaEspecificas = this.getTablaEspecificaService().listTablaEspecificaByTablaGeneralId(n);
		Assert.assertTrue(!tablaEspecificas.isEmpty());
		
	}



}