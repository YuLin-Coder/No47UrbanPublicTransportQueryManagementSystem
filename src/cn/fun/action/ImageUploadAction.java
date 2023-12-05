package cn.fun.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.fun.common.BaseAction;
import cn.fun.service.BizService;

@Namespace("/sys")
@Action("imageUpload")
@InterceptorRefs(value = { @InterceptorRef("fileUploadStack") })
@Results({ @Result(name = "success", location = "/common/uploadFileSuccess.jsp") })
public class ImageUploadAction extends BaseAction {
	@Autowired
	private BizService			service;
	private static final long	serialVersionUID	= 572146812454l;
	private static final int	BUFFER_SIZE			= 16 * 1024;
	// 封装上传文件域的属性
	//private File upload;
	private File				filedata;
	// 封装上传文件类型的属性
	private String				filedataContentType;
	// 封装上传文件名的属性
	private String				filedataFileName;
	private String				storageFileName;

	// since we are using <s:file name="upload" ... /> the File itself will be
	// obtained through getter/setter of <file-tag-name>
	public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	public String getFiledataContentType() {
		return filedataContentType;
	}

	public void setFiledataContentType(String filedataContentType) {
		this.filedataContentType = filedataContentType;
	}

	public String getFiledataFileName() {
		return filedataFileName;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public String getStorageFileName() {
		return storageFileName;
	}

	public void setStorageFileName(String storageFileName) {
		this.storageFileName = storageFileName;
	}

	// since we are using <s:file name="upload" ... /> the content type will be
	// obtained through getter/setter of <file-tag-name>ContentType

	public static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int len = 0;
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private String getExtention(String name) {
		return name.substring(name.lastIndexOf("."));
	}

	@Override
	public String execute() throws Exception {
		storageFileName = new Date().getTime() + getExtention(filedataFileName);
		//storageFileName = fileName;
		File storageFile = new File(ServletActionContext.getServletContext().getRealPath("/resource") + "/" + storageFileName);
		String error = "";
		try {
			copy(filedata, storageFile);
		} catch (Exception e) {
			error = e.getMessage();
			e.printStackTrace();
		}
		String http = "http://" + getHttpServletRequest().getServerName() + ":" + getHttpServletRequest().getServerPort()
				+ getHttpServletRequest().getContextPath();
		String url = http + "/resource/" + storageFileName;
		String json = String.format("{'err':'%s',msg:{'url':'%s','localname':'%s'}}", error, url, filedataFileName);
		getHttpServletResponse().getWriter().write(json);
		getHttpServletResponse().getWriter().flush();
		return null;
	}

}