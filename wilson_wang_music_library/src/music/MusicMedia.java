package music;

/**
 * MusicMedia is a abstract class. That stores the title and artist field of the music.
 * There is an abstract method play"(). It would be overridden in the all subclass.
 * @author 		Xiaolong Wang (Wilson)
 * @version 	1.0
 */
public abstract class MusicMedia {
	
	private String sku;
	private String title;
	private String artist;
	private int	   year;
	
	public static final int CURRENT_YEAR = 2022;
	public static final int FIRST_YEAR   = 1860;
	
	private static final int DEFAULT_YEAR = 0;
	
	public MusicMedia() {
		super();
	}

	/**
	 * Constructor
	 * @param sku		The series code for representing the music.
	 * @param title		The title of the music.
	 * @param artist	The artist of the music.
	 * @param year		The published year.
	 */
	public MusicMedia(String sku, String title, String artist, int year) {
		super();
		validateStringField(sku);
		validateStringField(title);
		validateStringField(artist);
		validateThePublishedYear(year);
		this.sku = sku;
		this.title  = title;
		this.artist = artist;
		this.year = year;
	}
	
	// Overloading Constructor. That set the unknown published year of the music to DEFAULT_YEAR 0.
	public MusicMedia(String sku, String title, String artist) {
		this(sku, title, artist, DEFAULT_YEAR);
	}
	
	// An abstract method play"(). It would be overridden in the all subclass.
	public abstract void play();

	/**
	 * This method can validate the title and artist field in this class.
	 * Make sure the title and artist are not null or blank.
	 * @param titleOrArtist The title or artist, which is a string field in this class.
	 */
	private void validateStringField(String titleOrArtist) {
		if (titleOrArtist == null || titleOrArtist.isBlank()) {
			throw new IllegalArgumentException("Invalid title or artist entered.");
		}
	}
	
	/**
	 * Validate the published year, which must be greater than and less than the constants.
	 * @param year the published year.
	 */
	private void validateThePublishedYear(int year) {
		if	(year < FIRST_YEAR || year > CURRENT_YEAR) {
			throw new IllegalArgumentException("The published year must after 1860 and before current year(2022).");
		}
	}
	
	// Get the sku of the music.
	public String getSku() {
		return sku;
	}

	/**
	 * Set the sku of the music.
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		validateStringField(sku);
		this.sku = sku;
	}

	// Get the published year of the music.
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		validateThePublishedYear(year);
		this.year = year;
	}

	// Get the title of the music.
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the music.
	 * @param title the title to set.
	 */
	public void setTitle(String title) {
		validateStringField(title);
		this.title = title;
	}

	// Get the artist of the music.
	public String getArtist() {
		return artist;
	}

	/**
	 * Set the artist of the music.
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		validateStringField(title);
		this.artist = artist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(sku);
		builder.append(" | ");
		builder.append(artist);
		builder.append(" | ");
		builder.append(title);
		builder.append(" | ");
		builder.append(year);
		return builder.toString();
	}
}
