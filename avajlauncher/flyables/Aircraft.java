package avajlauncher.flyables;

import avajlauncher.customs.exceptions.CustomException;
import avajlauncher.main.Coordinates;

public class Aircraft extends Flyable{
	
	protected String		type;
	protected long			id;
	protected String		name;
	protected Coordinates	coordinates;
	protected String		currentWeather;

	{
		type = "aircraft";
		currentWeather = "SUN";
	}
	
	protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) throws CustomException {
		if (AircraftFactory.getInstance().idExists(p_id)) {
			throw new CustomException("Aircraft:" + p_name + " an Aircraft with the same id:" + p_id + " already exists");
		}
		this.id = p_id;
		this.name = p_name;
		this.coordinates = p_coordinates;
	}

	/*
	 * Getters & Setters
	 */
	public String	getType() {
		return (this.type);
	}
	public String	getName() {
		return (this.name);
	}
	public long		getId() {
		return (this.id);
	}

	/**
	 * gets the current weather at this aircraft's position
	 */
	@Override
	public void	updateConditions() throws CustomException {
		if (this.weatherTower != null) {
			this.currentWeather = this.weatherTower.getWeather(this.coordinates);
		}
	}

}
