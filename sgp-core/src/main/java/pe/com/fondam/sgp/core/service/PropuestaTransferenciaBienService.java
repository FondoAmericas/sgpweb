package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBien;

public interface PropuestaTransferenciaBienService {

	PropuestaTransferenciaBien findPropuestaTransferenciaBienByPropuestaTransferenciaIdByBienId(
			Integer PropuestaTransferenciaById, Integer bienId);

	PropuestaTransferenciaBien updatePropuestaTransferenciaBien(
			PropuestaTransferenciaBien propuestaTransferenciaBien);

	List<PropuestaTransferenciaBien> findPropuestaTransferenciaBienByPropuestaTransferenciaId(
			int propuestaTransferenciaId);

	PropuestaTransferenciaBien findPropuestaTransferenciaBienById(
			Integer bienBienTransferido);

	List<PropuestaTransferenciaBien> findPropuestaTransferenciaBienByBienId(
			Integer bienId);

	void deletePropuestaTransferenciaBienByBienId(Integer bienID);

}
