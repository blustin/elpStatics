/*
 * BaseUtils.java
 * Copyright (c) 2015 KINJO
 * All rights reserved.
 */
package network.cycan.core.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.apache.commons.lang3.StringUtils;



/**
 * 基础公共工具类
 * 
 * @author jinchengYang
 * @version 1.0 2015-06-11
 * @since 1.0
 * 
 */
public class BaseUtils {



	/**
	 * 第一个参数为当前的权限值,第二个参数为权限基值 .返回是否有操作权限
	 */
	public static boolean checkPower(long userPurview, long optPurview) {
		long purviewValue = 1 << optPurview;
		return (userPurview & purviewValue) == purviewValue;
	}

	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim()) || "null".equals(str.toLowerCase().trim());
	}

	public static String nullToBlank(String src) {
		return src == null ? "" : src;
	}

	public static String replaceHtmlSymbols(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		return str.trim().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;")
				.replaceAll("'", "&#39;").replaceAll("&", "&amp;").replaceAll("\\)", ")").replaceAll("\\(", "(");
	}

	public static String replaceSymbolsHtml(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		return str.trim().replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"")
				.replaceAll("&#39;", "'").replaceAll("&amp;", "&").replaceAll(")", "\\)").replaceAll("(", "(\\(");
	}

	/**
	 * 数字转大写
	 * 
	 * @param money
	 * @return
	 * @throws Exception
	 */
	public static String toChineseCharacter(String moneyIn) {
		String result = "零";
		if (StringUtils.isBlank(moneyIn))
			return result;
		try {
			double money = Double.valueOf(moneyIn);
			double temp = 0;
			long l = Math.abs((long) money);
			BigDecimal bil = new BigDecimal(l);
			if (bil.toString().length() > 14) {
				// "数字太大，计算精度不够!"
				return result;
			}
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);
			int i = 0;
			String sign = "", tempStr = "", temp1 = "";
			String[] arr = null;
			sign = money < 0 ? "负" : "";
			temp = Math.abs(money);
			if (l == temp) {
				result = doForEach(new BigDecimal(temp).multiply(new BigDecimal(100)).toString(), sign);
			} else {
				nf.setMaximumFractionDigits(2);
				temp1 = nf.format(temp);
				arr = temp1.split(",");
				while (i < arr.length) {
					tempStr += arr[i];
					i++;
				}
				BigDecimal b = new BigDecimal(tempStr);
				b = b.multiply(new BigDecimal(100));
				tempStr = b.toString();
				if (tempStr.indexOf(".") == tempStr.length() - 3) {
					result = doForEach(tempStr.substring(0, tempStr.length() - 3), sign);
				} else {
					result = doForEach(tempStr.substring(0, tempStr.length() - 3) + "0", sign);
				}
			}
		} catch (Exception e) {
			//logger.error("toChineseCharacter  Exception", e);
		}
		return result;
	}

	private static String doForEach(String result, String sign) {
		String flag = "", b_string = "";
		String[] arr = { "分", "角", "圆", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾" };
		String[] arr1 = { "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		boolean zero = true;
		int len = 0, i = 0, z_count = 0;
		if (result == null) {
			len = 0;
		} else {
			len = result.length();
		}
		while (i < len) {
			flag = result.substring(i, i + 1);
			i++;
			if (flag.equals("0")) {
				if (len - i == 10 || len - i == 6 || len - i == 2 || len == i) {
					if (zero) {
						b_string = b_string.substring(0, (b_string.length()) - 1);
						zero = false;
					}
					if (len - i == 10) {
						b_string = b_string + "亿";
					}
					if (len - i == 6) {
						b_string = b_string + "万";
					}
					if (len - i == 2) {
						b_string = b_string + "圆";
					}
					if (len == i) {
						b_string = b_string + "整";
					}
					z_count = 0;
				} else {
					if (z_count == 0) {
						b_string = b_string + "零";
						zero = true;
					}
					z_count = z_count + 1;
				}
			} else {
				b_string = b_string + arr1[Integer.parseInt(flag) - 1] + arr[len - i];
				z_count = 0;
				zero = false;
			}
		}
		b_string = sign + b_string;
		return b_string;
	}

	/**
	 * 获取模糊处理的身份证号
	 * 
	 * @param idNo
	 */
	public static String getFuzzyIdNo(String idNo) {
		if (BaseUtils.isEmpty(idNo)) {
			return idNo;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(idNo.substring(0, 5));
		sb.append(" ***** ***** ");
		sb.append(idNo.substring(idNo.length() - 3, idNo.length()));
		return sb.toString();
	}

	/**
	 * 获取模糊处理的银行卡号： 头3位 ，尾4位
	 * 
	 * @param bankAccount
	 */
	public static String getFuzzyBankNo(String bankAccount) {
		if (StringUtils.isBlank(bankAccount) || bankAccount.length() < 4) {
			return bankAccount;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(bankAccount.substring(0, 3));
		sb.append(" **** **** ");
		sb.append(bankAccount.substring(bankAccount.length() - 4, bankAccount.length()));
		return sb.toString();
	}

	/***
	 * 获取模糊处理的银行卡号：尾4位
	 * @param bankAccount
	 * @return
	 */
	public static String getFuzzyBankNoFour(String bankAccount) {
		if (StringUtils.isBlank(bankAccount) || bankAccount.length() < 4) {
			return bankAccount;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("*** **** **** ");
		sb.append(bankAccount.substring(bankAccount.length() - 4, bankAccount.length()));
		return sb.toString();
	}

	/**
	 * 获取模糊处理的手机号 格式为 138 **** **xx
	 * 
	 * @param phoneNum
	 */
	public static String getFuzzyPhone(String phoneNum) {
		if (StringUtils.isBlank(phoneNum) || phoneNum.length() < 3) {
			return phoneNum;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(phoneNum.substring(0, 3));
		sb.append(" **** **");
		sb.append(phoneNum.substring(phoneNum.length() - 2, phoneNum.length()));
		return sb.toString();
	}

	/**
	 * 获取模糊处理的手机号 格式为 138 **** xxxx
	 * 
	 * @param phoneNum
	 */
	public static String getFuzzyPhoneFour(String phoneNum) {
		if (StringUtils.isBlank(phoneNum) || phoneNum.length() < 3) {
			return phoneNum;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(phoneNum.substring(0, 3));
		sb.append(" **** ");
		sb.append(phoneNum.substring(phoneNum.length() - 4, phoneNum.length()));
		return sb.toString();
	}

	/**
	 * 获取模糊处理的真实姓名
	 * 
	 * @param bankAccount
	 */
	public static String getFuzzyRealName(String realName) {
		if (StringUtils.isBlank(realName) || realName.length() < 1) {
			return realName;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(realName.substring(0, 1));
		sb.append(" **");
		return sb.toString();
	}

	/**
	 * 替换字符串
	 * 
	 * @param str
	 * @param fillStr
	 * @param len
	 */
	public static String stringFillRightBlank(String str, String fillStr, int len) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		if (str.length() > len) {
			StringBuffer sb = new StringBuffer(len);
			sb.append(str.substring(0, len));
			for (int i = 0; i < len; i++)
				sb.append(fillStr);
			return new String(sb);
		} else
			return str + fillStr + fillStr;
	}
	
	/**
	 * 获取银行卡后四位
	 * 
	 * @param bankAccount
	 */
	public static String getBankNoLastFour(String bankAccount) {
		if (StringUtils.isBlank(bankAccount) || bankAccount.length() < 4) {
			return bankAccount;
		}
		return bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
	}
	
	public static void main(String[] args){
	    System.out.println(BaseUtils.getFuzzyPhoneFour("15507592650"));
	}
}
