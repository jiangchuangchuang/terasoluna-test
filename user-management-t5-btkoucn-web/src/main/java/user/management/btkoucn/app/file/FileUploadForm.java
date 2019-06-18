package user.management.btkoucn.app.file;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {
	
	private MultipartFile file;

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	} 
}
