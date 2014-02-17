package pe.com.fondam.sgp.core.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.ProgramaDAO;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.TipoPeriodo;

@Transactional
public class ProgramaDAOTest extends AbstractBaseUnitTest {

	@Resource
	private ProgramaDAO programaDAO;


	public ProgramaDAO getProgramaDAO() {
		return programaDAO;
	}


	public void setProgramaDAO(ProgramaDAO programaDAO) {
		this.programaDAO = programaDAO;
	}


	@Test
	public void savePrograma() {
		TipoPeriodo t=new TipoPeriodo();
		t.setTipoPeriodoID(1);
		Programa p=new Programa();
		p.setNombrePrograma("PrubaPrograma");
		p.setTipoPeriodo(t);
		
		
		//	Integer pron=programaDAO.savePrograma(p);
		this.getProgramaDAO().savePrograma(p);

		logger.info("Programa.id = " + p.getProgramaID());
		Assert.assertNotNull(p.getProgramaID());


	}
	

	public void deletePrograma(){
		Programa p=programaDAO.findProgramaById(3);
		programaDAO.deletePrograma(p);
		logger.info("Programa.id delete = " + p.getProgramaID());
		Assert.assertNotNull(p.getProgramaID());
		
	}
@Test
	public void updatePrograma(){
		Programa p=new Programa();
		p.setProgramaID(1);
		p.setNombrePrograma("updatePrograma");
		
		programaDAO.updatePrograma(p);
		System.out.println("updateee...!!");
	}
@Test
	public void findPrograma(){
		
		List<Programa> list=programaDAO.findProgramas();
		Assert.assertTrue(!list.isEmpty());
	}
@Test
public void findProgramaById(){
	
	Programa programa=programaDAO.findProgramaById(2);
	Assert.assertNotNull(programa.getProgramaID());
}


	
}
