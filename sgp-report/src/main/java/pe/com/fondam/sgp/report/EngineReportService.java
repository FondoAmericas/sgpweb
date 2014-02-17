package pe.com.fondam.sgp.report;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

public interface EngineReportService {

	byte[] exportReportBytePdf(Collection<?> beanCollection,
			Map<?, ?> parameters, String sourceFileReportName) throws JRException,
			IOException;
	
	byte[] exportReportWithSubReportBytePdf(Collection<?> beanCollection,
			Map<?, ?> parameters, String sourceFileReportName, String sourceFileSubReportName) throws JRException,
			IOException;
}
