/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   Tools.java

package com.aotain.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.net.util.SubnetUtils;

// Referenced classes of package com.aotain.utils:
//            StringUtil, DateUtils

public class Tools {

	public Tools() {
	}
	public static void main(String[] args){
		//
		SubnetUtils utils = new SubnetUtils("115.239.210.36/32");
		System.out.println(utils.getInfo().getAddress());
		System.out.println(utils.getInfo().getNetworkAddress());
		System.out.println(utils.getInfo().getLowAddress());
	}
	public static boolean isIpAddress(String s) {
		if (s == null) {
			return false;
		} else {
			String regex = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(s);
			return m.matches();
		}
	}

	public static boolean isDomain(String domain) {
		if (domain == null) {
			return false;
		} else {
			String regex = "^([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\.((ac\\.cn)|(bj\\.cn)|(sh\\.cn)|(hk\\.cn)|(tj\\.cn)|(cq\\.cn)|(he\\.cn)|(sx\\.cn)|(nm\\.cn)|(ln\\.cn)|(jl\\.cn)|(hl\\.cn)|(js\\.cn)|(zj\\.cn)|(ah\\.cn)|(fj\\.cn)|(jx\\.cn)|(sd\\.cn)|(ha\\.cn)|(hb\\.cn)|(hn\\.cn)|(gd\\.cn)|(gx\\.cn)|(hi\\.cn)|(sc\\.cn)|(gz\\.cn)|(yn\\.cn)|(xz\\.cn)|(sn\\.cn)|(gs\\.cn)|(qh\\.cn)|(nx\\.cn)|(xj\\.cn)|(tw\\.cn)|(mo\\.cn)|(vnet\\.cn)|(com)|(net)|(org)|(gov\\.cn)|(info)|(cc)|(com\\.cn)|(net\\.cn)|(org\\.cn)|(name)|(biz)|(tv)|(cn)|(mobi)|(name)|(sh)|(ac)|(io)|(tw)|(com\\.tw)|(hk)|(com\\.hk)|(ws)|(travel)|(us)|(tm)|(la)|(me\\.uk)|(org\\.uk)|(ltd\\.uk)|(plc\\.uk)|(in)|(eu)|(it)|(jp))$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(domain);
			return m.matches();
		}
	}

	public static long ip2long(String ip) {
		if (!isIpAddress(ip)) {
			return -1L;
		} else {
			String ips[] = ip.split("[.]");
			long num = 16777216L * Long.parseLong(ips[0]) + 65536L * Long.parseLong(ips[1]) + 256L * Long.parseLong(ips[2]) + Long.parseLong(ips[3]);
			return num;
		}
	}

	public static String long2ip(long ipLong) {
		long mask[] = { 255L, 65280L, 16711680L, -16777216L };
		long num = 0L;
		StringBuffer ipInfo = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			num = (ipLong & mask[i]) >> i * 8;
			if (i > 0)
				ipInfo.insert(0, ".");
			ipInfo.insert(0, Long.toString(num, 10));
		}

		return ipInfo.toString();
	}

	public static String ClobToString(Clob clob) throws SQLException, IOException {
		String reString = "";
		java.io.Reader is = clob.getCharacterStream();
		BufferedReader br = new BufferedReader(is);
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		for (; s != null; s = br.readLine())
			sb.append(s);

		reString = sb.toString();
		return reString;
	}

	public static boolean isNumber(String num) {
		return num != null && (num.matches("[0-9]+") || num.matches("([0-9])+?\\.+([0-9]+)"));
	}

	public static boolean isEmail(String email) {
		return email != null && email.matches("^([a-z0-9A-Z]+[-\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
	}

	public static boolean isInt(String num) {
		int m;
		try {
			m = Integer.parseInt(num);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public static long getBatchId() {
		String mBatchId = "";
		Calendar cal = Calendar.getInstance();
		mBatchId = (new StringBuilder()).append(cal.get(1)).toString();
		mBatchId = (new StringBuilder(String.valueOf(mBatchId))).append(format2Two((new StringBuilder(String.valueOf(cal.get(2) + 1))).toString()))
				.toString();
		mBatchId = (new StringBuilder(String.valueOf(mBatchId))).append(format2Two((new StringBuilder(String.valueOf(cal.get(5)))).toString()))
				.toString();
		mBatchId = (new StringBuilder(String.valueOf(mBatchId))).append(format2Two((new StringBuilder(String.valueOf(cal.get(11)))).toString()))
				.toString();
		mBatchId = (new StringBuilder(String.valueOf(mBatchId))).append(format2Two((new StringBuilder(String.valueOf(cal.get(12)))).toString()))
				.toString();
		mBatchId = (new StringBuilder(String.valueOf(mBatchId))).append(format2Two((new StringBuilder(String.valueOf(cal.get(13)))).toString()))
				.toString();
		return Long.parseLong(mBatchId);
	}

	public static String format2Two(String num) {
		if (num.length() < 2)
			return (new StringBuilder("0")).append(num).toString();
		else
			return num;
	}

	public static String getTimegap(String dateString, String dateformat, Long timegap) {
		SimpleDateFormat format = new SimpleDateFormat(dateformat);
		String mydate = null;
		try {
			Date date = format.parse(dateString);
			long Time = date.getTime() / 1000L + timegap.longValue();
			date.setTime(Time * 1000L);
			mydate = format.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mydate;
	}

	public static Date getLastMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(2, -1);
		return cal.getTime();
	}

	public static int GetInt32(Object Obj, int defaultVal) {
		try {
			if (Obj == null && StringUtil.isEmptyString(Obj.toString()))
				return defaultVal;
		} catch (Exception e) {
			return defaultVal;
		}
		return Integer.parseInt(Obj.toString());
	}

	public static long GetInt64(Object Obj, long defaultVal) {
		try {
			if (Obj == null || StringUtil.isEmptyString(Obj.toString()))
				return defaultVal;
		} catch (Exception e) {
			return defaultVal;
		}
		return Long.parseLong(Obj.toString());
	}

	public static String GetString(Object Obj, String defaultVal) {
		try {
			if (Obj == null || StringUtil.isEmptyString(Obj.toString()))
				return defaultVal;
		} catch (Exception e) {
			return defaultVal;
		}
		return Obj.toString();
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getRemoteAddr();
		return ip;
	}

	public static Date LongToDateTime(long time) {
		Date dt = new Date(time * 1000L);
		return dt;
	}

	public static long DateTimeToLong(Date date) {
		long lSysTime1 = date.getTime() / 1000L;
		return lSysTime1;
	}

	public static boolean isLinuxSystem() {
		return System.getProperty("os.name").compareTo("Linux") == 0;
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		return Pattern.matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", phoneNumber);
	}

	public static String getRsaClearText(String passwd) {
		if (passwd.length() == 0) {
			return "";
		}
		String s = "";

		try {
			byte[] tmp = new BigInteger(passwd, 16).toByteArray();
			byte[] en_result = new byte[128];
			if (tmp.length > 128) {
				System.arraycopy(tmp, 1, en_result, 0, 128);
			} else {
				System.arraycopy(tmp, 0, en_result, 0, 128);
			}
			byte[] de_result = RSA.decrypt(RSACache.getRSACache().getPrivate(), en_result);
			StringBuffer sb = new StringBuffer();
			sb.append(new String(de_result));
			s = URLDecoder.decode(sb.reverse().toString(), "UTF-8");

		} catch (Exception e) {
			s = "";
			e.printStackTrace();
		}
		return s;
	}

	public static String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 复制对象，只有实现了序列化接口的对象能用.
	 * 
	 * @author 凯哥
	 * @param
	 * @return
	 */
	public static Object cloneObject(Object object) {
		ObjectOutputStream oo = null;
		ObjectInputStream oi = null;
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			oo = new ObjectOutputStream(bo);
			oo.writeObject(object);
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			oi = new ObjectInputStream(bi);
			return (oi.readObject());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (oo != null) {
					oo.close();
				}
				if (oi != null) {
					oi.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from: E:\eclipseWorkspace\Ubas\lib\HmgUtils.jar Total time: 12 ms
 * Jad reported messages/errors: Couldn't resolve all exception handlers in
 * method GetInt32 Couldn't resolve all exception handlers in method GetInt64
 * Couldn't resolve all exception handlers in method GetString Couldn't resolve
 * all exception handlers in method GetDateLong Couldn't resolve all exception
 * handlers in method GetDateTime Exit status: 0 Caught exceptions:
 */