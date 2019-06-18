package user.management.btkoucn.domain.common;

import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

public class CommonUtils {
	
	/**
	 * 日付フォーマットチェック
	 * @param taget　日付
	 * @param pattern　フォーマット
	 * @return　true:有効　false:無効
	 */
	public static boolean checkformat(String taget, String pattern){
		if(StringUtils.isEmpty(taget) || StringUtils.isEmpty(pattern)) {
			return true;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			format.setLenient(false);
			format.parse(taget);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
