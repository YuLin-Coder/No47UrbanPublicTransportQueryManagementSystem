package cn.fun.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	private static List<String> exts = new ArrayList<String>();
	static {
		exts.add("image/jpeg");
	}
	public static String readFile(String filepath, String encoding) {
		String ret = "";

		//File file = new File(filepath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath),encoding));  
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				ret += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		return ret;
	}

	public static boolean fileUploadAble(String ext) {
		// return exts.contains(ext);
		return true;
	}

	public static boolean isImage(String name) {
		return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
	}

	public static boolean isVideo(String name) {
		return name.endsWith(".mp4");
	}

	public static String getExt(String name) {
		int index = name.lastIndexOf('.');
		if (index > 0) {
			return name.substring(index);
		}
		return "";
	}

	public static String uploadFile(MultipartFile fileupload, long sizeLimit, String serverPath) {
		String filePath = null;
		String newFileName = null;
		try {
			if (fileupload != null && fileupload.getSize() > 0) {//存在文件上传

				String extName = "";
				String nowTimeStr = "";
				SimpleDateFormat sDateFormat;

				String savePath = "resource";
				savePath = serverPath + File.separator + savePath + File.separator;
				File pathdir = new File(savePath);
				if (!pathdir.exists()) {
					pathdir.mkdirs();
				}
				Random r = new Random();

				int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000;
				sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				nowTimeStr = sDateFormat.format(new Date());
				//fileName = fileupload.getOriginalFilename();
				// 获取文件的后缀  
				extName = getExt(fileupload.getOriginalFilename());

				newFileName = nowTimeStr + rannum + extName;
				filePath = savePath + newFileName;
				fileupload.transferTo(new File(filePath));
				return newFileName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 下载文件
	 * 
	 * @param request
	 * @param response
	 * @param storeName
	 * @param contentType
	 * @param realName
	 * @throws Exception
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response, String storeName, String contentType, String realName)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			String downLoadPath = storeName;

			long fileLength = new File(downLoadPath).length();

			response.setContentType(contentType);
			response.setHeader("Content-disposition", "attachment; filename=" + new String(realName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}

	}
}
