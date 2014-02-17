package pe.com.fondam.sgp.core.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.CategoriaActividadDAO;
import pe.com.fondam.sgp.core.domain.CategoriaActividad;

@Transactional
public class CategoriaActividadDAOTest extends AbstractBaseUnitTest {

	@Resource
	private CategoriaActividadDAO categoriaActividadDAO;

	public CategoriaActividadDAO getCategoriaActividadDAO() {
		return categoriaActividadDAO;
	}

	public void setCategoriaActividadDAO(
			CategoriaActividadDAO categoriaActividadDAO) {
		this.categoriaActividadDAO = categoriaActividadDAO;
	}

	@Test
	public void saveCategoriaActividad() {
		CategoriaActividad categoriaActividad = new CategoriaActividad();
		categoriaActividad
				.setDescripcionCategoriaActividad("DescripcionCategoriaActividad");
		categoriaActividad.setFkIdtablaespTipoActividad(1);
		this.getCategoriaActividadDAO().saveCategoriaActividad(
				categoriaActividad);
		logger.info("CategoriaActividad.id = "
				+ categoriaActividad.getCategoriaActividadID());
		Assert.assertNotNull(categoriaActividad.getCategoriaActividadID());
	}

	@Test
	public void updateCategoriaActividad() {
		CategoriaActividad categoriaActividad = new CategoriaActividad();
		categoriaActividad.setCategoriaActividadID(1);
		categoriaActividad.setFkIdtablaespTipoActividad(1);
		categoriaActividad.setDescripcionCategoriaActividad("DESCRIPCION");
		CategoriaActividad categoriaActividadUpdated = this
				.getCategoriaActividadDAO().updateCategoriaActividad(
						categoriaActividad);
		logger.info("categoriaActividadUpdated.DescripcionCategoriaActividad = "
				+ categoriaActividadUpdated.getDescripcionCategoriaActividad());
		Assert.assertTrue(categoriaActividad.getDescripcionCategoriaActividad()
				.equals(categoriaActividadUpdated
						.getDescripcionCategoriaActividad()));
	}

	//@Test
	public void findCategoriaActividadById() {
		Integer id = 1;
		CategoriaActividad categoriaActividad = this.getCategoriaActividadDAO()
				.findCategoriaActividadById(id);
		logger.info("categoriaActividad = " + categoriaActividad);
		Assert.assertNotNull(categoriaActividad);
	}

	//@Test
	public void findCategoriaActividades() {
		List<CategoriaActividad> categoriaActividades = this
				.getCategoriaActividadDAO().findCategoriaActividades();
		logger.info("categoriaActividades.size() = "
				+ categoriaActividades.size());
		Assert.assertTrue(!categoriaActividades.isEmpty());
	}

}
