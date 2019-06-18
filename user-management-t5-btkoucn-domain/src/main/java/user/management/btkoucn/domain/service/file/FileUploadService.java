package user.management.btkoucn.domain.service.file;

import java.io.InputStream;

import org.terasoluna.gfw.common.message.ResultMessages;

import user.management.btkoucn.domain.model.Account;


public interface FileUploadService {
	FileUploadOutputBean fileUpload(InputStream fileStream, String filePath);

	void insertUser(Account account,ResultMessages messages,int rowNumber);

	void insertJob(String jobStutas,String jobName);
}
