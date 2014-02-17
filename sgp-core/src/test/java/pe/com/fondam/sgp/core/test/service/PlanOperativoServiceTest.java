package pe.com.fondam.sgp.core.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.form.planOperativo.PlanOperativoForm;
import pe.com.fondam.sgp.core.form.planOperativo.ResultadoForm;
import pe.com.fondam.sgp.core.service.PlanOperativoService;
import pe.com.fondam.sgp.core.view.planOperativo.CronogramaCostoActividadView;

@Transactional
public class PlanOperativoServiceTest extends AbstractBaseUnitTest {

	@Resource
	private PlanOperativoService planOperativoService;

	public PlanOperativoService getPlanOperativoService() {
		return planOperativoService;
	}

	public void setPlanOperativoService(
			PlanOperativoService planOperativoService) {
		this.planOperativoService = planOperativoService;
	}

	@Test
	public void listCronogramaCostoActividadViewByCostoActividadID() {
		Integer costoActividadID = 1;
		
		List<CronogramaCostoActividadView> listCronogramaCostoActividadView = getPlanOperativoService()
				.listCronogramaCostoActividadViewByCostoActividadID(
						costoActividadID);
		
		logger.info("listCronogramaCostoActividadView.size() = " + listCronogramaCostoActividadView.size());
	}
	
	@Test
	public void mostrarPlanOperativoProyecto() {
		Integer datoPlanOperativoID = 6;
		PlanOperativoForm planOperativoForm = getPlanOperativoService().mostrarPlanOperativoProyecto(datoPlanOperativoID);
		List<ResultadoForm> listResultadoForm =planOperativoForm.getListResultadoForm();
		logger.info("listResultadoForm.size() = " + listResultadoForm.size());
		Assert.assertNotNull(planOperativoForm);
	}
	
	@Test
	public void mostrarResultadoByResultadoID() {
		Integer resultadoID = 8;
		ResultadoForm resultadoForm = getPlanOperativoService().mostrarResultadoByResultadoID(resultadoID);
		Assert.assertNotNull(resultadoForm);
	}
	
	@Test
	public void createPlanOperativo() {
		PlanOperativoForm planOperativoForm = setDataPlanOperativoForm();
		Integer datoPlanOperativoID = null;
		try {
			datoPlanOperativoID = getPlanOperativoService()
					.createPlanOperativo(planOperativoForm);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		Assert.assertNotNull(datoPlanOperativoID);
	}

	private PlanOperativoForm setDataPlanOperativoForm() {
		PlanOperativoForm planOperativoForm = new PlanOperativoForm();
		planOperativoForm.setVersion("version unit test");
		planOperativoForm.setEstadoPlanOperativo(1);
		planOperativoForm.setDatoProyectoID(1);

		ResultadoForm resultadoForm = null;
		List<ResultadoForm> listResultadoForm = new ArrayList<ResultadoForm>();

		resultadoForm = new ResultadoForm();
		resultadoForm.setDefinicionResultado("definicionResultado");
		resultadoForm.setSupuestoResultado("supuestoResultado");
		resultadoForm.setMetaResultado(1);
		resultadoForm.setEstratoId(1);
		resultadoForm.setDuracionMeses(3);
		listResultadoForm.add(resultadoForm);
				
		planOperativoForm.setListResultadoForm(listResultadoForm);
		return planOperativoForm;
	}

}
