package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.CuentaCorrienteBean;
import pe.com.fondam.sgp.core.dao.CuentaCorrienteDAO;
import pe.com.fondam.sgp.core.domain.CuentaCorriente;
import pe.com.fondam.sgp.core.service.CuentaCorrienteService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class CuentaCorrienteServiceImpl implements CuentaCorrienteService {

	//******  inyecciones  **********//
	@Resource
	CuentaCorrienteDAO cuentaCorrienteDAO;

	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	//******  metodos **********//
	@Override
	public CuentaCorriente findCuentaCorrienteById(int cuentaCorrienteId) {
		return cuentaCorrienteDAO.findCuentaCorrienteById(cuentaCorrienteId);
	}

	@Override
	public List<CuentaCorriente> findCuentaCorrienteByDatoProyectoId(
			Integer datoProyectoID) {
		String consulta= " from CuentaCorriente where datoProyecto.datoProyectoID = ?";
		Object[] params= new Object[1];
		params[0]=datoProyectoID;
		
		return cuentaCorrienteDAO.findCuentaCorrienteByConsulta(consulta, params);
	}

	@Override
	public CuentaCorriente updateCuentaCorriente(CuentaCorriente cuentaCorriente) {
		return cuentaCorrienteDAO.updateCuentaCorriente(cuentaCorriente);
	}

	@Override
	public void deleteCuentaCorriente(Integer cuentaCorrienteId) {
		cuentaCorrienteDAO.deleteCuentaCorriente(cuentaCorrienteDAO.findCuentaCorrienteById(cuentaCorrienteId));
		
	}
	
	@Override
	public List<CuentaCorrienteBean> llenaListCuentasCorrientesBean(
			List<CuentaCorriente> listCuentaCorriente) {

		List<CuentaCorrienteBean> listCuentaCorrienteBean = new ArrayList<CuentaCorrienteBean>();
		for (CuentaCorriente cuentaCorriente : listCuentaCorriente) {
			CuentaCorrienteBean cuentaCorrienteBean = new CuentaCorrienteBean();
			cuentaCorrienteBean.setCuentaCorrienteID(String
					.valueOf(cuentaCorriente.getCuentaCorrienteID()));
			cuentaCorrienteBean.setFkIdtablaespBanco(String
					.valueOf(cuentaCorriente.getFkIdtablaespBanco()));
			cuentaCorrienteBean.setDescripcionBanco(tablaEspecificaService
					.findTablaEspecificaById(
							cuentaCorriente.getFkIdtablaespBanco())
					.getDescripcionCabecera());
			cuentaCorrienteBean.setFkIdtablaespTipomoneda(String
					.valueOf(cuentaCorriente.getFkIdtablaespTipomoneda()));
			cuentaCorrienteBean.setDescripcionTipoMoneda(tablaEspecificaService
					.findTablaEspecificaById(
							cuentaCorriente.getFkIdtablaespTipomoneda())
					.getDescripcionCabecera());
			cuentaCorrienteBean.setNumeroCuenta(cuentaCorriente
					.getNumeroCuenta());
			cuentaCorrienteBean.setDatoProyecto(String.valueOf(cuentaCorriente
					.getDatoProyecto().getDatoProyectoID()));
			cuentaCorrienteBean.setFlagReferencia(cuentaCorriente
					.getFlagReferencia());
			listCuentaCorrienteBean.add(cuentaCorrienteBean);
		}
		return listCuentaCorrienteBean;
	}
	
	
}
