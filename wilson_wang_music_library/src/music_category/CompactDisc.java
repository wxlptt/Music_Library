package music_category;

/**
 * CompactDisc is the subclass of the MusicMedia. It stores the number of the tracks of the compact disc.
 * There is an play() method overrode.
 * @author 		Xiaolong Wang (Wilson)
 * @version 	1.0
 */
public class CompactDisc extends PhysicalMedia{
	
	private int numberOfTracks;
	
	private static final int MIN_OF_TRACKS = 0;
	private static final int MAX_OF_TRACKS = 255;

	public CompactDisc() {
		super();
	}

	/**
	 * Constructor	 
	 * @param sku		The series code for representing the music.
	 * @param title		The title of the music.
	 * @param artist	The artist of the music.
	 * @param year		The published year.
	 * @param numberOfTracks The number of tracks of the compact disc.
	 */
	public CompactDisc(String sku, String title, String artist, int year, int numberOfTracks) {
		
		super(sku, title, artist, year);
		
		validateNumberOfTracks(numberOfTracks);
		this.numberOfTracks = numberOfTracks;
	}
	
	@Override
	public void play() {
		System.out.println("CD is playing.");
	}
	
	/**
	 * This method can validate the number of tracks.
	 * It must be greater than MIN_OF_TRACKS and less than MAX_OF_TRACKS.
	 * @param numberOfTracks The number of tracks.
	 */
	public static void validateNumberOfTracks(int numberOfTracks) {
		if (numberOfTracks <= MIN_OF_TRACKS || numberOfTracks > MAX_OF_TRACKS) {
			throw new IllegalArgumentException("Invalid number of tracks");
		}
	}

	// Get the number of the tracks of the compact disc.
	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	/**
	 * Set the number of the tracks of the compact disc.
	 * @param numberOfTracks the numberOfTracks to set
	 */
	public void setNumberOfTracks(int numberOfTracks) {
		validateNumberOfTracks(numberOfTracks);
		this.numberOfTracks = numberOfTracks;
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
		builder.append(numberOfTracks);
		return builder.toString();
	}
}
