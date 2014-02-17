package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.CuentaCorriente;

/**
*
*/
public interface CuentaCorrienteDAO {

	void saveCuentaCorriente(CuentaCorriente cuentaCorriente);
	
	CuentaCorriente updateCuentaCorriente(CuentaCorriente cuentaCorriente);
	
	public void deleteCuentaCorriente(CuentaCorriente cuentaCorriente);
	
	public CuentaCorriente findCuentaCorrienteById(Integer id);
	
	public List<CuentaCorriente> findCuentaCorrienteByIdDatoProyecto(int idDatoProyecto);

	List<CuentaCorriente> findCuentaCorrienteByConsulta(String consulta,
			Object[] params);
	
}
