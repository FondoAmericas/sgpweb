package pe.com.fondam.sgp.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.SolicitaRpRfDAO;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.SolicitaRpRf;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.SolicitaRpRfService;

@Service
public class SolicitaRpRfServiceImpl implements SolicitaRpRfService {

	//***************** inyecciones *********************//
	@Resource
	SolicitaRpRfDAO solicitaRpRfDAO;
	
	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	//***************** metodos *********************//
	@Override
	public List<SolicitaRpRf> findSolicitaRpRfByDatoProyectoIdByVersionPo(
			Integer datoProyectoID, String version) {
		
		List<DetalleEstadoCabecera> listDetalleEstadoCabecera=detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estrprf");
Integer estadoRechazadoID=0;
		for (DetalleEstadoCabecera detalleEstadoCabecera : listDetalleEstadoCabecera) {
			if(detalleEstadoCabecera.getPrefijoEstado().equals("recha")){
				estadoRechazadoID=detalleEstadoCabecera.getDetalleEstadoCabeceraID();
			}
		}
		String consulta = "from SolicitaRpRf where datoProyecto.datoProyectoID = ? and versionPo = ? and fkIdDetalleEstadoCabRpRf <> ?";
		Object[] params = new Object[3];
		params[0]=datoProyectoID;
		params[1]=version;
		params[2]=estadoRechazadoID;
		return solicitaRpRfDAO.findSolicitaRpRfByConsulta(consulta, params);
	}

	@Override
	public SolicitaRpRf updateSolicitaRpRf(SolicitaRpRf solicitaRpRf) {
		return solicitaRpRfDAO.updateSolicitaRpRf(solicitaRpRf);
	}

	@Override
	public List<SolicitaRpRf> findSolicitaRpRfByDatoProyectoId(
			Integer datoProyectoID) {
		String consulta = "from SolicitaRpRf where datoProyecto.datoProyectoID = ? ";
		Object[] params = new Object[1];
		params[0]=datoProyectoID;
		return solicitaRpRfDAO.findSolicitaRpRfByConsulta(consulta, params);	
	}

	@Override
	public List<SolicitaRpRf> llenaListSolicitaRpRfCompleto(
			List<SolicitaRpRf> listSolicitaRpRf) {
		
		for (SolicitaRpRf solicitaRpRf : listSolicitaRpRf) {
			solicitaRpRf = llenaSolicitaRpRfCompleto(solicitaRpRf);
		}
		return listSolicitaRpRf;
	}

	@Override
	public SolicitaRpRf llenaSolicitaRpRfCompleto(SolicitaRpRf solicitaRpRf) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		solicitaRpRf.setDescripcionEstadoRpRf(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(solicitaRpRf.getFkIdDetalleEstadoCabRpRf()).getDescripEstado());
		solicitaRpRf.setPrefijoEstadoRpRf(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(solicitaRpRf.getFkIdDetalleEstadoCabRpRf()).getPrefijoEstado());
		solicitaRpRf.setFechaSolicitudString(formato.format(solicitaRpRf.getFechaSolicitud()));
		solicitaRpRf.setFechaAprobacionString(solicitaRpRf.getFechaAprobacion()!=null?formato.format(solicitaRpRf.getFechaAprobacion()):"-------");
		return solicitaRpRf;
	}

	@Override
	public SolicitaRpRf findSolicitaRpRfById(Integer solicitaRpRfId) {
		return solicitaRpRfDAO.findSolicitaRpRfById(solicitaRpRfId);
	}

}
