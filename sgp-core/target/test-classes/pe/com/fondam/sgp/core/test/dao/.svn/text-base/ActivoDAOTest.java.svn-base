package pe.com.fondam.sgp.core.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.ActivoDAO;
import pe.com.fondam.sgp.core.domain.Activo;

@Transactional
public class ActivoDAOTest extends AbstractBaseUnitTest {

	@Resource
	private ActivoDAO activoDAO;

	public ActivoDAO getActivoDAO() {
		return activoDAO;
	}

	public void setActivoDAO(ActivoDAO activoDAO) {
		this.activoDAO = activoDAO;
	}

	@Test
	public void saveActivo() {
		Activo activo = new Activo();
		activo.setDescripcionActivo("DESCRIPCION 2");
		activo.setFkIdtablaespCategoriaActivo(1);
		this.getActivoDAO().saveActivo(activo);
		logger.info("Activo.id = " + activo.getActivoID());
		Assert.assertNotNull(activo.getActivoID());
	}

	@Test
	public void updateActivo() {
		Activo activo = new Activo();
		activo.setActivoID(1);
		activo.setDescripcionActivo("DESCRIPCION");
		activo.setFkIdtablaespCategoriaActivo(1);
		Activo activoUpdated = this.getActivoDAO().updateActivo(activo);
		logger.info("activoUpdated.descripcionActivo = " + activoUpdated.getDescripcionActivo());
		Assert.assertTrue(activo.getDescripcionActivo().equals(activoUpdated.getDescripcionActivo()));
	}
	
	@Test
	public void findActivoById() {
		Integer id = 1;
		Activo activo = this.getActivoDAO().findActivoById(id);
		logger.info("activo = " + activo);
		Assert.assertNotNull(activo);
	}
	
	//@Test
	public void findActivos() {
		List<Activo> activos = this.getActivoDAO().findActivos();
		logger.info("activos.size() = " + activos.size());
		Assert.assertTrue(!activos.isEmpty());
	}
	
}
