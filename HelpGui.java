import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.text.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class HelpGui extends JPanel  {
	
	// This will be read in from file
	private String disMessage = new String();
	private String HelpFileLocation;
	
	public HelpGui(String HelpPath){
		HelpFileLocation = new String(HelpPath);
		this.getHelpMessage(HelpPath);
		
		//disMessage = HelpPath;
	    JTextArea textArea = new JTextArea(disMessage);

	    textArea.setFont(new Font("Verdana", Font.PLAIN, 12)); 
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(300, 250));
        areaScrollPane.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Help and About"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                areaScrollPane.getBorder()));

        textArea.setEditable(false); // Do not allow editing of text
        
        //Put everything together.
        JPanel TextPane = new JPanel(new BorderLayout());
        TextPane.add(areaScrollPane,
                     BorderLayout.CENTER);
 
        add(TextPane, BorderLayout.LINE_START);

	}
	
	public String getHelpMessage(String helpFile){

		System.out.println(helpFile + "helpFile");
		File aHFile = new File(helpFile);
		System.out.println(aHFile + "aHFile");

		try {

			Scanner scanner = new Scanner(aHFile);
			while (scanner.hasNextLine()) {
				//String temp1 = new String();
				String line = scanner.nextLine();
				//temp1 = line = line + scanner.nextLine();
				//String HelpMessage = new String(line);
				this.disMessage = this.disMessage + line;
				//System.out.println(disMessage);
			}
	
		}catch (FileNotFoundException e) {
		e.printStackTrace();
		}
				
				
		return disMessage;
	}

    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Help");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new HelpGui(HelpFileLocation));
 
        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
 
}