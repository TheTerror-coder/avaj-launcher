package avajlauncher.flyables;

import avajlauncher.customs.exceptions.CustomException;
import avajlauncher.main.Coordinates;
import avajlauncher.main.WeatherTower;

public abstract class Flyable {
	
	protected WeatherTower	weatherTower;


	abstract public void	updateConditions() throws CustomException;
	
	abstract public String	getType();
	abstract public String	getName();
	abstract public long	getId();

	public void				registerTower(WeatherTower p_tower) {
		this.weatherTower = p_tower;
	}

}
