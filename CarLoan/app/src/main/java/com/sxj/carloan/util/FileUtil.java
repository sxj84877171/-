package com.sxj.carloan.util;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import com.sxj.carloan.Cons;
import com.sxj.carloan.service.FileListAdapter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class FileUtil {

	public static File getFileFolder(){
		File fileDir = new File("/sdcard/loan/temp/");
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		return fileDir;
	}

	/**
	 * 根据用户是否有SD卡而创建文件
	 * 
	 * @param name
	 *            需要创建文件的名字
	 * @return 返回文件
	 */
	public static File getNewFile(Context context, String name) {
		File fileDir = new File("/sdcard/loan/temp/");
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		File file = new File(fileDir.getAbsoluteFile() + File.separator + name);
		if (file.exists()) {
			return file;
		} else {
			try {
				file.createNewFile();
				return file;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public static boolean isExit(Context context, String name) {
		File fileDir = new File(getCachDir(context));
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
		File file = new File(fileDir.getAbsoluteFile() + File.separator + name);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	public static File getRAM(Context context, String url) {
		File fileDir = new File(getCachDir(context) + File.separator + "arm");
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
		File file = new File(fileDir.getAbsoluteFile() + File.separator
				+ getMD5Hex(url) + ".amr");
		if (file.exists()) {
			return file;
		}
		return null;
	}

	public static File creatRAM(Context context, String url) {
		File fileDir = new File(getCachDir(context) + File.separator + "arm");
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
		File file = new File(fileDir.getAbsoluteFile() + File.separator
				+ getMD5Hex(url) + ".amr");
		if (file.exists()) {
			return file;
		} else {
			try {
				file.createNewFile();
				return file;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 获取缓存目录
	 * 
	 * @param context
	 * @return
	 */
	public static String getCachDir(Context context) {
		StringBuilder sb = new StringBuilder();
		String sdcardpath = Environment.getExternalStorageDirectory().getPath();
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return sb.append(sdcardpath).append(File.separator)
					.append(Cons.FILE_NAME).toString();
		} else {
			return sb.append(context.getCacheDir().getPath())
					.append(File.separator).append(Cons.FILE_NAME).toString();
		}
	}

	public static File getNewFile(Context context) throws IOException {
		String path = getCachDir(context) + File.separator + "temp"
				+ File.separator + UUID.randomUUID().toString() + ".jpg";
		File dirs = new File(getCachDir(context) + File.separator + "temp");
		if (!dirs.exists()) {
			dirs.mkdirs();
		}
		File file = new File(path);
		file.createNewFile();
		return file;
	}

	public final static String SDCARD_MNT = "/mnt/sdcard";
	public final static String SDCARD = "/sdcard";

	/**
	 * 判断当前Url是否标准的content://样式，如果不是，则返回绝对路径
	 */
	public static String getAbsolutePathFromNoStandardUri(Uri mUri) {
		String filePath = null;

		String mUriString = mUri.toString();
		mUriString = Uri.decode(mUriString);

		String pre1 = "file://" + SDCARD + File.separator;
		String pre2 = "file://" + SDCARD_MNT + File.separator;

		if (mUriString.startsWith(pre1)) {
			filePath = Environment.getExternalStorageDirectory().getPath()
					+ File.separator + mUriString.substring(pre1.length());
		} else if (mUriString.startsWith(pre2)) {
			filePath = Environment.getExternalStorageDirectory().getPath()
					+ File.separator + mUriString.substring(pre2.length());
		} else {
			filePath = Environment.getExternalStorageDirectory().getPath()
					+ mUriString.substring(("file://" + Environment
							.getExternalStorageDirectory().getPath()).length());
		}
		System.out.println("FilePath:" + filePath);
		return filePath;
	}

	/** 获取输入流 */
	public static InputStream getInputStreamByUri(Context context, Uri mUri) {
		try {
			if (mUri.getScheme().equals("file")) {
				return new java.io.FileInputStream(mUri.getPath());
			} else {
				return context.getContentResolver().openInputStream(mUri);
			}
		} catch (Exception ex) {
			return null;
		}
	}

	public static void deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
	}

	public static String getMD5Hex(String str) {
		if (str == null) {
			return null;
		}
		byte[] data = getMD5(str.getBytes());
		BigInteger bi = new BigInteger(data).abs();
		String result = bi.toString(36);
		return result;
	}

	private static byte[] getMD5(byte[] data) {

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(data);
			byte[] hash = digest.digest();
			return hash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 获取一个可以的缓存文件夹 Get a usable cache directory (external if available,
	 * internal otherwise).
	 * 
	 * @param context
	 *            The context to use
	 * @param uniqueName
	 *            A unique directory name to append to the cache dir
	 * @return The cache dir
	 */
	public static File getDiskCacheDir(Context context, String uniqueName) {
		// Check if media is mounted or storage is built-in, if so, try and use
		// external cache dir
		// otherwise use internal cache dir
		final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState()) || !isExternalStorageRemovable() ? getExternalCacheDir(
				context).getPath()
				: context.getCacheDir().getPath();

		return new File(cachePath + File.separator + uniqueName);
	}

	/**
	 * 检查外部存储器是否可用 Check if external storage is built-in or removable.
	 * 
	 * @return True if external storage is removable (like an SD card), false
	 *         otherwise.
	 */
	private static boolean isExternalStorageRemovable() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			return Environment.isExternalStorageRemovable();
		}
		return true;
	}

	/**
	 * 获取外部磁盘路径 Get the external app cache directory.
	 * 
	 * @param context
	 *            The context to use
	 * @return The external cache dir
	 */
	private static File getExternalCacheDir(Context context) {
		// if (BuiltUtils.hasFroyo()) {
		// return context.getExternalCacheDir();
		// }

		// Before Froyo we need to construct the external cache dir ourselves
		final String cacheDir = "/Android/data/" + context.getPackageName()
				+ "/cache/";
		return new File(Environment.getExternalStorageDirectory().getPath()
				+ cacheDir);
	}

	public static final int switchIcon(File file) {

		String suffix = getSuffix(file);
		suffix = suffix.toLowerCase();
		if (suffix.equals("jpeg") || suffix.equals("jpg")
				|| suffix.equals("bmp") || suffix.equals("gif")
				|| suffix.equals("png")) {
			return FileListAdapter.PHOTO;
		}
		return 0;

	}

	private static String getSuffix(File file) {
		String suffix;
		String name = file.getName();
		int la = name.lastIndexOf('.');
		if (la == -1)
			suffix = null;
		else
			suffix = name.substring(la + 1).toLowerCase();
		return suffix;
	}


	public static String getDesc(File file) {
		StringBuffer str = new StringBuffer("|");
		/**
		 * 文件夹就计算修改时间，如果是文件就计算大小
		 */
		if (file.isFile()) {
			String[] size = Util.fileSize(file.length());
			str.append(size[0]).append(size[1]).append(" |");
		}
		long time = file.lastModified();
		str.append(getLong2Date(time));
		str.append(" |").append(file.canRead() ? 'r' : '-')
				.append(file.canWrite() ? 'w' : '-');
		return str.toString();
	}

	public static String getLong2Date(long time) {
		if (time <= 0) {
			time = System.currentTimeMillis();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		Date date = new Date(time);
		return sdf.format(date);
	}


}
