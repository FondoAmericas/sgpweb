package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.InformeFinalBien;

public interface InformeFinalBienService {

	InformeFinalBien findInformeFinalBienByInformeFinalIdByBienId(
			Integer informeFinalId, Integer bienID);

	List<InformeFinalBien> findInformeFinalBienByBienId(Integer bienID);
	
	InformeFinalBien updateInformeFinalBien(InformeFinalBien informeFinalBienSave);

	void deleteInformeFinalBienByBienId(Integer bienID);

}
