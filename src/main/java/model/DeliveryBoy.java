package model;

import java.util.UUID;

public class DeliveryBoy {
	private final UUID ID;
	private Point location;

	public DeliveryBoy(UUID ID, Point location) {
		this.ID = ID;
		this.location = location;
	}

	@Override
	public String toString() {
		return "DeliveryBoy{" +
			"ID=" + ID +
			", location=" + location +
			'}';
	}

	public UUID getID() {
		return ID;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
}

