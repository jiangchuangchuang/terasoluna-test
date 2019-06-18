package user.management.btkoucn.app.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.terasoluna.gfw.web.download.AbstractFileDownloadView;

import user.management.btkoucn.app.common.CommonUtils;

@Component
public class TextFileDownloadView extends AbstractFileDownloadView {

	@Override
	protected InputStream getInputStream(Map<String, Object> model,
			HttpServletRequest request) throws IOException {
		String logFile = (String) model.get("logFileName");
		InputStream inputStream = new FileInputStream(logFile);
		return inputStream;
	}

	@Override
	protected void addResponseHeader(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) {
		String logFile = (String) model.get("logFileName");
		logFile = CommonUtils.getFileName(logFile);
		String redirectURL = (String) model.get("redirectURL");
		redirectURL = request.getContextPath()+ redirectURL;

//		try {
//			response.sendRedirect(redirectURL);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			response.setHeader("Content-Disposition", "attachment; filename="
					+  java.net.URLEncoder.encode(logFile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setContentType("text/plain");
		//response.setHeader("refresh","1;URL=" + redirectURL);
//		response.setHeader("location","url");
//		response.setHeader("Cache-Control", "no-cache");
		Cookie status = new Cookie("updateStatus","success");
	    status.setMaxAge(600);
	    response.addCookie(status);
	}
}
