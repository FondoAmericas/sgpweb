package pe.com.fondam.sgp.core.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.TipoActividadObligatoriaProgramaDAO;
import pe.com.fondam.sgp.core.domain.TipoActividadObligatoriaPrograma;

@Transactional
public class TipoActividadObligatoriaProgramaDAOTest extends AbstractBaseUnitTest {

	@Resource
	private TipoActividadObligatoriaProgramaDAO tipoActividadObligatoriaProgramaDAO;

	public TipoActividadObligatoriaProgramaDAO getTipoActividadObligatoriaProgramaDAO() {
		return tipoActividadObligatoriaProgramaDAO;
	}

	public void setTipoActividadObligatoriaProgramaDAO(
			TipoActividadObligatoriaProgramaDAO tipoActividadObligatoriaProgramaDAO) {
		this.tipoActividadObligatoriaProgramaDAO = tipoActividadObligatoriaProgramaDAO;
	}


	@Test
	public void findTipoActividadObligatoriaPrograma() {
		int n=179;
		List<TipoActividadObligatoriaPrograma> tipoActividadObli = this.getTipoActividadObligatoriaProgramaDAO().findTipoActividadObligatoriaProgramasByIdtablaespTipo(n);
for (int i = 0; i < tipoActividadObli.size(); i++) {
	System.out.println(i+tipoActividadObli.get(i).getDescripcion());
}
		
		Assert.assertTrue(!tipoActividadObli.isEmpty());
	}

	
}
