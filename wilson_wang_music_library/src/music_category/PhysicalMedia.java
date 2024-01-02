package music_category;

import music.MusicMedia;

/**
 * Abstract class PhysicalMedia, that extends MusicMedia.
 * It includes two subclass, CompactDisc and VinylRecord.
 * @author 		Xiaolong Wang (Wilson)
 * @version 	1.0
 */
public abstract class PhysicalMedia extends MusicMedia{
	
	public PhysicalMedia() {
		super();
	}

	/**
	 * Constructor
	 * @param sku		The series code for representing the music.
	 * @param title		The title of the music.
	 * @param artist	The artist of the music.
	 * @param year		The published year.
	 */
	public PhysicalMedia(String sku, String title, String artist, int year) {
		super(sku, title, artist, year);
	}
}
