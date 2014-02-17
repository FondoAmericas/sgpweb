package pe.com.fondam.sgp.web.controller.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.DetallePagoLiquidacion;
import pe.com.fondam.sgp.core.domain.LiquidacionGasto;
import pe.com.fondam.sgp.core.domain.PagoLiquidacion;
import pe.com.fondam.sgp.core.domain.TipoCambio;
import pe.com.fondam.sgp.core.service.DesembolsoService;
import pe.com.fondam.sgp.core.service.DetallePagoLiquidacionService;
import pe.com.fondam.sgp.core.service.FuenteFinanciadoraService;
import pe.com.fondam.sgp.core.service.LiquidacionGastoService;
import pe.com.fondam.sgp.core.service.PagoLiquidacionService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.TipoCambioService;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class ReportLiquidacionGastos {

	//***************** fuentes *****************************//
	private static Font titFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,Font.BOLD); 
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 11,Font.BOLD);
	 private static Font norFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.NORMAL);
	 
	 
	// ***************** inyecciones ******************//
	 @Resource
	 LiquidacionGastoService liquidacionGastoService ;
	 
	 @Resource
	 FuenteFinanciadoraService fuenteFinanciadoraService;
	 
	 @Resource
	 PagoLiquidacionService pagoLiquidacionService;
	 
	 @Resource
	 TablaEspecificaService tablaEspecificaService;
	 
	 @Resource
	 DetallePagoLiquidacionService detallePagoLiquidacionService;
	 
	 @Resource
	 DesembolsoService desembolsoService;
	 
	 @Resource
	 TipoCambioService tipoCambioService;
	 
	// ***************** metodos ******************//
	 @RequestMapping(value = "/principal/reportFormato2.jspx", method = RequestMethod.GET)
		public void reportFormato2(
				@RequestParam(required = true, value = "liquidacionGastoID") Integer  liquidacionGastoID,
				HttpServletRequest request, HttpServletResponse response)
				throws DocumentException, IOException {

		LiquidacionGasto liquidacionGasto = liquidacionGastoService.findLiquidacionGastoByID(liquidacionGastoID);
		liquidacionGasto= llenaLiquidacionGastoConPagos(liquidacionGasto);
			
			ServletContext context = request.getSession().getServletContext();
			String appPath = context.getRealPath("/");
			 
			String exportReportName = "CostoActividadFuente-"
					+ new Date().getTime() + ".pdf";
			
			Document doc = new Document(PageSize.A4_LANDSCAPE.rotate(), 10,10,5,5);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			PdfWriter.getInstance(doc, baos);

			doc = armaReporteFormato2(doc,appPath,liquidacionGasto );
			
			printReportePage(baos, response, exportReportName);

		}
	 
	
	 //********************* metodos internos **********************//
	private void printReportePage(ByteArrayOutputStream baos,
				HttpServletResponse response, String exportReportName)
				throws IOException {
			response.setContentType("application/pdf");

			response.setHeader("Content-Disposition", "inline; filename = "
					+ exportReportName);
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);

			response.setContentLength(baos.size());
			OutputStream out = response.getOutputStream();
			baos.writeTo(out);
			// out.write(byteArray, 0, byteArray.length);
			out.flush();
			out.close();

		}

	private static void agregarLineasEnBlanco(Paragraph parrafo, int nLineas) {
		for (int i = 0; i < nLineas; i++)
			parrafo.add(new Paragraph(" "));
	}
	
	private LiquidacionGasto llenaLiquidacionGastoConPagos(
			LiquidacionGasto liquidacionGasto) {
		
		 liquidacionGasto.setFuenteFinanciadora(fuenteFinanciadoraService.findFuenteFinanciadoraById(liquidacionGasto.getFuenteFinanciadora().getFuenteFinanciadoraID()));
		 liquidacionGasto.setListPagoLiquidacion(llenaPagoLiquidacionDetalle(pagoLiquidacionService.findPagoLiquidacionByLiquidacionGastoId(liquidacionGasto.getLiquidacionGastoID())));
		 
		return liquidacionGasto;
	}

	private List<PagoLiquidacion> llenaPagoLiquidacionDetalle(
			List<PagoLiquidacion> listPagoLiquidacion) {

		for (PagoLiquidacion pagoLiquidacion : listPagoLiquidacion) {
			pagoLiquidacion.setTipoMonedaDesc(tablaEspecificaService.findTablaEspecificaById(pagoLiquidacion.getFkIdtablaespTipoMoneda()).getDescripcionCabecera());
			pagoLiquidacion.setTipoComprobantePagoDesc(tablaEspecificaService.findTablaEspecificaById(pagoLiquidacion.getFkIdtablaespTipoComprobantePago()).getDescripcionCabecera());
			pagoLiquidacion.setLstDetallePagoLiquidacion(llenaDetallePagoLiquidacion( detallePagoLiquidacionService.findDetallePagoLiquidacionByPagoLiquidacionId(pagoLiquidacion.getPagoLiquidacionID())));
			
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			pagoLiquidacion.setFechaEmisionString(formateador.format(pagoLiquidacion.getFechaEmision()));
			
			pagoLiquidacion.setDesembolso(llenaTipoCambio(desembolsoService.findDesembolsoByID(pagoLiquidacion.getDesembolsoID())));
		}
		return listPagoLiquidacion;
	}
	
	private Desembolso llenaTipoCambio(Desembolso desembolso) {
		desembolso.setListTipoCambio(tipoCambioService.findTipoCambioByDesembolsoID(desembolso.getDesembolsoID()));
		
		return desembolso;
	}

	private List<DetallePagoLiquidacion> llenaDetallePagoLiquidacion(
			List<DetallePagoLiquidacion> listDetallePagoLiquidacion) {

		for (DetallePagoLiquidacion detallePagoLiquidacion : listDetallePagoLiquidacion) {
			detallePagoLiquidacion.setUnidadMedidaDesc(tablaEspecificaService.findTablaEspecificaById(detallePagoLiquidacion.getFkIdtablaespUnidadMedida()).getDescripcionCabecera());
		}
		return listDetallePagoLiquidacion;
	}

	private Document armaReporteFormato2(Document doc, String appPath,
			LiquidacionGasto liquidacionGasto) throws DocumentException, IOException {

		doc.open();
		Paragraph parrafoHoja = new Paragraph();
		
		Image img=Image.getInstance(appPath+"images/fondam.jpg");
		img.setAlignment(Image.ALIGN_LEFT);
		img.scaleAbsolute(130,70);//(newWidth, newHeight)
		parrafoHoja.add(img);
		parrafoHoja.setAlignment(Paragraph.ALIGN_LEFT);
		
		Chunk poEstado0= new Chunk("Formato de Liquidacion N° 2 ",titFont);
		
		parrafoHoja.add(poEstado0);
		parrafoHoja.setAlignment(Paragraph.ALIGN_LEFT);
		
		agregarLineasEnBlanco(parrafoHoja, 1);
		doc.add(parrafoHoja);
		
		Paragraph parrafoHoja1 = new Paragraph();
		Chunk poEstado1= new Chunk("Entidad: ",catFont);
		Chunk poEstadoText1= new Chunk( liquidacionGasto.getFuenteFinanciadora().getInstitucion().getNombreInstitucion(),norFont);
		
		parrafoHoja1.add(poEstado1);
		parrafoHoja1.add(poEstadoText1);
		parrafoHoja1.setAlignment(Paragraph.ALIGN_LEFT);
		
		//agregarLineasEnBlanco(parrafoHoja, 1);
		//doc.add(parrafoHoja);
		Paragraph parrafoHoja2 = new Paragraph();
		Chunk poEstado2= new Chunk("Proyecto: ",catFont);
		Chunk poEstadoText2= new Chunk( liquidacionGasto.getDatoProyecto().getNombreProyecto(),norFont);
		
		parrafoHoja2.add(poEstado2);
		parrafoHoja2.add(poEstadoText2);
		parrafoHoja2.setAlignment(Paragraph.ALIGN_LEFT);
		
		doc.add(parrafoHoja1);
		agregarLineasEnBlanco(parrafoHoja2, 1);
		doc.add(parrafoHoja2);
		
		Paragraph parrafoTituloCentral = new Paragraph("LIQUIDACION DE GASTOS DE FONDOS RECIBIDOS",titFont);
		parrafoTituloCentral.setAlignment(Paragraph.ALIGN_LEFT);
		
		agregarLineasEnBlanco(parrafoTituloCentral, 1);
		doc.add(parrafoTituloCentral);
		
				//tabla
				Integer cantCol=8;
				PdfPTable tabla= new PdfPTable(cantCol);
				
				//anchos de celdas de la tabla
				float[] medidaCeldas = new float[cantCol];
				medidaCeldas[0] = 2.50f;
				medidaCeldas[1] = 2.50f;
				medidaCeldas[2] = 2.50f;
				medidaCeldas[3] = 2.50f;
				medidaCeldas[4] = 5.00f;
				medidaCeldas[5] = 7.50f;
				medidaCeldas[6] = 2.50f;
				medidaCeldas[7] = 2.50f;
								
				tabla.setWidths(medidaCeldas);
				tabla.setWidthPercentage(100);
				
				 PdfPCell c1 = new PdfPCell(new Paragraph("Fecha (dd/mm/aaaa)",norFont));
				    //c1.setRowspan(2);
				 c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
				    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c1);

				    PdfPCell c2 = new PdfPCell(new Paragraph("Numero Cheque",norFont));
				    //c2.setRowspan(2);
				 c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
				    c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c2);

				    PdfPCell c3 = new PdfPCell(new Paragraph("N° Factura, Boleta, Recibo",norFont));
				    //c3.setRowspan(2);
				    c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
				    c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c3);
				    
				    PdfPCell c4 = new PdfPCell(new Paragraph("RUC Proveedor",norFont));
				    //c4.setRowspan(2);
				    c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
				    c4.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c4);
				    
				    PdfPCell c5 = new PdfPCell(new Paragraph("Nombre Proveedor",norFont));
				    //c4.setRowspan(2);
				    c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
				    c5.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c5);
				    
				    PdfPCell c6 = new PdfPCell(new Paragraph("Concepto",norFont));
				    //c4.setRowspan(2);
				    c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
				    c6.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c6);
				    
				    PdfPCell c7 = new PdfPCell(new Paragraph("Total",norFont));
				    //c4.setRowspan(2);
				    c7.setBackgroundColor(BaseColor.LIGHT_GRAY);
				    c7.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c7.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c7);
				    
				    PdfPCell c8 = new PdfPCell(new Paragraph("Monto IGV",norFont));
				    //c4.setRowspan(2);
				    c8.setBackgroundColor(BaseColor.LIGHT_GRAY);
				    c8.setHorizontalAlignment(Element.ALIGN_CENTER);
				    c8.setVerticalAlignment(Element.ALIGN_MIDDLE);
				    tabla.addCell(c8);
				    
				    Double sumaSubTotal=0.0;
				    Double sumaIGV=0.0;
				    
				    //colocar cantidad de decimales
				    NumberFormat nf = NumberFormat.getInstance();
				    nf.setMaximumFractionDigits(2); //3 decimales
				    nf.setMinimumFractionDigits(2);
				    
				    for (PagoLiquidacion pagoLiquidacion : liquidacionGasto.getListPagoLiquidacion()) {
				    	int cantDetallePago = pagoLiquidacion.getLstDetallePagoLiquidacion().size()==0?1:pagoLiquidacion.getLstDetallePagoLiquidacion().size();
				    	
				    	PdfPCell cInt = new PdfPCell(new Paragraph(pagoLiquidacion.getFechaEmisionString() ,norFont));
				    	cInt.setRowspan(cantDetallePago);
				    	cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(String.valueOf(pagoLiquidacion.getNumeroCheque()),norFont));
						cInt.setRowspan(cantDetallePago);
						cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(pagoLiquidacion.getTipoComprobantePagoDesc() +"\n"+pagoLiquidacion.getNumeroDocumento(),norFont));
						cInt.setRowspan(cantDetallePago);
						cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(String.valueOf(pagoLiquidacion.getRucProveedor()),norFont));
						cInt.setRowspan(cantDetallePago);
						cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
						tabla.addCell(cInt);
						
						cInt = new PdfPCell(new Paragraph(String.valueOf(pagoLiquidacion.getRazonSocial()),norFont));
						cInt.setRowspan(cantDetallePago);
						cInt.setHorizontalAlignment(Element.ALIGN_LEFT);
						tabla.addCell(cInt);

						String parrafoDetalle = "";
						for (DetallePagoLiquidacion detallePagoLiquidacion : pagoLiquidacion.getLstDetallePagoLiquidacion()) {
							parrafoDetalle=parrafoDetalle + String.valueOf(detallePagoLiquidacion.getCantidad()+"-"+detallePagoLiquidacion.getUnidadMedidaDesc()+"  "+  detallePagoLiquidacion.getConcepto())+" \n";
							
						}
						
						cInt = new PdfPCell(new Paragraph(parrafoDetalle,norFont));
						cInt.setRowspan(cantDetallePago);
						cInt.setHorizontalAlignment(Element.ALIGN_LEFT);
						tabla.addCell(cInt);
						
						if(pagoLiquidacion.getFkIdtablaespTipoMoneda().equals(169)){ //dolares
							Double tc=0.0;
							for (TipoCambio tipoCambio : pagoLiquidacion.getDesembolso().getListTipoCambio()) {
								if((tipoCambio.getFkIdtablaespTipoMonedaConvertDE()== pagoLiquidacion.getFkIdtablaespTipoMoneda())&&
										(tipoCambio.getFkIdtablaespTipoMonedaConvertA()== 168)){
									tc=tipoCambio.getTipoCambio();
								}
							}
							cInt = new PdfPCell(new Paragraph(String.valueOf(pagoLiquidacion.getTipoMonedaDesc()+"\n"+ nf.format(pagoLiquidacion.getMontoTotal())+"\nTC: "+nf.format(tc)),norFont));
							cInt.setRowspan(cantDetallePago);
							cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
							tabla.addCell(cInt);
							
							cInt = new PdfPCell(new Paragraph(String.valueOf(pagoLiquidacion.getTipoMonedaDesc()+"\n"+ nf.format(pagoLiquidacion.getMontoIgv())+"\nTC: "+nf.format(tc)),norFont));
							cInt.setRowspan(cantDetallePago);
							cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
							tabla.addCell(cInt);
								
							sumaSubTotal += pagoLiquidacion.getMontoTotal()*tc;
							sumaIGV += pagoLiquidacion.getMontoIgv()*tc;
						}else{ //soles
						
							cInt = new PdfPCell(new Paragraph(String.valueOf(pagoLiquidacion.getTipoMonedaDesc()+"\n"+ nf.format(pagoLiquidacion.getMontoTotal())),norFont));
							cInt.setRowspan(cantDetallePago);
							cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
							tabla.addCell(cInt);
							
							cInt = new PdfPCell(new Paragraph(String.valueOf(pagoLiquidacion.getTipoMonedaDesc()+"\n"+ nf.format(pagoLiquidacion.getMontoIgv())),norFont));
							cInt.setRowspan(cantDetallePago);
							cInt.setHorizontalAlignment(Element.ALIGN_CENTER);
							tabla.addCell(cInt);
						
							sumaSubTotal += pagoLiquidacion.getMontoTotal();
							sumaIGV += pagoLiquidacion.getMontoIgv();
						}
						
					}				    
				    
				    /*NumberFormat nf = NumberFormat.getInstance();
				    nf.setMaximumFractionDigits(2); //3 decimales
				    nf.setMinimumFractionDigits(2);*/
				    
				    PdfPCell cIntFoot = new PdfPCell(new Paragraph("Valores Totales en SOLES: ",titFont));
				    cIntFoot.setColspan(6);
					cIntFoot.setHorizontalAlignment(Element.ALIGN_CENTER);
					tabla.addCell(cIntFoot);
					
					cIntFoot = new PdfPCell(new Paragraph(String.valueOf("S/. "+nf.format(sumaSubTotal)),titFont));
				    cIntFoot.setColspan(1);
					cIntFoot.setHorizontalAlignment(Element.ALIGN_CENTER);
					tabla.addCell(cIntFoot);
					
					cIntFoot = new PdfPCell(new Paragraph(String.valueOf("S/. "+nf.format(sumaIGV)),titFont));
				    cIntFoot.setColspan(1);
					cIntFoot.setHorizontalAlignment(Element.ALIGN_CENTER);
					tabla.addCell(cIntFoot);				
				    
				    doc.add(tabla);
				
		doc.close();

		return doc;
	}

}
