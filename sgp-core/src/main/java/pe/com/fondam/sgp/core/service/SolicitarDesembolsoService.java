package pe.com.fondam.sgp.core.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.CuentaCorriente;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;

public interface SolicitarDesembolsoService{

	
	public DatoPlanOperativo findDatoPlanOperativoByDatoProyectoID2(Integer datoProyectoID);

	List<Resultado> findListResultadoByDatoPlanOperativoID(Integer datoPlanOperativoID);

	public String convertirListResultadoToArrayJson(List <Resultado> listResultado);
	
	public List<Actividad> findActividadByResultadoID(Integer resultadoID);

	public String convertirListActividadToArrayJson(List<Actividad> listActividad);
	
	public List<CostoActividad> findCostoActividadByActividadID(Integer actividadID);

	public String convertirListCostoActividadToArrayJson(List<CostoActividad> listCostoActividad);
	
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadIdAndFuenteFinanciadoraID(Integer costoActividadID,Integer fuenteFinanciadoraID);

	//public String convertirListCronogramaCostoActividadToArrayJson(List<CronogramaCostoActividad> listCronogramaCostoActividad,Integer costoActividadID);
	
	public String convertirListCronogramaCostoActividadToArrayJson(List<CronogramaCostoActividad> listCronogramaCostoActividad,String jsonObjectCostoActividad);

	public String convertirListTablaEspecificaToArrayJsonCBX(List<TablaEspecifica> listTipoDesembolso);

	public List<Desembolso> findDesembolsoUltimaVersionByDatoProyectoID(Integer datoProyectoID);

	public String convertirListDesembolsoToArrayJson(List<Desembolso> listDesembolso);

	public String convertirListFuenteFinanToArrayJsonCBX(List<FuenteFinanciadora> listFuenteFinan);

	public String convertirListCuentaCorrienteToArrayJsonCBX(List<CuentaCorriente> listCuentaCorriente);

	public String getPerfilUsuario(Integer datoUsuarioID);

	public String convertirListEstadosDesembolsoToArrayJsonCBX(List<DetalleEstadoCabecera> listEstadosDesembolso);

	public void saveSolicitudDesembolso(Map<String, String> parametrosSolDesembolso, JSONArray jsonListaTipoCambio);

	public String getUltimaVersionDesembolsoByDatoProyectoIDByPeriodo(int datoProyectoID,String periodo);

}
