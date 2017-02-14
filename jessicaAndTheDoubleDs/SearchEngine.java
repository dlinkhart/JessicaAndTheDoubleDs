/*Created by Jessica Stepp, Douglas Shirilla,
 * Douglas Linkhart, and Brandon Quijano
 * Java II 2017 Project 3 - Search Engine:
 * creating a GUI file search
 * that includes file upload and delete tools.
 */
package jessicaAndTheDoubleDs; // Team name

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchEngine extends JPanel implements ActionListener{	
	
	//Eclipse whines if this line isn't here...
	private static final long serialVersionUID = 1L;
	
	// Initialize radio button status
	Boolean orBtnSelected     = true,
			andBtnSelected    = false,
			phraseBtnSelected = false;
	
	JTextField txtSearchTerms = new JTextField( "Enter search terms here", 40 );
	JTextArea txtResults = new JTextArea(22, 40);
	
	StringBuilder sbStringToParse = new StringBuilder();
	
	public SearchEngine(){
		//used to set tabs to top left
		super (new GridLayout(1,1));
		
		//Build tabs pane
		JTabbedPane tabbedPane = new JTabbedPane();
		
		// Add Search panel
		JComponent searchPanel = textPanel( "" );
		//add tab and associated filler component
		tabbedPane.addTab("Search", searchPanel);
		
		//Create text area for user to enter text to search for
//		JTextField txtSearchTerms = new JTextField( "Enter search terms here", 40 );
		//enable text wrap to prevent unwanted growth of text area
		txtSearchTerms.setBorder(BorderFactory.createLineBorder(Color.black));
		searchPanel.add(txtSearchTerms);
		
		// Create buttons
		JButton btnSearch = new JButton( "Search" );
			
        /*To use absolute layout, use this code:
 		searchPanel.setLayout(null);
		btnSearch.setBounds(50, 50, 100, 100); 
		This also kills the initial panel text,
		but it could be put back with a label */ 
		
		// add a cooment here
	    btnSearch.setActionCommand( "search" ); 
	    btnSearch.addActionListener( this );
	    
	    JRadioButton btnOr     = new JRadioButton( "OR" );
        JRadioButton btnAnd    = new JRadioButton( "AND" );
        JRadioButton btnPhrase = new JRadioButton( "Phrase" );
        
	    btnOr.setActionCommand( "or" ); 
	    btnOr.addActionListener( this );
	    btnAnd.setActionCommand( "and" ); 
	    btnAnd.addActionListener( this );
	    btnPhrase.setActionCommand( "phrase" ); 
	    btnPhrase.addActionListener( this );
 
        // Group the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add( btnOr );
        group.add( btnAnd );
        group.add( btnPhrase ); 
 
        // Add buttons to panel
		searchPanel.add( btnSearch );
		searchPanel.add( btnOr );
		searchPanel.add( btnAnd );
		searchPanel.add( btnPhrase );
		
		//Add result text area to show matched files when search is completed
//		JTextArea txtResults = new JTextArea(22, 40);
		txtResults.setLineWrap(true);
		txtResults.setWrapStyleWord(true);
		txtResults.setBorder(BorderFactory.createLineBorder(Color.black));
		searchPanel.add(txtResults);
		
		// Set buttons to according to status that was initialized previously
		btnOr.setSelected( orBtnSelected );
		btnAnd.setSelected( andBtnSelected );
		btnPhrase.setSelected( phraseBtnSelected );
		
		//add filler component
		JComponent files = textPanel("File upload/update panel");
		//add tab and associated filler component
		tabbedPane.addTab("Files", files);
		
		// Build string for About tab using HTML
		StringBuilder sbAbout = new StringBuilder();
		sbAbout.append( "<html>" );
		sbAbout.append( "<font face='Times New Roman'>" );
		sbAbout.append( "<font size='+1'>");
		sbAbout.append( "<br> Search Engine 1.0 <br>");
		sbAbout.append( "<br> COP 2805  Project 3 <br>");
		sbAbout.append( "<font size='-1'>");
		sbAbout.append( "<br> by Jessica and the Double Ds: <br>");
		sbAbout.append( "<i> Douglas Linkhart <br>");
		sbAbout.append( "Brandon Quijano <br>");
		sbAbout.append( "Douglas Shirilla <br>");
		sbAbout.append( "Jessica Stepp <br> </i> </font>");
		sbAbout.append( "<font face='Arial'>" );
		sbAbout.append( "<font size='-1'>");
		sbAbout.append( "<br> Accompanying index data file: &nbsp; TBD");
		sbAbout.append( "</font>");
		sbAbout.append( "</html>" );
		
		// Instantiate About panel and add string
		JComponent aboutPanel = textPanel(sbAbout.toString());
	
		// Add tab and About panel
		tabbedPane.addTab("About", aboutPanel);
		
		add(tabbedPane);
		
	}//end SearchEngine()
	
	// Event handler
	public void actionPerformed(ActionEvent e) 
	{
		String nextLexeme = "";
		
		if (e.getActionCommand().equals("search")) 
		{
			JOptionPane.showMessageDialog( 
	  		null, 
	  		"You clicked the Search button...", 
	  		"SEARCH!!!", 
	  		JOptionPane.INFORMATION_MESSAGE );
			
			sbStringToParse.append( txtSearchTerms.getText() );
			
			StringBuilder sbResults = new StringBuilder();
			sbResults.append( "No results found. \r\n \r\n" );
			sbResults.append( "You searched for:\r\n \r\n" );
		//	sbResults.append( txtSearchTerms.getText() );
			
			
			while ( sbStringToParse.length() > 0 )
					{
				
	//			sbResults.append("lex before = x." + nextLexeme + ".x \r\n");
			//	sbResults.append( "x." + sbStringToParse.toString() + ".x" + "\r\n" );
				nextLexeme = getNextLexeme();
				
				if ( orBtnSelected  )
				{
					sbResults.append( nextLexeme );
					if ( sbStringToParse.length() > 0 )
					sbResults.append( " OR " );
				}
	//			sbResults.append("lex after = x." + nextLexeme + ".x \r\n");
		//		sbResults.append( "x." + sbStringToParse.toString() + ".x" + "\r\n" );
				
				if ( andBtnSelected  )
				{
					sbResults.append( nextLexeme );
					if ( sbStringToParse.length() > 0 )
					sbResults.append( " AND " );
				}
				
				if ( phraseBtnSelected  )
				{
					sbResults.append( nextLexeme + " " );
					if ( sbStringToParse.length() <= 0 )
					sbResults.append( "(PHRASE; terms in this order) " );
				}
			}
			
		//	sbResults.append("x.");
			

							
			txtResults.setText( sbResults.toString() );
			
			if ( orBtnSelected )
			{
				JOptionPane.showMessageDialog( 
				null, 
				"...and when you clicked it, OR was selected!", 
				"SEARCH!!!", 
				JOptionPane.INFORMATION_MESSAGE );
			}
			else if ( andBtnSelected )
			{
				JOptionPane.showMessageDialog( 
				null, 
				"...and when you clicked it, AND was selected!", 
				"SEARCH!!!", 
				JOptionPane.INFORMATION_MESSAGE );
			}
			else if ( phraseBtnSelected )
			{
				JOptionPane.showMessageDialog( 
				null, 
				"...and when you clicked it, PHRASE was selected!", 
				"SEARCH!!!", 
				JOptionPane.INFORMATION_MESSAGE );
			}
		}
		else if (e.getActionCommand().equals("or"))
		{
			orBtnSelected     = true;
			andBtnSelected    = false;
			phraseBtnSelected = false;
		}
		else if (e.getActionCommand().equals("and"))
		{
			orBtnSelected     = false;
			andBtnSelected    = true;
			phraseBtnSelected = false;
		}
		else if (e.getActionCommand().equals("phrase"))
		{
			orBtnSelected     = false;
			andBtnSelected    = false;
			phraseBtnSelected = true;
		}
	} // actionPerformed
	
	/*this is just a filler component
	 * it should be a text box for 
	 * the user to type into
	 */
	/*With the text box created, is this comment still necessary?
	 * 
	 */
	
	public String getNextLexeme()
	{
		int i;
		String lexeme;
		
		for ( i = 0; i < sbStringToParse.length(); i++ )
			if ( sbStringToParse.substring(i, i + 1).equals( " " ) )
				break;
				
		lexeme = sbStringToParse.substring(0, i);
		sbStringToParse.delete( 0, i + 1);

		return lexeme;
	} // getNextLexeme
	
	
	protected JComponent textPanel(String text){
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.add(filler);
		return panel; 
	} //end filler component
	
	//build main container
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Search Engine");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add tabbed pane to main container
		frame.add(new SearchEngine(), BorderLayout.CENTER);
		
		
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}//end creatAndShowGUI()

	public static void main(String[] args) {
		createAndShowGUI();
	}//end main

}//end class SearchEngine
