package model;

public class Point {
	private final Double Latitude;
	private final Double Longitude;

	public Point(Double latitude, Double longitude) {
		Latitude = latitude;
		Longitude = longitude;
	}

	@Override
	public String toString() {
		return "Point{" +
			"Latitude=" + Latitude +
			", Longitude=" + Longitude +
			'}';
	}

	public Double getLatitude() {
		return Latitude;
	}

	public Double getLongitude() {
		return Longitude;
	}
}
