package darken.jstuff;

import static darken.jstuff.Charsets.ISO_8859_1;
import static darken.jstuff.Charsets.UTF_8;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class EncodingConverterTest {

	public static EncodingConverter converter;

	public static byte[] inputISO;

	public static byte[] inputUTF8;

	public static String expectedString;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		converter = new EncodingConverter();

		File isoFile = new File("test/resources/inISO.txt");
		inputISO = FileUtil.readFile(isoFile);

		File utf8File = new File("test/resources/inUTF8.txt");
		inputUTF8 = FileUtil.readFile(utf8File);

		expectedString = new String(new char[] { 'á', 'é', 'í', 'ó', 'ú', 'ñ',
				'¿', 'Á', 'É', 'Í', 'Ó', 'Ú', 'Ñ' });
	}

	@Test
	public void testConvert() throws UnsupportedEncodingException {
		byte[] outputUTF8 = converter.convert(inputISO, ISO_8859_1, UTF_8);
		byte[] outputISO = converter.convert(inputUTF8, UTF_8, ISO_8859_1);

		Assert.assertTrue(Arrays.equals(inputISO, outputISO));
		Assert.assertTrue(Arrays.equals(inputUTF8, outputUTF8));

		Assert.assertTrue(Arrays.equals(expectedString.getBytes(ISO_8859_1),
				outputISO));
		Assert.assertTrue(Arrays.equals(expectedString.getBytes(UTF_8),
				outputUTF8));
	}

	@Test
	public void testConvertToString() throws UnsupportedEncodingException {
		String strInputISO = new String(inputISO, ISO_8859_1);
		String strInputUTF8 = new String(inputUTF8, UTF_8);

		String strOutputUTF8 = converter.convertToString(inputISO, ISO_8859_1,
				UTF_8);
		String strOutputISO = converter.convertToString(inputUTF8, UTF_8,
				ISO_8859_1);

		Assert.assertEquals(strInputUTF8, strOutputUTF8);
		Assert.assertEquals(strInputISO, strOutputISO);

		Assert.assertEquals(expectedString, strOutputUTF8);
		Assert.assertEquals(expectedString, strOutputISO);
	}

	@Test
	public void testConvertFromString() throws UnsupportedEncodingException {
		String strInputISO = new String(inputISO, ISO_8859_1);
		String strInputUTF8 = new String(inputUTF8, UTF_8);

		String strOutputUTF8 = converter.convert(strInputISO, UTF_8);
		String strOutputISO = converter.convert(strInputUTF8, ISO_8859_1);

		Assert.assertEquals(strInputUTF8, strOutputUTF8);
		Assert.assertEquals(strInputISO, strOutputISO);

		Assert.assertEquals(expectedString, strOutputUTF8);
		Assert.assertEquals(expectedString, strOutputISO);
	}

}
