package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.ReporteAvanceBeneficiario;

public interface ReporteAvanceBeneficiarioService {

	List<ReporteAvanceBeneficiario> findReporteAvanceBeneficiarioXBeneficiariosPorResultadoId(
			Integer beneficiariosPorResultadoID);

	void saveReporteAvanceBeneficiario(
			ReporteAvanceBeneficiario reporteAvanceBeneficiario);

	ReporteAvanceBeneficiario updateReporteAvanceBeneficiario(
			ReporteAvanceBeneficiario reporteAvanceBeneficiario);

	void deleteReporteAvanceBeneficiario(Integer reporteAvanceBeneficiarioID);

}
