package avajlauncher.flyables;

import java.util.HashMap;
import java.util.HashSet;

import avajlauncher.customs.exceptions.CustomException;
import avajlauncher.main.Coordinates;

public class AircraftFactory {
	
	/*
	 * fields
	 */
	static private AircraftFactory			instance;
	private HashSet<Long>					id_s;
	private HashMap<String, FlyableModel>	flyable_samples;
	private FlyableModel					baloon_sample;
	private FlyableModel					helicopter_sample;
	private FlyableModel					jetplane_sample;
	
	/*
	 * initialization blocks
	 */
	{
		instance = null;
		id_s = new HashSet<Long>();
		flyable_samples = new HashMap<String, FlyableModel>();

		baloon_sample = (long p_id, String p_name, Coordinates p_coordinates) -> {
			return (new Baloon(p_id, p_name, p_coordinates));
		};
		helicopter_sample = (long p_id, String p_name, Coordinates p_coordinates) -> {
			return (new Helicopter(p_id, p_name, p_coordinates));
		};
		jetplane_sample = (long p_id, String p_name, Coordinates p_coordinates) -> {
			return (new Jetplane(p_id, p_name, p_coordinates));
		};

		this.flyable_samples.put("baloon", this.baloon_sample);
		this.flyable_samples.put("helicopter", this.helicopter_sample);
		this.flyable_samples.put("jetplane", this.jetplane_sample);
	}
	
	/*
	 * constructors
	 */
	private AircraftFactory() {}
	
	/*
	 * getters & setters
	 */
	static {
		instance = new AircraftFactory();
	}
	
	static public AircraftFactory	getInstance() {
		if (instance == null) {
			instance = new AircraftFactory();
		}
		return (instance);
	}
	
	/*
	 * methods
	 */
	private long	newId() {
		long	id;

		{
			id = 0;
		}
		if (this.id_s == null) {
			return (0);
		}
		while (this.id_s.contains(id))
			id++;
		return id;
	}

	public void	registerId(long p_id) {
		if (this.id_s != null) {
			this.id_s.add(p_id);
		}
	}
	
	public void	unregisterId(long p_id) {
		if (this.id_s != null) {
			this.id_s.remove(p_id);
		}
	}
	
	public boolean	idExists(long p_id) {
		if (this.id_s != null) {
			return (this.id_s.contains(p_id));
		}
		return false;
	}

	public Flyable	newAircraft(String p_type, String p_name, Coordinates p_coordinates)  throws CustomException {
		long	id;
		FlyableModel model;
		Flyable newObject;
		
		{
			id = newId();
			model = this.flyable_samples.get(p_type.toLowerCase());
			newObject = null;
		}
		if (model != null) {
			newObject = model.newInstance(id, p_name, p_coordinates);
			if (newObject != null) {
				registerId(id);
			}
		}
		return (newObject);
	}

	
}
