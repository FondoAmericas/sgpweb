package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.CronogramaCostoActividadDAO;
import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.dao.DesembolsoDAO;
import pe.com.fondam.sgp.core.dao.FuenteFinanciadoraDAO;
import pe.com.fondam.sgp.core.dao.ImagenOArchivoDAO;
import pe.com.fondam.sgp.core.dao.InstitucionDAO;
import pe.com.fondam.sgp.core.dao.RestricionProgramaDAO;
import pe.com.fondam.sgp.core.dao.TablaEspecificaDAO;
import pe.com.fondam.sgp.core.dao.UbicacionProyectoDAO;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.DepProvDist;
import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;
import pe.com.fondam.sgp.core.domain.Institucion;
import pe.com.fondam.sgp.core.domain.Perfil;
import pe.com.fondam.sgp.core.domain.RestricionPrograma;
import pe.com.fondam.sgp.core.domain.UbicacionProyecto;
import pe.com.fondam.sgp.core.service.EvaluarService;
import pe.com.fondam.sgp.core.service.ProyectoService;
import pe.com.fondam.sgp.core.util.CommonUtilities;

@Service
public class ProyectoServiceImpl implements ProyectoService {

	protected final Log logger = LogFactory.getLog(ProyectoServiceImpl.class);

	@Resource
	private DatoProyectoDAO datoProyectoDAO;

	@Resource
	private ImagenOArchivoDAO imagenOArchivoDAO;

	@Resource
	private EvaluarService evaluarService;
	
	//@Resource
	//private	RegistroPerfilService registroPerfilService;

	@Resource
	private FuenteFinanciadoraDAO fuenteFinanciadoraDAO;

	@Resource
	private InstitucionDAO institucionDAO;

	//@Resource
	//private ResumenProyectoDAO resumenProyectoDAO;

	/*@Resource
	private IndicadorMarcoLogicoDAO indicadorMarcoLogicoDAO;*/

	//@Resource
	//private MarcoLogicoDAO marcoLogicoDAO;

	@Resource
	private UbicacionProyectoDAO ubicacionProyectoDAO;

	@Resource
	private DesembolsoDAO desembolsoDAO;

	@Resource
	private RestricionProgramaDAO restricionProgramaDAO;

	@Resource
	private CronogramaCostoActividadDAO cronogramaCostoActividadDAO;
	
	@Resource
	private TablaEspecificaDAO tablaEspecificaDAO; 

	// FileUploadDownload fileUploadDownload=new FileUploadDownload();

	public DatoProyectoDAO getDatoProyectoDAO() {
		return datoProyectoDAO;
	}

	public void setDatoProyectoDAO(DatoProyectoDAO datoProyectoDAO) {
		this.datoProyectoDAO = datoProyectoDAO;
	}

	public ImagenOArchivoDAO getImagenOArchivoDAO() {
		return imagenOArchivoDAO;
	}

	public void setImagenOArchivoDAO(ImagenOArchivoDAO imagenOArchivoDAO) {
		this.imagenOArchivoDAO = imagenOArchivoDAO;
	}

	@Override
	public ImagenOArchivo findProyetoByArchivoImagen(Integer datoProyectoID) {
		return datoProyectoDAO.findImagenOarchivoByProyecto(datoProyectoID);
	}

	@Override
	public void saveProyetoByArchivoImagen(ImagenOArchivo imagenOArchivo) {
		imagenOArchivoDAO.saveImagenOArchivo(imagenOArchivo);
	}

	@Override
	public DatoProyecto findDatoProyectoById(Integer datoProyectoID) {
		return datoProyectoDAO.findDatoProyectoById(datoProyectoID);
	}

	public boolean saveGestionProyecto(DatoProyecto datoProyecto,
			Map<String, String> params) {
		try {

			logger.info("--GRABA UBICACION DEL PROYECTO / BENEFICIARIOS POR RESULTADO--------------");
			List<UbicacionProyecto> listUbicacionProyecto = evaluarService
					.findUbicacionProyectoByIdProyecto(datoProyecto
							.getDatoProyectoID());
			UbicacionProyecto ubicacionProy;
			BeneficiariosPorResultado beneficiariosPorResultado1;
			List<Integer> lstPKsUbiProyEliminados = new ArrayList<Integer>();
			if (listUbicacionProyecto.size() > 0) {
				for (int i = 0; i < listUbicacionProyecto.size(); i++) {
					ubicacionProy = new UbicacionProyecto();
					ubicacionProy = listUbicacionProyecto.get(i);
					List<BeneficiariosPorResultado> listBeneficiariosPorResultado = evaluarService
							.findBeneficiariosPorResultadoByIdUbicacionProyecto(ubicacionProy
									.getUbicacionProyectoID());
					listBeneficiariosPorResultado = evaluarService
							.verificarSiBeneficiarioEstaReferenciadoPorTablas(listBeneficiariosPorResultado);
					int eliminados = 0;
					int tamListBeneficiariosPorResultado = listBeneficiariosPorResultado
							.size();
					for (int j = 0; j < tamListBeneficiariosPorResultado; j++) {
						if (!listBeneficiariosPorResultado.get(j)
								.isFlagReferencia()) {
							beneficiariosPorResultado1 = new BeneficiariosPorResultado();
							beneficiariosPorResultado1 = listBeneficiariosPorResultado
									.get(j);
							evaluarService
									.deleteBeneficiariosPorResultado(beneficiariosPorResultado1);
							eliminados++;
						}
					}

					if (tamListBeneficiariosPorResultado == eliminados) {
						lstPKsUbiProyEliminados.add(ubicacionProy
								.getUbicacionProyectoID());
						evaluarService.deleteUbicacionProyectoo(ubicacionProy);
					}

				}
			}

			JSONArray jsonListadoUbicacionBeneficiarios = (JSONArray) JSONSerializer
					.toJSON(params.get("txtListadoUbicacionBeneficiarios"));
			UbicacionProyecto ubicacionProyecto;
			BeneficiariosPorResultado beneficiariosPorResultado;
			for (int i = 0; i < jsonListadoUbicacionBeneficiarios.size(); i++) {
				JSONObject ObjJsonUbicacion = (JSONObject) jsonListadoUbicacionBeneficiarios
						.getJSONObject(i).getJSONObject("ubicacion");
				DepProvDist depProvDist = new DepProvDist();
				ubicacionProyecto = new UbicacionProyecto();
				String txaLoc = ObjJsonUbicacion.getString("txaLoc");
				ubicacionProyecto.setDetalleUbicacion(CommonUtilities
						.isNotNullOrBlank(txaLoc) ? txaLoc : null);
				depProvDist.setDepProvDistID(ObjJsonUbicacion
						.getInt("DepProvDistID"));
				ubicacionProyecto.setDepProvDist(depProvDist);// es un objeto
				ubicacionProyecto.setDatoProyecto(datoProyecto);// es un objeto
				//boolean flagReferenciaUbi = ObjJsonUbicacion.getBoolean("flagReferencia");
				int ubicacionProyectoID = ObjJsonUbicacion
						.getInt("ubicacionProyectoID");
				boolean PkUbicacionEliminada = false;
				for (int k = 0; k < lstPKsUbiProyEliminados.size(); k++) {
					if (lstPKsUbiProyEliminados.get(k) == ubicacionProyectoID) {
						PkUbicacionEliminada = true;
						break;
					}
				}

				if (ubicacionProyectoID != 0 && PkUbicacionEliminada == false) {
					ubicacionProyecto
							.setUbicacionProyectoID(ubicacionProyectoID);
				}

				UbicacionProyecto ubicacionProyecto1 = ubicacionProyectoDAO
						.findUbicacionProyectoById(ubicacionProyectoID);
			

				// evaluarService.saveUbicacionProyecto(ubicacionProyecto);

				// si hay beneficiarios
				if (!jsonListadoUbicacionBeneficiarios.getJSONObject(i)
						.getString("beneficiarios").equalsIgnoreCase("null")) {
					boolean guardarUbicacion = false;
					JSONArray arrayJsonBeneficiarios = (JSONArray) jsonListadoUbicacionBeneficiarios
							.getJSONObject(i).getJSONArray("beneficiarios");
					for (int x = 0; x < arrayJsonBeneficiarios.size(); x++) {
						JSONObject objJsonBeneficiario = (JSONObject) arrayJsonBeneficiarios
								.getJSONObject(x);
						boolean flagReferencia = objJsonBeneficiario
								.getBoolean("flagReferencia");
						if (CommonUtilities.isNullOrBlank(ubicacionProyecto1)
								&& guardarUbicacion == false) {// si existe la
																// ubicacion en
																// la BD la
																// guarda 1 sola
																// ves
							evaluarService
									.saveUbicacionProyecto(ubicacionProyecto);
							guardarUbicacion = true;
						}

						if (!flagReferencia) {

							// llenar beneficiarios
							beneficiariosPorResultado = new BeneficiariosPorResultado();
							beneficiariosPorResultado
									.setFkIdtablaespTipoBeneficiario(objJsonBeneficiario
											.getInt("cbxTipoBenValue"));
							beneficiariosPorResultado
									.setCaracteristicasPoblacion(objJsonBeneficiario
											.getString("txaCaracPoblacion"));
							beneficiariosPorResultado
									.setCantidadProgramado(objJsonBeneficiario
											.getInt("txtCantBen"));
							beneficiariosPorResultado
									.setFkidtablaespEstrato(objJsonBeneficiario
											.getInt("cbxEstSocBenValue"));
							beneficiariosPorResultado
									.setDescripcion(objJsonBeneficiario
											.getString("txaDescripcionPoblacion"));
							Perfil perfil = evaluarService
									.findPerfilByDatoProyectoID(datoProyecto
											.getDatoProyectoID());
							beneficiariosPorResultado.setPerfil(perfil);// es un
																		// objeto
							beneficiariosPorResultado
									.setUbicacionProyecto(ubicacionProyecto);
							beneficiariosPorResultado.setEstadoEliminado(1);
							
							evaluarService
									.saveBeneficiariosPorResultado(beneficiariosPorResultado);
						}
					}
				}

			}

			logger.info("--GRABA DATOS DE INSTITUCION / FUENTE FINANCIADORA-----------------");

			// desde la BD
			List<Institucion> lstInstituciones = getInstitucionesFinanciadorasByProyectoId(datoProyecto
					.getDatoProyectoID());

			for (int i = 0; i < lstInstituciones.size(); i++) {
				if (!lstInstituciones.get(i).isFlagReferencia()) {// eliminar
																	// fuente
																	// financiadora,
																	// si la
																	// institucion
																	// no esta
																	// referenciada
					FuenteFinanciadora fuenteFinanciadora = fuenteFinanciadoraDAO
							.findFuenteFinanciadoraByDatoProyectoIDByInstitucionID(
									datoProyecto.getDatoProyectoID(),
									lstInstituciones.get(i).getInstitucionID());

					fuenteFinanciadoraDAO
							.deleteFuenteFinanciadora(fuenteFinanciadora);
				}
			}
			// desdePantalla
			JSONArray jsonListadoInstitucionFuenFinan = (JSONArray) JSONSerializer
					.toJSON(params.get("txtListadoInstitucionFuenFinan"));

			FuenteFinanciadora fuenteFinanciadora;
			for (int i = 0; i < jsonListadoInstitucionFuenFinan.size(); i++) {
				JSONObject ObjJsonInstitucion = (JSONObject) jsonListadoInstitucionFuenFinan
						.getJSONObject(i).getJSONObject("institucion");
				Institucion institucion1 = institucionDAO
						.findInstitucionByRUC(ObjJsonInstitucion
								.getString("txtRuc"));

				if (institucion1 == null) {// si no existe institucion la graba
											// junto con su fuente financiadora
					Institucion institucion = new Institucion();
					institucion.setNombreInstitucion(ObjJsonInstitucion
							.getString("txtInstitucion"));
					institucion.setRucInstitucion(ObjJsonInstitucion
							.getString("txtRuc"));
					institucion.setDireccion(ObjJsonInstitucion
							.getString("txtDireccion"));
					institucion.setTelefono(ObjJsonInstitucion
							.getString("txtTelefono"));
					institucion.setRepresentanteLegal(ObjJsonInstitucion
							.getString("txtRepLegal"));
					institucion.setContacto(ObjJsonInstitucion
							.getString("txtContacto"));
					institucion.setObservacionDeInstitucion(ObjJsonInstitucion
							.getString("txaObservacion"));
					institucion.setCorreo(ObjJsonInstitucion
							.getString("txtCorreo"));
					institucion.setFkIdDetalleEstadoCabEstInstitucion(1);// estado
																			// 1:
																			// por
																			// evaluar
					evaluarService.saveInstitucion(institucion);

					// si hay fuente financiadoras
					if (!jsonListadoInstitucionFuenFinan.getJSONObject(i)
							.getString("fuenFinan").equalsIgnoreCase("null")) {
						JSONObject ObjJsonFuenteFinanciera = (JSONObject) jsonListadoInstitucionFuenFinan
								.getJSONObject(i).getJSONObject("fuenFinan");
						fuenteFinanciadora = new FuenteFinanciadora();
						fuenteFinanciadora.setDefineSiEsEjecutor(Integer
								.parseInt(ObjJsonFuenteFinanciera
										.getString("cbxInstitucionEjecutora")));
						fuenteFinanciadora
								.setFkIdtablaespTipoFuenteFinanciadora(Integer.parseInt(ObjJsonFuenteFinanciera
										.getString("cbxTipoFuenteFinan")));
						fuenteFinanciadora.setMontoFinanciado(Double
								.parseDouble(ObjJsonFuenteFinanciera
										.getString("txtMontoFinan")));
						fuenteFinanciadora.setFkIdtablaespTipoMoneda(Integer
								.parseInt(ObjJsonFuenteFinanciera
										.getString("cbxTipoMoneda")));
						fuenteFinanciadora.setDatoProyecto(datoProyecto);
						fuenteFinanciadora.setInstitucion(institucion);
						evaluarService
								.saveFuenteFinanciadora(fuenteFinanciadora);
					}
				} else {// si existe institucion
					boolean flagReferencia = ObjJsonInstitucion
							.getBoolean("flagReferencia");
					if (flagReferencia) {// si esta referenciada, actualizar
											// fuente financiadora
						if (!jsonListadoInstitucionFuenFinan.getJSONObject(i)
								.getString("fuenFinan")
								.equalsIgnoreCase("null")) {
							JSONObject ObjJsonFuenteFinanciera = (JSONObject) jsonListadoInstitucionFuenFinan
									.getJSONObject(i)
									.getJSONObject("fuenFinan");
							fuenteFinanciadora = new FuenteFinanciadora();
							fuenteFinanciadora
									.setFuenteFinanciadoraID(CommonUtilities.toInt(ObjJsonFuenteFinanciera
											.getString("fuenteFinanciadoraID")));
							fuenteFinanciadora
									.setDefineSiEsEjecutor(Integer.parseInt(ObjJsonFuenteFinanciera
											.getString("cbxInstitucionEjecutora")));
							fuenteFinanciadora
									.setFkIdtablaespTipoFuenteFinanciadora(Integer.parseInt(ObjJsonFuenteFinanciera
											.getString("cbxTipoFuenteFinan")));
							fuenteFinanciadora.setMontoFinanciado(Double
									.parseDouble(ObjJsonFuenteFinanciera
											.getString("txtMontoFinan")));
							fuenteFinanciadora
									.setFkIdtablaespTipoMoneda(Integer.parseInt(ObjJsonFuenteFinanciera
											.getString("cbxTipoMoneda")));
							fuenteFinanciadora.setDatoProyecto(datoProyecto);
							fuenteFinanciadora.setInstitucion(institucion1);
							//FuenteFinanciadora FuenteFinan = fuenteFinanciadoraDAO.updateFuenteFinanciadora(fuenteFinanciadora);
							fuenteFinanciadoraDAO.updateFuenteFinanciadora(fuenteFinanciadora);
						}
					} else {// si no esta referenciada, grabar fuente
							// financiadora
						if (!jsonListadoInstitucionFuenFinan.getJSONObject(i)
								.getString("fuenFinan")
								.equalsIgnoreCase("null")) {
							JSONObject ObjJsonFuenteFinanciera = (JSONObject) jsonListadoInstitucionFuenFinan
									.getJSONObject(i)
									.getJSONObject("fuenFinan");
							fuenteFinanciadora = new FuenteFinanciadora();
							fuenteFinanciadora
									.setDefineSiEsEjecutor(Integer.parseInt(ObjJsonFuenteFinanciera
											.getString("cbxInstitucionEjecutora")));
							fuenteFinanciadora
									.setFkIdtablaespTipoFuenteFinanciadora(Integer.parseInt(ObjJsonFuenteFinanciera
											.getString("cbxTipoFuenteFinan")));
							fuenteFinanciadora.setMontoFinanciado(Double
									.parseDouble(ObjJsonFuenteFinanciera
											.getString("txtMontoFinan")));
							fuenteFinanciadora
									.setFkIdtablaespTipoMoneda(Integer.parseInt(ObjJsonFuenteFinanciera
											.getString("cbxTipoMoneda")));
							fuenteFinanciadora.setDatoProyecto(datoProyecto);
							fuenteFinanciadora.setInstitucion(institucion1);
							fuenteFinanciadoraDAO
									.saveFuenteFinanciadora(fuenteFinanciadora);
						}
					}

				}
			}
/*
			logger.info("--GRABA RESUMEN PROYECTO--------------------------------------------");

			List<ResumenProyecto> listResumenProyecto = registroPerfilService.findResumenProyectoByIdDatoProyByTablaGeneralId(datoProyecto.getDatoProyectoID(),31); //id de tipo de resumen proyecto
			//evaluarService.findResumenProyectoByIdDatoProy(datoProyecto.getDatoProyectoID());
			ResumenProyecto resumenProyecto1;
			for (int i = 0; i < listResumenProyecto.size(); i++) {
				resumenProyecto1 = new ResumenProyecto();
				resumenProyecto1 = listResumenProyecto.get(i);
				resumenProyectoDAO.deleteResumenProyecto(resumenProyecto1);
			}

			JSONArray jsonListadoResumenProyecto = (JSONArray) JSONSerializer
					.toJSON(params.get("txtListadoResumenProyecto"));
			ResumenProyecto resumenProyecto;
			for (int i = 0; i < jsonListadoResumenProyecto.size(); i++) {
				int cbxTipoResumenProyecto = jsonListadoResumenProyecto
						.getJSONObject(i).getInt("cbxCodTipoResumenProyecto");
				//String txaResumenPerfil = jsonListadoResumenProyecto.getJSONObject(i).getString("txaResumenPerfil");
				resumenProyecto = new ResumenProyecto();
				resumenProyecto.setDefinicion(jsonListadoResumenProyecto
						.getJSONObject(i).getString("txaResumenPerfil"));
				resumenProyecto
						.setFkIdtablaespTipoResumenProy(cbxTipoResumenProyecto);
				resumenProyecto.setFkIdTablaGeneral(tablaEspecificaDAO.findTablaEspecificaById(cbxTipoResumenProyecto).getTablaGeneral().getTablaGeneralID());
				resumenProyecto.setDatoProyecto(datoProyecto);
				evaluarService.saveResumenProyecto(resumenProyecto);

			}*/

			
			/*logger.info("-----GRABA MARCO LOGICO-----");
			// eliminar el marco logico existente con sus indicadores
			MarcoLogico marcoLogico = evaluarService
					.findMarcoLogicoByDatoProyectoID(datoProyecto
							.getDatoProyectoID());
			//if (CommonUtilities.isNotNullOrBlank(marcoLogico1)) {

				if (marcoLogico != null) {
					List<IndicadorMarcoLogico> lstIndicadorMarcoLogico = indicadorMarcoLogicoDAO
							.findIndicadorMarcoLogicoByIdMarcoLogico(marcoLogico
									.getMarcoLogicoID());
					for (IndicadorMarcoLogico indicadorMarcoLogico : lstIndicadorMarcoLogico) {
						indicadorMarcoLogicoDAO.deleteIndicadorMarcoLogico(indicadorMarcoLogico);
					}
					//marcoLogicoDAO.deleteMarcoLogico(marcoLogico);
				}else{
					marcoLogico= new MarcoLogico();
				}

				// guardar el nuevo marco logico con sus indicadores
				marcoLogico = llenaMarcoLogico(datoProyecto, params,marcoLogico);
				evaluarService.saveMarcoLogico(marcoLogico);

				JSONArray jsonListadoIndicadores = (JSONArray) JSONSerializer
						.toJSON(params.get("txtListadoIndicadores"));
				//IndicadorMarcoLogico indicadorMarcoLogico;
				for (int i = 0; i < jsonListadoIndicadores.size(); i++) {
					IndicadorMarcoLogico indicadorMarcoLogico = llenaIndicadorMarcoLogico(
							marcoLogico, jsonListadoIndicadores, i);
					evaluarService
							.saveIndicadorMarcoLogico(indicadorMarcoLogico);
				}*/
			//}
			
			return true;
		} catch (Exception e) {

			logger.info("ERROR REGISTRANDO PROYECTO--------" + e.getMessage());
			return false;
		}

	}

/*	private IndicadorMarcoLogico llenaIndicadorMarcoLogico(
			MarcoLogico marcoLogico, JSONArray jsonListadoIndicadores, int i) {
		IndicadorMarcoLogico indicadorMarcoLogico;
		
		indicadorMarcoLogico = new IndicadorMarcoLogico();
		indicadorMarcoLogico.setIndicador(jsonListadoIndicadores.getJSONObject(i)
				.getString("indicador"));
		indicadorMarcoLogico.setDefinicionIndicador(jsonListadoIndicadores.getJSONObject(
				i).getString("definicionIndicador"));
		indicadorMarcoLogico.setUnidadMedida(jsonListadoIndicadores.getJSONObject(i)
				.getInt("unidadMedida"));
		indicadorMarcoLogico
				.setMedioVerificacion(jsonListadoIndicadores
						.getJSONObject(i).getString("medioVerificacion"));
		indicadorMarcoLogico.setSituacionActural(jsonListadoIndicadores.getJSONObject(i)
				.getInt("situacionActual"));
		indicadorMarcoLogico.setSituacionFinal(jsonListadoIndicadores.getJSONObject(i).getInt("situacionFinal"));
		indicadorMarcoLogico.setMarcoLogico(marcoLogico);
		return indicadorMarcoLogico;
	}

	private MarcoLogico llenaMarcoLogico(DatoProyecto datoProyecto,
			Map<String, String> params, MarcoLogico marcoLogico) {
		//MarcoLogico marcoLogico = new MarcoLogico();
		marcoLogico.setResumenEjecutivo(params
				.get("txaResumenEjecutivo"));
		marcoLogico.setFinDescrip(params.get("txaDescripcionFin"));
		marcoLogico.setFinSupuesto(params.get("txaSupuestoFin"));
		marcoLogico.setPropositoDescrip(params
				.get("txaDescripcionProposito"));
		marcoLogico.setPropositoSupuesto(params
				.get("txaSupuestoProposito"));
		marcoLogico.setDatoProyecto(datoProyecto);
		return marcoLogico;
	}
*/
	public List<Institucion> getInstitucionesFinanciadorasByProyectoId(
			Integer datoProyectoID) {
		Institucion institucion;
		List<Institucion> lstInstitucion = new ArrayList<Institucion>();
		List<FuenteFinanciadora> lstFuenteFinan = fuenteFinanciadoraDAO
				.findFuenteFinanciadorasByIdDatoProy(datoProyectoID);
		for (int i = 0; i < lstFuenteFinan.size(); i++) {
			institucion = new Institucion();
			institucion = institucionDAO.findInstitucionById(lstFuenteFinan
					.get(i).getInstitucion().getInstitucionID());
			List<Desembolso> lstDesembolso = desembolsoDAO
					.findDesembolsoByFuenteFinanciadoraID(lstFuenteFinan.get(i)
							.getFuenteFinanciadoraID());
			List<RestricionPrograma> lstRestProg = restricionProgramaDAO
					.findRestriccionProgramaByFuenteFinanciadoraID(lstFuenteFinan
							.get(i).getFuenteFinanciadoraID());
			List<CronogramaCostoActividad> lstCronoCostAct = cronogramaCostoActividadDAO
					.findCronogramaCostoActividadByFuenteFinanciadoraID(lstFuenteFinan
							.get(i).getFuenteFinanciadoraID());
			if ((lstDesembolso != null && lstDesembolso.size() > 0)
					|| (lstRestProg != null && lstRestProg.size() > 0)
					|| (lstCronoCostAct != null && lstCronoCostAct.size() > 0)) {
				institucion.setFlagReferencia(true);
			} else {
				institucion.setFlagReferencia(false);
			}
			lstInstitucion.add(institucion);
		}

		return lstInstitucion;
	}

	public void setTablaEspecificaDAO(TablaEspecificaDAO tablaEspecificaDAO) {
		this.tablaEspecificaDAO = tablaEspecificaDAO;
	}

	public TablaEspecificaDAO getTablaEspecificaDAO() {
		return tablaEspecificaDAO;
	}

}
