package pe.com.fondam.sgp.web.controller.principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.bean.CargaFormularioBean;
import pe.com.fondam.sgp.core.domain.CargaFormulario;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.service.CargaFormularioService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.web.InOutFiles.FileUploadDownload;
import pe.com.fondam.sgp.web.InOutFiles.LinkFile;

@Controller
public class CargaFormularioController {

	//***************** inyecciones ********************//
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	CargaFormularioService cargaFormularioService;
	
	
	//***************** variables globales ********************//
	FileUploadDownload fileUploadDownload=new FileUploadDownload();
	
	
	//***************** metodos ********************//
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
	@RequestMapping(value = "/principal/showCargaFormulario.jspx")
	public ModelAndView showCargaFormulario(HttpServletRequest request,
			HttpServletResponse resp) throws IOException {
		//securityController.removeSessionAttribute(request);
		Map<String, Object> model = new HashMap<String, Object>();

		CargaFormularioBean cargaFormularioBean= new CargaFormularioBean();
		model.put("cargaFormularioBean",cargaFormularioBean);
		
		return new ModelAndView("mostrarCargaFormulario", model);

	}
	
	@RequestMapping(value = "/principal/grabarFormulario.jspx")
	public String grabarFormulario(
			@ModelAttribute("cargaFormularioBean") CargaFormularioBean cargaFormularioBean,
			HttpServletRequest request,
			HttpServletResponse resp) throws IOException {
		//securityController.removeSessionAttribute(request);
		//Map<String, Object> model = new HashMap<String, Object>();

		String nombreArchivo= request.getParameter("nombreArchivo");
		String extencion= request.getParameter("extension");
		int codExtension =  tablaEspecificaService.findIdByDescripcionCabecera(extencion);
		
		CargaFormulario cargaFormulario= new CargaFormulario();
		cargaFormulario.setDescripcionFormulario(nombreArchivo);
		cargaFormulario.setFkIdtablaespTipoArchivo(codExtension);
		cargaFormulario.setFormulario(cargaFormularioBean.getFormulario());
		cargaFormularioService
				.updateCargaFormulario(cargaFormulario);
		
		 return "redirect:showCargaFormulario.jspx";

	}
	
	@RequestMapping(value = "/principal/showFormulario.jspx")
	public ModelAndView showFormulario(
			@RequestParam(required = false, value = "formularioId") Integer formularioId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<TablaEspecifica> tablaEspecificaListTipoFormatoArchivo = tablaEspecificaService
				.listTablaEspecificaByTablaGeneralId(40);// listado de
															// extensiones
		List<LinkFile> listLinkFile = new ArrayList<LinkFile>();
		LinkFile linkFile = new LinkFile();
		//ImagenOArchivo imagenOArchivo = null;
		CargaFormulario cargaFormulario=null;
		try {
			cargaFormulario = cargaFormularioService.findCargaFormularioById(formularioId);

			if (cargaFormulario != null) {
				linkFile.setId(cargaFormulario.getCargaFormularioID());
				linkFile.setNombre(cargaFormulario.getDescripcionFormulario());
				String extension = fileUploadDownload.getArchivoExtensionById(
						tablaEspecificaListTipoFormatoArchivo,cargaFormulario.getFkIdtablaespTipoArchivo());
				linkFile.setExtension(extension);
				listLinkFile.add(linkFile);
			}
		} catch (Exception e) {

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listLinkFile", listLinkFile);
		model.put("variable", "downloadFormulario.jspx");
		return new ModelAndView("mostrarArchivoDownload", model);
	}

	@RequestMapping(value = "/principal/downloadFormulario.jspx")
	public void downloadFormulario(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String idArchivo = request.getParameter("param");
		CargaFormulario cargaFormulario = new CargaFormulario();
		cargaFormulario = cargaFormularioService.findCargaFormularioById(Integer
				.parseInt(idArchivo));
		String extension = tablaEspecificaService.findTablaEspecificaById(cargaFormulario.getFkIdtablaespTipoArchivo()).getDescripcionCabecera();
		String archivo = cargaFormulario.getDescripcionFormulario() +"."+ extension;
		fileUploadDownload.downloadArchivo(request, response, archivo,
		cargaFormulario.getFormulario(),extension);
	}

}
