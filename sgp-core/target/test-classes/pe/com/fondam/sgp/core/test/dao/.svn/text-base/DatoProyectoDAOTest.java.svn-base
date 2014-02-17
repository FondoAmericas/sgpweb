package pe.com.fondam.sgp.core.test.dao;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.dao.TmpDatoProyectoDAO;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.TmpDatoProyecto;

@Transactional
public class DatoProyectoDAOTest  extends AbstractBaseUnitTest {
	
	@Resource
	DatoProyectoDAO datoProyectoDAO;  
	
	@Resource
	TmpDatoProyectoDAO tmpDatoProyectoDAO;
	public DatoProyectoDAO getDatoProyectoDAO() {
		return datoProyectoDAO;
	}

	public void setDatoProyectoDAO(DatoProyectoDAO datoProyectoDAO) {
		this.datoProyectoDAO = datoProyectoDAO;
	}

	@Test
	public void saveDatoProyecto() {
		TmpDatoProyecto tmpDatoProyecto=tmpDatoProyectoDAO.findTmpDatoProyectoById(1);

	DatoProyecto datoProyecto= new DatoProyecto() ;
	datoProyecto.setCantidadPeriodo(tmpDatoProyecto.getCantidadPeriodo());
	datoProyecto.setCodigoProyecto(tmpDatoProyecto.getCodigoProyecto());
    datoProyecto.setNombreProyecto(tmpDatoProyecto.getNombreProyecto());
    datoProyecto.setDuracionProyecto(tmpDatoProyecto.getDuracionProyecto());
	datoProyecto.setFkIddetallestadocabEstproy(tmpDatoProyecto.getFkIddetallestadocabEstproy());
	datoProyecto.setPrograma(tmpDatoProyecto.getPrograma());
	datoProyecto.setNumero_orden_dato_proyecto(tmpDatoProyecto.getNumeroOrdenDatoProyecto());
	datoProyecto.setSubAreaTematica(tmpDatoProyecto.getSubAreaTematica());
		Integer a=datoProyectoDAO.saveDatoProyecto(datoProyecto);
		logger.info("datoProyecto.id = " + a);
		Assert.assertNotNull(datoProyecto.getDatoProyectoID());
	}
	
}
