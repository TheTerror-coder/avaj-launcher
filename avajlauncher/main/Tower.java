package avajlauncher.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import avajlauncher.customs.exceptions.CustomException;
import avajlauncher.flyables.*;

public class Tower {
	
	private List<Flyable>	observers;
	private Iterator<Flyable>	observers_iterator;
	
	{
		observers = new LinkedList<>();
		observers_iterator = observers.iterator();
	}

	/**
	 * loops on all registered flyables calling condition updating on them meaning a weather change occured
	 * @throws CustomException
	 */
	protected void	conditionChanged() throws CustomException{
		if (this.observers != null) {
			this.observers_iterator = this.observers.iterator();
			while (this.observers_iterator.hasNext())
			{
				Flyable elm = this.observers_iterator.next();
				elm.updateConditions();
			}
		}
	}
	
	/**
	 * registers the flyable object to the tower
	 * then logs the registration
	 * @param p_flyable
	 * @throws CustomException
	 */
	public void	register(Flyable p_flyable) throws CustomException{
		if (this.observers != null && !this.observers.contains(p_flyable)) {
			String	type;
			String	name;
			long	id;
			
			{
				type = p_flyable.getType();
				name = p_flyable.getName();
				id = p_flyable.getId();
			}
			this.observers.add(p_flyable);
			SimulationLogger.getInstance().logAircraftRegistration(type, name, id);
		}
	}
	
	/**
	 * unregisters the flyable from the tower
	 * @param p_flyable
	 */
	public void	unregister(Flyable p_flyable) throws CustomException {
		this.observers_iterator.remove();
		SimulationLogger.getInstance().logAircraftUnregistration(p_flyable.getType(), p_flyable.getName(), p_flyable.getId());
	}

}
