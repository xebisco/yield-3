package yield.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yield.engine.YieldApp;

public class Room {
	private String name;

	public static Room defaultRoom;

	private static Room actRoom;

	private List<Body> bodies;

	public Room(String name) {
		setBodies(new ArrayList<>());
		this.name = name;
		load();
	}

	public final void update() {
		for (int i = 0; i < getBodies().size(); i++) {
			Body body = getBodies().get(i);
			YieldApp.app().processBody(body);
			if (body.getLayer() >= 0) {
				Collections.swap(getBodies(), body.getLayer(), i);
				body.setLayer(-1);
			}
		}
	}

	public final void renderer(Graphics g) {
		for (int i = 0; i < getBodies().size(); i++) {
			Body body = getBodies().get(i);
			YieldApp.app().processBody(body, g);
		}
	}

	public void load() {
		if(YieldApp.app().getRooms() == null) {
			YieldApp.app().setRooms(new ArrayList<>());
		}
		YieldApp.app().getRooms().add(this);
	}

	public void remove() {
		YieldApp.app().getRooms().remove(this);
	}

	public static void load(Room room) {
		YieldApp.app().getRooms().add(room);
	}

	public static void remove(Room room) {
		YieldApp.app().getRooms().remove(room);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Room getActRoom() {
		return actRoom;
	}

	public static void setActRoom(Room actRoom) {
		Room.actRoom = actRoom;
	}

	public List<Body> getBodies() {
		return bodies;
	}

	public void setBodies(List<Body> bodies) {
		this.bodies = bodies;
	}

	public void addBody(Body body) {
		body.setRoom(this);
		getBodies().add(body);
	}

	public void removeBody(Body body) {
		body.setRoom(Room.defaultRoom);
		getBodies().remove(body);
	}
	
	@Override
	public String toString() {
		return "Room: " + name;
	}

}
