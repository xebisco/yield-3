package yield.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Escreve arquivos.
 */
public final class YldWriter {

	public static YldWriter writer;

	public static void writeFile(String path, String[] things) throws IOException, URISyntaxException {

		File filep = new File(writer.getClass().getResource("/").getFile(), path);

		filep.createNewFile();

		FileChannel.open(filep.toPath(), StandardOpenOption.WRITE).truncate(0).close();

		FileOutputStream file = new FileOutputStream(filep);

		for (int i2 = 0; i2 < things.length; i2++) {
			String st = things[i2];

			String a = System.getProperty("line.separator");

			char ch[] = st.toCharArray();

			char ch2[] = a.toCharArray();

			for (int i = 0; i < st.length(); i++) {

				// we will write the string by writing each
				// character one by one to file
				file.write(ch[i]);
			}

			if (!(i2 + 1 == things.length)) {
				for (int i = 0; i < a.length(); i++) {

					// we will write the string by writing each
					// character one by one to file
					file.write(ch2[i]);
				}
			}

		}

		file.close();
	}

	public static void writeFile(String path, String things) throws IOException, URISyntaxException {

		File filep = new File(writer.getClass().getResource("/").getFile(), path);

		filep.createNewFile();

		FileChannel.open(filep.toPath(), StandardOpenOption.WRITE).truncate(0).close();

		FileOutputStream file = new FileOutputStream(filep);

		String st = things;

		String a = System.getProperty("line.separator");

		char ch[] = st.toCharArray();

		char ch2[] = a.toCharArray();

		for (int i = 0; i < st.length(); i++) {

			// we will write the string by writing each
			// character one by one to file
			file.write(ch[i]);
		}

		for (int i = 0; i < a.length(); i++) {

			// we will write the string by writing each
			// character one by one to file
			file.write(ch2[i]);
		}

		file.close();

	}

	public static void writeFile(String path, List<String> things) throws IOException, URISyntaxException {

		File filep = new File(writer.getClass().getResource("/").getFile(), path);

		filep.createNewFile();

		FileChannel.open(filep.toPath(), StandardOpenOption.WRITE).truncate(0).close();

		FileOutputStream file = new FileOutputStream(filep);

		for (int i2 = 0; i2 < things.size(); i2++) {
			String st = things.get(i2);

			String a = System.getProperty("line.separator");

			char ch[] = st.toCharArray();

			char ch2[] = a.toCharArray();

			for (int i = 0; i < st.length(); i++) {

				// we will write the string by writing each
				// character one by one to file
				file.write(ch[i]);
			}

			if (!(i2 + 1 == things.size())) {
				for (int i = 0; i < a.length(); i++) {

					// we will write the string by writing each
					// character one by one to file
					file.write(ch2[i]);
				}
			}

		}

		file.close();

	}

}
