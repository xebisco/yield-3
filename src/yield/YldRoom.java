package yield;

/**
 * Uma coletânia de YldBodies.
 */
public final class YldRoom {
	
	private static YldRoom actRoom;

	/**
	 * @return the actRoom
	 */
	public static YldRoom getActRoom() {
		return actRoom;
	}

	/**
	 * @param actRoom the actRoom to set
	 */
	public static void setActRoom(YldRoom actRoom) {
		YldRoom.actRoom = actRoom;
	}
	
	

}
