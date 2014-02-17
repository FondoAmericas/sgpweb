package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.bean.CuentaCorrienteBean;
import pe.com.fondam.sgp.core.domain.CuentaCorriente;

public interface CuentaCorrienteService {

	CuentaCorriente findCuentaCorrienteById(int cuentaCorrienteId);

	List<CuentaCorriente> findCuentaCorrienteByDatoProyectoId(
			Integer datoProyectoID);
	
	CuentaCorriente updateCuentaCorriente(CuentaCorriente cuentaCorriente);

	void deleteCuentaCorriente(Integer cuentaCorrienteId);
		
	List<CuentaCorrienteBean> llenaListCuentasCorrientesBean(
			List<CuentaCorriente> listCuentaCorriente);
}
