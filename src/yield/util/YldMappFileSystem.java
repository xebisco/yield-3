package yield.util;

import mapp.interpreter.MappInterpreter;
import mapp.types.MappImage;
import yieldg.YldSprite;
import yieldg.util.YldSpriteArray;
import yieldg.util.YldSpriteList;

/**
 * O YldMappFileSystem carrega informações de um arquivo .mapp e transforma em listas de Imagens.
 */
@Deprecated
public final class YldMappFileSystem {

	public static final String MAPP_FILE_SYSTEM_VERSION = "2";

	public static YldSpriteList fromPath(String path) {
		YldSpriteList list = new YldSpriteList();

		MappImage[] images = MappInterpreter.fromPath(path);
		
		for(int i = 0; i < images.length; i++) {
			list.list.add(new YldSprite(images[i].getImage(), images[i].getName()));
		}

		return list;
	}

	public static YldSpriteArray toArray(String path) {
		YldSpriteArray array = null;

		MappImage[] images = MappInterpreter.fromPath(path);

		array = new YldSpriteArray(images.length);

		for(int i = 0; i < images.length; i++) {
			array.list[i] = new YldSprite(images[i].getImage(), images[i].getName());
		}

		return array;
	}

}
