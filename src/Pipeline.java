import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//This class will include all parts for the pipelining. including each possible way of doing each section
//of the pipeline. For example their are several different greyscale methods that transform the image into
//greyscale they are all numbered but vary only by the different algorithms used. Dont confuse these methods with 
// the actual algorithms for greyscale. this c

public class Pipeline {
	

	
	public static BufferedImage IMAGE; 
	public static int  HEIGHT;
	public static int WIDTH;
	
	public static String red;
    public static String green;
    public static String blue;
   
    public static String greyString ;
    public static String threshString; 
    
 // used to create a histogram of the values 0 to 255 of the greyscale image 
 	public	int  histogram[] = new int[256];
 	public int greyImage[][]= new int[HEIGHT][WIDTH];
    
    public Pipeline(int h, int w, BufferedImage i)
    {
    	HEIGHT=h; 
    	WIDTH=w; 
    	IMAGE=i; 
    	
    }
    
    // returns name of greyscale method that is chosen in the program and will be 
    //displayed in the final pictures jframe that is processed to keep track of what methods 
    //are used to process the image. 
    public String labelNameGrey(int n)
    {
    	switch(n)
    	{
    		case 1:
    			return "Min-Dec";
    		case 2:
    			return "Max-Dec";
    		case 3:
    			return "Desat";
    		case 4:
    			return "AVG";
    		case 5:
    			return "Lumin1";
    		case 6:
    			return "Lumin2";
    		case 7:
    			return "SCRed";
    		case 8: 
    			return "SCGreen";
    		case 9:
    			return "SCBlue";
    		case 10:
    			return "CNG";
    		default:
    			return "Custom";
    	}
    }
    
    
    // returns name of Threshold method that is chosen in the program and will be 
    //displayed in the final pictures jframe that is processed to keep track of what methods 
    //are used to process the image. 
    public String labelNamethresh(int n)
    {
    	switch(n)
    	{
    		case 1:
    			return "Fixed ";
    		case 2:
    			return "kmeans ";
    		case 3:
    			return "otsu";
    		case 4:
    			return "max E";
    		case 5:
    			return "GMM";
    		case 6: 
    			return "mean";
    		default: 
    			return "meanAvg"; 
    	}
    }
    
    // converts the 32 bit representation of the rgb value to an int value. 
    public static int bitToInt(String binary){
	    char[] numbers = binary.toCharArray();
	    int result = 0;
	    int count = 0;
	    for(int i=numbers.length-1;i>=0;i--){
	         if(numbers[i]=='1')result+=(int)Math.pow(2, count);
	         count++;
	    }
	    return result;
	}
    

    
    
    
	// is the method that is used to convert a color image into greyscale image. 
	public int[][] greyScale(int greyscaleNumber, BufferedImage image)
	{
		//creates new image thats the same size as the image we are greyscaling 
		BufferedImage newImage = new BufferedImage(WIDTH, HEIGHT,
        	    BufferedImage.TYPE_INT_RGB);  
		
		//used to keep greyscale value
		int[][] greyarray= new int[HEIGHT][WIDTH];
		
		//Greyscale is a class with several different methods to greyscale the image. 
		GreyScale greyscale = new GreyScale();
		
		
		
		
		    //loops through each pixel of the image
		    for(int j=0; j<HEIGHT; j++)
		    {
		    	for(int i=0; i<WIDTH; i++)
		    	{
		    		//retrieves the rgb of the image for the specific j and i pixel in the image. 
		    	String binary=	Integer.toBinaryString(image.getRGB(i,j));
		    		//specificaly takes out the red green and blue of the image for the specific i j  pixel 
		    	String	alpha= binary.substring(0, 8);
		    	String	red= binary.substring(8, 16);
		    	String	green= binary.substring(16, 24);
		    	String 	blue = binary.substring(24, 32);
		    	int a=bitToInt(alpha);
				int re=bitToInt(red);
				int g=bitToInt(green);
				int b=bitToInt(blue);
		    	int grey, rgb; 
		    	
				
					//based on the greyscale number that is given by selecting 
		    		//a greyscale method on the gui, this method will run the 
		    		//appropriate greyscale method
		    		switch(greyscaleNumber)
		    		{
		    		
			    		case 1:
			    			
			    			grey=greyscale.minDecomp(re,g ,b );
			    			rgb= new Color(grey, grey, grey).getRGB();
			    			newImage.setRGB(i ,j, rgb);
			    			histogram[grey]++;
			    			greyarray[j][i]=grey; 
			    			break; 
			    		
			    		case 2: 
			    			grey=greyscale.maxDecomp(re,g ,b );
			    			rgb= new Color(grey, grey, grey).getRGB();
			    			newImage.setRGB(i ,j, rgb);
			    			histogram[grey]++;
			    			greyarray[j][i]=grey; 
			    			break; 
			    		case 3: 
			    			grey=greyscale.Desaturation(re,g ,b );
			    			rgb= new Color(grey, grey, grey).getRGB();
			    			newImage.setRGB(i ,j, rgb);
			    			histogram[grey]++;
			    			greyarray[j][i]=grey; 
		    				break; 
			    		case 4: 
			    			grey=greyscale.Avg(re,g ,b );
			    			rgb= new Color(grey, grey, grey).getRGB();
			    			newImage.setRGB(i ,j, rgb);
			    			histogram[grey]++;
			    			greyarray[j][i]=grey; 
		    				break; 
			    		case 5: 
			    			grey=(int) greyscale.Luminosity1(re,g ,b );
			    			rgb= new Color(grey, grey, grey).getRGB();
			    			newImage.setRGB(i ,j, rgb);
			    			histogram[grey]++;
			    			greyarray[j][i]=grey; 
			    			
		    				break; 
			    		case 6: 
			    			grey=(int) greyscale.Luminosity2(re,g ,b );
			    			rgb= new Color(grey, grey, grey).getRGB();
			    			newImage.setRGB(i ,j, rgb);
			    			histogram[grey]++;
			    			greyarray[j][i]=grey; 
		    				break; 
			    		case 7: 
			    			grey=greyscale.SingleChannelR(re,g ,b);
			    			rgb= new Color(grey, grey, grey).getRGB();
			    			newImage.setRGB(i ,j, rgb);
			    			histogram[grey]++;
			    			greyarray[j][i]=grey; 
			    			
		    				break; 
			    		case 8:
			    			grey=greyscale.SingleChannelG(re,g ,b);
			    			rgb= new Color(grey, grey, grey).getRGB();
			    			newImage.setRGB(i ,j, rgb);
			    			histogram[grey]++;
			    			greyarray[j][i]=grey; 
			    			 
		    				break; 
			    		case 9:
			    			grey=greyscale.SingleChannelB(re,g ,b);
			    			rgb= new Color(grey, grey, grey).getRGB();
			    			newImage.setRGB(i ,j, rgb);
			    			histogram[grey]++;
			    			greyarray[j][i]=grey; 
			    			 
		    				break; 
			    		case 10:	
			    			grey=greyscale.CustonNumberOfGrays(re,g ,b, 128 );
			    			rgb= new Color(grey, grey, grey).getRGB();
			    			newImage.setRGB(i ,j, rgb);
			    			histogram[grey]++;
			    			 
			    			greyarray[j][i]=grey; 
			    			break; 
			    		 
			    					
		    		}
		    		
		    	  }	
		    	
		    		
		    	}
		    
		   
				
		    greyString=labelNameGrey(greyscaleNumber);
		    return greyarray; 
		    
		    
	}
	
	
	//is the method that converts a greyscale image into a monochrome image.
	public int[][] threshHolding(int threshNumber, int greyarray[][])
	{
		
		new threshHolding(histogram);
		int thresh=127; 
		
		//based on the threshold number that is given by selecting 
		//a threshold method on the gui, this method will run the 
		//appropriate threshold method
		switch(threshNumber)
    	{
    		case 1:
    			thresh=127; 
    			break;
    		case 2:
    			thresh=threshHolding.kmeans();
    			System.out.println("kmeans "+thresh);
    			break; 
    		case 3:
    			thresh=threshHolding.Otsu();
    			System.out.println("otsu "+thresh);
    			break; 
    		case 4:
    			thresh=threshHolding.maxEntropy();
    			System.out.println("max e "+thresh);
    			break; 
    		case 5:
    			thresh=threshHolding.GMMFinal();
    			System.out.println("gmm "+thresh);
    			break; 
    		case 6:
    			thresh=(int) threshHolding.mean();
    			break; 
    		case 7: 
    			thresh=(int) threshHolding.Vote();
    			break;
    	}
		
		
		int mono[][]= new int[HEIGHT][WIDTH];
		BufferedImage blackNWhite = new BufferedImage(WIDTH, HEIGHT ,BufferedImage.TYPE_BYTE_BINARY);
		for(int j=0; j<HEIGHT; j++)
	    {
	    	for(int i=0; i<WIDTH; i++)
	    	{
				if(greyarray[j][i]<=thresh)
				{
					blackNWhite.setRGB(i,j , Color.black.getRGB());
					mono[j][i]=0;
				}
				else
				{
					blackNWhite.setRGB(i, j, Color.WHITE.getRGB());
					mono[j][i]=1;
					
				}
				
				
				

	    		
	    	}
	    	
	    }
		System.out.println(WIDTH+" "+HEIGHT);
		//displays the processed threshold image. 
		threshString=labelNamethresh(threshNumber); 
		JFrame frame1 = new JFrame();
	        frame1.setSize(400, 400);
	       frame1.setTitle(greyString+" , "+threshString);
	       JLabel label1 = new JLabel(new ImageIcon(blackNWhite));
	        frame1.add(label1);
	        frame1.setVisible(true);
	        
	        return mono;
	         
	    	
	}
	
	public void Measurement(int mono[][], int boundNumber)
	{
		Components c= new Components(HEIGHT, WIDTH, mono);
		int comp[][] = c.components();
		
		//finds max component number
        int max=0; 
       for(int j=0; j<HEIGHT; j++)
 		{
 			for(int i=0; i<WIDTH; i++)
 			{
 				//System.out.println("component ["+j+"]["+i+"] = "+comp[j][i]);
 				//System.out.print(" "+comp2[j][i]);
 			
 				if(comp[j][i]>max)
 					max= comp[j][i];
 			}
 			
 		}
		// contains measuring algorithms
		Measurements m= new Measurements(HEIGHT, WIDTH, mono);
		
		//linkedlist is used to hold every measured blob. 
		LinkedList list= null; 
		Hashtable hash= null; 
		
		if(boundNumber==1)
		{
			list= m.boundingBox(comp, max);
		}
		else if(boundNumber==2)
		{
			list=m.convexHull(comp, max);
		}
		
		
		//Stored letters creates a hashtable of letters to refference. In order for this to work you need to set the 
		//directory of the File object in the hashtable method to the directory of the image refference.png which is 
		//included in the folder 
		StoredLetters store = new StoredLetters();
		hash= store.HashTable();
		
		
		//at this point I would have compared the blobs to the stored letters but I ran out of time to finish this 
		//project so this is the best I can do given the time I have. 
		
		
		
	}
	
	

	 
}
		
		
	
	

	
	
		   
    


	


