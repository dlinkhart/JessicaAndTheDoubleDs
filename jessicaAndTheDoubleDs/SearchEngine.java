/*Created by Jessica Stepp, Douglas Shirilla,
 * Douglas Linkhart and Brandon Quijano
 * JavaII 2017 project Search Engine:
 * creating a GUI file search
 * that includes file upload and delete tools.
 */
package jessicaAndTheDoubleDs;// Team name

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SearchEngine extends JPanel{	
	public SearchEngine(){
		//used to set tabs to top left
		super (new GridLayout(1,1));
		
		//Build tabs pane
		JTabbedPane tabbedPane = new JTabbedPane();
		
		//add filler component
		JComponent searchPanel = textPanel("Search Panel");
		//add tab and associated filler component
		tabbedPane.addTab("Search", searchPanel);
		
		//add filler component
		JComponent files = textPanel("File upload/update panel");
		//add tab and associated filler component
		tabbedPane.addTab("Files", files);
		
		//add filler component
		JComponent aboutPanel = textPanel("About");
		//add tab and associated filler component
		tabbedPane.addTab("About", aboutPanel);
		
		add(tabbedPane);
		
	}//end SearchEngine()
	
	/*this is just a filler component
	 * it should be a text box for 
	 * the user to type into
	 */
	protected JComponent textPanel(String text){
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.add(filler);
		return panel;
	}//end filler componenet
	
	//build main container
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Search Engine");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add tabbed pane to main container
		frame.add(new SearchEngine(), BorderLayout.CENTER);
		
		
		frame.setSize(500,500);
		frame.setVisible(true);
	}//end creatAndShowGUI()

	public static void main(String[] args) {
		
		
		createAndShowGUI();
		
	}//end main

}//end class SearchEngine
