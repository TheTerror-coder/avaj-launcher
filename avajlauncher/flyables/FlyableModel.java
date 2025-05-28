package avajlauncher.flyables;

import avajlauncher.customs.exceptions.CustomException;
import avajlauncher.main.Coordinates;

@FunctionalInterface
public interface FlyableModel {

	public Flyable newInstance(long p_id, String p_name, Coordinates p_coordinates)  throws CustomException;

}
