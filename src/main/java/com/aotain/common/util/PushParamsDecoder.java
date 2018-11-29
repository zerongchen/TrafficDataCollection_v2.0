package com.aotain.common.util;

public class PushParamsDecoder {

	private static int GetBase64Value(char ch) {
		if ((ch >= 'A') && (ch <= 'Z')) {
			return ch - 'A';
		}
		if ((ch >= 'a') && (ch <= 'z')) {
			return ch - 'a' + 26;
		}
		if ((ch >= '0') && (ch <= '9')) {
			return ch - '0' + 52;
		}
		switch (ch) {
		case '+':
			return 62;
		case '/':
			return 63;
		case '=':
			return 0;
		}
		return 0;
	}

	private static int Base64Dec(char[] text, int size, int[] out) {
		if (size % 4 != 0)
			return -1;
		int[] chunk = new int[4];
		int parsenum = 0;
		int i = 0;
		int j = 0;

		while (size > 0) {
			chunk[0] = (63 - GetBase64Value(text[(i++)]));
			chunk[1] = (63 - GetBase64Value(text[(i++)]));
			chunk[2] = (63 - GetBase64Value(text[(i++)]));
			chunk[3] = (63 - GetBase64Value(text[(i++)]));

			out[(j++)] = (chunk[0] << 2 | (chunk[1] & 0x30) >> 4);
			out[(j++)] = ((chunk[1] & 0xF) << 4 | (chunk[2] & 0x3C) >> 2);
			out[(j++)] = ((chunk[2] & 0x3) << 6 | chunk[3]);

			size -= 4;
			parsenum += 3;
		}
		return parsenum;
	}

	private static int HmgDecode(char[] text, int[] out) {
		int outsize = 0;
		int n = text.length - 2;
		char[] s2 = new char[n];

		System.arraycopy(text, 2, s2, 0, n);

		outsize = Base64Dec(s2, s2.length, out);
		if (outsize < 0)
			return -1;

		return 0;
	}

	public static String getPushParamString(String src) {
		String result = "error";
		int size = src.length() - 2;
		int[] out = new int[size * 3 / 4];
		if (HmgDecode(src.toCharArray(), out) == 0) {
			StringBuffer sf = new StringBuffer();
			for (int i = 0; i < out.length; i++) {
				if (out[i] == 255)
					continue;
				sf.append((char) out[i]);
			}
			result = sf.toString();
		}
		return result;
	}

	public static void main(String[] args) {
		String src = "i5qMi8/Px7/OyczRmJs=";
		System.out.println(getPushParamString(src.replace(" ", "+")));
	}
}
 