package user.management.btkoucn.app.file;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

import user.management.btkoucn.app.common.CommonUtils;
import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.service.file.FileUploadOutputBean;
import user.management.btkoucn.domain.service.file.FileUploadService;

@RequestMapping("file")
@Controller
@TransactionTokenCheck("user")
public class FileController {

	@Inject
	FileUploadService fileUploadService;

	@Inject
	FileUploadFormValidator fileUploadFormValidator;
	
	@Inject
	MessageSource messageSource;

	@ModelAttribute
	public FileUploadForm setFileUploadForm() {
		return new FileUploadForm();
	}

	@InitBinder("fileUploadForm")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(fileUploadFormValidator);
	}

	@RequestMapping(value = "upload", method = RequestMethod.GET)
    @TransactionTokenCheck(value = "upload",type=TransactionTokenType.BEGIN)
	public String uploadForm(Model model) {
		return "file/uploadForm";
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
    @TransactionTokenCheck(value = "upload",type=TransactionTokenType.IN)
	public String upload(@Validated FileUploadForm form,
			BindingResult result, Model model, 
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "file/uploadForm";
		}

		CommonsMultipartFile uploadFile = (CommonsMultipartFile) form.getFile();
		String fileName = uploadFile.getFileItem().getName();
		String jobStutas = "0";

		// ファイル解析
		FileUploadOutputBean output = new FileUploadOutputBean();
		try {
			output = fileUploadService.fileUpload(uploadFile.getInputStream(),
					fileName);
		} catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
			jobStutas = "1";
			fileUploadService.insertJob(jobStutas,
					fileName);
			return "file/uploadForm";
		} catch (IOException e) {
			model.addAttribute("message", e.getMessage());
			jobStutas = "1";
			fileUploadService.insertJob(jobStutas,
					fileName);
			return "file/uploadForm";
		}

		// ユーザ登録
		int insertCount = 0;
		ResultMessages resultMessages = output.getResultMessages();
		if (output.getAccountList() != null
				&& output.getAccountList().size() > 0) {
			Map<Integer, Account> accountList = output.getAccountList();
			for (Integer rowNumber : accountList.keySet()) {
				Account account = accountList.get(rowNumber);
				try {
					fileUploadService.insertUser(account, resultMessages, rowNumber);
					insertCount++;
				} catch (BusinessException e) {
					continue;
				}
			}

		}

		// job 登録
		if (resultMessages.getList() == null
				|| resultMessages.getList().size() == 0) {
			// 正常終了
			jobStutas = "0";
			fileUploadService.insertJob(jobStutas,
					fileName);
			
			redirectAttributes.addFlashAttribute("fileName", fileName);
			return "redirect:/file/upload?complete";
		} else {

			if (insertCount > 0) {
				// 警告終了
				jobStutas = "2";
				fileUploadService.insertJob(jobStutas,
						fileName);
			} else {

				// 異常終了
				jobStutas = "1";
				fileUploadService.insertJob(jobStutas,
						fileName);
			}
			
			// ファイル処理ログ出力
			String logFile = CommonUtils.writeMessageToFile(resultMessages,messageSource,fileName);
			redirectAttributes.addFlashAttribute("logFileName", logFile);
			redirectAttributes.addFlashAttribute("redirectURL", "/file/upload");
			return "redirect:/file/upload?error";
		}
	}

	@RequestMapping(value = "upload", method = RequestMethod.GET, params = "complete")
	public String uploadComplete(Model model) {
		String fileName = (String)model.asMap().get("fileName");
		model.addAttribute(ResultMessages.info().add("e.file.success", fileName));
		return "file/uploadComplete";
	}

	@RequestMapping(value = "upload", method = RequestMethod.GET, params = "error")
	public String uploadError(Model model) {
		return "textFileDownloadView";
	}
}
