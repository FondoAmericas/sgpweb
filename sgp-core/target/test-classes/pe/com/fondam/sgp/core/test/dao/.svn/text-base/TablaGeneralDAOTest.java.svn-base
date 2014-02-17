package pe.com.fondam.sgp.core.test.dao;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.dao.TablaGeneralDAO;
import pe.com.fondam.sgp.core.domain.TablaGeneral;

@Transactional
public class TablaGeneralDAOTest extends AbstractBaseUnitTest {

	@Resource
	private TablaGeneralDAO tablaGeneralDAO;

	public TablaGeneralDAO getTablaGeneralDAO() {
		return tablaGeneralDAO;
	}

	public void setTablaGeneralDAO(TablaGeneralDAO tablaGeneralDAO) {
		this.tablaGeneralDAO = tablaGeneralDAO;
	}

	@Test
	public void findTablaGeneralByPrefijo() {
		TablaGeneral tablaGeneral = getTablaGeneralDAO()
				.findTablaGeneralByPrefijo(FondamConstans.PREFIJO_BANCO);
		logger.info("TablaGeneral.cabecera = " + tablaGeneral.getCabecera());
		Assert.assertNotNull(tablaGeneral);
	}

}
