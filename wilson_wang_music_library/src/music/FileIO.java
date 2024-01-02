package music;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.Scanner;

import music_category.AudioFile;
import music_category.CompactDisc;
import music_category.VinylRecord;
import music_library.MusicLibrary;

/**
 * File IO, that can read and write the data file. It also can initiate the program.
 * @author 		Xiaolong Wang(Wilson)
 * @version 	1.0
 */
public class FileIO {
	
	private MusicLibrary library = new MusicLibrary();

	public FileIO() {
		super();
	}
	
	/**
	 * It reads a file which is contains some musics.
	 * Then, based on the music's type, collects them into the music library.
	 * @param filePath The path of the file.
	 */
	public void init(String filePath) {
		
		// Read file and store the music into library.
		FileReader fileReader = null;
		Scanner fileScanner = null;
		try {
			fileReader = new FileReader(filePath);
			fileScanner = new Scanner(fileReader);

			// Categorize the music in file, and store them into music library. 
			while(fileScanner.hasNextLine()) {
				String aMusicInfo[] = fileScanner.nextLine().split("\\|");
				library.parseListIntoMusicLibrary(aMusicInfo);
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @category Unused method.
	 * Display all the musics in the library.
	 * Then, display the music list by inputting the type from users.
	 */
	public void displayMenu() {
		
		System.out.println("DEBUG: Library Contents");
		library.getLibrary()
					.values()
					.forEach(System.out::println);
		System.out.println("");
		
		boolean exit = false;
		// while loop, it interact with users until they input 4(exit).
		while(exit == false) {
			
			System.out.println("Choose one of the following options:");
			System.out.println("1. Display Audio Files");
			System.out.println("2. Display CompactDiscs");
			System.out.println("3. Display Vinyl Records");
			System.out.println("4. Exit");
			
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			String value = in.next();
				
			switch(value) {
				case "1":
					library.displayChoice(MusicLibrary.TYPE_AUDIO_FILE);
					break;
				case "2":
					library.displayChoice(MusicLibrary.TYPE_COMPACT_DISC);
					break;
				case "3":
					library.displayChoice(MusicLibrary.TYPE_VINYL_RECORD);
					break;
				case "4":
					System.out.println("So long for now!");
					exit = true;
					break;
			default:
				System.out.println("Invalid Enter. Please choose the list of the option.");
				break;
			}
		}
	}
	
	/**
	 * Write each music media into a file, the music medias are stored in the music library.
	 * @param filePath		The path of file.
	 * @param musicLibrary	The music library.
	 */
	public static void writeDataIntoFile(String filePath, HashMap<String, MusicMedia> musicLibrary){
		try {
		
			StringBuilder content = new StringBuilder();
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			
			// Append each music media in StringBuilder in specific format.
			for	(MusicMedia musicMedia:musicLibrary.values()) {
				content.append(musicMedia.getSku());
				content.append("|");
				content.append(musicMedia.getTitle());
				content.append("|");
				content.append(musicMedia.getArtist());
				content.append("|");
				content.append(musicMedia.getYear());
				content.append("|");
				if(musicMedia instanceof AudioFile) {
					AudioFile audioFile = (AudioFile)musicMedia;
					content.append(audioFile.getFileName());
					content.append("|");
					content.append(audioFile.getFileResolution());
					content.append(System.lineSeparator());
				}
				else if(musicMedia instanceof CompactDisc) {
					CompactDisc compactDisc = (CompactDisc)musicMedia;
					content.append(compactDisc.getNumberOfTracks());
					content.append(System.lineSeparator());
				}
				else if(musicMedia instanceof VinylRecord) {
					VinylRecord vinylRecord = (VinylRecord)musicMedia;
					content.append(vinylRecord.getNumberOfTracks());
					content.append("|");
					content.append(vinylRecord.getWeightInGrams());
					content.append("|");
					content.append(vinylRecord.getSizeInInch());
					content.append(System.lineSeparator());
				}
			}
			writer.write(content.toString());
			writer.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the hash map of the library.
	 * @return the hash map.
	 */
	public HashMap<String, MusicMedia> getLibraryMap() {
		return library.getLibrary();
	}
}
