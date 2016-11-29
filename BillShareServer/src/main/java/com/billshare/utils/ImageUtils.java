package com.billshare.utils;

import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.google.api.client.util.Base64;

public class ImageUtils {
	private static ImageUtils imageUtils;

	private ImageUtils() {

	}

	public static ImageUtils instance() {
		if (imageUtils == null) {
			imageUtils = new ImageUtils();
		}
		return imageUtils;
	}

	public Blob getBlobFromString(String byteArrayString) {
		byte[] decodeBase64 = Base64.decodeBase64(byteArrayString);
		Blob blob = null;
		try {
			blob = new SerialBlob(decodeBase64);

		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blob;
	}

	public String getByteStringFromBlob(Blob blob) {
		int blobLength;
		try {
			blobLength = (int) blob.length();
			byte[] blobAsBytes = blob.getBytes(1, blobLength);
			String encodeBase64String = Base64.encodeBase64String(blobAsBytes);
			return encodeBase64String;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
