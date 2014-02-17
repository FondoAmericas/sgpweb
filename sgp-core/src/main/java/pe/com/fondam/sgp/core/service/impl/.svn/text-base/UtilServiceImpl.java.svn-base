package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.constants.FondamConstans;
import pe.com.fondam.sgp.core.dao.CategoriaActividadDAO;
import pe.com.fondam.sgp.core.dao.DetalleEstadoCabeceraDAO;
import pe.com.fondam.sgp.core.dao.EstadoCabeceraDAO;
import pe.com.fondam.sgp.core.dao.PartidaEspecificaDAO;
import pe.com.fondam.sgp.core.dao.PartidaGenericaDAO;
import pe.com.fondam.sgp.core.dao.RubroGenericoDAO;
import pe.com.fondam.sgp.core.dao.TablaEspecificaDAO;
import pe.com.fondam.sgp.core.dao.TablaGeneralDAO;
import pe.com.fondam.sgp.core.domain.CategoriaActividad;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.EstadoCabecera;
import pe.com.fondam.sgp.core.domain.PartidaEspecifica;
import pe.com.fondam.sgp.core.domain.PartidaGenerica;
import pe.com.fondam.sgp.core.domain.RubroGenerico;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.TablaGeneral;
import pe.com.fondam.sgp.core.service.UtilService;

@Service
public class UtilServiceImpl implements UtilService {

	@Autowired
	private TablaGeneralDAO tablaGeneralDAO;

	@Autowired
	private TablaEspecificaDAO tablaEspecificaDAO;

	@Autowired
	private EstadoCabeceraDAO estadoCabeceraDAO;

	@Autowired
	private DetalleEstadoCabeceraDAO detalleEstadoCabeceraDAO;

	
	@Autowired
	private CategoriaActividadDAO categoriaActividadDAO;

	@Autowired
	private RubroGenericoDAO rubroGenericoDAO;

	@Autowired
	private PartidaGenericaDAO partidaGenericaDAO;

	@Autowired
	private PartidaEspecificaDAO partidaEspecificaDAO;

	public TablaGeneralDAO getTablaGeneralDAO() {
		return tablaGeneralDAO;
	}

	public void setTablaGeneralDAO(TablaGeneralDAO tablaGeneralDAO) {
		this.tablaGeneralDAO = tablaGeneralDAO;
	}

	public TablaEspecificaDAO getTablaEspecificaDAO() {
		return tablaEspecificaDAO;
	}

	public void setTablaEspecificaDAO(TablaEspecificaDAO tablaEspecificaDAO) {
		this.tablaEspecificaDAO = tablaEspecificaDAO;
	}

	public CategoriaActividadDAO getCategoriaActividadDAO() {
		return categoriaActividadDAO;
	}

	public void setCategoriaActividadDAO(
			CategoriaActividadDAO categoriaActividadDAO) {
		this.categoriaActividadDAO = categoriaActividadDAO;
	}

	public RubroGenericoDAO getRubroGenericoDAO() {
		return rubroGenericoDAO;
	}

	public void setRubroGenericoDAO(RubroGenericoDAO rubroGenericoDAO) {
		this.rubroGenericoDAO = rubroGenericoDAO;
	}

	public PartidaGenericaDAO getPartidaGenericaDAO() {
		return partidaGenericaDAO;
	}

	public void setPartidaGenericaDAO(PartidaGenericaDAO partidaGenericaDAO) {
		this.partidaGenericaDAO = partidaGenericaDAO;
	}

	public PartidaEspecificaDAO getPartidaEspecificaDAO() {
		return partidaEspecificaDAO;
	}

	public void setPartidaEspecificaDAO(
			PartidaEspecificaDAO partidaEspecificaDAO) {
		this.partidaEspecificaDAO = partidaEspecificaDAO;
	}

	@Override
	public List<CategoriaActividad> listaCategoriaActividadByTipoActividad(
			Integer tipoActividadId) {
		return getCategoriaActividadDAO()
				.findCategoriaActividadByTipoActividadId(tipoActividadId);
	}

	@Override
	public List<RubroGenerico> listaRubroGenericoByCategoriaActividad(
			Integer categoriaActividadID) {
		
		String consulta = "from RubroGenerico where categoriaActividad.categoriaActividadID = ? and idespecificacionRubroGenerico = 0 ";
		Object[] params = new Object[1];
		params[0] = categoriaActividadID;
		
		return rubroGenericoDAO.findRubroGenericoByConsulta(consulta, params);
	}
	
	@Override
	public List<RubroGenerico> listaRubroEspecificoByCategoriaActividadByRubroGenerico(
			Integer categoriaActividadID, Integer rubroGenericoId) {
		
		String consulta = "from RubroGenerico where categoriaActividad.categoriaActividadID = ? and idcabeceraRubrogenerico = ?  and idespecificacionRubroGenerico != 0 ";
		Object[] params = new Object[2];
		params[0] = categoriaActividadID;
		params[1] = rubroGenericoId;
		
		return rubroGenericoDAO.findRubroGenericoByConsulta(consulta, params);
	}

	@Override
	public List<PartidaGenerica> listaPartidaGenericaByCategoriaActividad(
			Integer categoriaActividadID) {
		return getPartidaGenericaDAO().findPartidaGenericaByCategoriaActividadID(categoriaActividadID);
	}

	@Override
	public List<PartidaEspecifica> listaPartidaEspecificaByPartidaGenerica(
			Integer partidaGenericaID) {
		return getPartidaEspecificaDAO().findPartidaEspecificaByPartidaGenericaID(partidaGenericaID);
	}

	@Override
	public TablaEspecifica obtenerTablaEspecificaById(Integer tablaEspecificaID) {
		return getTablaEspecificaDAO().findTablaEspecificaById(
				tablaEspecificaID);
	}

	@Override
	public List<TablaEspecifica> listaTipoCuenta() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_CUENTA);
	}

	@Override
	public List<TablaEspecifica> listaModalidadFinanciamiento() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_MODALIDAD_FINANCIAMIENTO);
	}

	@Override
	public List<TablaEspecifica> listaBanco() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_BANCO);
	}

	@Override
	public List<TablaEspecifica> listaAreaTematica() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_AREA_TEMATICA);
	}

	@Override
	public List<TablaEspecifica> listaUnidadMedida() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_UNIDADES_MEDIDA);
	}

	@Override
	public List<TablaEspecifica> listaCategoriaActivo() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_CATEGORIA_ACTIVO);
	}

	@Override
	public List<TablaEspecifica> listaTipoComprobantePago() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPOS_COMPROBANTES_PAGOS);
	}

	@Override
	public List<TablaEspecifica> listaEstratos() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_ESTRATOS);
	}

	@Override
	public List<TablaEspecifica> listaTipoBeneficiario() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_BENEFICIARIO);
	}

	@Override
	public List<TablaEspecifica> listaTipoDesembolso() {
		return listarTablaEspecificaByPrefijo("TPDB");

	}

	@Override
	public List<TablaEspecifica> listaTipoInspeccion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaModalidadInspeccion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoSupervicionAdministrativa() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoPercepcion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoObservacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaEfectosProyecto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoMaterial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaEvaluacionFinalCabecera() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoLeccion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoBien() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaCabeceraConclusionIf() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaCargo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaActividadTransferencia() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_ACTIVIDAD_TRANSFERENCIA);
	}

	@Override
	public List<TablaEspecifica> listaTipoDocumento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoMoneda() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_MONEDA);
	}

	@Override
	public List<TablaEspecifica> listaTipoActividad() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_ACTIVIDAD);
	}

	@Override
	public List<TablaEspecifica> listaTipoEvaluacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoRestriccionesPrograma() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoResultadoActividadesObligatoria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoFuenteFinanciadora() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoResumenProyecto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoResumenPerfil() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaFormacionProfesional() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTiempoDedicado() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaEtapasPersonalTecnico() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaApreciacionResultadosRa() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoInforme() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaTipoIndicadorResultado() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_INDICADOR_RESULTADO);
	}

	@Override
	public List<TablaEspecifica> listaTipoIndicadorActividad() {
		return listarTablaEspecificaByPrefijo(FondamConstans.PREFIJO_TIPO_INDICADOR_ACTIVIDAD);
	}

	@Override
	public List<TablaEspecifica> listaTipoArchivo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TablaEspecifica> listaJerarquia() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TablaEspecifica> listarTablaEspecificaByPrefijo(String prefijo) {
		TablaGeneral tablaGeneral = getTablaGeneralDAO()
				.findTablaGeneralByPrefijo(prefijo);
		List<TablaEspecifica> listaTablaEspecifica = getTablaEspecificaDAO()
				.findTablaEspecificasByTablaGeneralId(
						tablaGeneral.getTablaGeneralID());
		return listaTablaEspecifica;
	}
	
	public List<DetalleEstadoCabecera> listarDetalleEstadoCabeceraByPrefijo(String prefijo) {
		EstadoCabecera estadoCabecera = estadoCabeceraDAO.findEstadoCabeceraByPrefijo(prefijo);
		List<DetalleEstadoCabecera> listaDetalleEstadoCabecera = detalleEstadoCabeceraDAO.findDetalleEstadoCabecerabyEstadoCabeceraId(estadoCabecera.getEstadoCabeceraID());
		return listaDetalleEstadoCabecera;
	}
	
	
	public String convertirTablaEspecificaToJSONArrayCBX(List<TablaEspecifica> lstTablaEsp){
		
		JSONArray jsonData = new JSONArray();
		
		JSONObject jsonObjectElementTable = new JSONObject();
		
		if(lstTablaEsp != null && lstTablaEsp.size() > 0){
			for(int i=0; i<lstTablaEsp.size(); i++){
				
				jsonObjectElementTable = new JSONObject();
				
				if(lstTablaEsp.get(i).getTablaEspecificaID() != null){
					jsonObjectElementTable.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, lstTablaEsp.get(i).getTablaEspecificaID());
				}else{
					jsonObjectElementTable.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, FondamConstans.EMPTY_STRING);
				}
				
				if(lstTablaEsp.get(i).getDescripcionCabecera() != null){
					jsonObjectElementTable.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, lstTablaEsp.get(i).getDescripcionCabecera());
				}else{
					jsonObjectElementTable.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, FondamConstans.EMPTY_STRING);
				}
				
				jsonData.add(jsonObjectElementTable);
			}
			
		}
		
		return jsonData.toString();
	}

	public String convertirTablaEstadoCabeceraToJSONArrayCBX(List<DetalleEstadoCabecera> lstDetEstCab){
		
		JSONArray jsonData = new JSONArray();
		
		JSONObject jsonObjectDetEstCab = new JSONObject();
		
		if(lstDetEstCab != null && lstDetEstCab.size() > 0){
			for(int i=0; i<lstDetEstCab.size(); i++){
				
				jsonObjectDetEstCab = new JSONObject();
				
				if(lstDetEstCab.get(i).getDetalleEstadoCabeceraID() != null){
					jsonObjectDetEstCab.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, lstDetEstCab.get(i).getDetalleEstadoCabeceraID());
				}else{
					jsonObjectDetEstCab.accumulate(FondamConstans.VALUE_OPCION_COMBOBOX, FondamConstans.EMPTY_STRING);
				}
				
				if(lstDetEstCab.get(i).getDescripEstado() != null){
					jsonObjectDetEstCab.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, lstDetEstCab.get(i).getDescripEstado());
				}else{
					jsonObjectDetEstCab.accumulate(FondamConstans.TEXT_OPCION_COMBOBOX, FondamConstans.EMPTY_STRING);
				}
				
				jsonData.add(jsonObjectDetEstCab);
			}
			
		}
		
		return jsonData.toString();
	}
}
