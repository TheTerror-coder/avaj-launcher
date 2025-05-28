package avajlauncher.weather;

import java.lang.Math;
import avajlauncher.main.Coordinates;

public class WeatherProvider {
	
	private String[]				weather;
	private static WeatherProvider	instance;

	/*
	 * initialization block
	 */
	{
		weather = new String[5];
		weather[0] = "RAIN";
		weather[1] = "FOG";
		weather[2] = "SUN";
		weather[3] = "SNOW";
	}
	static {
		instance = null;
	}

	/*
	 * Constructors
	 */
	private WeatherProvider() {
	}

	/*
	 * getters & setters
	 */
	static public WeatherProvider	getInstance() {
		if (instance == null) {
			instance = new WeatherProvider();
		}
		return (instance);
	}

	/*
	 * methods
	 */

	 /**
	  * computes the weather at the point represented by the given coordinates
	  * @param p_coordinates
	  * @return
	  */
	public String	getCurrentWeather(Coordinates p_coordinates) {
		int	longitude;
		int	latitude;
		int	height;
		// double	cos_longitude;
		double	sin_longitude;
		double	cos_latitude;
		double	sin_latitude;

		{
			longitude = p_coordinates.getLongitude();
			latitude = p_coordinates.getLatitude();
			height = p_coordinates.getHeight();
			// cos_longitude = Math.cos(longitude);
			sin_longitude = Math.sin(latitude);
			cos_latitude = Math.cos(longitude);
			sin_latitude = Math.sin(latitude);
		}

		if (height > 50)
			return ("SUN");

		{
			/**
			 * polar areas
			 */
			if ((sin_latitude >= -1 && sin_latitude <= -0.85) || (sin_latitude >= 0.85 && sin_latitude <= 1)) {
				/**
				 * clouds
				 */
				if (height > 25)
					return ("FOG");
				return ("SNOW");
			}
			/**
			 * temperate regions
			 */
			else if ((sin_latitude >= -0.85 && sin_latitude <= -0.6) || (sin_latitude >= 0.6 && sin_latitude <= 0.85)) {
				/**
				 * low altitude
				 */
				if (height < 10)
					return ("RAIN");
				/**
				 * mid altitude
				 */
				if (height < 30)
					return ("SNOW");
				/**
				 * high altitude => clouds
				 */
				return ("FOG");
			}
			/**
			 * sub tropical areas
			 */
			else if ((sin_latitude >= -0.6 && sin_latitude <= -0.3) || (sin_latitude >= 0.3 && sin_latitude <= 0.6)) {
				/**
				 * all subtropical areas except sahara and middleEast parts
				 */
				if (!(cos_latitude >= 0 && cos_latitude <= 1 && sin_longitude >= 0 && sin_longitude <= 1)) {
					/**
					 * high altitude
					 */
					if (height >= 40)
						return ("FOG");
				}
				return ("SUN");
			}
			/**
			 * tropical areas
			 */
			else {
				/**
				 * high altitude
				 */
				if (height > 25)
					return ("FOG");
				return ("RAIN");
			}
		}
	}
}
