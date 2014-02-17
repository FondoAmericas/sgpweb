package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.BienDAO;
import pe.com.fondam.sgp.core.domain.Bien;
import pe.com.fondam.sgp.core.domain.OrgBienTranferido;
import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBien;
import pe.com.fondam.sgp.core.service.BienService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.OrgBienTranferidoService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaBienService;
import pe.com.fondam.sgp.core.service.RecursoUtilizadoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class BienServiceImpl implements BienService {

	//************** inyecciones  ******************//
	@Resource
	BienDAO bienDAO;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	@Resource
	RecursoUtilizadoService recursoUtilizadoService;
	
	@Resource
	PropuestaTransferenciaBienService propuestaTransferenciaBienService;
	
	@Resource
	OrgBienTranferidoService orgBienTranferidoService;
	
	//************** metodos ******************//
	@Override
	public List<Bien> findBienByDatoProyectoId(Integer datoProyectoId) {
		String consulta = "from Bien where datoProyectoId = ?";
		Object[] params = new Object[1];
		params[0]= datoProyectoId;
		
		List<Bien> listBien=bienDAO.findBienByConsulta(consulta,params);
		for (Bien bien : listBien) {
			bien.setDescripcionEstadoConservacion(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(bien.getFkIdDetalleEstCabEstadoConservacion()).getDescripEstado());
			bien.setDescripcionTipoBien(tablaEspecificaService.findTablaEspecificaById(bien.getFkIdtablaespTipoBien()).getDescripcionCabecera());
			bien.setDescripcionTipoMoneda(tablaEspecificaService.findTablaEspecificaById(bien.getFkIdtablaespTipoMoneda()).getDescripcionCabecera());
			bien.setDescripcionUnidadMedida(tablaEspecificaService.findTablaEspecificaById(bien.getFkIdtablaespUnidadMedida()).getDescripcionCabecera());
		}
		return listBien;
	}

	@Override
	public Bien updateBien(Bien bien) {
		return bienDAO.updateBien(bien);
	}
	
	@Override
	public List<Bien> llenaListBien(List<Bien> listBien) {
		for (Bien bien : listBien) {
			bien.getActivo().setDescripcionCategoriaActivo(
					tablaEspecificaService.findTablaEspecificaById(
							bien.getActivo().getFkIdtablaespCategoriaActivo())
							.getDescripcionCabecera());
			bien.setListRecursoUtilizadoBean(recursoUtilizadoService
					.findRecursoUtilizadoBeanByBienId(bien.getBienID()));
			
			List<PropuestaTransferenciaBien> listPropuestaTransferenciaBien=propuestaTransferenciaBienService.findPropuestaTransferenciaBienByBienId(bien.getBienID());
			bien.setListPropuestaTransferenciaBien(listPropuestaTransferenciaBien);
			//bien.setCantPropuestaTransferenciaBien(listPropuestaTransferenciaBien.size());
			
			Integer cantOrgBienTranferido=0;
			List<OrgBienTranferido> listOrgBienTranferido = new ArrayList<OrgBienTranferido>();
			for (PropuestaTransferenciaBien propuestaTransferenciaBien : listPropuestaTransferenciaBien) {
				listOrgBienTranferido = orgBienTranferidoService.findOrgBienTranferidoByPropuestaTransferenciaBienId(propuestaTransferenciaBien.getPropuestaTransferenciaBienID());
				cantOrgBienTranferido+=listOrgBienTranferido.size();
			}
			bien.setCantOrgBienTransferido(cantOrgBienTranferido);
		}
		return listBien;
	}

	@Override
	public void deleteBien(Integer idRegistro) {
		Bien bien =bienDAO.findBienById(idRegistro);
		
		bienDAO.deleteBien(bien);
				
	}
}
