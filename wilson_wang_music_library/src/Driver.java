import java.awt.EventQueue;
import music.FileIO;
import user_interface.MainFrame;

/**
 * Driver Class.
 * @author 		Xiaolong Wang(Wilson)
 * @version 	1.0
 */
public class Driver {
	public static void main(String[] args) {
		new Driver().drive();
	}
	
	private void drive() {
		//Start the program interface.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Connect to data file, and initiate the program data.
					FileIO fileIO = new FileIO();
					String filePath = "src\\music\\music_data.txt";
					fileIO.init(filePath);
					
					// Start Mainframe.
					MainFrame frame = new MainFrame(fileIO.getLibraryMap());
					frame.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
