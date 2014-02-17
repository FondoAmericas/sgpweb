package pe.com.fondam.sgp.core.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.TablaEspecificaDAO;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;

@Transactional
public class TablaEspecificaDAOTest extends AbstractBaseUnitTest {

	@Resource
	private TablaEspecificaDAO tablaEspecificaDAO;

	public TablaEspecificaDAO getTablaEspecificaDAO() {
		return tablaEspecificaDAO;
	}

	public void setTablaEspecificaDAO(TablaEspecificaDAO tablaEspecificaDAO) {
		this.tablaEspecificaDAO = tablaEspecificaDAO;
	}

	@Test
	public void findTablaEspecificasByTablaGeneralId() {

		Integer tablaGeneralID = 8;
		List<TablaEspecifica> tablaEspecificas = this.getTablaEspecificaDAO()
				.findTablaEspecificasByTablaGeneralId(tablaGeneralID);
		Assert.assertNotNull(tablaEspecificas);
	}

}
