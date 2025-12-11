package avajlauncher.flyables;

import avajlauncher.customs.exceptions.CustomException;
import avajlauncher.main.Coordinates;
import avajlauncher.main.SimulationLogger;

public class Jetplane extends Aircraft {
	
	{
		this.type = "jetplane";
	}

	public Jetplane(long p_id, String p_name, Coordinates p_coordinates)  throws CustomException {
		super(p_id, p_name, p_coordinates);
	}

	@Override
	public void	updateConditions() throws CustomException {
		super.updateConditions();
		switch (this.currentWeather.toLowerCase()) {
			case "sun":
				this.coordinates.incrementLatitude(10);
				this.coordinates.incrementHeight(2);
				SimulationLogger.getInstance().logAircraftWeatherChangeMessage(this.type, this.name, this.id, "It feels like the sun is roasting everything in sight!");
				break;
			case "rain":
				this.coordinates.incrementLatitude(5);
				SimulationLogger.getInstance().logAircraftWeatherChangeMessage(this.type, this.name, this.id, "The rain is pounding the roof like a drum!");
				break;
			case "fog":
				this.coordinates.incrementLatitude(1);
				SimulationLogger.getInstance().logAircraftWeatherChangeMessage(this.type, this.name, this.id, "The fog is creeping in, swallowing everything in its path!");
				break;
			case "snow":
				this.coordinates.decrementHeight(7);
				SimulationLogger.getInstance().logAircraftWeatherChangeMessage(this.type, this.name, this.id, "It’s like the snow is gently whispering as it falls, blanketing everything!");
				break;
		
			default:
				break;
		}
		if (this.coordinates.getHeight() <= 0) {
			this.weatherTower.unregister(this);
		}
	}

}
