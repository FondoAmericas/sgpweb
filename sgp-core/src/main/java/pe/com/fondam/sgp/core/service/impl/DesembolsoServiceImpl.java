package pe.com.fondam.sgp.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.dao.DesembolsoDAO;
import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.service.DesembolsoService;
import pe.com.fondam.sgp.core.service.DetalleDesembolsoService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.FuenteFinanciadoraService;
import pe.com.fondam.sgp.core.service.ObservacionService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class DesembolsoServiceImpl implements DesembolsoService {

	/************** inyecciones ***************/
	@Resource
	DesembolsoDAO desembolsoDAO;

	@Resource
	TablaEspecificaService tablaEspecificaService;

	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	@Resource
	FuenteFinanciadoraService fuenteFinanciadoraService;
	
	@Resource
	DetalleDesembolsoService detalleDesembolsoService;
	
	@Resource
	ObservacionService observacionService;

	/************** metodos ***************/
	@Override
	public List<Desembolso> findDesembolsoByDatoProyectoID(
			Integer datoProyectoID) {
		return desembolsoDAO.findDesembolsoByDatoProyectoID(datoProyectoID);
	}

	@Override
	public Desembolso updateDesembolso(Desembolso desembolso) {
		return desembolsoDAO.updateDesembolso(desembolso);
	}

	@Override
	public List<Desembolso> findDesembolsoByDatoProyectoIDByFuenteFinanciadoraId(
			Integer datoProyectoId, Integer fuenteFinanciadoraId) {
		String queryString = "from Desembolso where datoProyecto.datoProyectoID = ? and fuenteFinanciadora.fuenteFinanciadoraID = ? order by desembolsoID asc";
		Object[] params = new Object[2];
		params[0] = datoProyectoId;
		params[1] = fuenteFinanciadoraId;
		return desembolsoDAO.findDesembolso(queryString, params);
	}

	@Override
	public List<Desembolso> llenaDesembolsoCompleto(
			List<Desembolso> listDesembolso) {

		for (Desembolso desembolso : listDesembolso) {
			desembolso.setDescripcionEstDesembolso(detalleEstadoCabeceraService
					.findDetalleEstadoCabeceraById(
							desembolso.getFkIdtablaespEstDesembolso())
					.getDescripEstado());
			desembolso.setDescripcionTipoDesembolso(tablaEspecificaService
					.findTablaEspecificaById(
							desembolso.getFkIdtablaespTipoDesembolso())
					.getDescripcionCabecera());
			desembolso.setDescripcionTipoMoneda(tablaEspecificaService
					.findTablaEspecificaById(
							desembolso.getFkIdtablaespTipoMoneda())
					.getDescripcionCabecera());
		}
		return listDesembolso;
	}

	@Override
	public Desembolso findDesembolsoByID(int desembolsoId) {
		return desembolsoDAO.findDesembolsoById(desembolsoId);
	}

	@Override
	public Desembolso llenaDesembolsoCompleto(Desembolso desembolso) {
		desembolso.setDescripcionTipoDesembolso(tablaEspecificaService.findTablaEspecificaById(desembolso.getFkIdtablaespTipoDesembolso()).getDescripcionCabecera());
		desembolso.setDescripcionTipoMoneda(tablaEspecificaService.findTablaEspecificaById(desembolso.getFkIdtablaespTipoMoneda()).getDescripcionCabecera());
		desembolso.setDescripcionEstDesembolso(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(desembolso.getFkIdtablaespEstDesembolso()).getDescripEstado());
		desembolso.setCantObservacionesRelevantes(observacionService.findObservacionesRelevatesAlDocumento(desembolso.getDatoProyecto().getDatoProyectoID(),desembolso.getDesembolsoID(),FondamConstans.TABLA_CLASE_NOMBRE_DESEMBOLSO));
		
		List<FuenteFinanciadora> listFuenteFinanciadora=fuenteFinanciadoraService.findFuenteFinanciadoraByDatoProyectoId(desembolso.getDatoProyecto().getDatoProyectoID());
		for (FuenteFinanciadora fuenteFinanciadora : listFuenteFinanciadora) {
			if(fuenteFinanciadora.getDefineSiEsEjecutor().equals(1)){
				desembolso.setInstitucionEjecutora(fuenteFinanciadora.getInstitucion());
			}
		}
		desembolso.setListDetalleDesembolso(detalleDesembolsoService.llenaListDetalleDesembolso(detalleDesembolsoService.findDetalleDesembolsoByDesemboloId(desembolso.getDesembolsoID())));

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		desembolso.setFechaSolicitudString(formato.format(desembolso.getFechaSolicitud()));
		
		return desembolso;
	}

	@Override
	public Integer findDesembolsoByDatoProyectoIDYAprobado(
			Integer datoProyectoID, String prefijoEstadoDesembolsoAprobado, String prefijoEstadoDesembolso) {
		
int estadoId=detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraByPrefijoDetalleEstado(prefijoEstadoDesembolso, prefijoEstadoDesembolsoAprobado).getDetalleEstadoCabeceraID() ;
		
		String queryString = "from Desembolso where datoProyecto.datoProyectoID = ? and fkIdtablaespEstDesembolso = ? ";
		Object[] params = new Object[2];
		params[0] = datoProyectoID;
		params[1] = estadoId;
		List<Desembolso> listDesembolso=desembolsoDAO.findDesembolso(queryString, params);
		
		return listDesembolso.size();
	}

	

}
