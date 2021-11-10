package yield.util;

import java.io.IOException;

import yieldg.YldSprite;
import yieldg.util.YldSpriteArray;
import yieldg.util.YldSpriteList;

/**
 * O YldMappFileSystem carrega informações de um arquivo .mapp e transforma em listas de Imagens.
 */
public final class YldMappFileSystem {

	public static final String MAPP_FILE_SYSTEM_VERSION = "1.1";

	public static YldSpriteList fromPath(String path) {
		YldSpriteList mappFile_list = getListFromMapp(path);

		return mappFile_list;
	}

	public static YldSpriteArray toSpriteArray(String path) {
		YldSpriteList mappFile_list = getListFromMapp(path);

		YldSpriteArray mappFile_array = new YldSpriteArray(mappFile_list.size());

		for(int i = 0; i < mappFile_list.size(); i++) {
			mappFile_array.list[i] = mappFile_list.list.get(i);
		}

		return mappFile_array;
	}

	public static YldSprite[] toArray(String path) {
		YldSpriteList mappFile_list = getListFromMapp(path);

		YldSprite[] mappFile_array = new YldSprite[mappFile_list.size()];

		for(int i = 0; i < mappFile_list.size(); i++) {
			mappFile_array[i] = mappFile_list.list.get(i);
		}

		return mappFile_array;
	}

	private static YldSpriteList getListFromMapp(String path) {
		YldSprite mappFile_mum = null;
		int mappFile_x = 0, mappFile_y = 0, mappFile_width = 1, mappFile_height = 1, mappFile_ax = 0, mappFile_ay = 0;
		String mappFile_actname = "";
		YldSpriteList mappFile_list;

		mappFile_list = new YldSpriteList();

		try {
			String[] mappFile = YldReader.readFile(path, "-/-/-7-yieldfileseparator-7-/-/-")
					.split("-/-/-7-yieldfileseparator-7-/-/-");

			for (int i = 0; i < mappFile.length; i++) {
				String[] spl = mappFile[i].split(" ");
				for (int i2 = 0; i2 < spl.length; i2++) {
					String[] spl2 = spl[i2].split("=");
					for (int i3 = 0; i3 < spl2.length / 2; i3++) {

						switch (spl2[0]) {
						case "<":
							mappFile_x = 0;
							mappFile_y = 0;
							mappFile_ax = 0;
							mappFile_ay = 0;
							mappFile_width = 0;
							mappFile_height = 0;
							mappFile_actname = spl2[1];

							break;

						case ">":
							YldSprite sc = mappFile_mum.getSpriteint(mappFile_x, mappFile_y, mappFile_width,
									mappFile_height);
							sc.name = (mappFile_actname);
							sc.addX = mappFile_ax;
							sc.addY = mappFile_ay;
							mappFile_list.add(sc);
							break;
						case "path":
							mappFile_mum = new YldSprite(spl2[1]);
							break;
						case "x":
							mappFile_x = Integer.parseInt(spl2[1]);
							break;
						case "y":
							mappFile_y = Integer.parseInt(spl2[1]);
							break;
						case "width":
							mappFile_width = Integer.parseInt(spl2[1]);
							break;
						case "height":
							mappFile_height = Integer.parseInt(spl2[1]);
							break;
						case "ax":
							mappFile_ax = Integer.parseInt(spl2[1]);
							break;
						case "ay":
							mappFile_ay = Integer.parseInt(spl2[1]);
							break;
						}

					}
				}
			}
		} catch (IOException e) {
			System.out.println("Cannot find mapp file:" + path);
		} catch (Exception e) {
			System.out.println("Unexpected error happened.");
			e.printStackTrace();
		}

		return mappFile_list;
	}

}
