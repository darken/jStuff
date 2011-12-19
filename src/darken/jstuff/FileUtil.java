package darken.jstuff;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

	public static byte[] readFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[(int) file.length()];
		fis.read(b);
		return b;
	}

	public static void writeFile(File file, byte[] b) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(b);
	}

}
