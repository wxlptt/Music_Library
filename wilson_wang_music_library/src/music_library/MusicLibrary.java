package music_library;

import java.util.HashMap;

import music.MusicMedia;
import music_category.AudioFile;
import music_category.CompactDisc;
import music_category.VinylRecord;

/**
 * This is class MusicLibrary. 
 * It includes a hash map which can store the musics and filter them to display for users.
 * @author 		Xiaolong Wang (Wilson)
 * @version 	1.0
 */
public class MusicLibrary {
	
	private HashMap<String, MusicMedia> library = new HashMap<>();
	
	public static final String TYPE_AUDIO_FILE   = "af";
	public static final String TYPE_COMPACT_DISC = "cd";
	public static final String TYPE_VINYL_RECORD = "vr";

	public MusicLibrary() {
		super();
	}
	
	/**
	 * The method add the music into the music library hash map.
	 * @param selection The MusicMedia instance.
	 */
	public void addMusic(MusicMedia selection) {
		validateMusic(selection);
		library.put(selection.getSku(), selection);
	}
	
	/**
	 * Uncompleted Method.
	 * Unused Method.
	 * Display all the music in the library by the nested method toString().
	 */
	public void displayLibrary() {
		System.out.println(library.toString());
	}
	
	/**
	 * Display the music which the music's type is specified.
	 * Options are “af”, “cd”, “vr”.
	 * @param prefix
	 */
	public void displayChoice(String prefix) {
		
		validateChoice(prefix);
	
		for (String key: this.library.keySet()) {
			if (prefix.equalsIgnoreCase(key.substring(0, 2))) {
				System.out.println(library.get(key).toString());
			}	
		}
	}
	
	/**
	 * Validate the choice string.
	 * @param prefix the prefix music name.
	 */
	private void validateChoice(String prefix) {
		if (!prefix.equalsIgnoreCase(TYPE_AUDIO_FILE)) {
			if (!prefix.equalsIgnoreCase(TYPE_COMPACT_DISC)) {
				if (!prefix.equalsIgnoreCase(TYPE_VINYL_RECORD)) {
					throw new IllegalArgumentException("Invalid Music Type");
				}
			}
		}
	}
	
	/**
	 * Validate the music media input, it can not be null.
	 * @param selection The music media.
	 */
	private void validateMusic(MusicMedia selection) {
		if (selection == null) {
			throw new IllegalArgumentException("Invalid Music entered.");
		}
	}

	/**
	 * parse a string array into a music media, then add it into music library.
	 * It uses for handling different input data.
	 * @param musicMediaElements A array, it includes variety of media elements.
	 */
	public void parseListIntoMusicLibrary(String musicMediaElements[]) {
		
		// Get the two letter in the first elements, which stores the media type.
		String musicType = musicMediaElements[0].substring(0, 2);
		
		// Instance the music media and add it into music library.
		switch(musicType) {
			case MusicLibrary.TYPE_AUDIO_FILE:
				addMusic(new AudioFile(musicMediaElements[0], 
									   musicMediaElements[1], 
									   musicMediaElements[2], 
									   Integer.parseInt(musicMediaElements[3]),
									   musicMediaElements[4],
									   Integer.parseInt(musicMediaElements[5])));
				break;
			case MusicLibrary.TYPE_COMPACT_DISC:
				addMusic(new CompactDisc(musicMediaElements[0],
										 musicMediaElements[1],
										 musicMediaElements[2],
										 Integer.parseInt(musicMediaElements[3]),
										 Integer.parseInt(musicMediaElements[4])));
				break;
			case MusicLibrary.TYPE_VINYL_RECORD:
				addMusic(new VinylRecord(musicMediaElements[0],
										 musicMediaElements[1],
										 musicMediaElements[2],
										 Integer.parseInt(musicMediaElements[3]),
										 Integer.parseInt(musicMediaElements[4]),
										 Integer.parseInt(musicMediaElements[6]),
										 Integer.parseInt(musicMediaElements[5])));
				break;
			default:
				System.out.println("Invalid Value");
				break;
		}
	}

	/**
	 * Get the library.
	 * @return the library
	 */
	public HashMap<String, MusicMedia> getLibrary() {
		return library;
	}
}
