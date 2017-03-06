package model.context;

import model.Point;

import java.util.UUID;

// Note : This POJO is same as order but created sep. for extendability and separation of concerns.
public class StashRequest {
	private final UUID orderID;
	private final Long cityID;
	private final Long areaID;
	private final Long assignAfter;
	private final Point orderLocation;

	public StashRequest(UUID orderID, Long cityID, Long areaID, Long assignAfter, Point orderLocation) {
		this.orderID = orderID;
		this.cityID = cityID;
		this.areaID = areaID;
		this.assignAfter = assignAfter;
		this.orderLocation = orderLocation;
	}

	@Override
	public String toString() {
		return "StashRequest{" +
			"orderID=" + orderID +
			", cityID=" + cityID +
			", areaID=" + areaID +
			", assignAfter=" + assignAfter +
			", orderLocation=" + orderLocation +
			'}';
	}

	public UUID getOrderID() {
		return orderID;
	}

	public Long getCityID() {
		return cityID;
	}

	public Long getAreaID() {
		return areaID;
	}

	public Long getAssignAfter() {
		return assignAfter;
	}

	public Point getOrderLocation() {
		return orderLocation;
	}
}
