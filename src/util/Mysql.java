package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Mysql {

	public static String	mysqlRoot		= "H:\\Software\\Mysql5.5.6";
	public static String	mysqlPassword	= "123456";
	public static String	dbname			= "d9_gjcx";

	public static void load(String fPath) throws Exception {
		OutputStream out = null;
		BufferedReader br = null;
		OutputStreamWriter writer = null;
		try {
			Runtime rt = Runtime.getRuntime();

			// 调用 mysql 的 cmd:      
			Process child = rt.exec(mysqlRoot + "//bin//mysql.exe -uroot -p" + mysqlPassword + " " + dbname + " ");
			out = child.getOutputStream();
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fPath), "utf8"));
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();

			writer = new OutputStreamWriter(out, "utf8");
			writer.write(outStr);
			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免      
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 别忘记关闭输入输出流      
			try {
				out.close();
			} catch (Exception e) {
			}
			try {
				br.close();
			} catch (Exception e) {
			}
			try {
				writer.close();
			} catch (Exception e) {
			}
		}

	}

	public static void backup(String fPath) throws Exception {
		// 设置导出编码为utf-8。这里必须是utf-8
		// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
		InputStream in = null;
		InputStreamReader xx = null;
		// 组合控制台输出信息字符串
		BufferedReader br = null;
		// 要用来做导入用的sql目标文件：
		FileOutputStream fout = null;
		OutputStreamWriter writer = null;
		try {
			Runtime rt = Runtime.getRuntime();

			// 调用 调用mysql的安装目录的命令
			Process child = rt.exec(mysqlRoot + "//bin//mysqldump -h localhost -uroot -p" + mysqlPassword + " " + dbname + " ");
			in = child.getInputStream();

			xx = new InputStreamReader(in, "utf-8");

			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();

			fout = new FileOutputStream(fPath);
			writer = new OutputStreamWriter(fout, "utf-8");
			writer.write(outStr);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				xx.close();
			} catch (Exception e) {
			}
			try {
				br.close();
			} catch (Exception e) {
			}
			try {
				writer.close();
			} catch (Exception e) {
			}
			try {
				fout.close();
			} catch (Exception e) {
			}
		}

	}
}
