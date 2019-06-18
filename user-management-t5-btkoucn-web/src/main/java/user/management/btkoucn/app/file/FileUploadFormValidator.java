package user.management.btkoucn.app.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadFormValidator implements SmartValidator {

	@Value("${upload.allowableFileSize}")
    private int uploadAllowableFileSize;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return FileUploadForm.class.isAssignableFrom(clazz);
	}

    @Override
    public void validate(Object target, Errors errors) {
        validate(target, errors, new Object[] {});
    }

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {
    	if (errors.hasFieldErrors("file")) {
			return;
		}

    	FileUploadForm form = (FileUploadForm) target;
    	MultipartFile uploadFile = form.getFile();
		
		if (form.getFile() == null 
				|| !StringUtils.hasLength(form.getFile().getName())){
			// file is not select
			errors.rejectValue("file",
	                   "e.file.not.exist",null,"");
		}else if (uploadFile.isEmpty()) {
			// file is not exist
			errors.rejectValue("file",
	                   "e.file.size.null",null,"");
			
		}else if (uploadAllowableFileSize < uploadFile.getSize()) {
			// file size is overflow
			errors.rejectValue("file",
	                   "e.file.size.max",new Object[]{uploadAllowableFileSize},"");
		}
		
    }

}
