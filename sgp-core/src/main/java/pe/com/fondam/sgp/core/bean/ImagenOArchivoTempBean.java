package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

public class ImagenOArchivoTempBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7722288020713188921L;
	private byte[] imagenODocumento;
	
	
	public ImagenOArchivoTempBean(){
		
	}

	public byte[] getImagenODocumento() {
		return imagenODocumento;
	}

	public void setImagenODocumento(byte[] imagenODocumento) {
		this.imagenODocumento = imagenODocumento;
	}
	
}
