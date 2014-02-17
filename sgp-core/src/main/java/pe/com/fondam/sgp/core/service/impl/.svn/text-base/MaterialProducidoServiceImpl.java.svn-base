package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.MaterialProducidoBean;
import pe.com.fondam.sgp.core.dao.MaterialProducidoDAO;
import pe.com.fondam.sgp.core.domain.MaterialProducido;
import pe.com.fondam.sgp.core.service.MaterialProducidoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class MaterialProducidoServiceImpl implements MaterialProducidoService {

	//************** inyecciones  *********************//
	@Resource
	MaterialProducidoDAO materialProducidoDAO;

	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	
	//************** metodos *********************//
	@Override
	public MaterialProducido updateMaterialProducido(
			MaterialProducido materialProducido) {
		return materialProducidoDAO.updateMaterialProducido(materialProducido);
	}

	@Override
	public List<MaterialProducido> findMaterialProducidoByInformeFinalId(
			Integer informeFinalId) {
		String consulta =" from MaterialProducido where informeFinal.informeFinalID = ? ";
		Object[] params = new Object[1];
		params[0]=informeFinalId;
		
		return materialProducidoDAO.findMaterialProducidoByConsulta(consulta, params);
	}

	@Override
	public List<MaterialProducido> llenaCompletaListMaterialProducido(
			List<MaterialProducido> listMaterialProducido) {
		
		for (MaterialProducido materialProducido : listMaterialProducido) {
			materialProducido.setDescripcionTipoMaterial(tablaEspecificaService.findTablaEspecificaById(materialProducido.getFkIdtablaespTipoMaterial()).getDescripcionCabecera());
		}
		return listMaterialProducido;
	}

	@Override
	public void deleteMaterialProducido(Integer idRegistro) {
		materialProducidoDAO.deleteMaterialProducido(materialProducidoDAO.findMaterialProducidoById(idRegistro));
	}

	@Override
	public List<MaterialProducidoBean> llenaListMaterialProducidoBean(
			List<MaterialProducido> listMaterialProducido) {
		
		List<MaterialProducidoBean> listMaterialProducidoBean = new ArrayList<MaterialProducidoBean>();
		for (MaterialProducido materialProducido : listMaterialProducido) {
			listMaterialProducidoBean.add(llenaMaterialProducidoBean( materialProducido));
		}
		return listMaterialProducidoBean;
	}

	private MaterialProducidoBean llenaMaterialProducidoBean(
			MaterialProducido materialProducido) {
	
		MaterialProducidoBean materialProducidoBean= new MaterialProducidoBean();
		
		materialProducidoBean.setCantidad(materialProducido.getCantidad());
		materialProducidoBean.setDescripcionMaterialProducido(materialProducido.getDescripcionMaterialProducido());
		materialProducidoBean.setDescripcionTipoMaterial(materialProducido.getDescripcionTipoMaterial());
		materialProducidoBean.setFkIdtablaespTipoMaterial(materialProducido.getFkIdtablaespTipoMaterial());
		materialProducidoBean.setInformeFinal(materialProducido.getInformeFinal());
		materialProducidoBean.setMaterialProducidoID(materialProducido.getMaterialProducidoID());
		
		return materialProducidoBean;
	}
	
}
