package avajlauncher.main;

import avajlauncher.customs.exceptions.CustomException;
import avajlauncher.customs.exceptions.NullSimulationException;
import avajlauncher.variables.Variables;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

	static final int							AIRCRAFT_FORMAT_LENGTH = 5;
	static int									NSimulation = 0;
	static ArrayList<HashMap<String, String>>	Aircrafts = new ArrayList<>();

	public static void main(String[] args) {
		
		try {
			String	scenarioFilename;
			Variables	vars = new Variables();

			if (args.length < 1)
				throw new CustomException("missing argument, expected a scenario text file");
			if (args.length > 1)
				throw new CustomException("too much arguments");
			if (args[0].length() < 1)
				throw new CustomException("empty file name");

			scenarioFilename = args[0];
			validateScenarioFile(scenarioFilename);
			parseScenarioFile(vars, scenarioFilename);

			for (HashMap<String, String> tmp : Aircrafts) {
				for (Map.Entry<String, String> tmp2 : tmp.entrySet()) {
					System.out.print(tmp2.getValue());
					System.out.print(' ');
				}
				System.out.println();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
		}
	}

	public static boolean validateScenarioFile(String scenarioFilename) throws CustomException {
		try {
			File	scenarioFile = new File(scenarioFilename);

			if (!scenarioFile.exists())
				throw new CustomException("file '" + scenarioFilename + "' not found");
			validateTxtExtension(scenarioFile);
			if (!scenarioFile.canRead())
				throw new CustomException("cannot read file '" + scenarioFilename + "'");
				
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			return false;
		}
		return (true);
	}

	public static boolean validateTxtExtension(File f) throws CustomException {
		String		filename;
		String[]	filenameParts;
		String		extension;
		
		filename = f.getName();
		filenameParts = filename.split("\\.");
		if (filenameParts.length < 2)
			throw new CustomException("incorrect file type");
		extension = filenameParts[filenameParts.length - 1];
		if (!extension.equals("txt")){
			throw new CustomException("incorrect file extension '." + extension + "', expected '.txt' extension");
		}

		return (true);
	}

	public static boolean	parseScenarioFile(Variables vars, String scenarioFilename) throws FileNotFoundException, CustomException {
		File	f = new File(scenarioFilename);
		Scanner	sc = new Scanner(f);

		vars.lineNumber = 0;
		validateNSimulation(vars, sc);
		validateAircrafts(vars, sc);

		return (true);
	}

	private static boolean	validateNSimulation(Variables vars, Scanner sc) throws CustomException {
		try {
			
			String	line = "";
			
			if (sc.hasNext()) {
				line = sc.nextLine();
				vars.lineNumber++;
			}
			try (Scanner	strSc = new Scanner(line)) {
				if (!strSc.hasNext())
					throw new CustomException("Invalid scenario: expected weather change recurrence number -- line" +  vars.lineNumber);
				NSimulation = strSc.nextInt();
				if (strSc.hasNext())
					throw new CustomException("Invalid scenario: expected one token -- line" +  vars.lineNumber);
				if (NSimulation < 0)
					throw new CustomException("Invalid scenario: expected positive integer -- line" +  vars.lineNumber);
			}

		} catch (InputMismatchException e) {
			throw new CustomException("Invalid scenario: " + e.getMessage() + " -- line" + vars.lineNumber);
		} catch (NoSuchElementException e) {
			throw new CustomException("Invalid scenario: " + e.getMessage() + " -- line" + vars.lineNumber);
		} catch (IllegalStateException e) {
			throw new CustomException("Invalid scenario: " + e.getMessage() + " -- line" + vars.lineNumber);
		} catch (CustomException e) {
			throw e;
		}

		return (true);
	}

	private static boolean	validateAircrafts(Variables vars, Scanner sc) throws CustomException {
		try {
			
			String	line;
			
			while (sc.hasNext()) {

				line = sc.nextLine();
				vars.lineNumber++;
				validateSingleAircraft(vars, line);
			}

		} catch (InputMismatchException e) {
			throw new CustomException("Invalid scenario: " + e.getMessage() + " -- line" + vars.lineNumber);
		} catch (NoSuchElementException e) {
			throw new CustomException("Invalid scenario: " + e.getMessage() + " -- line" + vars.lineNumber);
		} catch (IllegalStateException e) {
			throw new CustomException("Invalid scenario: " + e.getMessage() + " -- line" + vars.lineNumber);
		} catch (CustomException e) {
			throw e;
		}

		return (true);
	}

	private static boolean	validateSingleAircraft(Variables vars, String line) throws CustomException {
		try {
			
			
			try (Scanner strSc = new Scanner(line)) {
				String		token;
				int			i = 1;
				HashMap<String, String>	tmpmap = new HashMap<>();

				while (strSc.hasNext()) {
					token = strSc.next();
					switch (i) {
						case 1:
							if (!vars.aircraftStrParamPattern.matcher(token).matches())
								throw new CustomException("Invalid scenario: invalid aircraft type at line" + vars.lineNumber);
							tmpmap.put("type", token);
							break;
						case 2:
							if (!vars.aircraftStrParamPattern.matcher(token).matches())
								throw new CustomException("Invalid scenario: invalid aircraft name at line" + vars.lineNumber);
							tmpmap.put("name", token);
							break;
						case 3:
							if (!vars.positiveIntPattern.matcher(token).matches())
								throw new CustomException("Invalid scenario: invalid aircraft longitude at line" + vars.lineNumber);
							tmpmap.put("longitude", token);
							break;
						case 4:
							if (!vars.positiveIntPattern.matcher(token).matches())
								throw new CustomException("Invalid scenario: invalid aircraft latitude at line" + vars.lineNumber);
							tmpmap.put("latitude", token);
							break;
						case 5:
							if (!vars.positiveIntPattern.matcher(token).matches())
								throw new CustomException("Invalid scenario: invalid aircraft heigth at line" + vars.lineNumber);
							
							try {
								if (Integer.parseInt(token) > 100)
									throw new CustomException("Invalid scenario: aircraft heigth exceeds upper limit(100) at line" + vars.lineNumber);
							} catch (NumberFormatException e) {
								throw new CustomException("failed to parse aircraft heigth at line" + vars.lineNumber);
							}
							
							tmpmap.put("heigth", token);
							break;
						case 6:
							throw new CustomException("Invalid scenario: too much tokens at line" + vars.lineNumber);
						default:
							break;
					}
					i++;

				}
				Aircrafts.add(tmpmap);
			}

		} catch (InputMismatchException e) {
			throw new CustomException("Invalid scenario: " + e.getMessage() + " -- line" + vars.lineNumber);
		} catch (NoSuchElementException e) {
			throw new CustomException("Invalid scenario: " + e.getMessage() + " -- line" + vars.lineNumber);
		} catch (IllegalStateException e) {
			throw new CustomException("Invalid scenario: " + e.getMessage() + " -- line" + vars.lineNumber);
		} catch (CustomException e) {
			throw e;
		}

		return (true);
	}
}