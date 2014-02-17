package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.CompromisoPago;

/**
*
* @author Zolanch Távara
*/
public interface CompromisoPagoDAO {

	void saveCompromisoPago(CompromisoPago compromisoPago);
	
	CompromisoPago updateCompromisoPago(CompromisoPago compromisoPago);
	
	void deleteCompromisoPago(CompromisoPago compromisoPago);
	
	CompromisoPago findCompromisoPagoById(Integer id);


	List<CompromisoPago> findCompromisoPagos(String queryString, Object[] params);

}
