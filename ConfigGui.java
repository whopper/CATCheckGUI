import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class ConfigGui{
	// Initialize script paths; taken from config file
	  String PrPath = new String(); // Printer Check script path - passed from main GUI
	  String RuPath = new String(); // Runaway Check path - passed from main GUI
		// If config exists and paths are present, initialize these values to those paths
	  
	    // Initialize all swing objects.
	  private JFrame frame = new JFrame("Configure"); //create Frame
	  private JPanel pnlNorth = new JPanel(); // Printer check path
	  private JPanel pnlCenter = new JPanel(); // Runaway check path
	  private JPanel pnlWest = new JPanel(); // Cancel button
	  private JPanel pnlSouth = new JPanel(); // Apply Button
	  
	  // Field Labels
	  private JLabel PrMessage = new JLabel("    Printer Check Script Path:", JLabel.LEADING);
	  private JLabel RuMessage = new JLabel("Runaway Check Script Path:", JLabel.LEADING);

	  // text fields for printer and runaway scripts paths
	  int TextFieldSize = 40;
	  private JTextField PrPathField = new JTextField(TextFieldSize);
	  private JTextField RuPathField = new JTextField(TextFieldSize);
	  private JButton ApplyButton = new JButton("Apply");

	  
	// Menu Items
	  private JMenuBar mb = new JMenuBar(); // Menubar
	  private JMenu mnuHelp = new JMenu("Help"); // Help Menu entry
	  private JMenuItem mnuItemAbout = new JMenuItem("About"); // About sub item
	  
	  // Help Object
	  private String HelpPath = new String(this.GetHelpPath());
	  private HelpGui ConfigHelp = new HelpGui(HelpPath);
	  

      /** Constructor for the GUI */
	  public ConfigGui(){
	 
	  // Set menubar
	  frame.setJMenuBar(mb);
	          
	  //Build Menus
	  mb.add(mnuHelp);
	  
	  // Add menu list buttons
	  mnuHelp.add(mnuItemAbout); // Create About line
	 
	  
	  // Add Buttons and text fields
	  pnlNorth.add(PrPathField);
	  pnlCenter.add(RuPathField);
	  pnlSouth.add(ApplyButton);
	  
	  // Initialize Text Labels
	  PrMessage.setLabelFor(PrPathField);
	  RuMessage.setLabelFor(RuPathField);
	         
	  // Setup Main Frame
	  frame.getContentPane().setLayout(new BorderLayout());
	  frame.getContentPane().add(pnlNorth, BorderLayout.NORTH);
	  frame.getContentPane().add(pnlCenter, BorderLayout.CENTER);
	  frame.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
	  pnlNorth.add(PrMessage);
	  pnlCenter.add(RuMessage);
	  
	  //PrMessage.setHorizontalTextPosition(SwingConstants.LEFT);
	  
	  // Initialize text fields from config file, or preset message is no paths
	  
	  // Add Listener for about button, cancel button, and apply button
	  mnuItemAbout.addActionListener(new ListenmnuHelp());
	  ApplyButton.addActionListener(new ListenApplyButton());
	 	  
	  } // end constructor
	  
	  // Listen for about button (menu)
	  public class ListenmnuHelp implements ActionListener{
		  public void actionPerformed(ActionEvent e) { // help button clicked
			  ConfigHelp.createAndShowGUI();
		  }
	  }
	  	  
	  public class ListenApplyButton implements ActionListener{
		  public void actionPerformed(ActionEvent e) { // runaway button clicked
			  // Write paths to .CATCheckGuirc, close frame
			  String NewPuPath = new String(PrPathField.getText());
			  String NewRuPath = new String(RuPathField.getText());
		
			  System.out.println(NewPuPath);
			  System.out.println(NewRuPath);
	
			  tempwait(1); DisableButtons();
			  frame.dispose();
		  }  
	  }
	  
	  // return path to proper help file
	  public String GetHelpPath(){ 
			String helpFile = new String("C:\\Users\\Billy\\Desktop\\Java\\GUIFiles\\ConfigHelp.txt"); // windows home path
			//String helpFile = new String("/u/whopper/work/Programs/GUIFILES/ConfigHelp.txt"); // linux path - PSU
			//String helpFile = new String("/home/whopper/CAT/CheckGUIFiles/ConfigHelp.txt"); // linux path - Home box


			return helpFile;
	  }
	  
	  // disable buttons while closing frame
	  public void DisableButtons(){
		  PrPathField.setEnabled(false);
		  RuPathField.setEnabled(false);
		  ApplyButton.setEnabled(false);
	  }
	  
	  // wait before closing frame
	  public void tempwait(int seconds){
	      try {
	          Thread.sleep(seconds * 1000);
	      } catch (InterruptedException e) {}
		  
	  }
	  	  
	  
	  // Display the gui frame
	  public void launchFrame(){
	     // Display Frame
	      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      frame.pack(); //Adjusts panel to components for display
	      frame.setLocationRelativeTo(null); 
	      frame.setVisible(true);
	  }

}
