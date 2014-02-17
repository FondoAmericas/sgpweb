package pe.com.fondam.sgp.core.test.helper;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.helper.PlanOperativoHelper;

@Transactional
public class PlanOperativoHelperTest extends AbstractBaseUnitTest {

	@Resource
	private PlanOperativoHelper planOperativoHelper;

	public PlanOperativoHelper getPlanOperativoHelper() {
		return planOperativoHelper;
	}

	public void setPlanOperativoHelper(PlanOperativoHelper planOperativoHelper) {
		this.planOperativoHelper = planOperativoHelper;
	}

	@Test
	public void sumMontoFinanciadoByFuenteFinanciadoras() {
		Integer datoPlanOperativoID = 2;

		Double sumMontoFinanciado = getPlanOperativoHelper()
				.sumMontoFinanciadoByFuenteFinanciadoras(datoPlanOperativoID);

		logger.info("sumMontoFinanciado = " + sumMontoFinanciado);
		Assert.assertTrue(true);
	}

	@Test
	public void sumCostoActividadByDatoPlanOperativoID() {
		Integer datoPlanOperativoID = 2;

		Double sumCostoActividad = getPlanOperativoHelper().sumCostoActividadByDatoPlanOperativoID(datoPlanOperativoID);

		logger.info("sumCostoActividad = " + sumCostoActividad);
		Assert.assertTrue(true);
	}
	
	@Test
	public void montoFinanciadoByFuenteFinanciadoraIDAndDatoProyectoID() {
		Integer fuenteFinanciadoraID = 100;
		Double montoFinanciado = getPlanOperativoHelper().montoFinanciadoByFuenteFinanciadoraIDAndDatoProyectoID(fuenteFinanciadoraID);
		logger.info("montoFinanciado = " + montoFinanciado);
		Assert.assertTrue(true);
	}
	
	
	@Test
	public void sumCronogramaCostoActividadByFuenteFinanciadoraIDAndDatoProyectoID() {
		Integer fuenteFinanciadoraID = 100;
		Integer datoPlanOperativoID = 2;
		Double sumCronogramaCostoActividad =  getPlanOperativoHelper().sumCronogramaCostoActividadByFuenteFinanciadoraIDAndDatoProyectoID(fuenteFinanciadoraID, datoPlanOperativoID);
		logger.info("sumCronogramaCostoActividad = " + sumCronogramaCostoActividad);
		Assert.assertTrue(true);
	}
	
	
}
