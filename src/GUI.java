import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



@SuppressWarnings("serial")
public class GUI extends JFrame  {
	
			//file chooser 
	        
	        
	        public static BufferedImage image1;
	        public static File selectedFile= null; 
	        public static File paramFile=null; 
	        private Pipeline pipe;
	        
	        //buttons for the gui
	        private JButton fileButton = new JButton("Process Image");
	        private JButton paramButton = new JButton("Process Parameter File");

	
	        // Menu bar 
			private JMenuBar bar= new JMenuBar();
			private JMenu menu = new JMenu("Menu");
			private JMenu greyscale = new JMenu("Greyscale"); 
			private JMenu thresh = new JMenu("ThreshHolding"); 
			private JMenu measure= new JMenu("Measurement ");
			
			//menu items for main menu
			private JMenuItem image = new JMenuItem("Import image");
			private JMenuItem file = new JMenuItem("Import Param File");
			
			
			//menu items for greyscale 
			private JMenuItem min = new JMenuItem("Min Decomposition");
			private JMenuItem max = new JMenuItem("Max Decomposition"); 
			private JMenuItem desaturation = new JMenuItem("Desaturation");
			private JMenuItem avg = new JMenuItem("Avg"); 
			private JMenuItem luminosity1 = new JMenuItem("Luminosity1");
			private JMenuItem luminosity2 = new JMenuItem("Luminosity2"); 
			private JMenuItem singlechannelR = new JMenuItem("Single Channel Red");
			private JMenuItem singlechannelG = new JMenuItem("Single Channel Green");
			private JMenuItem singlechannelB = new JMenuItem("Single Channel Blue");
			private JMenuItem custom = new JMenuItem("Custom Number of Grays");
			
			
			private JMenuItem fixed = new JMenuItem("Fixed ");
			private JMenuItem kmeans = new JMenuItem("Kmeans");
			private JMenuItem otsu = new JMenuItem("Otsu method");
			private JMenuItem maxe = new JMenuItem("Max Entrepy"); 
			private JMenuItem gmm = new JMenuItem("Gaussian Mixed Model");
			private JMenuItem mean = new JMenuItem("Mean ");
			private JMenuItem vote = new JMenuItem("Voting System");
			
			
			private JMenuItem bound = new JMenuItem("Bounding Box");
			private JMenuItem convex= new JMenuItem("Convex Hull");
			
			private static int GreyscaleNumber=1;
			private static int ThreshNumber=1; 
			private static int 	BoundNumber=1; 
			private int HEIGHT; 
			private int WIDTH;
	
	public GUI()
	{
		super("OCR");
		
		
		
		
		
		setLayout(new GridLayout(0,1));
		
		
		
		// main menu 
		add(bar);
		bar.add(menu);
		menu.add(image);
		menu.add(file);
		
		
		//adding greyscale items to greyscale menu 
		bar.add(greyscale);
		greyscale.add(min);
		greyscale.add(max);
		greyscale.add(desaturation);
		greyscale.add(avg);
		greyscale.add(luminosity1);
		greyscale.add(luminosity2);
		greyscale.add(singlechannelR);
		greyscale.add(singlechannelG);
		greyscale.add(singlechannelB);
		greyscale.add(custom);
		
		//adding threshholding items to menu bar
		bar.add(thresh);
		thresh.add(fixed);
		thresh.add(kmeans);
		thresh.add(otsu);
		thresh.add(maxe);
		thresh.add(gmm);
		thresh.add(mean);
		thresh.add(vote);
		
		// adding measuring items
		bar.add(measure);
		measure.add(bound);
		measure.add(convex);


		MenuHandler menuhandler = new MenuHandler();
		
		menu.addActionListener(menuhandler);
		image.addActionListener(menuhandler);
		file.addActionListener(menuhandler);
		
		
		fileButton.addActionListener(menuhandler);
		fileButton.setBackground(new Color(245));
		paramButton.addActionListener(menuhandler);
		paramButton.setBackground(new Color(245));
		
	
		GreyHandler grey= new GreyHandler();
		
		// greyscale action listener 
		greyscale.addActionListener(grey);
		min.addActionListener(grey);
		max.addActionListener(grey);
		desaturation.addActionListener(grey);
		avg.addActionListener(grey);
		luminosity1.addActionListener(grey);
		luminosity2.addActionListener(grey);
		singlechannelR.addActionListener(grey);
		singlechannelG.addActionListener(grey);
		singlechannelB.addActionListener(grey);
		custom.addActionListener(grey);
		
		
		ThreshHandler thresh= new ThreshHandler();
		fixed.addActionListener(thresh);
		kmeans.addActionListener(thresh);
		otsu.addActionListener(thresh);
		maxe.addActionListener(thresh);
		gmm.addActionListener(thresh);
		mean.addActionListener(thresh);
		vote.addActionListener(thresh);
		
		MeasureHandler meas= new MeasureHandler();
		convex.addActionListener(meas);
		bound.addActionListener(meas);
		
		add(fileButton);
		add(paramButton);
		
	}
	
	public class MenuHandler implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			
			//chooses image for processing 
			if(e.getSource()==image)
			{
				JFileChooser fileChooser = new JFileChooser();
	    		fileChooser.setDialogTitle("Choose Image for Processing");
	    		int result = fileChooser.showOpenDialog(fileChooser);
	    		if (result == JFileChooser.APPROVE_OPTION)
	    		 selectedFile = fileChooser.getSelectedFile();
	    	    //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
	    	    
	    	    
	    	   
	    	    try {
					image1 = ImageIO.read(selectedFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		
	            //getting height and width of image
	             HEIGHT= image1.getHeight();
	             WIDTH= image1.getWidth();
	             
	             pipe = new Pipeline(HEIGHT, WIDTH, image1);
	             
	             JFrame frame = new JFrame();
	             frame.setSize(400, 400);
	             JLabel label1 = new JLabel(new ImageIcon(image1));
	             frame.add(label1);
	             frame.setTitle("Original Image");
	             frame.setVisible(true);
	    	   
	    	 //System.out.print(selectedFile.getAbsolutePath());
	    	   
			}
			
			
			
			// chooses parameter file (MIGHT NOT NEED) 
			if(e.getSource()==file)
			{
				JFileChooser fileChooser = new JFileChooser();
	    		fileChooser.setDialogTitle("Choose Image for Processing");
	    		int result = fileChooser.showOpenDialog(fileChooser);
	    		if (result == JFileChooser.APPROVE_OPTION)
	    		paramFile = fileChooser.getSelectedFile();
	    	    //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
	    	    
			}
			
			
			//the button that processes the image normally  
			if(e.getSource()==fileButton)
			{
				
				if(selectedFile==null)
				{
					JOptionPane.showMessageDialog(null, "Please select a file to Process");
					
				}
				else
				{
			
				int[][] image2= pipe.greyScale(GreyscaleNumber, image1);
				int[][] mono=pipe.threshHolding(ThreshNumber, image2);
				pipe.Measurement(mono, 	BoundNumber);
				
				
				}
				
			}
			
			
			
			//the button that processes image using paramater file 
			if(e.getSource()==paramButton)
			{
				
				if(paramFile==null || selectedFile==null)
				{
					JOptionPane.showMessageDialog(null, "Please select a parameter file to Process");
					
				}
				else
				{
					String line;
					BufferedReader bufRdr = null;
					try {
						bufRdr = new BufferedReader(new FileReader(paramFile));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					 try {
						 int grey; 
						 int thresh; 
						 int measure; 
						while((line= bufRdr.readLine()) != null)
						 {
						      // extracts data from file  
						      StringTokenizer st = new StringTokenizer(line,"/");
						     
						    	  grey=Integer.parseInt(st.nextToken());
						    	  thresh=Integer.parseInt(st.nextToken());
						    	 measure=Integer.parseInt(st.nextToken());
						      
						     
						      
						      
						     
						      		int array[][]=	pipe.greyScale(grey, image1);
						      		int mono[][]=pipe.threshHolding(thresh,array );
						      			pipe.Measurement(mono, measure);
						  
						}
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					
						
						
				
				}
				
			}
		}


	}
	
	public class GreyHandler implements ActionListener{

	

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if(e.getSource()==min)
			{
				GreyscaleNumber=1; 
				
			}
			
			if(e.getSource()==max)
			{
				GreyscaleNumber=2;				

			}
			
			if(e.getSource()==desaturation)
			{
				GreyscaleNumber=3;
				
			}
			
			if(e.getSource()==avg)
			{
				GreyscaleNumber=4;
				
			}
			
			if(e.getSource()==luminosity1)
			{
				GreyscaleNumber=5;
			
			}
			
			if(e.getSource()==luminosity2)
			{
				GreyscaleNumber=6;
				
			}
			
			if(e.getSource()==singlechannelR)
			{
				GreyscaleNumber=7;
		
			}
			if(e.getSource()==singlechannelG)
			{
				GreyscaleNumber=8;
		
			}
			if(e.getSource()==singlechannelB)
			{
				GreyscaleNumber=9;
		
			}
			
			if(e.getSource()==custom)
			{
				GreyscaleNumber=10;

			}
			
			
			
			
		}
	}
	
	public class ThreshHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if(e.getSource()==fixed)
			{
				ThreshNumber=1; 
				
			}
			
			if(e.getSource()==kmeans)
			{
				ThreshNumber=2;				

			
			}
			
			if(e.getSource()==otsu)
			{
				ThreshNumber=3;
				
				
			}
			
			if(e.getSource()==maxe)
			{
				ThreshNumber=4;
				
				
			}
			
			if(e.getSource()==gmm)
			{
				ThreshNumber=5;
			
			}
			if(e.getSource()==mean)
			{
				ThreshNumber=6; 
			}
			if(e.getSource()==vote)
			{
				ThreshNumber=7; 
			}
			
			
			
			
			
		}

		
	}
	
	public class MeasureHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if(e.getSource()==bound)
			{
				BoundNumber=1; 
				
			}
			if( e.getSource()==convex)
			{
				BoundNumber=2; 
			}
		}
	}
			

}
	
