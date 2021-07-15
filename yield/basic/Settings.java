package basic;

public class Settings {

	// configurações que a engine precisa para iniciar o jogo >

	private static int game_FPS = 60, window_WIDTH = 1280, window_HEIGHT = 720, game_WIDTH = 1280, game_HEIGHT = 720;
	private static boolean window_FULLSCREEN, canOpenPanel = true;
	private static String game_NAME, game_COMPANY, game_VERSION;
	private static String icon_PATH = "/engine/resourses/icon.png";

	// Getters e setters >

	public static String getIcon_PATH() {
		return icon_PATH;
	}
	
	public static int getGame_FPS() {
		return game_FPS;
	}

	public static int getWindow_WIDTH() {
		return window_WIDTH;
	}

	public static int getWindow_HEIGHT() {
		return window_HEIGHT;
	}

	public static int getGame_WIDTH() {
		return game_WIDTH;
	}

	public static int getGame_HEIGHT() {
		return game_HEIGHT;
	}

	public static boolean isFULLSCREEN() {
		return window_FULLSCREEN;
	}
	public static boolean canOpenPanel() {
		return canOpenPanel;
	}

	public static String getGame_NAME() {
		return game_NAME;
	}

	public static String getGame_COMPANY() {
		return game_COMPANY;
	}

	public static String getGame_VERSION() {
		return game_VERSION;
	}
	public static void changeResolution(int width, int height) {
		game_WIDTH = width;
		game_HEIGHT = height;
	}

	// Getters e setters <

	// configurações que a engine precisa para iniciar o jogo <

	// classes para que na hora que se mude as configurações seja mais fácil e
	// organizado >

	public static class Window {

		// as configurações da janela primária da engine

		public static void Change(int window_WIDTH, int window_HEIGHT, boolean window_FULLSCREEN) {

			// esse método serve para mudar as configurações da janela

			Settings.window_WIDTH = window_WIDTH;
			Settings.window_HEIGHT = window_HEIGHT;
			Settings.window_FULLSCREEN = window_FULLSCREEN;

		}

	}

	public static class Info {

		// as informações para fazer o perfil do jogo(offline)

		public static void Change(String game_NAME, String game_COMPANY, String game_VERSION) {

			// esse método serve para mudar as informações do perfil do jogo

			Settings.game_NAME = game_NAME;
			Settings.game_COMPANY = game_COMPANY;
			Settings.game_VERSION = game_VERSION;

		}

	}

	public static class Engine {

		// as configurações de execução da engine

		public static void Change(int game_WIDTH, int game_HEIGHT, int game_FPS) {

			// esse método serve para mudar as configurações de execução da engine

			Settings.game_WIDTH = game_WIDTH;
			Settings.game_HEIGHT = game_HEIGHT;
			Settings.game_FPS = game_FPS;

		}

	}

	public static class Optional {

		// configurações opicionais que a engine não necessita para iniciar

		public static void ChangeIcon(String path) {
			icon_PATH = path;
		}
		
		public static void canOpenPanel(boolean is) {
			canOpenPanel = is;
		}

	}

	// classes para que na hora que se mude as configurações seja mais fácil e
	// organizado <
}
