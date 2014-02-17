package pe.com.fondam.sgp.report.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.com.fondam.sgp.report.EngineReportService;

public class EngineReportServiceImpl implements EngineReportService {
	public static final String EXPORT_TYPE_WEB = "WEB";
	public static final String EXPORT_TYPE_DESKTOP = "DESKTOP";
	public static final String REPORT_TYPE_PDF = "TYPE_PDF";
	public static final String REPORT_TYPE_XLS = "TYPE_XLS";
	
	@Override
	public byte[] exportReportBytePdf(Collection<?> beanCollection, Map<?, ?> parameters, String sourceFileReportName)
			throws JRException, IOException {
		JasperPrint jasperPrint = jasperPrintObjectInstance(beanCollection, parameters, sourceFileReportName, null);
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@Override
	public byte[] exportReportWithSubReportBytePdf(Collection<?> beanCollection, Map<?, ?> parameters, String sourceFileReportName, String sourceFileSubReportName)
			throws JRException, IOException {
		JasperPrint jasperPrint = jasperPrintObjectInstance(beanCollection, parameters, sourceFileReportName, sourceFileSubReportName);
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@SuppressWarnings("unchecked")
	private JasperPrint jasperPrintObjectInstance(Collection<?> beanCollection, Map<?, ?> parameters, String sourceFileReportName, String sourceFileSubReportName)
			throws JRException {
		/**Compile subReport **/
		if (sourceFileSubReportName != null) {
			JasperCompileManager.compileReportToFile(sourceFileSubReportName);	
			
		}
		JasperReport jasperReport = JasperCompileManager.compileReport(sourceFileReportName);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(beanCollection);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, (Map<String, Object>) parameters, dataSource);
		return jasperPrint;
	}
	
}
