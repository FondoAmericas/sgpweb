package pe.com.fondam.sgp.core.test.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.EvaluadorDAO;
import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.domain.Evaluador;
import pe.com.fondam.sgp.core.domain.Programa;

@Transactional
public class EvaluadorDAOTest extends AbstractBaseUnitTest {
	@Resource
	private EvaluadorDAO evaluadorDAO;

	public EvaluadorDAO getEvaluadorDAO() {
		return evaluadorDAO;
	}

	public void setEvaluadorDAO(EvaluadorDAO evaluadorDAO) {
		this.evaluadorDAO = evaluadorDAO;
	}

	@Test
	public void saveEvalaudor() {
		//Integer valor = 0;
		Evaluador evaluador = new Evaluador();
		Programa programa = new Programa();
		programa.setProgramaID(18);
		DatoUsuario datoUsuario = new DatoUsuario();
		datoUsuario.setDatoUsuarioID(1);
		evaluador.setDatoUsuario(datoUsuario);
		evaluador.setPrograma(programa);
		evaluadorDAO.saveEvaluador(evaluador);
	}

//	public void findEvaluadorByDatoUsuarioAndPrograma() {
//		// Evaluador evaluador = evaluadorDAO
//		// .findEvaluadorByDatoUsuarioIDAndProgramaID(1, 18);
//		// Assert.assertNotNull(evaluador);
//		logger.info("find evaluador:" + evaluador.getEvaluadorID());
//	}
}
