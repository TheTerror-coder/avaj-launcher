package avajlauncher.main;

import avajlauncher.customs.exceptions.CustomException;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

class Main {

	public static void main(String[] args) {
		
		try {
			Path	scenarioFilePath;
			
			if (args.length < 1)
				throw new CustomException("missing argument, expected a scenario text file");
			else if (args.length > 1)
				throw new CustomException("too much arguments");
			
			scenarioFilePath = FileSystems.getDefault().getPath(args[0]);
			System.out.println(scenarioFilePath);
			System.out.println(Files.exists(scenarioFilePath));
			// scenarioFilename = args[0];
			// validateScenarioFilename(scenarioFilename);
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		finally {
		}
	}

	// public static boolean validateScenarioFilename(Path scenarioFilename) {
	// 	try {
	// 		if (!Files.exists(scenarioFilename, LinkOption.NOFOLLOW_LINKS))
	// 			return false;
				
	// 	} catch (Exception e) {
	// 			return false;
	// 	}
	// 	return (true);
	// }
}