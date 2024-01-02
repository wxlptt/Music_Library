package user_interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import music.MusicMedia;
import net.miginfocom.swing.MigLayout;

/**
 * Library content dialog.
 * It can sort the music media and add them into list model, that can display orderly.
 * @author 		Xiaolong Wang (Wilson)
 * @version 	1.0
 */
public class libraryContentDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	/**
	 * Create library content dialog.
	 * @param musicLibrary	The music library.
	 * @param sortedFormat	The sorted format.
	 */
	public libraryContentDialog(HashMap<String, MusicMedia> musicLibrary, String sortedFormat) {
	
		
		// Set Dialog.
		setSize(450, 300);
		setLocationRelativeTo(null);
		setTitle("Library Contents");
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		// Set list area, that show all the music media in list format.
		{
			// Set scroll pane in list area.
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 0,grow");
			
			// Set list pane.
			JList<MusicMedia> list = new JList<MusicMedia>(getListModel(musicLibrary, sortedFormat));
			// List selection.
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if(!e.getValueIsAdjusting()) {
						// Select one music media.
						MusicMedia musicMedia = list.getSelectedValue();
						// Open a music dialog, and display the information.
						MusicDialog musicDialog = new MusicDialog(musicLibrary, musicMedia);
						musicDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						musicDialog.setVisible(true);
					}
				}
			});
			scrollPane.setViewportView(list);
		}
		
		// Set button pane.
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				// Set ok button, that can dispose this dialog.
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	// Sort the music media list and add them into list model. 
	public DefaultListModel<MusicMedia> getListModel(HashMap<String, MusicMedia> mediaLabrary,String listPattern){
		
		DefaultListModel<MusicMedia> model = new DefaultListModel<MusicMedia>();
		
		// Sort the music media and add them into list model.
		switch(listPattern) {
			case "by type":
				mediaLabrary.entrySet().stream()
				.map(entry -> entry.getValue())
				.sorted(Comparator.comparing(MusicMedia::getSku))
				.forEach(model::addElement);
				break;
			case "by artist":
				mediaLabrary.entrySet().stream()
				.map(entry -> entry.getValue())
				.sorted(Comparator.comparing(MusicMedia::getArtist))
				.forEach(model::addElement);
				break;
			case "by title":
				mediaLabrary.entrySet().stream()
				.map(entry -> entry.getValue())
				.sorted(Comparator.comparing(MusicMedia::getTitle))
				.forEach(model::addElement);
				break;
			case "by year":
				mediaLabrary.entrySet().stream()
				.map(entry -> entry.getValue())
				.sorted(Comparator.comparing(MusicMedia::getYear))
				.forEach(model::addElement);
				break;
			default:
				System.out.println("Nothing output");
				break;	
		}
		return model;
	}
}
