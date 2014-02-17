package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.ReporteAvanceBeneficiario;

public interface ReporteAvanceBeneficiarioDAO {

	List<ReporteAvanceBeneficiario> findReporteAvanceBeneficiarioXBeneficiariosPorResultadoId(
			String consulta, Object[] params);

	void saveReporteAvanceBeneficiario(
			ReporteAvanceBeneficiario reporteAvanceBeneficiario);

	List<ReporteAvanceBeneficiario> findReporteAvanceBeneficiarioXConsulta(
			String consulta, Object[] params);

	ReporteAvanceBeneficiario updateReporteAvanceBeneficiario(
			ReporteAvanceBeneficiario reporteAvanceBeneficiario);
	
	public List<ReporteAvanceBeneficiario> findReporteAvanceBeneficiarioByBeneficiariosPorResultadoId(Integer beneficiariosPorResultadoID);

	ReporteAvanceBeneficiario findReporteAvanceBeneficiarioById(
			Integer reporteAvanceBeneficiarioID);

	void deleteReporteAvanceBeneficiario(
			ReporteAvanceBeneficiario reporteAvanceBeneficiario);

}
