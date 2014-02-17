package pe.com.fondam.sgp.core.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import pe.com.fondam.sgp.core.commons.AbstractBaseUnitTest;
import pe.com.fondam.sgp.core.report.RptContraPartida;
import pe.com.fondam.sgp.core.report.RptCostoActividadFuente;
import pe.com.fondam.sgp.core.report.RptCronogramaActividad;
import pe.com.fondam.sgp.core.report.RptResultados;
import pe.com.fondam.sgp.core.report.RptRptCostoActividadFuenteDet;
import pe.com.fondam.sgp.core.service.ReportService;

@Transactional
public class ReportServiceTest extends AbstractBaseUnitTest {

	@Resource
	private ReportService reportService;

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	@Test
	public void planOperativoCostoActividadFuente() {
		
		Integer datoPlanOperativoID = 2;
		Integer fuenteFinanciadoraID = 101;
		Integer actividadID = 6;
		
		List<RptCostoActividadFuente> listRptCostoActividadFuente = getReportService().planOperativoCostoActividadFuente(datoPlanOperativoID, fuenteFinanciadoraID, actividadID);
		for (RptCostoActividadFuente rptCostoActividadFuente : listRptCostoActividadFuente) {
			logger.info("rptCostoActividadFuente.getRubro = " + rptCostoActividadFuente.getRubro());
			List<RptRptCostoActividadFuenteDet> detalles = rptCostoActividadFuente.getDetalles();
			for (RptRptCostoActividadFuenteDet rptRptCostoActividadFuenteDet : detalles) {
				logger.info("Descripcion = " + rptRptCostoActividadFuenteDet.getDescripcion());
				logger.info("Unidad = " + rptRptCostoActividadFuenteDet.getUnidad());
				logger.info("Cantidad = " + rptRptCostoActividadFuenteDet.getCantidad());
				logger.info("Precio = " + rptRptCostoActividadFuenteDet.getPrecio());
				logger.info("CostoTotal = " + rptRptCostoActividadFuenteDet.getCostoTotal());
				logger.info("MontoPeriodo1 = " + rptRptCostoActividadFuenteDet.getMontoPeriodo1());
				logger.info("MontoPeriodo2 = " + rptRptCostoActividadFuenteDet.getMontoPeriodo2());
				logger.info("MontoPeriodo3 = " + rptRptCostoActividadFuenteDet.getMontoPeriodo3());
				logger.info("MontoPeriodo4 = " + rptRptCostoActividadFuenteDet.getMontoPeriodo4());
				logger.info("MontoPeriodo5 = " + rptRptCostoActividadFuenteDet.getMontoPeriodo5());
				logger.info("MontoPeriodo6 = " + rptRptCostoActividadFuenteDet.getMontoPeriodo6());
				logger.info("MontoPeriodo7 = " + rptRptCostoActividadFuenteDet.getMontoPeriodo7());
				logger.info("MontoPeriodo8 = " + rptRptCostoActividadFuenteDet.getMontoPeriodo8());
			}
		}
		
	}
	
	
//	@Test
//	public void costoActividadFuenteParam3() {
//
//		Integer actividadID = 1;
//		Integer fuenteFinanciadoraID = 1;
//		Integer partidaGenericaID = 18;
//
//		List<RptCostoActividadFuenteOld> listCostoActividadFuente = getReportService()
//				.planOperativoCostoActividadFuenteParam3(actividadID, fuenteFinanciadoraID,
//						partidaGenericaID);
//
//		for (RptCostoActividadFuenteOld costoActividadFuente : listCostoActividadFuente) {
//			logger.info("costoActividadFuente.getCostoTotal() = "
//					+ costoActividadFuente.getCostoTotal());
//		}
//
//		Assert.assertTrue(!listCostoActividadFuente.isEmpty());
//
//	}

	@Test
	public void planOperativoCronogramaActividadByDatoPlanOperativoID() {
		Integer datoPlanOperativoID = 1;
		List<RptCronogramaActividad> listRptCronogramaActividad = getReportService().planOperativoCronogramaActividadByDatoPlanOperativoID(datoPlanOperativoID);
		for (RptCronogramaActividad rptCronogramaActividad : listRptCronogramaActividad) {
			logger.info("rptCronogramaActividad.resultado = " + rptCronogramaActividad.getResultado());
			logger.info("rptCronogramaActividad.getDetalles().size() = " + rptCronogramaActividad.getDetalles().size());
		}
		Assert.assertTrue(!listRptCronogramaActividad.isEmpty());
	}
	
	
	@Test
	public void resultadosByDatoPlanOperativoID() {
		Integer datoPlanOperativoID = 1;
		List<RptResultados> listResultados = getReportService()
				.planOperativoResultadosByDatoPlanOperativoID(datoPlanOperativoID);

		for (RptResultados resultados : listResultados) {
			logger.info("resultados.getResultado = "
					+ resultados.getDefinicionResultado() );
		}
		
		Assert.assertTrue(!listResultados.isEmpty());
	}
	
	
	@Test
	public void planOperativoContraPartida() {
		Integer datoPlanOperativoID = 9;
		List<RptContraPartida> listRptContraPartida = getReportService().planOperativoContraPartida(datoPlanOperativoID);
		
		Assert.assertTrue(!listRptContraPartida.isEmpty());
		
	}
	
}