package pe.com.fondam.sgp.web.InOutFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.NotSerializableException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import pe.com.fondam.sgp.core.domain.TablaEspecifica;


public class FileUploadDownload implements Serializable {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 5129221189285758031L;
	private String filePath=null;
	private String nombreArchivo=null;
	private String extension=null;

	
	public FileUploadDownload(){
        
		/*ResourceBundle resourceBundle = ResourceBundle.getBundle("pe.com.fondam.sgp.web.InOutFiles.PathFiles");
        this.filePath = resourceBundle.getString("pathFileDirectory");
        System.out.println("pathFileDirectory: "+filePath);
        */
		/*
		// aqui obtengo el camino absoluto de mi directorio actual
		 String directorio = System.getProperty("user.dir");
		 // aqui obtengo el tipo de separador que hay entre las carpetas
		 String separador = System.getProperty("file.separator");
		//new File("temp").mkdir();
		this.filePath=directorio+separador+"src"+separador+"fileTemp";
		  */      

	}

	
	/****UPLOAD *************************************************************************************************/
	@SuppressWarnings("rawtypes")
	public void archivoUpload(HttpServletRequest request, HttpServletResponse response)throws IOException, NotSerializableException{
		// create file upload factory and upload servlet
		try{
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		// set file upload progress listener
		FileUploadListener listener = new FileUploadListener();
		HttpSession session = request.getSession();
		session.setAttribute("LISTENER", listener);
		// upload servlet allows to set upload listener
		upload.setProgressListener(listener);
		List uploadedItems = null;
		FileItem fileItem = null;		
 		
			// iterate over all uploaded files
			uploadedItems = upload.parseRequest(request);
			Iterator i = uploadedItems.iterator();
			while (i.hasNext()){
				fileItem = (FileItem) i.next();
			    if (fileItem.isFormField() == false){
                        if (fileItem.getSize() > 0){
                            File uploadedFile = null;
                            String myFullFileName = fileItem.getName(),
                            myFileName = "",
                            slashType = (myFullFileName.lastIndexOf("\\") > 0) ? "\\" : "/"; // Windows or UNIX
                            int	startIndex = myFullFileName.lastIndexOf(slashType);
                            // Ignore the path and get the filename
                            myFileName = myFullFileName.substring(startIndex + 1, myFullFileName.length());
                            System.out.println("myFileName.ext: "+myFileName);
                            setNombreArchivo(myFileName.substring(0,myFileName.lastIndexOf(".")));
                            System.out.println("nombreArchivo: "+getNombreArchivo());
                            setExtension(myFileName.substring(myFileName.lastIndexOf( "." )));
                            System.out.println("extensionn: "+getExtension());
                            // Create new File object
                            uploadedFile = new File(getFilePath(), myFileName);
                            // Write the uploaded file to the system
                            fileItem.write(uploadedFile);
                        }
                 }
			}
		} 
		catch (FileUploadException e){
			e.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings({ "rawtypes" }) 
	public List recogeParam(HttpServletRequest request){
        List list = null;
        try{
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            list = upload.parseRequest(request);
        }catch (FileUploadException ex) {
        }

        return list;
    }
	
	public static byte[] convertirFileToArrayByte(File file) throws IOException{
        InputStream is = new FileInputStream(file);
        long length = file.length();
        if(length > Integer.MAX_VALUE){
            // el archivo es muy grande
        }
        byte[] bytes = new byte[(int)length];
        int offset = 0;
        int numRead = 0;
        while(offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0){
            offset += numRead;
        }
        if (offset < bytes.length){
            throw new IOException("No se completo la lectura del archivo "+file.getName());
        }
        is.close();
        return bytes;
    }
	
	/****DOWNLOAD **********************************************************************************************/
	public void downloadArchivo(HttpServletRequest request, HttpServletResponse response, String nombreArchivoExtension, byte[] imagenOArchivoByte, String extencion ) throws IOException {
		try {
			if(extencion.equals("jpg")||extencion.equals("JPG")||extencion.equals("jpeg")||extencion.equals("JPEG")){
				response.setContentType("image/jpg");
			}else{
				response.setContentType("APPLICATION/OCTET-STREAM");//unico para cualkier tipo de archivo
			}
            OutputStream os = response.getOutputStream();
            String nombreArchivo=nombreArchivoExtension.trim();
            response.setHeader("Content-Disposition","attachment;filename=\""+nombreArchivo);
            //System.out.println("nombreArchivo:"+nombreArchivo);
            response.setContentLength(imagenOArchivoByte.length);
            os.write(imagenOArchivoByte);
            os.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	/****OBTENER TIPO DE ARCHIVO  **********************************************************************************************/
	public String getArchivoExtensionById(List<TablaEspecifica> tablaEspecificaListTipoFormatoArchivo,int extensionId){
		//List<TablaEspecifica> tablaEspecificaListTipoFormatoArchivo=tablaEspecificaService.listTablaEspecificaByTablaGeneralId(40); //listado de tipos de archivos
	    String extension = null;
	    for(int i=0; i<tablaEspecificaListTipoFormatoArchivo.size(); i++){
	    	TablaEspecifica tablaEspecifica=tablaEspecificaListTipoFormatoArchivo.get(i);
	    	if(tablaEspecifica.getTablaEspecificaID()==extensionId){
	    		extension="."+tablaEspecifica.getDescripcionCabecera();
	    	}
	    }
	    return extension;
	}
	
	/****GETTERS AND SETTERS ***********************************************************************************/
	private void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	private void setExtension(String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return extension;
	}

	public String getFilePath() {
		return filePath;
	}
	
}
