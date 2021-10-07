package yield.file;

import java.io.IOException;

import yieldg.main.Sprite;
import yieldg.util.SpriteList;

public final class MappFileSystem {

	public static SpriteList fromPath(String path) {
		Sprite mappFile_mum = null;
		int mappFile_x = 0, mappFile_y = 0, mappFile_width = 1, mappFile_height = 1, mappFile_ax = 0, mappFile_ay = 0;
		String mappFile_actname = "";
		SpriteList mappFile_list;

		mappFile_list = new SpriteList();

		try {
			String[] mappFile = FReader.readFile(path, "-/-/-7-yieldfileseparator-7-/-/-")
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
							Sprite sc = mappFile_mum.getSpriteint(mappFile_x, mappFile_y, mappFile_width,
									mappFile_height);
							sc.name = (mappFile_actname);
							sc.addX = mappFile_ax;
							sc.addY = mappFile_ay;
							mappFile_list.add(sc);
							break;
						case "path":
							mappFile_mum = new Sprite(spl2[1]);
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
