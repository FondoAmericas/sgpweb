package pe.com.fondam.sgp.core.test.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.fondam.sgp.core.domain.ActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.TipoActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.TipoPeriodo;
import pe.com.fondam.sgp.core.service.CrearProgramaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:pe/com/fondam/sgp/core/resources/application-context.xml" })
public class CrearProgramaServiceTest {

	@Resource
	private CrearProgramaService crearProgramaService;
	
	

	public CrearProgramaService getCrearProgramaService() {
		return crearProgramaService;
	}

	public void setCrearProgramaService(CrearProgramaService crearProgramaService) {
		this.crearProgramaService = crearProgramaService;
	}

	@Test
	public void listTipoPeriodo() {
	
		List<TipoPeriodo> tipoperiodos = this.getCrearProgramaService().listTipoPeriodos();
		Assert.assertTrue(!tipoperiodos.isEmpty());
		
	}
	@Test
	public void listTipoActividadObligatoria() {
		Integer id=179;
		List<TipoActividadObligatoriaPrograma> tipoActividadesObli = this.getCrearProgramaService().findTipoActividadObligatoriaProgramasByIdtablaespTipo(id);
		Assert.assertTrue(!tipoActividadesObli.isEmpty());
		
	}
	
	@Test
	public void savePrograma(){
		TipoPeriodo t=new TipoPeriodo();
		t.setTipoPeriodoID(1);
		Programa programa=new Programa();
		programa.setNombrePrograma("zolanch");
		programa.setTipoPeriodo(t);
		programa.setFechaConvocatoria(new Date());
		this.getCrearProgramaService().savePrograma(programa);
		System.out.println("agrege prgrama-service");
	}

	public void deletePrograma(){

		Programa programa=crearProgramaService.findProgramaById(8);
		this.getCrearProgramaService().deletePrograma(programa);

	}
	
	@Test
	public void listProgramas(){
		List<Programa> list=getCrearProgramaService().listProgramas();
		Assert.assertTrue(!list.isEmpty());
		System.out.println("++"+list.size());
		
	}
	@Test
	public void saveActividadObligatoria(){
		Programa programa=new Programa();
		programa.setProgramaID(1);
		
		TipoActividadObligatoriaPrograma tipoActividadObligatoriaPrograma=new TipoActividadObligatoriaPrograma();
		tipoActividadObligatoriaPrograma.setTipoActividadObligatoriaProgramaID(2);
		
		ActividadObligatoriaPrograma actividadObligatoriaPrograma=new ActividadObligatoriaPrograma();
		actividadObligatoriaPrograma.setPrograma(programa);
		actividadObligatoriaPrograma.setTipoActividadObligatoriaPrograma(tipoActividadObligatoriaPrograma);

		this.crearProgramaService.saveActividadObligatoriaPrograma(actividadObligatoriaPrograma);
	System.out.println("ActividadObligatoriaPrograma.id = " + actividadObligatoriaPrograma.getActividadObligatoriaProgramaID());
		Assert.assertNotNull(actividadObligatoriaPrograma.getActividadObligatoriaProgramaID());
	}
	



}