package pe.com.fondam.sgp.core.test.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.PuntajeEvaluacionDAO;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.PuntajeEvaluacion;

@Transactional
public class PuntajeEvaluacionDAOTest extends AbstractBaseUnitTest {

	@Resource
	private PuntajeEvaluacionDAO puntajeEvaluacionDAO;


	public PuntajeEvaluacionDAO getPuntajeEvaluacionDAO() {
		return puntajeEvaluacionDAO;
	}



	public void setPuntajeEvaluacionDAO(PuntajeEvaluacionDAO puntajeEvaluacionDAO) {
		this.puntajeEvaluacionDAO = puntajeEvaluacionDAO;
	}



	@Test
	public void savePuntajeEvalucion() {
		Programa p =new Programa();
		p.setProgramaID(1);
		PuntajeEvaluacion puntajeEvaluacion=new PuntajeEvaluacion(); 
		puntajeEvaluacion.setFkIdtablaespTipoEvaluacion(1);
		puntajeEvaluacion.setMaximo(10);
		puntajeEvaluacion.setMinimo(545);
		puntajeEvaluacion.setMinimoAprobatorio(1);
		puntajeEvaluacion.setPrograma(p);
		this.puntajeEvaluacionDAO.savePuntajeEvaluacion(puntajeEvaluacion);

	}


}
