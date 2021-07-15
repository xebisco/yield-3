package util.file.mapp;

import util.file.txt.Reader;
import util.graphics.Sprite;
import util.graphics.SpriteChild;
import util.graphics.SpriteList;

public class Interpreter {

	private static Sprite mum;
	private static int x, y, width, height, hx, hy;
	private static String hotPoint, actname;
	private static SpriteList list;

	public static SpriteList mappFile(String path) {

		if (!path.endsWith(".mapp")) {
			path.concat(".mapp");
		}

		list = new SpriteList(path);

		String readed = Reader.readFile(path, "----////separator////----");

		interpret(readed.split("----////separator////----"));

		return list;
	}

	public static void interpret(String[] mappFile) {
		for (int i = 0; i < mappFile.length; i++) {
			String[] spl = mappFile[i].split(" ");
			for (int i2 = 0; i2 < spl.length; i2++) {
				String[] spl2 = spl[i2].split("=");
				for (int i3 = 0; i3 < spl2.length/2; i3++) {
					// System.out.println(spl2[i3]);

					switch (spl2[0]) {
					case "<":
						x = 0;
						y = 0;
						width = 0;
						height = 0;
						hx = 0;
						hy = 0;
						hotPoint = "";
						actname = spl2[1];
						
						break;
						
					case ">":
						SpriteChild sc = new SpriteChild(x, y, width, height, mum);
						sc.setName(actname);
						if(!hotPoint.equals("")) {
							sc.setHotPoint(hotPoint);
						} else {
							sc.setHotPoint(hx, hy);
						}
						list.add(sc);
						break;
					case "path":
						mum = new Sprite("/" + spl2[1]);
						break;
					case "name":
						list.setName(spl2[1]);
						break;
					case "x":
						x = Integer.parseInt(spl2[1]);
						break;
					case "y":
						y = Integer.parseInt(spl2[1]);
						break;
					case "width":
						width = Integer.parseInt(spl2[1]);
						break;
					case "height":
						height = Integer.parseInt(spl2[1]);
						break;
					case "hotPoint":
						hotPoint = spl2[1];
						break;
					case "hx":
						hx = Integer.parseInt(spl2[1]);
						break;
					case "hy":
						hy = Integer.parseInt(spl2[1]);
						break;
					}
					
				}
			}
		}

	}
}
