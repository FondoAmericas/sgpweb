package pe.com.fondam.sgp.core.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.dao.TipoPeriodoDAO;
import pe.com.fondam.sgp.core.domain.TipoPeriodo;

@Transactional
public class TipoPeriodoDAOTest extends AbstractBaseUnitTest {

	@Resource
	private TipoPeriodoDAO tipoPeriodoDAO;



	public TipoPeriodoDAO getTipoPeriodoDAO() {
		return tipoPeriodoDAO;
	}

	public void setTipoPeriodoDAO(TipoPeriodoDAO tipoPeriodoDAO) {
		this.tipoPeriodoDAO = tipoPeriodoDAO;
	}

	@Test
	public void findTipPeriodos() {

		
		List<TipoPeriodo> tablaEspecificas = this.getTipoPeriodoDAO().findTipoPeriodos();

		Assert.assertTrue(!tablaEspecificas.isEmpty());

	}
	
	
}
