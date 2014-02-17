package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.CategoriaActividad;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.PartidaEspecifica;
import pe.com.fondam.sgp.core.domain.PartidaGenerica;
import pe.com.fondam.sgp.core.domain.RubroGenerico;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;

public interface UtilService {

	TablaEspecifica obtenerTablaEspecificaById(Integer tablaEspecificaID);

	List<CategoriaActividad> listaCategoriaActividadByTipoActividad(Integer tipoActividadId);
	
	List<RubroGenerico> listaRubroGenericoByCategoriaActividad(Integer categoriaActividadID);
	
	List<PartidaGenerica> listaPartidaGenericaByCategoriaActividad(Integer categoriaActividadID);
	
	List<PartidaEspecifica> listaPartidaEspecificaByPartidaGenerica(Integer partidaGenericaID);
	
	List<TablaEspecifica> listaTipoCuenta();

	List<TablaEspecifica> listaModalidadFinanciamiento();

	List<TablaEspecifica> listaBanco();

	List<TablaEspecifica> listaAreaTematica();

	List<TablaEspecifica> listaUnidadMedida();

	List<TablaEspecifica> listaCategoriaActivo();

	List<TablaEspecifica> listaTipoComprobantePago();

	List<TablaEspecifica> listaEstratos();

	List<TablaEspecifica> listaTipoBeneficiario();

	List<TablaEspecifica> listaTipoDesembolso();

	List<TablaEspecifica> listaTipoInspeccion();

	List<TablaEspecifica> listaModalidadInspeccion();

	List<TablaEspecifica> listaTipoSupervicionAdministrativa();

	List<TablaEspecifica> listaTipoPercepcion();

	List<TablaEspecifica> listaTipoObservacion();

	List<TablaEspecifica> listaEfectosProyecto();

	List<TablaEspecifica> listaTipoMaterial();

	List<TablaEspecifica> listaEvaluacionFinalCabecera();

	List<TablaEspecifica> listaTipoLeccion();

	List<TablaEspecifica> listaTipoBien();

	List<TablaEspecifica> listaCabeceraConclusionIf();

	List<TablaEspecifica> listaCargo();

	List<TablaEspecifica> listaActividadTransferencia();

	List<TablaEspecifica> listaTipoDocumento();

	List<TablaEspecifica> listaTipoMoneda();

	List<TablaEspecifica> listaTipoActividad();

	List<TablaEspecifica> listaTipoEvaluacion();

	List<TablaEspecifica> listaTipoRestriccionesPrograma();

	List<TablaEspecifica> listaTipoResultadoActividadesObligatoria();

	List<TablaEspecifica> listaTipoFuenteFinanciadora();

	List<TablaEspecifica> listaTipoResumenProyecto();

	List<TablaEspecifica> listaTipoResumenPerfil();

	List<TablaEspecifica> listaFormacionProfesional();

	List<TablaEspecifica> listaTiempoDedicado();

	List<TablaEspecifica> listaEtapasPersonalTecnico();

	List<TablaEspecifica> listaApreciacionResultadosRa();

	List<TablaEspecifica> listaTipoInforme();

	List<TablaEspecifica> listaTipoIndicadorResultado();

	List<TablaEspecifica> listaTipoIndicadorActividad();

	List<TablaEspecifica> listaTipoArchivo();

	List<TablaEspecifica> listaJerarquia();

	public List<TablaEspecifica> listarTablaEspecificaByPrefijo(String prefijo);
	public List<DetalleEstadoCabecera> listarDetalleEstadoCabeceraByPrefijo(String prefijo);

	String convertirTablaEspecificaToJSONArrayCBX(List<TablaEspecifica> lstTablaEsp);

	String convertirTablaEstadoCabeceraToJSONArrayCBX(List<DetalleEstadoCabecera> lstDetEstCab);

	List<RubroGenerico> listaRubroEspecificoByCategoriaActividadByRubroGenerico(
			Integer categoriaActividadID, Integer rubroGenericoId);
}
