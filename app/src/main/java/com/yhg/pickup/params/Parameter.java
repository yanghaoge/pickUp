package com.yhg.pickup.params;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;

public class Parameter {
	public static final int CUSTOMER_ID = 8001;
	public static final String CUSTOMER_KEY = "+6Hp9X5zR39SOI6oP0685Bk77gG56m7PkV89xYvl86A=";
	public static final String APPNAME = "CcooCity";
	// 将毫秒转换为指定格式日期
	public static String dateFormat(long l) {// new Date().getTime()是当前的毫秒数

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String date = df.format(l);// 将当前事件转换为指定格式的日期
		System.out.println(date);// 打印指定格式的日期
		return date;
	}

	// 首页下载接口字符串
	public static String createnewsParam(String method, JSONObject params) {
		String time = dateFormat(System.currentTimeMillis());
		JSONObject pp = new JSONObject();
		try {
			pp.put("customerID", CUSTOMER_ID);
			pp.put("requestTime", time);
			pp.put("Method", method);
			pp.put("customerKey", encodeByMD5(CUSTOMER_KEY + method
					+ time));
			// pp.put("customerKey", Constants.CUSTOMER_KEY);
			pp.put("appName", APPNAME);
			pp.put("version", "5.3.1");
			pp.put("Param", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pp.toString();
	}


	/** 对字符串进行MD5加密 */
	public static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/** 将一个字节转化成十六进制形式的字符串 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return HEX_DIGITS[d1] + HEX_DIGITS[d2];
	}

	// 十六进制下数字到字符的映射数组
	private final static String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

}
