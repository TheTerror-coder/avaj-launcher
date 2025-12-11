package avajlauncher.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.constant.ConstantDescs;

import avajlauncher.customs.exceptions.CustomException;
import avajlauncher.variables.Constants;

public class SimulationLogger {
	private static SimulationLogger	instance = null;
	private File					simulationFile = null;

	/*
	 * initialization block
	 */
	{
		instance = null;
	}
	
	/*
	* constructors
	*/
	private SimulationLogger() throws CustomException {
		try {
			simulationFile = new File(Constants.sSIMULATION_OUTPUT_FILENAME);

			if (simulationFile.isDirectory()) {
				throw new CustomException("simulation.txt is not a file");
			}
			if (!simulationFile.canWrite()) {
				throw new CustomException("simulation.txt file is not writable");
			}
			try (FileWriter	w = new FileWriter(this.simulationFile)) {
				
			} catch (Exception e) {
				throw e;
			}
		} catch (IOException e) {
			System.out.println("simulation.txt file creation failed!");
			throw new CustomException(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * get singleton instance
	 * @return singleton
	 * @throws CustomException
	 */
	public static SimulationLogger	getInstance() throws CustomException{
		if (instance == null) {
			instance = new SimulationLogger();
		}
		return (instance);
	}

	public void	logAircraftRegistration(String p_type, String p_name, long p_id) throws CustomException {
		try (FileWriter	w = new FileWriter(this.simulationFile, true)) {
			w.write("Tower says: " + p_type + "#" + p_name + "(" + p_id + "): registered to weather tower.\n");
		} catch (IOException e) {
			throw new CustomException(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void	logAircraftUnregistration(String p_type, String p_name, long p_id) throws CustomException {
		try (FileWriter	w = new FileWriter(this.simulationFile, true)) {
			w.write("Tower says: " + p_type + "#" + p_name + "(" + p_id + "): unregistered from weather tower.\n");
		} catch (IOException e) {
			throw new CustomException(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void	logAircraftWeatherChangeMessage(String p_type, String p_name, long p_id, String p_msg) throws CustomException {
		try (FileWriter	w = new FileWriter(this.simulationFile, true)) {
			w.write(p_type + "#" + p_name + "(" + p_id + "): " + p_msg + "\n");
		} catch (IOException e) {
			throw new CustomException(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void	putSeperator() throws CustomException {
		try (FileWriter	w = new FileWriter(this.simulationFile, true)) {
			w.write("-----------------------\n");
		} catch (IOException e) {
			throw new CustomException(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
