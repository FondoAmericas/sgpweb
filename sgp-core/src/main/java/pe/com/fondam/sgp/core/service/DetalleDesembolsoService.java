package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DetalleDesembolso;

public interface DetalleDesembolsoService {

	List<DetalleDesembolso> findDetalleDesembolsoByDesemboloId(Integer desembolsoID);

	DetalleDesembolso updateDetalleDesembolso(DetalleDesembolso detalleDesembolso);
	
	List<DetalleDesembolso> llenaListDetalleDesembolso(List<DetalleDesembolso> listDetalleDesembolso);

}
