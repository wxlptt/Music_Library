package music;

/**
 * The interface for file processor.
 * It includes two methods. save and delete the audio file in the storage.
 * @author 		Wilson (Xiaolong Wang)
 * @version 	1.0
 */
public interface FileProcessor {
	void save(String filePath);
	void delete(String filePath);
}
