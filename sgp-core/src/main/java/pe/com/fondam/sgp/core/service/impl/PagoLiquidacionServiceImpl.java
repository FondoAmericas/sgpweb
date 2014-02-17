package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.PagoLiquidacionDAO;
import pe.com.fondam.sgp.core.domain.PagoLiquidacion;
import pe.com.fondam.sgp.core.service.DetallePagoLiquidacionService;
import pe.com.fondam.sgp.core.service.PagoLiquidacionService;

@Service
public class PagoLiquidacionServiceImpl implements PagoLiquidacionService {
	
	//********************** inyecciones ************************//
	@Resource
	DetallePagoLiquidacionService detallePagoLiquidacionService;
	
	@Resource
	PagoLiquidacionDAO pagoLiquidacionDAO;
	
	
	//********************** metodos ************************//
	@Override
	public List<PagoLiquidacion> llenaPagoLiquidacionCompletoConListas(
			List<PagoLiquidacion> listPagoLiquidacion) {
		for (PagoLiquidacion pagoLiquidacion : listPagoLiquidacion) {
			pagoLiquidacion.setLstDetallePagoLiquidacion(detallePagoLiquidacionService.llenaDetallePagoLiquidacionCompleto(detallePagoLiquidacionService.findDetallePagoLiquidacionByPagoLiquidacionId(pagoLiquidacion.getPagoLiquidacionID())));
		}
		return listPagoLiquidacion;
	}

	@Override
	public PagoLiquidacion findPagoLiquidacionById(int pagoLiquidacionID) {
		
		return pagoLiquidacionDAO.findPagoLiquidacionById(pagoLiquidacionID);
	}

	@Override
	public PagoLiquidacion updatePagoLiquidacion(PagoLiquidacion pagoLiquidacion) {
		return pagoLiquidacionDAO.updatePagoLiquidacion(pagoLiquidacion);
	}

	@Override
	public List<PagoLiquidacion> findPagoLiquidacionByLiquidacionGastoId(
			Integer liquidacionGastoID) {
		 String consulta =" from PagoLiquidacion where liquidacionGasto.liquidacionGastoID = ? ";
		 
		 Object[] params = new  Object[1];
		 params[0]= liquidacionGastoID;
		 
		return pagoLiquidacionDAO.findPagoLiquidacione(consulta,params);
		
	}

}
