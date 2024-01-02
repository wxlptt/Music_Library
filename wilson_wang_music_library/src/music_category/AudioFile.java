package music_category;

/**
 * AudioFile is the subclass of the MusicMedia. It stores the file name and size in bps of the audio file.
 * There is an play() method overrode.
 * @author 		Xiaolong Wang (Wilson)
 * @version 	1.0
 */
public class AudioFile extends DigitalMedia{
	
	private String fileName;
	private int    fileResolution;
	
	private static final int DEFAULT_BPS = 0;
	
	public AudioFile() {
		super();
	}

	/**
	 * Constructor
	 * @param title		The title of the music in super class.
	 * @param artist	The artist of the music in super class.
	 * @param fileName	The file name of the audio file.
	 * @param fileResolution	The file Resolution size in bps of the audio file.
	 */
	public AudioFile(String sku, String title, String artist, int year, String fileName, int fileResolution) {
		
		super(sku, title, artist, year);
		
		validateFileName(fileName);
		validateFileResolution(fileResolution);
		
		this.fileName      = fileName;
		this.fileResolution = fileResolution;
	}
	
	public AudioFile(String sku, String title, String artist, int year, String fileName) {
		this(sku, title, artist, year, fileName, DEFAULT_BPS);
	}
	
	@Override
	public void play() {
		System.out.println("This file is playing.");
	}
	
	/**
	 * This method can validate the file name.
	 * Make sure the file name is not null or blank.
	 * @param fileName the file name.
	 */
	private void validateFileName(String fileName) {
		if (fileName == null || fileName.isBlank()) {
			throw new IllegalArgumentException("Invalid fileName entered.");
		}
	}
	
	/**
	 * This method can validate the file size.
	 * It must be not less than 0.
	 * @param fileSizeInBps The file size in Bps.
	 */
	private void validateFileResolution(int fileResolution) {
		if (fileResolution < 0) {
			throw new IllegalArgumentException("Invalid file size.");
		}
	}

	// Get the file name of the audio file.
	public String getFileName() {
		return fileName;
	}

	/**
	 * Set the file name.
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		validateFileName(fileName);
		this.fileName = fileName;
	}

	// Get the file size of the audio file.
	public int getFileResolution() {
		return fileResolution;
	}

	/**
	 * Set the file size of the audio file in bps.
	 * @param fileSizeInBps the fileSizeInBps to set
	 */
	public void setFileResolution(int fileResolution) {
		validateFileResolution(fileResolution);
		this.fileResolution = fileResolution;
	}

	/**
	 * It uses for storing the data in a specific format.
	 * @return A specific format string.
	 */
	public String toStoredFormat() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.getSku());
		builder.append("|");
		builder.append(super.getTitle());
		builder.append("|");
		builder.append(super.getArtist());
		builder.append("|");
		builder.append(super.getYear());
		builder.append("|");
		builder.append(fileName);
		builder.append("|");
		builder.append(fileResolution);
		return builder.toString();
	}
}
