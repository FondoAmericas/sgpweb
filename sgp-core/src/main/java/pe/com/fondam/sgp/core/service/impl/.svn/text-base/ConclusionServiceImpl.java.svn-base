package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.ConclusionBean;
import pe.com.fondam.sgp.core.dao.ConclusionDAO;
import pe.com.fondam.sgp.core.dao.DetalleConcluIfDAO;
import pe.com.fondam.sgp.core.domain.Conclusion;
import pe.com.fondam.sgp.core.service.ConclusionService;
import pe.com.fondam.sgp.core.service.DetalleConcluIfService;

@Service
public class ConclusionServiceImpl implements ConclusionService {

	//********** inyecciones **************//
	@Resource
	ConclusionDAO conclusionDAO;
	
	@Resource
	DetalleConcluIfService detalleConcluIfService;
	
	@Resource
	DetalleConcluIfDAO detalleConcluIfDAO;

	
	//********** metodos **************//
	@Override
	public List<Conclusion> findconclusionByInformeFinalId(
			Integer informeFinalId) {
		String consulta=" from Conclusion where informeFinal.informeFinalID = ? ";
		Object[] params= new Object[1];
		params[0]=informeFinalId;
		return conclusionDAO.findConclusionByConsulta(consulta, params);
	}


	@Override
	public List<Conclusion> llenaCompletaListConclusion(
			List<Conclusion> listConclusion) {

		for (Conclusion conclusion : listConclusion) {
			conclusion.setDetalleConcluIf(detalleConcluIfService.llenaCompletoDetalleConcluIf(detalleConcluIfDAO.findDetalleConcluIfById(conclusion.getDetalleConcluIf().getDetalleConcluIFID())));
		}
		return listConclusion;
	}


	@Override
	public Conclusion updateConclusion(Conclusion conclusion) {
		return conclusionDAO.updateConclusion(conclusion);
	}


	@Override
	public void deleteConclusion(Integer idRegistro) {
		conclusionDAO.deleteConclusion(conclusionDAO.findConclusionById(idRegistro));
		
	}


	@Override
	public List<ConclusionBean> llenaListConclusionBean(
			List<Conclusion> listConclusion) {
		
		List<ConclusionBean> listConclusionBean= new ArrayList<ConclusionBean>();
		for (Conclusion conclusion : listConclusion) {
			listConclusionBean.add(llenaConclusionBean( conclusion));
		}
		return listConclusionBean;
	}


	private ConclusionBean llenaConclusionBean(Conclusion conclusion) {
		ConclusionBean conclusionBean= new ConclusionBean();
		
		conclusionBean.setComentario(conclusion.getComentario());
		conclusionBean.setConclusionID(conclusion.getConclusionID());
		conclusionBean.setDetalleConcluIf(conclusion.getDetalleConcluIf());
		conclusionBean.setInformeFinal(conclusion.getInformeFinal());
		
		return conclusionBean;
	}	
	
}
