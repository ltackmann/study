package uge6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOTest {
	public void somethingIsWrong(String scr, String des) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(scr);
			out = new FileOutputStream(des);
			byte[] buf = new byte[1024];
			int n;
			while ((n = in.read(buf)) >= 0)
				out.write(buf, 0, n);
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}
	}

	public void java7Baby(String scr, String des) throws IOException {
		try (InputStream in = new FileInputStream(scr);
			 OutputStream out = new FileOutputStream(des)) {
			byte[] buf = new byte[1024];
			int n;
			while ((n = in.read(buf)) >= 0)
				out.write(buf, 0, n);
		}
	}
}
