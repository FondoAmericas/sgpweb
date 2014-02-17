package pe.com.fondam.sgp.core.test.dao;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.InstitucionDAO;
import pe.com.fondam.sgp.core.dao.TmpFuenteFinanciadoraDAO;
import pe.com.fondam.sgp.core.dao.TmpInstitucionDAO;
import pe.com.fondam.sgp.core.domain.Institucion;
import pe.com.fondam.sgp.core.domain.TmpFuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.TmpInstitucion;

@Transactional
public class InstitucionDAOTest extends AbstractBaseUnitTest {
	@Resource
	InstitucionDAO institucionDAO;
	
	@Resource
	TmpInstitucionDAO tmpInstitucionDAO;
	
	@Resource
	TmpFuenteFinanciadoraDAO tmpFuenteFinanciadoraDAO;
	
	
	public InstitucionDAO getInstitucionDAO() {
		return institucionDAO;
	}


	public void setInstitucionDAO(InstitucionDAO institucionDAO) {
		this.institucionDAO = institucionDAO;
	}


	@Test
	public void saveInstitucion() {
		TmpFuenteFinanciadora tmpFuenteFinanciadora= tmpFuenteFinanciadoraDAO.findTmpFuenteFinanciadoraByTmpIdDatoProyecto(1);

		TmpInstitucion tmpInstitucion=tmpInstitucionDAO.findTmpInstitucionById(tmpFuenteFinanciadora.getTMPInstitucion().getTMPInstitucionID());

		Institucion institucion=new Institucion();
		institucion.setContacto(tmpInstitucion.getContacto());
		institucion.setFkIdDetalleEstadoCabEstInstitucion(tmpInstitucion.getFkIdDetalleEstadoCabEstInstitucion());
		institucion.setNombreInstitucion(tmpInstitucion.getNombreInstitucion());
		institucion.setRucInstitucion(tmpInstitucion.getRucInstitucion());
		institucion.setObservacionDeInstitucion(tmpInstitucion.getObservacionDeInstitucion());
		institucion.setTelefono(tmpInstitucion.getTelefono());
		institucion.setRepresentanteLegal(tmpInstitucion.getRepresentanteLegal());
		institucion.setDireccion(tmpInstitucion.getDireccion());
		
		//	Integer pron=programaDAO.savePrograma(p);
		this.getInstitucionDAO().saveInstitucion(institucion);

		logger.info("Programa.id = " + institucion.getInstitucionID());
		Assert.assertNotNull(institucion.getInstitucionID());


	}
}
