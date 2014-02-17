package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.ApreciacionResultadoBean;
import pe.com.fondam.sgp.core.bean.ProblemaSolucionBean;
import pe.com.fondam.sgp.core.bean.ReporteAvanceBeneficiarioBean;
import pe.com.fondam.sgp.core.dao.ReporteAvanceDAO;
import pe.com.fondam.sgp.core.domain.ApreciacionResultado;
import pe.com.fondam.sgp.core.domain.DetalleEstadoCabecera;
import pe.com.fondam.sgp.core.domain.ProblemaSolucion;
import pe.com.fondam.sgp.core.domain.ReporteAvance;
import pe.com.fondam.sgp.core.domain.ReporteAvanceBeneficiario;
import pe.com.fondam.sgp.core.service.DepProvDistService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.ReporteAvanceBeneficiarioService;
import pe.com.fondam.sgp.core.service.ReporteAvanceService;
import pe.com.fondam.sgp.core.service.ResultadoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.util.UtilList;

@Service
public class ReporteAvanceServiceImpl implements ReporteAvanceService {

	/***************** Inyecciones *****************************/
	@Resource
	ReporteAvanceDAO reporteAvanceDAO;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	DepProvDistService depProvDistService;
	
	@Resource
	ResultadoService resultadoService;
	
	@Resource
	ReporteAvanceBeneficiarioService reporteAvanceBeneficiarioService;
	
	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	/***************** Metodos *****************************/
	@Override
	public void saveReporteAvance(ReporteAvance reporteAvance) {
		reporteAvanceDAO.updateReporteAvance(reporteAvance);
		
	}

	@Override
	public List<ReporteAvance> findReporteAvanceXDatoProyectoId(
			Integer datoProyectoId) {
		
		String consulta=" from ReporteAvance where datoProyecto.datoProyectoID = ? ";
		
		Object[] params= new Object[1];
		params[0]= datoProyectoId;
		
		return reporteAvanceDAO.findReporteAvanceXConsulta(consulta, params);
	}

	@Override
	public ReporteAvance findReporteAvanceById(Integer reporteAvanceId) {
		return reporteAvanceDAO.findReporteAvanceById(reporteAvanceId);
	}	
	
	public List<ProblemaSolucionBean> llenaListProblemaSolucionBean(List<ProblemaSolucion> listProblemaSolucion){
		List<ProblemaSolucionBean> listProblemaSolucionBean = new ArrayList<ProblemaSolucionBean>();

		for (ProblemaSolucion problemaSolucion : listProblemaSolucion) {
			ProblemaSolucionBean problemaSolucionBean = new ProblemaSolucionBean();

			problemaSolucionBean.setComentario(problemaSolucion.getComentario()
					.length() < 200 ? problemaSolucion.getComentario()
					: problemaSolucion.getComentario().substring(0, 200));
			problemaSolucionBean.setProblema(problemaSolucion.getProblema()
					.length() < 200 ? problemaSolucion.getProblema()
					: problemaSolucion.getProblema().substring(0, 200));
			problemaSolucionBean.setProblemaRelevanteAlProy(problemaSolucion
					.getProblemaRelevanteAlProy());
			problemaSolucionBean.setProblemaSolucionID(problemaSolucion
					.getProblemaSolucionID());
			problemaSolucionBean.setReporteAvance(findReporteAvanceById(problemaSolucion.getReporteAvance()
							.getReporteAvanceID()));
			problemaSolucionBean.setResultado(problemaSolucion.getResultado()
					.length() < 200 ? problemaSolucion.getResultado()
					: problemaSolucion.getResultado().substring(0, 200));
			problemaSolucionBean.setSolucion(problemaSolucion.getSolucion()
					.length() < 200 ? problemaSolucion.getSolucion()
					: problemaSolucion.getSolucion().substring(0, 200));

			listProblemaSolucionBean.add(problemaSolucionBean);
		}
		return listProblemaSolucionBean;
	
	}
	
	public List<ApreciacionResultadoBean> llenaListApreciacionResultadoBean(List<ApreciacionResultado> listApreciacionResultado){
		
		List<ApreciacionResultadoBean> listApreciacionResultadoBean = new ArrayList<ApreciacionResultadoBean>();
		for (ApreciacionResultado apreciacionResultado : listApreciacionResultado) {
			ApreciacionResultadoBean apreciacionResultadoBean = llenaApreciacionResultadoBean(apreciacionResultado);

			listApreciacionResultadoBean.add(apreciacionResultadoBean);
		}
		return listApreciacionResultadoBean;
	}

	private ApreciacionResultadoBean llenaApreciacionResultadoBean(
			ApreciacionResultado apreciacionResultado) {

		ApreciacionResultadoBean apreciacionResultadoBean = new ApreciacionResultadoBean();

		apreciacionResultadoBean.setApreciacionResultadoID(apreciacionResultado
				.getApreciacionResultadoID());
		apreciacionResultadoBean.setComentario(apreciacionResultado
				.getComentario().length() < 200 ? apreciacionResultado
				.getComentario() : apreciacionResultado.getComentario()
				.substring(0, 350));
		apreciacionResultadoBean
				.setFkIdtablaespApreciacionResultadoRa(apreciacionResultado
						.getFkIdtablaespApreciacionResultadoRa());
		apreciacionResultadoBean
				.setDescripcionTipoApreciacionResultadoRa(tablaEspecificaService
						.findTablaEspecificaById(
								apreciacionResultado
										.getFkIdtablaespApreciacionResultadoRa())
						.getDescripcionCabecera());
		apreciacionResultadoBean.setReporteAvance(findReporteAvanceById(apreciacionResultado.getReporteAvance()
						.getReporteAvanceID()));

		return apreciacionResultadoBean;
	}
	/*
	public List<BeneficiariosPorResultadoBean> llenaListBeneficiariosPorResultadoBean(List<BeneficiariosPorResultado> listBeneficiariosPorResultado){
		
		List<BeneficiariosPorResultadoBean> listBeneficiariosPorResultadoBean = new ArrayList<BeneficiariosPorResultadoBean>();
		for (BeneficiariosPorResultado beneficiariosPorResultado : listBeneficiariosPorResultado) {

			BeneficiariosPorResultadoBean beneficiariosPorResultadoBean = llenaBeneficiariosPorResultadoBean(beneficiariosPorResultado);

			listBeneficiariosPorResultadoBean
					.add(beneficiariosPorResultadoBean);
		}
		return listBeneficiariosPorResultadoBean;
	}
	
	public BeneficiariosPorResultadoBean llenaBeneficiariosPorResultadoBean(
			BeneficiariosPorResultado beneficiariosPorResultado) {

		BeneficiariosPorResultadoBean beneficiariosPorResultadoBean = new BeneficiariosPorResultadoBean();

		beneficiariosPorResultadoBean
				.setBeneficiariosPorResultadoID(beneficiariosPorResultado
						.getBeneficiariosPorResultadoID());
		beneficiariosPorResultadoBean
				.setCantidadProgramado(beneficiariosPorResultado
						.getCantidadProgramado());
		beneficiariosPorResultadoBean
				.setCaracteristicasPoblacion(beneficiariosPorResultado
						.getCaracteristicasPoblacion());
		beneficiariosPorResultadoBean.setDescripcion(beneficiariosPorResultado
				.getDescripcion());
		beneficiariosPorResultadoBean
				.setDescripcionEstrato(tablaEspecificaService
						.findTablaEspecificaById(
								beneficiariosPorResultado
										.getFkidtablaespEstrato())
						.getDescripcionCabecera());
		beneficiariosPorResultadoBean
				.setDescripcionTipoBeneficiario(tablaEspecificaService
						.findTablaEspecificaById(
								beneficiariosPorResultado
										.getFkIdtablaespTipoBeneficiario())
						.getDescripcionCabecera());
		beneficiariosPorResultadoBean
				.setFkidtablaespEstrato(beneficiariosPorResultado
						.getFkidtablaespEstrato());
		beneficiariosPorResultadoBean
				.setFkIdtablaespTipoBeneficiario(beneficiariosPorResultado
						.getFkIdtablaespTipoBeneficiario());
		beneficiariosPorResultadoBean.setDepartamento(depProvDistService
				.findDescripcionDepProvDist("depa", beneficiariosPorResultado
						.getUbicacionProyecto().getDepProvDist()));
		beneficiariosPorResultadoBean.setProvincia(depProvDistService
				.findDescripcionDepProvDist("prov", beneficiariosPorResultado
						.getUbicacionProyecto().getDepProvDist()));
		beneficiariosPorResultadoBean.setDistrito(depProvDistService
				.findDescripcionDepProvDist("dist", beneficiariosPorResultado
						.getUbicacionProyecto().getDepProvDist()));
		beneficiariosPorResultadoBean.setDetalleUbicacion(beneficiariosPorResultado.getUbicacionProyecto().getDetalleUbicacion());
		
		if (beneficiariosPorResultado.getResultado() != null) {
			beneficiariosPorResultadoBean
					.setDescripcionResultado(resultadoService
							.findResultadoByID(
									beneficiariosPorResultado.getResultado()
											.getResultadoID())
							.getDefinicionResultado());
			beneficiariosPorResultadoBean
					.setResultadoID(beneficiariosPorResultado.getResultado()
							.getResultadoID());
		} else {
			beneficiariosPorResultadoBean
					.setDescripcionResultado("No se asigno resultado para estos beneficiarios.");
		}

		beneficiariosPorResultadoBean
				.setFkIdtablaespTipoBeneficiario(beneficiariosPorResultado
						.getFkIdtablaespTipoBeneficiario());

		beneficiariosPorResultadoBean
				.setListReporteAvanceBeneficiarioBean(llenaListReporteAvanceBeneficiarioBean(reporteAvanceBeneficiarioService
						.findReporteAvanceBeneficiarioXBeneficiariosPorResultadoId(beneficiariosPorResultado
								.getBeneficiariosPorResultadoID())));

		return beneficiariosPorResultadoBean;
	}
	*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteAvanceBeneficiarioBean> llenaListReporteAvanceBeneficiarioBean(
			List<ReporteAvanceBeneficiario> listReporteAvanceBeneficiario) {

		List<ReporteAvanceBeneficiarioBean> listReporteAvanceBeneficiarioBean = new ArrayList<ReporteAvanceBeneficiarioBean>();
		for (ReporteAvanceBeneficiario reporteAvanceBeneficiario : listReporteAvanceBeneficiario) {
			ReporteAvanceBeneficiarioBean reporteAvanceBeneficiarioBean = new ReporteAvanceBeneficiarioBean();

			reporteAvanceBeneficiarioBean
					.setCantidadLograda(reporteAvanceBeneficiario
							.getCantidadLograda());
			reporteAvanceBeneficiarioBean
					.setReporteAvanceBeneficiarioID(reporteAvanceBeneficiario
							.getReporteAvanceBeneficiarioID());
			reporteAvanceBeneficiarioBean
					.setPeriodoReporte(findReporteAvanceById(
									reporteAvanceBeneficiario
											.getReporteAvance()
											.getReporteAvanceID()).getPeriodo());
			reporteAvanceBeneficiarioBean.setReporteAvance(findReporteAvanceById(reporteAvanceBeneficiario.getReporteAvance().getReporteAvanceID()));

			listReporteAvanceBeneficiarioBean
					.add(reporteAvanceBeneficiarioBean);
		}
		return (List<ReporteAvanceBeneficiarioBean>) UtilList.orderAscList(
				listReporteAvanceBeneficiarioBean, "periodoReporte");
	}

	@Override
	public List<ReporteAvance> findReporteAvanceXDatoProyectoIdSinAprobar(
			Integer datoProyectoID) {
		
		List<DetalleEstadoCabecera> listDetalleEstadoCabecera=detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraPrefijo("estrpav");
		Integer estadoAprobadoID=0;
				for (DetalleEstadoCabecera detalleEstadoCabecera : listDetalleEstadoCabecera) {
					if(detalleEstadoCabecera.getPrefijoEstado().equals("apro")){
						estadoAprobadoID=detalleEstadoCabecera.getDetalleEstadoCabeceraID();
					}
				}
		String queryString = "from ReporteAvance where datoProyecto.datoProyectoID= ? and fkIdDetalleEstadoCabEstRepAvance <> ? "; 
		Object[] params = new Object[2];
		params[0] = datoProyectoID;
		params[1]=estadoAprobadoID;

		return reporteAvanceDAO.findReporteAvanceXConsulta(queryString, params);
		}

	}
