package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;



public interface DetalleEstadoCabeceraService {


	List<DetalleEstadoCabecera> findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo(
			String string);

	//int findDetalleEstadoCabecerabyPrefijoEstado(String prefijo);
	
	DetalleEstadoCabecera findDetalleEstadoCabeceraById(
			int detalleEstadoCabeceraId);

	DetalleEstadoCabecera findDetalleEstadoCabecerabyPrefijoEstadoCabeceraByPrefijoDetalleEstado(
			String prefijoEstadoCabecera, String prefijoDetalleEstadoCabecera);

}
