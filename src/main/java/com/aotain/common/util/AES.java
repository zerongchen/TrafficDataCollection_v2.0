package com.aotain.common.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AES {

	 // ����
    public static String Encrypt(String sSrc, String sKey,String sIv) throws Exception {
        if (sKey == null) {
            System.out.print("KeyΪ��null");
            return null;
        }
        // �ж�Key�Ƿ�Ϊ16λ
        if (sKey.length() != 16) {
            System.out.print("Key���Ȳ���16λ");
            return null;
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"�㷨/ģʽ/���뷽ʽ"
        IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());//ʹ��CBCģʽ����Ҫһ������iv�������Ӽ����㷨��ǿ��
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());

        return new BASE64Encoder().encode(encrypted);//�˴�ʹ��BASE64��ת�빦�ܣ�ͬʱ����2�μ��ܵ����á�
    }
    
	 // ����
    public static String Encrypt(byte[] src, String sKey,String sIv) throws Exception {
        if (sKey == null) {
            System.out.print("KeyΪ��null");
            return null;
        }
        // �ж�Key�Ƿ�Ϊ16λ
        if (sKey.length() != 16) {
            System.out.print("Key���Ȳ���16λ");
            return null;
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"�㷨/ģʽ/���뷽ʽ"
        IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());//ʹ��CBCģʽ����Ҫһ������iv�������Ӽ����㷨��ǿ��
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(src);

        return new BASE64Encoder().encode(encrypted);//�˴�ʹ��BASE64��ת�빦�ܣ�ͬʱ����2�μ��ܵ����á�
    }

    // ����
    public static String Decrypt(String sSrc, String sKey,String sIv) throws Exception {
        try {
            // �ж�Key�Ƿ���ȷ
            if (sKey == null) {
                System.out.print("KeyΪ��null");
                return null;
            }
            // �ж�Key�Ƿ�Ϊ16λ
            if (sKey.length() != 16) {
                System.out.print("Key���Ȳ���16λ");
                return null;
            }
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//����base64����
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    // ����
    public static byte[] DecryptReturnByte(String sSrc, String sKey,String sIv) throws Exception {
        try {
            // �ж�Key�Ƿ���ȷ
            if (sKey == null) {
                System.out.print("KeyΪ��null");
                return null;
            }
            // �ж�Key�Ƿ�Ϊ16λ
            if (sKey.length() != 16) {
                System.out.print("Key���Ȳ���16λ");
                return null;
            }
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//����base64����
            try {
                byte[] original = cipher.doFinal(encrypted1);
                return original;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
 // ����
    public static String DecryptAESAndMD5(String sSrc, String sKey,String sIv) throws Exception {
        try {
            // �ж�Key�Ƿ���ȷ
            if (sKey == null) {
                System.out.print("KeyΪ��null");
                return null;
            }
            // �ж�Key�Ƿ�Ϊ16λ
            if (sKey.length() != 16) {
                System.out.print("Key���Ȳ���16λ");
                return null;
            }
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//����base64����
            try {
                byte[] original = cipher.doFinal(encrypted1);
                MessageDigest md = MessageDigest.getInstance("MD5"); 
    			md.update(original); 
    			byte b[] = md.digest(); 
    			return new BASE64Encoder().encodeBuffer(b).replace("\r\n", "").replace("\n", ""); 
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        /*
         * �����õ�Key ������26����ĸ��������ɣ���ò�Ҫ�ñ����ַ���Ȼ����?������ô�þ������˿������
         * �˴�ʹ��AES-128-CBC����ģʽ��key��ҪΪ16λ��
         */
        String cKey = "1234567890123456";
        // ��Ҫ���ܵ��ִ�
        String cSrc = "Email : arix04@xxx.com";
        System.out.println(cSrc);
        // ����
        long lStart = System.currentTimeMillis();
        String enString = AES.Encrypt(cSrc, cKey,"0102030405060708");
        System.out.println("���ܺ���ִ��ǣ�" + enString);

        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("���ܺ�ʱ��" + lUseTime + "����");
        // ����
        lStart = System.currentTimeMillis();
        String DeString = AES.Decrypt(enString, cKey,"0102030405060708");
        System.out.println("���ܺ���ִ��ǣ�" + DeString);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("���ܺ�ʱ��" + lUseTime + "����");
    }

}
