package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.bean.ApreciacionResultadoBean;
import pe.com.fondam.sgp.core.bean.ProblemaSolucionBean;
import pe.com.fondam.sgp.core.bean.ReporteAvanceBeneficiarioBean;
import pe.com.fondam.sgp.core.domain.ApreciacionResultado;
import pe.com.fondam.sgp.core.domain.ProblemaSolucion;
import pe.com.fondam.sgp.core.domain.ReporteAvance;
import pe.com.fondam.sgp.core.domain.ReporteAvanceBeneficiario;

public interface ReporteAvanceService {

	void saveReporteAvance(ReporteAvance reporteAvance);

	List<ReporteAvance> findReporteAvanceXDatoProyectoId(Integer datoProyectoId);

	ReporteAvance findReporteAvanceById(Integer reporteAvanceId);

	List<ProblemaSolucionBean> llenaListProblemaSolucionBean(List<ProblemaSolucion> listProblemaSolucion);

	List<ApreciacionResultadoBean> llenaListApreciacionResultadoBean(List<ApreciacionResultado> listApreciacionResultado);
/*
	List<BeneficiariosPorResultadoBean> llenaListBeneficiariosPorResultadoBean(List<BeneficiariosPorResultado> listBeneficiariosPorResultado);
	
	BeneficiariosPorResultadoBean llenaBeneficiariosPorResultadoBean(
			BeneficiariosPorResultado beneficiariosPorResultado);
	*/
	List<ReporteAvanceBeneficiarioBean> llenaListReporteAvanceBeneficiarioBean(
			List<ReporteAvanceBeneficiario> listReporteAvanceBeneficiario);

	List<ReporteAvance> findReporteAvanceXDatoProyectoIdSinAprobar(Integer datoProyectoID);

}
