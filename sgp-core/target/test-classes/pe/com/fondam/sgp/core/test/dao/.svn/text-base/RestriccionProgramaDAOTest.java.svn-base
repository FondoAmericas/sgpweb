package pe.com.fondam.sgp.core.test.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.RestricionProgramaDAO;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.RestricionPrograma;

@Transactional
public class RestriccionProgramaDAOTest extends AbstractBaseUnitTest {

	@Resource
	private RestricionProgramaDAO restricionProgramaDAO;

	public RestricionProgramaDAO getRestricionProgramaDAO() {
		return restricionProgramaDAO;
	}

	public void setRestricionProgramaDAO(RestricionProgramaDAO restricionProgramaDAO) {
		this.restricionProgramaDAO = restricionProgramaDAO;
	}



	@Test
	public void saveRestriccionPrograma() {
		Programa programa=new Programa();
		programa.setProgramaID(1);
		
		RestricionPrograma restricionPrograma=new RestricionPrograma();
	
		restricionPrograma.setPorcenMaximo(10);
		restricionPrograma.setPorcenMinimo(10);
		restricionPrograma.setPrograma(programa);
		restricionProgramaDAO.saveRestricionPrograma(restricionPrograma);
		System.out.println("SAVEE RESTRICCION PROGRAMA");
	}

}
