package music_category;

/**
 * VinylRecord is the subclass of the MusicMedia. 
 * It stores the vinyl record information the number of the tracks, the size in inch, 
 * and weight in grams.
 * There is an play() method overrode.
 * @author 		Xiaolong Wang (Wilson)
 * @version 	1.0
 */
public class VinylRecord extends PhysicalMedia{
	
	private int numberOfTracks;
	private int sizeInInch;
	private int weightInGrams;
	
	private static final int DEFAULT_WEIGHT      = 40;
	private static final int DEFAULT_SIZE        = 7;
	private static final int DEFAULT_TRACK_COUNT = 2;
	
	private final int WEIGHT_STANDARD_ONE 	= 140;
	private final int WEIGHT_STANDARD_TWO 	= 180;
	private final int WEIGHT_STANDARD_THREE = 200;
	private final int WEIGHT_STARDARD_FOUR  = 100;
	
	private final int SIZE_STANDARD_ONE   = 7;
	private final int SIZE_STANDARD_TWO   = 10;
	private final int SIZE_STANDARD_THREE = 12;
	
	{
		numberOfTracks = DEFAULT_TRACK_COUNT;
		sizeInInch     = DEFAULT_SIZE;
		weightInGrams  = DEFAULT_WEIGHT;
	}
	
	public VinylRecord() {
		super();
	}

	/**
	 * Constructor
	 * @param sku		The series code for representing the music.
	 * @param title		The title of the music.
	 * @param artist	The artist of the music.
	 * @param year		The published year.
	 * @param numberOfTracks The number of the tracks of the vinyl record.
	 * @param sizeInInch	 The size of the vinyl record in inch.
	 * @param weightInGrams  The weight of the vinyl record in grams.
	 */
	public VinylRecord(String sku, String title, String artist, int year, int numberOfTracks, int sizeInInch, int weightInGrams) {
		
		super(sku, title, artist, year);
		
		CompactDisc.validateNumberOfTracks(numberOfTracks);
		validateSizeInInch(sizeInInch);
		validateWeightInGrams(weightInGrams);
		
		this.numberOfTracks = numberOfTracks;
		this.sizeInInch = sizeInInch;
		this.weightInGrams = weightInGrams;
	}
	
	// Overloading Constructor.
	public VinylRecord(String sku, String title, String artist, int year, int numberOfTracks) {
		this(sku, title, artist, year, numberOfTracks, DEFAULT_SIZE, DEFAULT_SIZE);
	}
	
	@Override
	/**
	 * Uncompleted method.
	 * Unused method.
	 */
	public void play() {
		System.out.println("Vinyl record is playing.");
	}
	
	/**
	 * This method can validate the size of the vinyl record in inch.
	 * It must be 7inch, 10inch, or 12inch.
	 * @param sizeInInch The size of vinyl record.
	 */
	private void validateSizeInInch(int sizeInInch) {
		if (sizeInInch != SIZE_STANDARD_ONE 
				&& sizeInInch != SIZE_STANDARD_TWO 
				&& sizeInInch != SIZE_STANDARD_THREE) {
			throw new IllegalArgumentException("The size of vinyl record must be 7inch, 10inch, or 12inch.");
		}
	}
	
	/**
	 * This method can validate the weight of the vinyl record in grams.
	 * It must be 40, 140g, 180g, or 200g.
	 * @param sizeInInch The weight of vinyl record.
	 */
	private void validateWeightInGrams(int weightInGrams) {
		if (weightInGrams != WEIGHT_STANDARD_ONE 
				&& weightInGrams != WEIGHT_STANDARD_TWO
				&& weightInGrams != WEIGHT_STANDARD_THREE
				&& weightInGrams != WEIGHT_STARDARD_FOUR
				&& weightInGrams != DEFAULT_WEIGHT) {
			throw new IllegalArgumentException("The weight in grams must be 40G, 140g, 180g, or 200g.");
		}
	}
	
	// Get the number of tracks of the vinyl record.
	public int getNumberOfTracks() {
		return numberOfTracks;
	}
	
	/**
	 * Set the number of the tracks of the vinyl record. It must be a positive number and less than 256.
	 * @param numberOfTracks the numberOfTracks to set
	 */
	public void setNumberOfTracks(int numberOfTracks) {
		CompactDisc.validateNumberOfTracks(numberOfTracks);
		this.numberOfTracks = numberOfTracks;
	}
	
	// Get the size of the vinyl record in inch.
	public int getSizeInInch() {
		return sizeInInch;
	}
	
	/**
	 * Set the size of the vinyl record in inch. 
	 * @param sizeInInch the sizeInInch to set
	 */
	public void setSizeInInch(int sizeInInch) {
		validateSizeInInch(sizeInInch);
		this.sizeInInch = sizeInInch;
	}
	
	// Get the weight of the vinyl record in grams.
	public int getWeightInGrams() {
		return weightInGrams;
	}
	
	/**
	 * Set the weight of the vinyl record in grams. 
	 * @param weightInGrams the weightInGrams to set
	 */
	public void setWeightInGrams(int weightInGrams) {
		validateWeightInGrams(weightInGrams);
		this.weightInGrams = weightInGrams;
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
		builder.append("|");
		builder.append(weightInGrams);
		builder.append("|");
		builder.append(sizeInInch);
		return builder.toString();
	}
}
