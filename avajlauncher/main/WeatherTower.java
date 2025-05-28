package avajlauncher.main;

import avajlauncher.customs.exceptions.CustomException;
import avajlauncher.flyables.Flyable;
import avajlauncher.weather.WeatherProvider;

public class WeatherTower extends Tower {
	
	/**
	 * calls 'register' super class (Tower) method then registers this current weather tower in the flyable
	 * @param p_flyable a flyable object
	 */
	@Override
	public void	register(Flyable p_flyable) throws CustomException {
		super.register(p_flyable);
		p_flyable.registerTower(this);
	}

	/**
	 * 
	 * @param p_coordinates
	 * @return a string representing the current weather
	 */
	public String	getWeather(Coordinates p_coordinates) {
		return (WeatherProvider.getInstance().getCurrentWeather(p_coordinates));
	}

	/**
	 * a weather change occured, trigger state updatings
	 * @throws CustomException
	 */
	public void		changeWeather() throws CustomException {
		this.conditionChanged();
	}
}
