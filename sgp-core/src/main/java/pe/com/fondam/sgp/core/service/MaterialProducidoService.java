package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.bean.MaterialProducidoBean;
import pe.com.fondam.sgp.core.domain.MaterialProducido;

public interface MaterialProducidoService {

	MaterialProducido updateMaterialProducido(MaterialProducido materialProducido);

	List<MaterialProducido> findMaterialProducidoByInformeFinalId(Integer informeFinalId);

	List<MaterialProducido> llenaCompletaListMaterialProducido(
			List<MaterialProducido> listMaterialProducido);

	void deleteMaterialProducido(Integer idRegistro);

	List<MaterialProducidoBean> llenaListMaterialProducidoBean(
			List<MaterialProducido> listMaterialProducido);

}
