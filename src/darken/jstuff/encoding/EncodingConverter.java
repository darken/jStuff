package darken.jstuff.encoding;

import java.io.UnsupportedEncodingException;

public class EncodingConverter {

	public byte[] convert(byte[] input, String inputCharsetName,
			String outputCharsetName) throws UnsupportedEncodingException {
		String inputString = new String(input, inputCharsetName);
		byte[] output = inputString.getBytes(outputCharsetName);
		return output;
	}

	public String convertToString(byte[] input, String inputCharsetName,
			String outputCharsetName) throws UnsupportedEncodingException {
		byte[] output = convert(input, inputCharsetName, outputCharsetName);
		String outputString = new String(output, outputCharsetName);
		return outputString;
	}

	public String convert(String input, String outputCharsetName)
			throws UnsupportedEncodingException {
		byte[] output = input.getBytes(outputCharsetName);
		String outputString = new String(output, outputCharsetName);
		return outputString;
	}

}
