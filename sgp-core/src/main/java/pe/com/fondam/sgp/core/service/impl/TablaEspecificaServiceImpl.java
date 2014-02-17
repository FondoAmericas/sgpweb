package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.TablaEspecificaDAO;
import pe.com.fondam.sgp.core.dao.TablaGeneralDAO;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.TablaGeneral;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
@Service
public class TablaEspecificaServiceImpl implements TablaEspecificaService{
	
	//******************* inyecciones ******************//
	@Resource
	TablaEspecificaDAO tablaEspecificaDAO;
	
	@Resource
	TablaGeneralDAO tablaGeneralDAO;

	//******************* metodos ******************//
	@Override
	public List<TablaEspecifica> listTablaEspecificaByTablaGeneralId(Integer id) {
		return tablaEspecificaDAO.findTablaEspecificasByTablaGeneralId(id);
	}

	@Override
	public TablaEspecifica findTablaEspecificaById(Integer tablaEspecificaID) {
		return tablaEspecificaDAO.findTablaEspecificaById(tablaEspecificaID);  
	}
	
	@Override
	public List<TablaEspecifica> findTablaEspecificabyPrefijoTablaGeneral(
			String prefijo) {
		 TablaGeneral tablaGeneral=tablaGeneralDAO.findTablaGeneralByPrefijo(prefijo);
		
			return tablaEspecificaDAO.findTablaEspecificasByTablaGeneralId(tablaGeneral.getTablaGeneralID());
	}


	@Override
	public int findIdByDescripcionCabecera(String descripcionCabecera) {
		//String consulta = "";
		//if(descripcionCabecera.compareTo(descripcionCabecera.toLowerCase())==0){
		String	consulta = " from TablaEspecifica where descripcionCabecera LIKE BINARY ('"+descripcionCabecera+"') ";
		/*}else{
			consulta = " from TablaEspecifica where descripcionCabecera=? ";
		}*/
		
		/*Object[] params = new Object[1];
		params[0] = descripcionCabecera;*/
		List<TablaEspecifica> listTablaEspecifica= tablaEspecificaDAO.findTablaEspecificaByConsultaSinParametros(consulta);
		
		if(listTablaEspecifica.size()>0){
			return listTablaEspecifica.get(0).getTablaEspecificaID();
		}
		return 0; //cero significa que extencion no existe

	}


}
