package com.thenewboston.terry;

public class Weather {
	public String city = null;
	public int temperature = 0;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "Weather [City=" + city + ", Temperature=" + temperature + "]";
	}

}
