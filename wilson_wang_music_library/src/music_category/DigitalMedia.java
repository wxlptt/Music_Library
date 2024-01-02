package music_category;

import music.FileProcessor;
import music.MusicMedia;

/**
 * Abstract class DigitalMedia, that extends MusicMedia.
 * It includes a subclass, AudioFile.
 * @author 		Xiaolong Wang (Wilson)
 * @version 	1.0
 */
public abstract class DigitalMedia extends MusicMedia implements FileProcessor{
	
	@SuppressWarnings("unused")
	// Uncompleted file processor.
	// Use another way to handle file writing.
	private String filePath;
	private String DEFAULT_FILE_PATH = "C:\\myMusic\\";
	{
		filePath = DEFAULT_FILE_PATH;
	}

	public DigitalMedia() {
		super();
	}

	/**
	 * Constructor.
	 * @param sku		The series code for representing the music.
	 * @param title		The title of the music.
	 * @param artist	The artist of the music.
	 * @param year		The published year.
	 */
	public DigitalMedia(String sku, String title, String artist, int year) {
		
		super(sku, title, artist, year);
	
//		this.save(filePath);
	}
	
	/**
	 * Validate the file path, it must not be null or blank.
	 * @param filePath The file path of the music.
	 */
	private void validateFilePath(String filePath) {
		if (filePath == null || filePath.isBlank()) {
			throw new IllegalArgumentException("Invalid file path entered.");
		}
	}
	
	
	/**
	 * Uncompleted file processor.
	 * Use another way to handle file writing.
	 * The save method implements from interface FileProcessor.
	 * It can save the audio in the path.
	 * @param filePath The path which stores the audio file.
	 */
	@Override
	public void save(String filePath) {
		validateFilePath(filePath);
		this.filePath = filePath;
		System.out.println("Saving the audio File");
	}
	
	
	/**
	 * Uncompleted file processor.
	 * Use another way to handle record deleting.
	 * The delete method implements from interface FileProcessor.
	 * It can delete the audio from the path.
	 * @param filePath The path of the music.
	 */
	@Override
	public void delete(String filePath) {
		System.out.println("Deleting the audio file");
	}
}
