package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DetalleDesembolso;

public interface DetalleDesembolsoDAO {

	public List<DetalleDesembolso> findDetalleDesembolsoByDesembolsoID(Integer desembolsoID);
	
	public void saveDetalleDesembolso(DetalleDesembolso detalleDesembolso);

	public DetalleDesembolso updateDetalleDesembolso(DetalleDesembolso detalleDesembolso);

}
