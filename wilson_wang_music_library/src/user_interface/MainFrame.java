package user_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import music.FileIO;
import music.MusicMedia;

/**
 * Mainframe
 * Menu Bar
 * File			Sort			Help
 * >			>				>
 * Save data	By Type			About
 * Exit			By Artist
 * 				By Title
 * 				By Year
 * 
 * @author 		Xiaolong Wang (Wilson)
 * @version 	1.0
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrame(HashMap<String, MusicMedia> musicLibrary) {

		// Set main frame.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane);
		setLocationRelativeTo(null);
		
		// Set menu bar start.
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Set file menu.
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);
		
		// Set save data menu item in file menu.
		JMenuItem saveDataMenuItem = new JMenuItem("Save Data");
		saveDataMenuItem.setMnemonic('S');
		saveDataMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		saveDataMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Show confirm dialog, if click yes, it will save the data and rewrite the data file.
				int result = JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to save changes?", "Save Data", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					String filePath = "src\\music\\music_data.txt";
					FileIO.writeDataIntoFile(filePath, musicLibrary);
				}
			}
		});
		fileMenu.add(saveDataMenuItem);
		
		// Set sort menu.
		JMenu sortMenu = new JMenu("Sort");
		sortMenu.setMnemonic('S');
		sortMenu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(sortMenu);
		
		// Set by type menu item in sort menu.
		JMenuItem byTypeMenuItem = new JMenuItem("By Type");
		byTypeMenuItem.setMnemonic('T');
		byTypeMenuItem.setMnemonic(KeyEvent.VK_T);
		byTypeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open new library content dialog to show the music media in order.
				libraryContentDialog dialog = new libraryContentDialog(musicLibrary, "by type");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		sortMenu.add(byTypeMenuItem);
		
		// Set by artist menu item in sort menu.
		JMenuItem byArtistMenuItem = new JMenuItem("By Artist");
		byArtistMenuItem.setMnemonic('A');
		byArtistMenuItem.setMnemonic(KeyEvent.VK_A);
		byArtistMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open new library content dialog to show the music media in order.
				libraryContentDialog dialog = new libraryContentDialog(musicLibrary, "by artist");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		sortMenu.add(byArtistMenuItem);
		
		// Set by title menu item in sort menu.
		JMenuItem byTitleMenuItem = new JMenuItem("By Title");
		byTitleMenuItem.setMnemonic('i');
		byTitleMenuItem.setMnemonic(KeyEvent.VK_I);
		byTitleMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open new library content dialog to show the music media in order.
				libraryContentDialog dialog = new libraryContentDialog(musicLibrary, "by title");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		sortMenu.add(byTitleMenuItem);
		
		// Set by year menu item in sort menu.
		JMenuItem byYearMenuItem = new JMenuItem("By Year");
		byYearMenuItem.setMnemonic('y');
		byYearMenuItem.setMnemonic(KeyEvent.VK_Y);
		byYearMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open new library content dialog to show the music media in order.
				libraryContentDialog dialog = new libraryContentDialog(musicLibrary, "by year");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		sortMenu.add(byYearMenuItem);
		
		// Set exit menu item in file menu.
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setMnemonic('x');
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_DOWN_MASK));
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(exitMenuItem);
		
		// Set help menu.
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		helpMenu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(helpMenu);
		
		// Set about menu item in help menu.
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.setMnemonic('A');
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Display information about the author and content.
				JOptionPane.showMessageDialog(MainFrame.this, "Assignment 2 by\nXiaolong Wang (Wilson)\nA01350436", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		helpMenu.add(aboutMenuItem);
		//Set menu bar end.
	}
}
