import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Object to create a basic GUI
public class BasicGui{

	// Initialize script paths; taken from config file
  String PrPath = new String(); // Printer Check script path
  String RuPath = new String(); // Runaway Check path
	
    // Initialize all swing objects.
  private JFrame frame = new JFrame("Runaway and Printercheck GUI"); //create Frame
  private JPanel pnlNorth = new JPanel();
  private JPanel pnlEast = new JPanel(); // East quadrant
  private JPanel pnlWest = new JPanel(); // West quadrant

  // Buttons and text field for status display
  int tfieldsize = 30, statfsize = 30;
  private JButton PrintCButton = new JButton("Printer Check");
  private JButton RunawayButton = new JButton("Runaway Process Check");
  private JTextField StatusField = new JTextField(statfsize);
  
// Menu Items
  private JMenuBar mb = new JMenuBar(); // Menubar
  private JMenu mnuFile = new JMenu("File"); // File Entry on Menu bar
  private JMenu mnuSettings = new JMenu("Settings"); // Settings entry in menu bar
  private JMenu mnuHelp = new JMenu("Help"); // Help Menu entry

  private JMenuItem mnuItemPrinter = new JMenuItem("Printer Check"); // Printer Check sub item
  private JMenuItem mnuItemRunaway = new JMenuItem("Runaway Check"); // Runaway Check sub item
  private JMenuItem mnuItemConfig = new JMenuItem("Configure"); // Configure sub item
  private JMenuItem mnuItemQuit = new JMenuItem("Quit"); // Quit sub item
  private JMenuItem mnuItemAbout = new JMenuItem("About"); // About sub item
  
  private String HelpPath;
  
      /** Constructor for the GUI */
  public BasicGui(){
 
  // Set menubar
  frame.setJMenuBar(mb);
          
  //Build Menus
  mb.add(mnuFile);        // Add Menu items to form
  mb.add(mnuSettings);
  mb.add(mnuHelp);
  // Add menu list buttons
  mnuFile.add(mnuItemPrinter); // create printer check line
  mnuFile.add(mnuItemRunaway); // create runaway check guide
  mnuFile.add(mnuItemQuit);  // Create Quit line
  mnuSettings.add(mnuItemConfig); // create configure item
  mnuHelp.add(mnuItemAbout); // Create About line
 
  
  // Add Buttons
  pnlEast.add(RunawayButton);
  pnlWest.add(PrintCButton);
  pnlNorth.add(StatusField);
         
  // Setup Main Frame
  frame.getContentPane().setLayout(new BorderLayout());
  frame.getContentPane().add(pnlEast, BorderLayout.EAST);
  frame.getContentPane().add(pnlWest, BorderLayout.WEST);
  frame.getContentPane().add(pnlNorth, BorderLayout.NORTH);
  StatusField.setEditable(false); // Do not allow editing of text

          
  // Initialize Text Bar
  StatusField.setText("Click a button to begin!");
  
  // Add Listener for about button
  mnuItemAbout.addActionListener(new ListenmnuHelp());
  mnuItemConfig.addActionListener(new ListenmnuConfig());
  mnuItemPrinter.addActionListener(new ListenPrintC());
  mnuItemRunaway.addActionListener(new ListenRunawayC());
  
  // Add listeners to printcheck and runawaycheck buttons
  PrintCButton.addActionListener(new ListenPrintC());
  RunawayButton.addActionListener(new ListenRunawayC());
  
  // Allows the App to be closed
  frame.addWindowListener(new ListenCloseWdw());         
  mnuItemQuit.addActionListener(new ListenMenuQuit());
  
  HelpPath = new String(this.getHelpPath());
  
  } // end constructor
      
  // Listen for about button
  public class ListenmnuHelp implements ActionListener{
	  public void actionPerformed(ActionEvent e) { // help button clicked
		  // Launch Help GUI
		  HelpGui help = new HelpGui(HelpPath);
		  help.createAndShowGUI();
	  }
  }
  
  // Listen for config button
  public class ListenmnuConfig implements ActionListener{
	  public void actionPerformed(ActionEvent e) { // help button clicked
		  // Launch config GUI
		  ConfigGui config = new ConfigGui();
		  config.launchFrame();
	  }
  }
  // Listen for PrintCheck signal
  public class ListenPrintC implements ActionListener{
	  public void actionPerformed(ActionEvent e) { // print button clicked
		  StatusField.setText("Printer Check Running");
		  // launch PrinterCheck GUI
		  PrintCheck PCheck = new PrintCheck();
		  PCheck.launchFrame();
	  }  
  }
  
  // Listen for RunawayCheck signal
  public class ListenRunawayC implements ActionListener{
	  public void actionPerformed(ActionEvent e) { // runaway button clicked
		  StatusField.setText("Runaway Check Running");
		  // Launch RunawayCheck GUI
	  }  
  }
  
  // listens for quit signal from menu
  public class ListenMenuQuit implements ActionListener{
	  public void actionPerformed(ActionEvent e){
		  System.exit(0);         
      }
  } // end ListenMenuQuit
      
  // Listens for quit signal from X
  public class ListenCloseWdw extends WindowAdapter{
      public void windowClosing(WindowEvent e){
          System.exit(0);         
      }
  }
      
  // get help path (point to file)
  public String getHelpPath(){
		String helpFile = new String("C:\\Users\\Will\\Desktop\\Java\\GUIFiles\\MainHelp.txt"); // windows home path
		//String helpFile = new String("/u/whopper/work/Programs/GUIFILES/MainHelp.txt"); // PSU linux path
		//String helpFile = new String("/home/whopper/CAT/CheckGUIFiles/MainHelp.txt"); // home box path

		return helpFile;
		
  }
  
  // Display the gui frame
  public void launchFrame(){
     // Display Frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack(); //Adjusts panel to components for display
      frame.setLocationRelativeTo(null); 
      frame.setVisible(true);
  }
      
  public static void main(String args[]){
      BasicGui gui = new BasicGui();
      gui.launchFrame();
 }
}
