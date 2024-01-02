package user_interface;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import music.MusicMedia;
import music_category.AudioFile;
import music_category.CompactDisc;
import music_category.VinylRecord;
import music_library.MusicLibrary;
import net.miginfocom.swing.MigLayout;

/**
 * Music Dialog, it shows the detailed information of the music media.
 * @author 		Xiaolong Wang (Wilson)
 * @version 	1.0
 */
public class MusicDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private JTextField 	skuField;
	private JTextField 	titleField;
	private JTextField 	artistField;
	private JTextField 	yearField;
	private JTextField  trackCountFieldCD;
	private JTextField  fileNameField;
	private JTextField  resolutionField;
	private JTextField  trackCountFieldVR;
	private JTextField  sizeField;
	private JTextField  weightField;

	private JLabel skuLabel;
	private JLabel titleLabel;
	private JLabel artistLabel;
	private JLabel yearLabel;
	private JLabel trackCountLabelCD;
	private JLabel fileNameLabel;
	private JLabel resolutionLabel;
	private JLabel trackCountLabelVR;
	private JLabel sizeLabel;
	private JLabel weightLabel;
	
	/**
	 * Create music dialog.
	 * @param musicLibrary  The music library.
	 * @param musicMedia	The music media.
	 */
	public MusicDialog(HashMap<String, MusicMedia> musicLibrary, MusicMedia musicMedia) {
	
		// Set dialog start
		setSize(450,300);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][341.00]", "[][]"));
		
		// Set Id label and field.
		skuLabel = new JLabel("SU");
		contentPanel.add(skuLabel, "cell 1 1,alignx right, aligny center");
		skuField = new JTextField(String.valueOf(musicMedia.getSku()));
		// If set the sku field uneditable, uncomment below.
//		idField.setEditable(false);
		contentPanel.add(skuField, "cell 2 1,growx");

		// Set first name label and field.
		titleLabel = new JLabel("Title");
		contentPanel.add(titleLabel, "cell 1 2,alignx right, aligny center");
		titleField = new JTextField(musicMedia.getTitle());
		contentPanel.add(titleField, "cell 2 2,growx");
		
		// Set last name label and field.
		artistLabel = new JLabel("Artist");
		contentPanel.add(artistLabel, "cell 1 3,alignx right, aligny center");
		artistField = new JTextField(musicMedia.getArtist());
		contentPanel.add(artistField, "cell 2 3,growx");
		
		// Set street label and field.
		yearLabel = new JLabel("Year");
		contentPanel.add(yearLabel, "cell 1 4,alignx right, aligny center");
		yearField = new JTextField(String.valueOf(musicMedia.getYear()));
		contentPanel.add(yearField, "cell 2 4,growx");
		// set dialog end.
		
		// Depending on the class type, to set the title and the field in different format.
		switch(musicMedia.getClass().getSimpleName()) {
			case "CompactDisc":
				setTitle("CompactDisc");
				setCompactDiscLabelField(musicMedia);
				break;
			case "AudioFile":
				setTitle("AudioFile");
				setAudioFileLabelField(musicMedia);
				break;
			case "VinylRecord":
				setTitle("VinylRecord");
				setVinylRecordLabelField(musicMedia);
				break;
			default:
				setTitle("Unknown Type");
				break;
		}
	
		// Set button pane.
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			// Set clear button
			JButton clearButton = new JButton("Clear");
			clearButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Clear the text field.
					skuField.setText("");
					titleField.setText("");
					artistField.setText("");
					yearField.setText("");
					// Depending on different class type, clear different text fields.
					switch(musicMedia.getClass().getSimpleName()) {
						case "CompactDisc":
							trackCountFieldCD.setText("");
							break;
						case "AudioFile":
							fileNameField.setText("");
							resolutionField.setText("");
							break;
						case "VinylRecord":
							trackCountFieldVR.setText("");
							sizeField.setText("");
							weightField.setText("");
							break;
						default:
							setTitle("Unknown Type");
							break;
					}
				}
				
			});
			
			// Set button pane to MigLayout.
			buttonPane.setLayout(new MigLayout("", "[57px][57px][63px][65px][][][][][][]", "[23px]"));
			clearButton.setActionCommand("CLEAR");
			buttonPane.add(clearButton, "cell 0 0,alignx left,aligny top");
			
			// Set save button.
			JButton saveButton = new JButton("Save");
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Modify music media information, if it does not exist, it will create new one.
					modifyMusic(musicLibrary, musicMedia);
				}
			});
			saveButton.setActionCommand("SAVE");
			buttonPane.add(saveButton, "cell 7 0,alignx left,aligny top");
			
			// Set delete button.
			JButton deleteButton = new JButton("Delete");
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Show confirm dialog, if click yes, it will delete the music media.
					int result = JOptionPane.showConfirmDialog(MusicDialog.this, "Are you sure you want to delete this selection?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						musicLibrary.remove(musicMedia.getSku());
					}
				}
			});
			deleteButton.setActionCommand("DELETE");
			buttonPane.add(deleteButton, "cell 8 0,alignx left,aligny top");
			
			// Set cancel button.
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setActionCommand("CANCEL");
			buttonPane.add(cancelButton, "cell 9 0,alignx left,aligny top");
			
			// The default button is cancel button.
			getRootPane().setDefaultButton(cancelButton);
		}
	}
	
	/**
	 * If the music media type is CompactDisc, set the corresponding label and field.
	 * @param musicMedia the music media.
	 */
	private void setCompactDiscLabelField(MusicMedia musicMedia) {
		// Set track count label.
		trackCountLabelCD = new JLabel("Track Count");
		contentPanel.add(trackCountLabelCD, "cell 1 5,alignx right, aligny center");
		// Set track count field, it displays the current music media information.
		CompactDisc castMusicMedia = (CompactDisc)musicMedia;
		trackCountFieldCD = new JTextField(String.valueOf(castMusicMedia.getNumberOfTracks()));
		contentPanel.add(trackCountFieldCD, "cell 2 5,growx");
	}
	
	/**
	 * If the music media type is AudioFile, set the corresponding label and field.
	 * @param musicMedia the music media.
	 */
	private void setAudioFileLabelField(MusicMedia musicMedia) {
		AudioFile castMusicMedia = (AudioFile)musicMedia;
		// Set file name label.
		fileNameLabel = new JLabel("File Name");
		contentPanel.add(fileNameLabel, "cell 1 5,alignx right, aligny center");
		// Set file name field, it displays the current music media information.
		fileNameField = new JTextField(castMusicMedia.getFileName());
		contentPanel.add(fileNameField, "cell 2 5,growx");
		// Set resolution label.
		resolutionLabel = new JLabel("Resolution");
		contentPanel.add(resolutionLabel, "cell 1 6,alignx right, aligny center");
		// Set resolution field, it displays the current music media information.
		resolutionField = new JTextField(String.valueOf(castMusicMedia.getFileResolution()));
		contentPanel.add(resolutionField, "cell 2 6,growx");
	}
	
	/**
	 * If the music media type is VinylRecord, set the corresponding label and field.
	 * @param musicMedia the music media.
	 */
	private void setVinylRecordLabelField(MusicMedia musicMedia) {
		VinylRecord castMusicMedia = (VinylRecord)musicMedia;
		// Set track count label.
		trackCountLabelVR = new JLabel("Track Count");
		contentPanel.add(trackCountLabelVR, "cell 1 5,alignx right, aligny center");
		// Set track count field, it displays the current music media information.
		trackCountFieldVR = new JTextField(String.valueOf(castMusicMedia.getNumberOfTracks()));
		contentPanel.add(trackCountFieldVR, "cell 2 5,growx");
		// Set size label.
		sizeLabel = new JLabel("Size in Inch");
		contentPanel.add(sizeLabel, "cell 1 6,alignx right, aligny center");
		// Set size field, it displays the current music media information.
		sizeField = new JTextField(String.valueOf(castMusicMedia.getSizeInInch()));
		contentPanel.add(sizeField, "cell 2 6,growx");
		// Set weight label.
		weightLabel = new JLabel("Weight in Grams");
		contentPanel.add(weightLabel, "cell 1 7,alignx right, aligny center");
		// Set weight field, it displays the current music media information.
		weightField = new JTextField(String.valueOf(castMusicMedia.getWeightInGrams()));
		contentPanel.add(weightField, "cell 2 7,growx");
	}
	
	/**
	 * This method uses for modifying the information of music media.
	 * If the sku number is not exist, it will create new music media and add it into music library.
	 * @param musicLibrary	The music library.
	 * @param musicMedia	The music media.
	 */
	private void modifyMusic(HashMap<String, MusicMedia> musicLibrary, MusicMedia musicMedia) {
		// Modify the music media information.
		if(skuField.getText().equals(musicMedia.getSku())) {
			musicMedia.setTitle(titleField.getText());
			musicMedia.setArtist(artistField.getText());
			musicMedia.setYear(Integer.parseInt(yearField.getText()));
			// Depending on the class type, modify the different field.
			switch(musicMedia.getClass().getSimpleName()) {
				case "CompactDisc":
					CompactDisc castCD = (CompactDisc)musicMedia;
					castCD.setNumberOfTracks(Integer.parseInt(trackCountFieldCD.getText()));
					break;
				case "AudioFile":
					AudioFile castAF = (AudioFile)musicMedia;
					castAF.setFileName(fileNameField.getText());
					castAF.setFileResolution(Integer.parseInt(resolutionField.getText()));
					break;
				case "VinylRecord":
					VinylRecord castVR = (VinylRecord)musicMedia;
					castVR.setNumberOfTracks(Integer.parseInt(trackCountFieldVR.getText()));
					castVR.setSizeInInch(Integer.parseInt(sizeField.getText()));
					castVR.setWeightInGrams(Integer.parseInt(weightField.getText()));
					break;
				default:
					setTitle("Unknown Type");
					break;
			}
		}
		else {
			// If the sku number is not exist, it will create new music media, and add it into music library.
			try {
				// Depending on information, instance it in different type and add then into music library.
				if(skuField.getText(0, 2).equalsIgnoreCase(MusicLibrary.TYPE_AUDIO_FILE)) {
					musicLibrary.put(skuField.getText(), new AudioFile(skuField.getText(),
											titleField.getText(),
											artistField.getText(),
											Integer.parseInt(yearField.getText()),
											fileNameField.getText(),
											Integer.parseInt(resolutionField.getText())));
				}
				else if(skuField.getText(0, 2).equalsIgnoreCase(MusicLibrary.TYPE_COMPACT_DISC)) {
					musicLibrary.put(skuField.getText(), new CompactDisc(skuField.getText(),
							titleField.getText(),
							artistField.getText(),
							Integer.parseInt(yearField.getText()),
							Integer.parseInt(trackCountFieldCD.getText())));
				}
				else if(skuField.getText(0, 2).equalsIgnoreCase(MusicLibrary.TYPE_VINYL_RECORD)) {
					musicLibrary.put(skuField.getText(), new VinylRecord(skuField.getText(),
							titleField.getText(),
							artistField.getText(),
							Integer.parseInt(yearField.getText()),
							Integer.parseInt(trackCountFieldVR.getText()),
							Integer.parseInt(weightField.getText()),
							Integer.parseInt(sizeField.getText())));
				}
				else {
					System.out.println("Wrong value.");
				}
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}
}
		