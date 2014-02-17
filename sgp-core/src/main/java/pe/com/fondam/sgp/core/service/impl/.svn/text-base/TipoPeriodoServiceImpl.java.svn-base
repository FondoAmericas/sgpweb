package pe.com.fondam.sgp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.TipoPeriodoDAO;
import pe.com.fondam.sgp.core.domain.TipoPeriodo;
import pe.com.fondam.sgp.core.service.TipoPeriodoService;

@Service
public class TipoPeriodoServiceImpl implements TipoPeriodoService {

	@Resource
	TipoPeriodoDAO tipoPeriodoDAO;

	@Override
	public TipoPeriodo findTipoPeriodoById(Integer tipoPeriodoID) {
		return tipoPeriodoDAO.findTipoPeriodoById(tipoPeriodoID);
	}
}
