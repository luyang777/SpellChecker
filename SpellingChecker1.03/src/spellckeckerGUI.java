/**
 * Spelling checker using Bloom filter algorithm MD5 functionality with SWING GUI.
 *  Designed by Michael Ekubay Negassi (002238528) and Lu YANG (002238352).
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JScrollPane;
//Main graphical interface class for Spelling checker

public class spellckeckerGUI {

	public JFrame frmSpellingChecker;
	public JTextArea InputTA;
	public static JTextArea SpelligCheckerTA;
    public JButton btnCheckSpelling;
    public JButton btnBrowse;
    public JButton btnLoadDictionary;
    public JScrollPane scroll;
    public static String Path;
    public File NameDir, NamePath;
    private JScrollPane InputSP;
    private JScrollPane SpelligCheckerSP;
    public String filename=null;
    public String [] List ;
    public SpellingChecker checker;
    public CreateDictionary Gen;
  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					spellckeckerGUI window = new spellckeckerGUI();
					window.frmSpellingChecker.setVisible(true);
								    	
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public spellckeckerGUI() {
		initialize();
		Config properties = new Config();
   	    try {
			Path = properties.getPropValues("Path");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR");
		}
   	 
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		//Dimension screenSize;
		
	
		//Absolute frame
		frmSpellingChecker = new JFrame();
		frmSpellingChecker.setTitle("Spelling Checker");
		// screenSize = new Dimension(500, 400);
		frmSpellingChecker.setIconImage(Toolkit.getDefaultToolkit().getImage("c:/Users/mikya/eclipse-workspace/SpellingChecker1.03/src/bu.png"));
		frmSpellingChecker.setResizable(true);
		frmSpellingChecker.setBounds(10, 10, 1120, 540);
		frmSpellingChecker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		 /*Dimension screenSize = new Dimension(2300, 1800);
		 frmSpellingChecker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frmSpellingChecker.setResizable(true);
		 frmSpellingChecker.setPreferredSize(screenSize);
		 frmSpellingChecker.setLocationRelativeTo(null);		  
		 frmSpellingChecker.pack();
		 frmSpellingChecker.setVisible(true);*/
		 
		//browse input button
		btnBrowse = new JButton("Browse Input");
		btnBrowse.setBounds(36, 50, 150, 28);
		btnBrowse.setForeground(new Color(128, 0, 128));
		btnBrowse.setToolTipText("Browse Input file or Type in the Text area");
		btnBrowse.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 18));
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filename=null;
				//JOptionPane.showMessageDialog(null,"Please select the file or Type in Text area below");
				JFileChooser chooser= new JFileChooser();
				File F = new File(Path);	
				InputTA.setText(null);
				SpelligCheckerTA.setText(null);
				int Checker;
				chooser.setCurrentDirectory(F);
				Checker = chooser.showOpenDialog(null);
				if(Checker == JFileChooser.APPROVE_OPTION){
				    NameDir = chooser.getCurrentDirectory();
				    NamePath = chooser.getSelectedFile();
				    filename=NamePath.toString();
				    }
				else{
				    JOptionPane.showMessageDialog(null, " Your have clicked Cancel ");
					}
			  
				try {
					FileReader reader=new FileReader(NamePath);
					BufferedReader buffer=new BufferedReader(reader);				
					InputTA.read(buffer,null);
					reader.close();
					buffer.close();
					InputTA.requestFocus();
					NamePath=null;
					
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, " You didn't select an input file ");	
					  
				}
			}
		});
		frmSpellingChecker.getContentPane().setLayout(null);
		frmSpellingChecker.getContentPane().add(btnBrowse);
		//End of Browse button
		
		//Load Dictionary
		btnLoadDictionary = new JButton("Generate Dictionary");
		btnLoadDictionary.setBounds(800, 10, 200, 28);
		btnLoadDictionary.setForeground(new Color(128, 0, 128));
		btnLoadDictionary.setToolTipText("Correct your Spelling errors");
		btnLoadDictionary.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 18));
		btnLoadDictionary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					filename=null;
				
					 Gen = new CreateDictionary(Path);
					
					JOptionPane.showMessageDialog(null, " Your Data Dictionary is Generated! ");	
					
					InputTA.setText(null);
					SpelligCheckerTA.setText(null);
					
				}
				catch(Exception e){
					  JOptionPane.showMessageDialog(null, " Your file is empty  ");				
				}
				
			}
		});
		frmSpellingChecker.getContentPane().setLayout(null);
		frmSpellingChecker.getContentPane().add(btnLoadDictionary);
		//End of Load button
		
		//Check spelling button
		btnCheckSpelling = new JButton("Check Spelling");
		btnCheckSpelling.setBounds(557, 50, 160, 28);
		
		btnCheckSpelling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File CheckDirctionary = new File(Path +"dictionary.txt");
			     boolean exists = CheckDirctionary.exists();
			     if(!exists)
			     {
			    	 JOptionPane.showMessageDialog(null, " Please Generate the data dictionary !");
			    	 filename=null;
			     }
			     if(exists) {
				try {
				
			        checker = new SpellingChecker(Path,List,filename);
			    	filename=null;
			    	JOptionPane.showMessageDialog(null, " You spelling mistakes are corrected successfully!");
			    				    
				}
				catch(Exception e){
					  JOptionPane.showMessageDialog(null, " Please Select a file");				
				}
			     }
			}
		});
		btnCheckSpelling.setForeground(new Color(128, 0, 128));
		btnCheckSpelling.setToolTipText("Correct your Spelling errors");
		btnCheckSpelling.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 18));
		frmSpellingChecker.getContentPane().add(btnCheckSpelling);
	    
		SpelligCheckerSP = new JScrollPane();
	    SpelligCheckerSP.setBounds(557, 95, 478, 367);
	    frmSpellingChecker.getContentPane().add(SpelligCheckerSP);
	    
		
	    SpelligCheckerTA = new JTextArea(10,20);
	    SpelligCheckerSP.setViewportView(SpelligCheckerTA);
	    SpelligCheckerTA.setEditable(false);
		
		InputSP = new JScrollPane();
		InputSP.setBounds(36, 95, 440, 367);
		frmSpellingChecker.getContentPane().add(InputSP);
		
		InputTA = new JTextArea(10,20);
		InputSP.setViewportView(InputTA);
		InputTA.setEditable(false);
		InputTA.putClientProperty("JComponent.sizeVariant", "large");
		InputSP.putClientProperty("JComponent.sizeVariant", "large");
		
	
	}
}
