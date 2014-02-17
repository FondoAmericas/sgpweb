package pe.com.fondam.sgp.core.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.DepProvDistDAO;
import pe.com.fondam.sgp.core.domain.DepProvDist;

@Transactional
public class DepProvDistDAOTest extends AbstractBaseUnitTest {

	@Resource
	private DepProvDistDAO depProvDistDAO;

	public DepProvDistDAO getDepProvDistDAO() {
		return depProvDistDAO;
	}

	public void setDepProvDistDAO(DepProvDistDAO depProvDistDAO) {
		this.depProvDistDAO = depProvDistDAO;
	}

	@Test
	public void saveDepProvDist() {
		DepProvDist DepProvDist = new DepProvDist();
		DepProvDist.setIdDepartamento("1");
		DepProvDist.setIdProvincia("1");
		DepProvDist.setIdDistrito("1");
		DepProvDist.setDescripcion("DESCRIPCION 888");
		this.getDepProvDistDAO().saveDepProvDist(DepProvDist);
		logger.info("DepProvDist.id = " + DepProvDist.getDepProvDistID());
		Assert.assertNotNull(DepProvDist.getDepProvDistID());
	}

	@Test
	public void updateDepProvDist() {
		DepProvDist DepProvDist = new DepProvDist();
		DepProvDist.setDepProvDistID(1);
		DepProvDist.setIdDepartamento("1");
		DepProvDist.setIdProvincia("1");
		DepProvDist.setIdDistrito("1");
		DepProvDist.setDescripcion("DESCRIPCION");
		DepProvDist DepProvDistUpdated = this.getDepProvDistDAO().updateDepProvDist(DepProvDist);
		logger.info("DepProvDistUpdated.descripcionDepProvDist = " + DepProvDistUpdated.getDescripcion());
		Assert.assertTrue(DepProvDist.getDescripcion().equals(DepProvDistUpdated.getDescripcion()));
	}
	
	@Test
	public void findDepProvDistById() {
		Integer id = 1;
		DepProvDist DepProvDist = this.getDepProvDistDAO().findDepProvDistById(id);
		logger.info("DepProvDist = " + DepProvDist);
		Assert.assertNotNull(DepProvDist);
	}
	
	@Test
	public void findDepProvDists() {
		List<DepProvDist> DepProvDists = this.getDepProvDistDAO().findDepProvDistritos(3,"0","0","0");
		logger.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaa  DepProvDists.size() = " + DepProvDists.size());
		Assert.assertTrue(!DepProvDists.isEmpty());
	}
	
}
