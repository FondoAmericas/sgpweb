package pe.com.fondam.sgp.core.test.dao;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.domain.TmpDatoProyecto;
import pe.com.fondam.sgp.core.service.TablaTemporalService;
@Transactional
public class TmpDatoProyectoServiceTest  extends AbstractBaseUnitTest {

	@Resource
	TablaTemporalService tablaTemporalService;

	public TablaTemporalService getTablaTemporalService() {
		return tablaTemporalService;
	}

	public void setTablaTemporalService(TablaTemporalService tablaTemporalService) {
		this.tablaTemporalService = tablaTemporalService;
	}
	@Test
	public void findTmpDatoProyecto(){
		TmpDatoProyecto tmpDatoProyecto=getTablaTemporalService().findTmpDatoProyectoById(2);
		Assert.assertNotNull(tmpDatoProyecto.getTMPDatoProyectoID());
	}

}
