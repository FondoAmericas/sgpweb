package pe.com.fondam.sgp.core.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.dao.ActividadDAO;
import pe.com.fondam.sgp.core.dao.ActividadDetallePagoDAO;
import pe.com.fondam.sgp.core.dao.ActivoDAO;
import pe.com.fondam.sgp.core.dao.CompromisoPagoDAO;
import pe.com.fondam.sgp.core.dao.CostoActividadDAO;
import pe.com.fondam.sgp.core.dao.CronogramaCostoActividadDAO;
import pe.com.fondam.sgp.core.dao.DatoPlanOperativoDAO;
import pe.com.fondam.sgp.core.dao.DatoUsuarioDAO;
import pe.com.fondam.sgp.core.dao.DesembolsoDAO;
import pe.com.fondam.sgp.core.dao.DetalleEstadoCabeceraDAO;
import pe.com.fondam.sgp.core.dao.DetallePagoLiquidacionDAO;
import pe.com.fondam.sgp.core.dao.FuenteFinanciadoraDAO;
import pe.com.fondam.sgp.core.dao.ImagenOArchivoDAO;
import pe.com.fondam.sgp.core.dao.IngresoPropioDAO;
import pe.com.fondam.sgp.core.dao.LiquidacionGastoDAO;
import pe.com.fondam.sgp.core.dao.PagoLiquidacionDAO;
import pe.com.fondam.sgp.core.dao.RaLgDAO;
import pe.com.fondam.sgp.core.dao.ReporteAvanceDAO;
import pe.com.fondam.sgp.core.dao.ResultadoDAO;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.ActividadDetallePago;
import pe.com.fondam.sgp.core.domain.Activo;
import pe.com.fondam.sgp.core.domain.CompromisoPago;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.DetallePagoLiquidacion;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;
import pe.com.fondam.sgp.core.domain.IngresoPropio;
import pe.com.fondam.sgp.core.domain.LiquidacionGasto;
import pe.com.fondam.sgp.core.domain.PagoLiquidacion;
import pe.com.fondam.sgp.core.domain.RaLg;
import pe.com.fondam.sgp.core.domain.ReporteAvance;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.domain.TipoCambio;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.DetallePagoLiquidacionService;
import pe.com.fondam.sgp.core.service.LiquidacionGastoService;
import pe.com.fondam.sgp.core.service.ObservacionService;
import pe.com.fondam.sgp.core.service.PagoLiquidacionService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.TipoCambioService;
@Service
public class LiquidacionGastoServiceImpl implements LiquidacionGastoService{
	
	//************ inyecciones *******************//
	@Resource
	LiquidacionGastoDAO liquidacionGastoDAO;
	
	@Resource
	RaLgDAO raLgDAO;
	
	@Resource
	ReporteAvanceDAO reporteAvanceDAO ;
	
	@Resource
	FuenteFinanciadoraDAO fuenteFinanciadoraDAO;
	
	@Resource
	DatoUsuarioDAO datoUsuarioDAO;
	
	@Resource
	ResultadoDAO resultadoDAO;
	
	@Resource
	ActividadDAO actividadDAO;
	
	@Resource
	CostoActividadDAO costoActividadDAO;

	@Resource
	CronogramaCostoActividadDAO cronogramaCostoActividadDAO;

	@Resource
	IngresoPropioDAO ingresoPropioDAO;
	
	@Resource
	CompromisoPagoDAO compromisoPagoDAO;
	
	@Resource
	PagoLiquidacionDAO 	pagoLiquidacionDAO;
	
	@Resource
	ActivoDAO 	activoDAO;
	
	@Resource
	DetallePagoLiquidacionDAO 	detallePagoLiquidacionDAO;
	
	@Resource
	ActividadDetallePagoDAO 	actividadDetallePagoDAO;
	
	@Resource
	DesembolsoDAO 	desembolsoDAO;
	
	@Resource
	ImagenOArchivoDAO 	imagenOArchivoDAO;
	
	@Resource
	TipoCambioService tipoCambioService;
	
	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	@Resource
	DatoPlanOperativoDAO datoPlanOperativoDAO;
	
	@Resource
	DetallePagoLiquidacionService detallePagoLiquidacionService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	PagoLiquidacionService pagoLiquidacionService;
	
	@Resource
	DetalleEstadoCabeceraDAO detalleEstadoCabeceraDAO;
	
	@Resource
	ObservacionService observacionService;
	
	
	//*************** metodos *****************//
	@Override
	public List<RaLg> findRaLgByDatoProyectoID(Integer datoProyectoID) {
		return raLgDAO.findRaLgByDatoProyectoID(datoProyectoID);
	}

	@Override
	public List<FuenteFinanciadora> findFuenteFinanciadoraByDatoProyectoID(Integer datoProyectoID) {
		return fuenteFinanciadoraDAO.findFuenteFinanciadoraByDatoProyectoID(datoProyectoID);
	}

	@Override
	public void saveLiquidacionGasto(LiquidacionGasto liquidacionGasto) {
		liquidacionGastoDAO.saveLiquidacionGasto(liquidacionGasto);
		
	}
	
	@Override
	public LiquidacionGasto updateLiquidacionGasto(LiquidacionGasto liquidacionGasto) {
		return liquidacionGastoDAO.updateLiquidacionGasto(liquidacionGasto);
		
	}

	@Override
	public DatoUsuario findDatoUsuarioById(Integer datoUsuarioID) {
		return datoUsuarioDAO.findDatoUsuarioById(datoUsuarioID);
	}


	/*@Override 
	public List<LiquidacionGasto> findLiquidacionGastoByFuenteFinanciadoraID(
			LiquidacionGasto liquidacionGasto) throws ParseException {
		List<LiquidacionGasto> listLiquidacionGasto=llenaListLiquidacionGastoCompleto( liquidacionGastoDAO.findLiquidacionGastosByFuenteFinanciadora(liquidacionGasto));
		
		return listLiquidacionGasto;
	}*/

	@Override 
	public List<LiquidacionGasto> findLiquidacionGastoByFuenteFinanciadoraID(
			Integer datoProyectoID,Integer fuenteFinanciadoraID) throws ParseException{
		List<LiquidacionGasto> listLiquidacionGasto=llenaListLiquidacionGastoCompleto( liquidacionGastoDAO.findLiquidacionGastosByFuenteFinanciadora(datoProyectoID,fuenteFinanciadoraID));
		
		return listLiquidacionGasto;
	}
	
	@Override
	public List<LiquidacionGasto> findLiquidacionGastoByDatoProyectoID(
			Integer datoProyectoID) throws ParseException {
		
		String queryString = "from LiquidacionGasto where datoProyecto.datoProyectoID= ?";
		Object[] params = new Object[1];
		params[0] = datoProyectoID;
	
		List<LiquidacionGasto> listLiquidacionGasto=new ArrayList<LiquidacionGasto>(); 
		listLiquidacionGasto=liquidacionGastoDAO.findLiquidacionesGasto(queryString, params);
		if(listLiquidacionGasto.size()>0){
			listLiquidacionGasto=llenaListLiquidacionGastoCompleto(listLiquidacionGasto);
		}
		return listLiquidacionGasto;
	}

	@Override
	public int finNumLiqParcialByPeriodoByDatoProyectoID(
			String periodo, Integer datoProyectoID) {
		
		String queryString = "from LiquidacionGasto where datoProyecto.datoProyectoID= ? and periodo=?";
		Object[] params = new Object[2];
		params[0] = datoProyectoID;
		params[1] = periodo;
		
		List<LiquidacionGasto> listLiquidacionGasto=liquidacionGastoDAO.findLiquidacionesGasto(queryString, params);
		return listLiquidacionGasto.size();
	}

	@Override
	public ReporteAvance getReporteAvanceByID(int reporteAvance) {
			return reporteAvanceDAO.findReporteAvanceById(reporteAvance);	
	}

	@Override
	public void saveRaLg(RaLg ralg) {
		raLgDAO.saveRaLg(ralg);
		
	}

	@Override
	public List<Resultado> findResultadoByDatoProyectoID(Integer datoProyectoID) {
		DatoPlanOperativo datoPlanOperativo= datoPlanOperativoDAO.findDatoPlanOperativoByDatoProyectoID(datoProyectoID);
		return resultadoDAO.findListResultadoByDatoPlanOperativoID(datoPlanOperativo.getDatoPlanOperativoID());
	}

	@Override
	public List<Actividad> findActidadByResultadoID(Integer resultadoID) {
		return actividadDAO.findActividadByResultadoID(resultadoID);
	}

	@Override
	public LiquidacionGasto findLiquidacionGastoByID(int liquidacionPagoID) {
		return liquidacionGastoDAO.findLiquidacionGastoById(liquidacionPagoID);
	}

	@Override
	public List<IngresoPropio> findIngresoPropioByLiquidacionGasto(
			int liquidacionGastoID) {


		String queryString = "from IngresoPropio where liquidacionGasto.liquidacionGastoID= ? ";
		Object[] params = new Object[1];
		params[0] = liquidacionGastoID;

		List<IngresoPropio> listIngresoPropio=ingresoPropioDAO.findIngresoPropio(queryString, params);

		return listIngresoPropio;
	}

	@Override
	public IngresoPropio findIngresoPropioByID(int ingresoPropioID) {
		return ingresoPropioDAO.findIngresoPropioById(ingresoPropioID);
	}
	
	@Override
	public void updateIngresoPropio(IngresoPropio ingresoPropio) {
		ingresoPropioDAO.updateIngresoPropio(ingresoPropio);
	}
	
	@Override
	public void saveIngresoPropio(IngresoPropio ingresoPropio) {
		ingresoPropioDAO.saveIngresoPropio(ingresoPropio);
	}

	@Override
	public List<CostoActividad> findCostoActividadByActividadID(int actividadID) {
		return costoActividadDAO.findCostoActividadByActividadID(actividadID);
	}

	@Override
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadID(
			int costoActividadID) {
		return cronogramaCostoActividadDAO.findCronogramaCostoActividadByCostoActividadID(costoActividadID);
	}

	@Override
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadIDByPeriodoID(
			int costoActividadID, int periodoID) {
		String queryString = "from CronogramaCostoActividad where costoActividad.costoActividadID= ?  and periodo=?  and estadoEliminado=1";
		Object[] params = new Object[2];
		params[0] = costoActividadID;
		params[1] = periodoID;


		List<CronogramaCostoActividad> listCronogramaCostoActividad=cronogramaCostoActividadDAO.findCronogramaCostoActividad(queryString, params);

		return listCronogramaCostoActividad;
	}

	@Override
	public CronogramaCostoActividad findCronogramaCostoActividadByID(
			Integer cronogramaCostoActividadID) {
		return cronogramaCostoActividadDAO.findCronogramaCostoActividadById(cronogramaCostoActividadID);
	}

	@Override
	public CostoActividad findCostoActividadByID(Integer costoActividadID) {
		return costoActividadDAO.findCostoActividadById(costoActividadID);
	}

	@Override
	public Actividad findActividadByID(Integer actividadID) {
		return actividadDAO.findActividadById(actividadID);
	}

	@Override
	public CompromisoPago findCompromisoPagoByID(int compromisoPagoID) {
		return compromisoPagoDAO.findCompromisoPagoById(compromisoPagoID);
	}

	@Override
	public void saveCompromisoPago(CompromisoPago compromisoPago) {
		compromisoPagoDAO.saveCompromisoPago(compromisoPago);
		
	}

	@Override
	public void updateCompromisoPago(CompromisoPago compromisoPago) {
		compromisoPagoDAO.updateCompromisoPago(compromisoPago);
		
	}

	@Override
	public List<CompromisoPago> findCompromisoPagoByLiquidacionGasto(
			int liquidacionGastoID) {
		String queryString = "from CompromisoPago where liquidacionGasto.liquidacionGastoID= ? ";
		Object[] params = new Object[1];
		params[0] = liquidacionGastoID;

		List<CompromisoPago> listCompromisoPago=compromisoPagoDAO.findCompromisoPagos(queryString, params);

		return listCompromisoPago;
	}

	@Override
	public List<PagoLiquidacion> findIngresoPagoByliquidacionGastoID(
			int liquidacionGastoID) {
		String queryString = "from PagoLiquidacion where liquidacionGasto.liquidacionGastoID= ? ";
		Object[] params = new Object[1];
		params[0] = liquidacionGastoID;

		List<PagoLiquidacion> listPagoLiquidacion=pagoLiquidacionDAO.findPagoLiquidacione(queryString, params);

		return listPagoLiquidacion;
	}

	@Override
	public void savePagoLiquidacion(PagoLiquidacion pagoLiquidacion) {
		pagoLiquidacionDAO.savePagoLiquidacion(pagoLiquidacion);
	}

	@Override
	public PagoLiquidacion findPagoLiquidacionByID(int pagoLiquidacionID) {
		return pagoLiquidacionDAO.findPagoLiquidacionById(pagoLiquidacionID);
	}

	@Override
	public void saveActivo(Activo activo) {
		activoDAO.saveActivo(activo);
	}

	@Override
	public void saveDetallePagoLiquidacion(DetallePagoLiquidacion detallePagoLiquidacion) {
		
		detallePagoLiquidacionDAO.saveDetallePagoLiquidacion(detallePagoLiquidacion);
	}

	@Override
	public List<DetallePagoLiquidacion> findDetallePagoLiquidacionByPagoLiquidacionID(
			int pagoLiquidacionID) {
		String queryString = "from DetallePagoLiquidacion where pagoLiquidacion.pagoLiquidacionID= ? ";
		Object[] params = new Object[1];
		params[0] = pagoLiquidacionID;

		List<DetallePagoLiquidacion> listDetallePagoLiquidacion=detallePagoLiquidacionDAO.findDetallePagoLiquidacion(queryString, params);

		return listDetallePagoLiquidacion;	
		}

	@Override
	public List<Desembolso> findDesembolsoByBLiquidacionGasto(
			LiquidacionGasto liquidacionGasto) {
		String queryString = "from Desembolso where datoProyecto.datoProyectoID= ? and fuenteFinanciadora.fuenteFinanciadoraID=? and fkIdtablaespEstDesembolso=23 ";
		Object[] params = new Object[2];
		params[0] = liquidacionGasto.getDatoProyecto().getDatoProyectoID();
		params[1] = liquidacionGasto.getFuenteFinanciadora().getFuenteFinanciadoraID();

		List<Desembolso> listDesembolso=desembolsoDAO.findDesembolso(queryString, params);

		return listDesembolso;
	}

	@Override
	public void saveActividadDetallePago(
			ActividadDetallePago actividadDetallePago) {
		actividadDetallePagoDAO.saveActividadDetallePago(actividadDetallePago);
	}

	@Override
	public IngresoPropio getIngresoPropioByComprobanteByRuc(String comprobate,
			String ruc) {
		String queryString = "from IngresoPropio where numeroComprobante=? and rucComprador=? ";
		Object[] params = new Object[2];
		params[0] = comprobate;
		params[1] = ruc;

		List<IngresoPropio> listIngresoPropio=ingresoPropioDAO.findIngresoPropio(queryString, params);
		if (!listIngresoPropio.isEmpty()) {
			return listIngresoPropio.get(0);
		}

		return null;
	}

	@Override
	public PagoLiquidacion getPagoLiquidacionByComprobanteByRuc(String comprobate,
			String ruc) {
		String queryString = "from PagoLiquidacion where numeroDocumento=? and rucProveedor=? ";
		Object[] params = new Object[2];
		params[0] = comprobate;
		params[1] = ruc;

		List<PagoLiquidacion> listPagoLiquidacion=pagoLiquidacionDAO.findPagoLiquidacione(queryString, params);
		if (!listPagoLiquidacion.isEmpty()) {
			return listPagoLiquidacion.get(0);
		}

		return null;
	}

	@Override
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadIDByFuenteFinanciadoraID(
			int costoActividadID, int fuenteFinanciadoraID) {
		String queryString = "from CronogramaCostoActividad where costoActividad.costoActividadID= ?  and fuenteFinanciadora.fuenteFinanciadoraID=? and estadoEliminado=1";
		Object[] params = new Object[2];
		params[0] = costoActividadID;
		params[1] = fuenteFinanciadoraID;

		List<CronogramaCostoActividad> listCronogramaCostoActividad=cronogramaCostoActividadDAO.findCronogramaCostoActividad(queryString, params);

		return listCronogramaCostoActividad;
	}

	@Override
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByPeriodoID(
			int periodoID) {
		String queryString = "from CronogramaCostoActividad where  cronogramaCostoActividadID=? and estadoLiquidacion=0 and estadoEliminado=1";
		Object[] params = new Object[1];
		params[0] = periodoID;


		List<CronogramaCostoActividad> listCronogramaCostoActividad=cronogramaCostoActividadDAO.findCronogramaCostoActividad(queryString, params);

		return listCronogramaCostoActividad;
	}

	@Override
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByFuenteFinanciadoraID(
			Integer fuenteFinanciadoraID) {
		String queryString = "from CronogramaCostoActividad where  fuenteFinanciadora.fuenteFinanciadoraID=? and estadoLiquidacion=0  and estadoEliminado=1";
		Object[] params = new Object[1];
		params[0] = fuenteFinanciadoraID;


		List<CronogramaCostoActividad> listCronogramaCostoActividad=cronogramaCostoActividadDAO.findCronogramaCostoActividad(queryString, params);

		return listCronogramaCostoActividad;

	}

	@Override
	public PagoLiquidacion updatePagoLiquidacion(PagoLiquidacion pagoLiquidacion) {
		return pagoLiquidacionDAO.updatePagoLiquidacion(pagoLiquidacion);	
	}

	@Override
	public DetallePagoLiquidacion findDetallePagoLiquidacionByID(
			int detallePagoID) {
		return detallePagoLiquidacionDAO.findDetallePagoLiquidacionById(detallePagoID);
	}

	@Override
	public double getSaldoDisponibleIngresoPago(
			LiquidacionGasto liquidacionGasto) {

		///////*********************  REVISAR  **************************//////////////
		
		String queryString = "from LiquidacionGasto where  fuenteFinanciadora.fuenteFinanciadoraID=? and datoProyecto.datoProyectoID=?";
		Object[] params = new Object[2];
		params[0] = liquidacionGasto.getFuenteFinanciadora().getFuenteFinanciadoraID();
		params[1] = liquidacionGasto.getDatoProyecto().getDatoProyectoID();
		
		List<LiquidacionGasto> listLiquidacion=liquidacionGastoDAO.findLiquidacionesGasto(queryString, params);
		double montoTotal=0;
		
		for (LiquidacionGasto Lgasto : listLiquidacion) {
			String queryString1 = "from PagoLiquidacion where  liquidacionGasto.liquidacionGastoID=?";
			Object[] params1 = new Object[1];
			params1[0] = Lgasto.getLiquidacionGastoID();
			
			List<PagoLiquidacion> listPagoLiquidacion=pagoLiquidacionDAO.findPagoLiquidacione(queryString1, params1);
			
			if (!listPagoLiquidacion.isEmpty()) {
				for (PagoLiquidacion pagoLiquidacion : listPagoLiquidacion) {
					double montoCambiado=0; 
					if (pagoLiquidacion.getFkIdtablaespTipoMoneda().equals(liquidacionGasto.getFuenteFinanciadora().getFkIdtablaespTipoMoneda())) {
						montoTotal+=pagoLiquidacion.getMontoTotal();		
					}else {
							TipoCambio tipoCambio= tipoCambioService.findTipoCambioByTipoMonedaDeAByDesembolsoID(pagoLiquidacion.getFkIdtablaespTipoMoneda(),liquidacionGasto.getFuenteFinanciadora().getFkIdtablaespTipoMoneda(),pagoLiquidacion.getDesembolsoID());
							montoCambiado=pagoLiquidacion.getMontoTotal()/tipoCambio.getTipoCambio();
							montoTotal+=montoCambiado;	   		
						}
				}	
			}			
		}
		
		if (montoTotal==0) {
			return  liquidacionGasto.getFuenteFinanciadora().getMontoFinanciado();
		}else {
			double resultado=liquidacionGasto.getFuenteFinanciadora().getMontoFinanciado()-montoTotal;
			if (resultado>=0) {
				return resultado;
			}else {
				return 0 ;
			}	
		}			
	}

	@Override
	public ImagenOArchivo findPagoLiquidacionByArchivoImagen(
			Integer pagoLiquidacionID) {
		String queryString = "from ImagenOArchivo where informeVisitaCampo.informeVisitaCampoID=null  and reporteAvance.reporteAvanceID=null and programa.programaID=null and pagoLiquidacion.pagoLiquidacionID=null and pagoLiquidacion.pagoLiquidacionID=?";
		Object[] params = new Object[1];
		params[0] = pagoLiquidacionID;


		List<ImagenOArchivo> listImagenOArchivo=imagenOArchivoDAO.findImagenOArchivo(queryString, params);
		if (!listImagenOArchivo.isEmpty()) {
			return listImagenOArchivo.get(0);
		}
		return null;
	}

	@Override
	public void updateImagenOArchivoPagoLiquidacion(
			ImagenOArchivo imagenOArchivo) {
			imagenOArchivoDAO.updateImagenOArchivo(imagenOArchivo);
			
	}

	@Override
	public void saveImagenOArchivoPagoLiquidacion(ImagenOArchivo imagenOArchivo) {
		imagenOArchivoDAO.saveImagenOArchivo(imagenOArchivo);
	}

	@Override
	public Desembolso findDesembolsoByID(Integer desembolsoID) {
		return desembolsoDAO.findDesembolsoById(desembolsoID);
	}

	@Override
	public List<ActividadDetallePago> findActividadDetallePagoByDetallePagoID(
			int detallePagoID) {
		String queryString = "from ActividadDetallePago where  detallePagoLiquidacion.detallePagoLiquidacionID=? and  estadoEliminado=1";
		Object[] params = new Object[1];
		params[0] = detallePagoID;


		List<ActividadDetallePago> listActividadDetallePago=actividadDetallePagoDAO.findActividadDetallePago(queryString, params);
		return listActividadDetallePago;
	}

	@Override
	public List<ActividadDetallePago> findActividadDetallePagoByCostoActividad(
			int costoActividadID) {
		String queryString = "from ActividadDetallePago where  cronogramaCostoActividad.costoActividad.costoActividadID=?  and estadoEliminado=1";
		Object[] params = new Object[1];
		params[0] = costoActividadID;


		List<ActividadDetallePago> listActividadDetallePago=actividadDetallePagoDAO.findActividadDetallePago(queryString, params);
		return listActividadDetallePago;

	}

	@Override
	public ActividadDetallePago findActividadDetallePagoByID(
			int actividadDetallePagoID) {
		return actividadDetallePagoDAO.findActividadDetallePagoById(actividadDetallePagoID);
	}

	@Override
	public void deleteActividadDetallePago(
			ActividadDetallePago actividadDetallePago) {
		actividadDetallePagoDAO.deleteActividadDetallePago(actividadDetallePago);
	}

	@Override
	public CompromisoPago findCompromisoPagoByCronogramaIDByLiquidacionID(
			int cronograma, int liquidacionGastoID) {
		String queryString = "from CompromisoPago where  cronogramaCostoActividad.cronogramaCostoActividadID=? and liquidacionGasto.liquidacionGastoID=?";
		Object[] params = new Object[2];
		params[0] = cronograma;
		params[1] = liquidacionGastoID;


		List<CompromisoPago> listCompromisoPago=compromisoPagoDAO.findCompromisoPagos(queryString, params);
		
		if (!listCompromisoPago.isEmpty()) {
			return listCompromisoPago.get(0);
		}
		return null;
	}

	@Override
	public ActividadDetallePago findActividadDetallePagoByCronogramaIDByLiquidacionID(
			int cronograma, int liquidacionGastoID) {
		String queryString = "from ActividadDetallePago where  cronogramaCostoActividad.cronogramaCostoActividadID=? and detallePagoLiquidacion.pagoLiquidacion.liquidacionGasto.liquidacionGastoID=? and estadoEliminado=1";
		Object[] params = new Object[2];
		params[0] = cronograma;
		params[1] = liquidacionGastoID;

		List<ActividadDetallePago> listActividadDetallePago=actividadDetallePagoDAO.findActividadDetallePago(queryString, params);
		
		if (!listActividadDetallePago.isEmpty()) {
			return listActividadDetallePago.get(0);
		}
		
		return null;
	}

	@Override
	public List<Actividad> findActidad() {
	
		return actividadDAO.findActividad();
	}

	@Override
	public List<CostoActividad> findCostoActividad() {
		String queryString = "from CostoActividad";
		
		return costoActividadDAO.findCostoActividad(queryString, null);
	}

	@Override
	public void deleteIngresoPropio(IngresoPropio ingresoPropio) {
		ingresoPropioDAO.deleteIngresoPropio(ingresoPropio);
	}

	@Override
	public void deleteCompromisoPago(CompromisoPago compromisoPago) {
       compromisoPagoDAO.deleteCompromisoPago(compromisoPago);		
	}

	@Override
	public void deletePagoLiquidacion(Integer pagoLiquidacionId) {
		pagoLiquidacionDAO.deletePagoLiquidacion(pagoLiquidacionDAO.findPagoLiquidacionById(pagoLiquidacionId));		
	}

	@Override
	public void deleteDetallePago(DetallePagoLiquidacion detallePagoLiquidacion) {
		DetallePagoLiquidacion detallePagoLiquidacionNew=findDetallePagoLiquidacionByID(detallePagoLiquidacion.getDetallePagoLiquidacionID());
		detallePagoLiquidacionDAO.deleteDetallePagoLiquidacion(detallePagoLiquidacionNew);	
	}

	@Override
	public Activo findActivoById(Integer activoID) {
		return activoDAO.findActivoById(activoID);
	}

	@Override
	public void updateActivo(Activo activo) {
		activoDAO.updateActivo(activo);		
	}

	@Override
	public void updateDetallePagoLiquidacion(
			DetallePagoLiquidacion detallePagoLiquidacion) {
		detallePagoLiquidacionDAO.updateDetallePagoLiquidacion(detallePagoLiquidacion);
	}

	@Override
	public void deleteLiquidacionGasto(LiquidacionGasto liquidacionGasto) {
		liquidacionGastoDAO.deleteLiquidacionGasto(liquidacionGasto);
	}

	@Override
	public void deleteRaLg(RaLg raLg) {
		raLgDAO.deleteRaLg(raLg);		
	}

	@Override
	public RaLg findRaLgByLiquidcionGastoID(int liquidacionGastoID) {
		return raLgDAO.findRaLgByLiquidcionGastoID(liquidacionGastoID);
	}

	@Override
	public List<LiquidacionGasto> findLiquidacionGastoByDatoProyectoIDSinAprobar(
			Integer datoProyectoID) {
		List<DetalleEstadoCabecera> listDetalleEstadoCabecera=detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estligs");
		Integer estadoAprobadoID=0;
				for (DetalleEstadoCabecera detalleEstadoCabecera : listDetalleEstadoCabecera) {
					if(detalleEstadoCabecera.getPrefijoEstado().equals("apro")){
						estadoAprobadoID=detalleEstadoCabecera.getDetalleEstadoCabeceraID();
					}
				}
		String queryString = "from LiquidacionGasto where datoProyecto.datoProyectoID= ? and fkIdDetalleEstadoCabEstLiqGasto <> ? "; 
		Object[] params = new Object[2];
		params[0] = datoProyectoID;
		params[1] = estadoAprobadoID;

		return liquidacionGastoDAO.findLiquidacionesGasto(queryString, params);
	}
	
//*********************** listas ************************//
	
	private List<LiquidacionGasto> llenaListLiquidacionGastoCompleto(
			List<LiquidacionGasto> listLiquidacionGasto) throws ParseException {
		
		for (LiquidacionGasto liquidacionGasto : listLiquidacionGasto) {
			liquidacionGasto=llenaLiquidacionGastoCompleto(liquidacionGasto);
		}
		return listLiquidacionGasto;
	}

	@Override
	public LiquidacionGasto llenaLiquidacionGastoCompleto(
			LiquidacionGasto liquidacionGasto) {
		
		liquidacionGasto.setEstLiqGastoDesc(
				(detalleEstadoCabeceraDAO.findDetalleEstadoCabeceraById(liquidacionGasto.getFkIdDetalleEstadoCabEstLiqGasto())).getDescripEstado()		
		);
		liquidacionGasto.setPrefijoEstadoLiqGasto(detalleEstadoCabeceraService
				.findDetalleEstadoCabeceraById(
						liquidacionGasto.getFkIdDetalleEstadoCabEstLiqGasto()).getPrefijoEstado());
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String fechaInicio=formato.format(liquidacionGasto.getFechaInicio());
		String fechaFin=formato.format(liquidacionGasto.getFechaFin());
		
		liquidacionGasto.setFechaInicioString(fechaInicio);
		liquidacionGasto.setFechaFinString(fechaFin);
		liquidacionGasto.setCantObservacionesRelevantes(observacionService.findObservacionesRelevatesAlDocumento(liquidacionGasto.getDatoProyecto().getDatoProyectoID(),liquidacionGasto.getLiquidacionGastoID(),FondamConstans.TABLA_CLASE_NOMBRE_LIQUIDACION_GASTO));
		
		return liquidacionGasto;
	}

	@Override
	public List<PagoLiquidacion> llenaListPagoLiquidacionCompleto(
			List<PagoLiquidacion> listPagoLiquidacion) {
		for (PagoLiquidacion pagoLiquidacion : listPagoLiquidacion) {
			pagoLiquidacion= llenaPagoLiquidacionCompleto(pagoLiquidacion);
		}
		return listPagoLiquidacion;
	}

	private PagoLiquidacion llenaPagoLiquidacionCompleto(
			PagoLiquidacion pagoLiquidacion) {
		
		pagoLiquidacion.setLstDetallePagoLiquidacion(detallePagoLiquidacionService.findDetallePagoLiquidacionByPagoLiquidacionId(pagoLiquidacion.getPagoLiquidacionID()));
		pagoLiquidacion.setCantidadDetallePagoLiquidacion(pagoLiquidacion.getLstDetallePagoLiquidacion().size());
		Desembolso desembolso=desembolsoDAO.findDesembolsoById(pagoLiquidacion.getDesembolsoID());
		pagoLiquidacion.setDescripcionDesembolso(desembolso.getPeriodo()+"."+desembolso.getFuenteFinanciadora().getFuenteFinanciadoraID()+"."+desembolso.getVersionDePeriodo()+"."+ tablaEspecificaService.findTablaEspecificaById(desembolso.getFkIdtablaespTipoDesembolso()).getDescripcionCabecera());
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fechaEmision = formateador.format(pagoLiquidacion.getFechaEmision());

		pagoLiquidacion.setFechaEmisionString(fechaEmision);
		
		return pagoLiquidacion;
	}

	@Override
	public LiquidacionGasto llenaLiquidacionGastoCompletoConListas(
			LiquidacionGasto liquidacionGasto) {

		//lleno la liquidacion de gastos con toda su documentacion
		liquidacionGasto.setListPagoLiquidacion(pagoLiquidacionService.llenaPagoLiquidacionCompletoConListas (findIngresoPagoByliquidacionGastoID(liquidacionGasto.getLiquidacionGastoID())));

		return liquidacionGasto;
	}

	
}
