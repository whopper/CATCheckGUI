import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

// Object to create a basic GUI
public class PrintCheck extends JPanel{

	// Initialize script paths; taken from config filez
  String PrPath = new String(); // Printer Check script path
	
    // Initialize all swing objects.
  private JFrame frame = new JFrame("Run Printer Check"); //create Frame
  private JPanel pnlNorth = new JPanel();
  private JPanel pnlSouth = new JPanel();
  private JPanel pnlEast = new JPanel(); // East quadrant
  private JPanel pnlWest = new JPanel(); // West quadrant
  private JPanel pnlCenter = new JPanel();
  private JPanel TextPane = this.getTextBar();

  // Buttons and text field for status display
  int infofieldsize = 30;
  private JButton EBButton = new JButton("EB Printers");
  private JButton FABButton = new JButton("FAB Printers");
  private JButton CancelButton = new JButton("Cancel");

  private JTextField MiniCheck = new JTextField(15);
  

      /** Constructor for the GUI */
  public PrintCheck(){
   
  // Add Buttons
  pnlWest.add(EBButton);
  pnlCenter.add(FABButton);
  pnlEast.add(CancelButton);
  pnlNorth.add(TextPane);
  pnlSouth.add(MiniCheck);
         
  // Setup Main Frame
  frame.getContentPane().setLayout(new BorderLayout());
  frame.getContentPane().add(pnlWest, BorderLayout.WEST);
  frame.getContentPane().add(pnlCenter, BorderLayout.CENTER);
  frame.getContentPane().add(pnlEast, BorderLayout.EAST);
  frame.getContentPane().add(pnlNorth, BorderLayout.NORTH);
  frame.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
 
  //InfoField.setText("Select which printers to send test pages to, or enter individual printer name below to test a single printer");
  MiniCheck.setText("Printer Name (Optional)");
  
  // Add listeners to each button
  EBButton.addActionListener(new ListenEBButton());
  FABButton.addActionListener(new ListenFABButton());
  CancelButton.addActionListener(new ListenCancelButton());
  
  // Add listeners to MiniCheck text field
  MiniCheck.addActionListener(new ListenMiniCheck());
     
  } // end constructor
      
 
  // Listen for EBButton signal
  public class ListenEBButton implements ActionListener{
	  public void actionPerformed(ActionEvent e) { // EB Button clicked
		  MiniCheck.setText("Sending Test Pages to EB Printers");
		  DisableButtons();
		  SuccessMsg report = new SuccessMsg(); report.displayFrame(); // show success window
		  tempwait(1); // wait n seconds before closing frame
		  frame.dispose();
	  }  
  }
  
  // Listen for FABButton signal
  public class ListenFABButton implements ActionListener{
	  public void actionPerformed(ActionEvent e) { // FAB Button Clicked
		  MiniCheck.setText("Sending Test Pages to FAB Printers");
		  DisableButtons();
		  SuccessMsg report = new SuccessMsg(); report.displayFrame(); // show success window
		  tempwait(1); // wait n seconds before closing frame
		  frame.dispose();
	  }  
  }
  
  // Listen for signal from MiniCheck text field
  public class ListenMiniCheck implements ActionListener{
	  public void actionPerformed(ActionEvent e) { // custom printer entered into queue
		  MiniCheck.setText("Sending test page to: " + MiniCheck.getText());
		  DisableButtons();
		  SuccessMsg report = new SuccessMsg(); report.displayFrame(); // show success window
		  tempwait(1); // wait n seconds before closing frame
		  frame.dispose();
	  }  
  }
  
  // Sleep for specified time
  public void tempwait(int seconds){
      try {
          Thread.sleep(seconds * 1000);
      } catch (InterruptedException e) {}
	  
  }
  
  // Disable all buttons/fields after pressed
  public void DisableButtons(){
	  EBButton.setEnabled(false);
	  FABButton.setEnabled(false);
	  CancelButton.setEnabled(false);
	  MiniCheck.setEnabled(false);
  }
  // listens for quit signal from Cancel Button
  public class ListenCancelButton implements ActionListener{
	  public void actionPerformed(ActionEvent e){
		  //System.exit(0);   
		  frame.dispose();
      }
  } 
      
  // Listens for quit signal from X

  // Get text bar for usage information
  public JPanel getTextBar(){
	  JTextArea InfoBar = new JTextArea( // this text will be read in from a file

		  		"Click the button corresponding to the printers that " +
		  		"should have test pages sent. To check an individual " +
		  		"printer, type its name in the text field below and  " +
		  		"hit Enter."
		  );

		  InfoBar.setFont(new Font("Verdana", Font.PLAIN, 12)); 
		  InfoBar.setLineWrap(true);
		  InfoBar.setWrapStyleWord(true);
		  
		  JScrollPane areaScrollPane = new JScrollPane(InfoBar);
		  areaScrollPane.setVerticalScrollBarPolicy(
		                  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		  areaScrollPane.setPreferredSize(new Dimension(400, 100));
		  areaScrollPane.setBorder(
		      BorderFactory.createCompoundBorder(
		          BorderFactory.createCompoundBorder(
		                          BorderFactory.createTitledBorder("Select Printers"),
		                          BorderFactory.createEmptyBorder(5,5,5,5)),
		          areaScrollPane.getBorder()));

		  InfoBar.setEditable(false); // Do not allow editing of text
		  
		  //Put everything together.
		  JPanel TextPane = new JPanel(new BorderLayout());
		  TextPane.add(areaScrollPane,
		               BorderLayout.CENTER);

		  add(TextPane, BorderLayout.LINE_START);
		  return TextPane;
  }
  
  // Used to alert user of successful print
  public class SuccessMsg extends JPanel {
	  private JFrame sframe = new JFrame("Complete"); //create Frame
	  private JPanel spnlNorth = new JPanel();
	  private JLabel sMessage = new JLabel("Success!");

	  public SuccessMsg(){
		  spnlNorth.add(sMessage);
		  
		  sframe.getContentPane().add(spnlNorth, BorderLayout.NORTH);

	  }
	  public void displayFrame(){
		     // Display Frame
		      sframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		      sframe.pack(); //Adjusts panel to components for display
		      sframe.setLocationRelativeTo(null); 
		      sframe.setVisible(true);
		  }
	  
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