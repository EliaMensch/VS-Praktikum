package uebung2_2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class Main22_1 {

	public static void main(String[] args) {

		ArrayList<String> resources = new ArrayList<String>();

		resources.add("intro.pdf");
		resources.add("index.html");
		resources.add("d41d8cd98f00b204e9800998ecf8427e");
		resources.add("0cc175b9c0f1b6a831c399e269772661");
		resources.add("900150983cd24fb0d6963f7d28e17f72");
		resources.add("f96b697d7cb7938d525a2f31aaf161d0");
		resources.add("c3fcd3d76192e4007dfb496cca67e13b");
		resources.add("c0008dfc-b5c6-4ac8-9b96-c1780084109b");
		resources.add("fde20f54-6d1f-45a8-baf0-82d20b6a0c62");
		resources.add("10af8b96-bf4a-4567-9cf4-56646cfefb23");
		resources.add("cb4c2299-be2b-4673-ae76-0e98d1539833");
		resources.add("c01c429b-5a55-4930-be48-8b0e2ae4db5d");
		
		final String CHARBITS = "65536";

		ArrayList<String> server = new ArrayList<String>();

		server.add("s1.example.com");
		server.add("s2.example.com");
		server.add("s3.example.com");

		LinkedHashMap<String, String> modulusList = new LinkedHashMap<String, String>();

		for (int i = 0; i < resources.size(); i++) {
			
			BigInteger bi = new BigInteger("0");
			
			for (int j = 0; j < resources.get(i).length(); j++) {
				
				bi = bi.multiply(new BigInteger(CHARBITS));
				int c = resources.get(i).charAt(j);
				bi = bi.add(new BigInteger("" + c));
				
			}
			
//			System.out.println(bi);
//			System.out.println(bi.mod(new BigInteger("" + server.size())).intValue());

			modulusList.put(resources.get(i), server.get(bi.mod(new BigInteger("" + server.size())).intValue()));

		}

		System.out.println(mapToString(modulusList));

	}

	public static String mapToString(LinkedHashMap<String, String> map) {

		StringBuffer sb = new StringBuffer();

		Set<String> keys = map.keySet();

		for (String k : keys) {

			sb.append(k);

			int tabs = (int) Math.ceil((6 * 8.0 - k.length()) / 8);

			for (int i = 0; i < tabs; i++) {

				sb.append("\t");

			}

			sb.append(map.get(k));
			sb.append("\n");

		}

		return sb.toString();

	}

}
