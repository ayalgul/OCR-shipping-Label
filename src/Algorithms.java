import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Algorithms {
	
	


	
	
	public static void cluster (BufferedImage In, int clust_size)
	{
		int n, i , j, k , new_clust; 
		float edist;
		float clustx[]= new float[256];
		float clusty[]= new float[256];
		
		// initialzie cluster count variable n 
		n=0;
		
		//double loop through each pixel of binary image
		
		for(  i=0; i< In.getHeight(); ++i)
			for( j=0; j< In.getWidth(); ++j )
			{
				Color in = new Color(In.getRGB(j, i));
				if(n==0)
				{
				
		    		// initially the code was if( pix(In, x,y)!=0)
					//according to the book on page 24 pix(In, x,y ) 
					//provides the pixel for the coordinate x,y
					// instead I used the color class to provide me 
					// with the pixel of the given coordinate.In addition
					// the book on page 44 also stated that this algorithm assumes the image
					// is a binary image, because of this I use in.getRed() to 
					//get the pixel because since the image is binary the r g and b values are all the
					// same
					
					// only do for 1st cluster 
					if(in.getRed()!=0)
					{
						n=1; //1st cluster found 
						
						//store x coord
						clustx[1]=i; 
						
						//store y coord
						clusty[1]=j; 
						
						//mark pixel as cluster  1
						In.setRGB(j, i, 1);
					}
				}
				// test for membership ina ll known clusters 
				
				else if(in.getRed()!=0)
				{
					//marker for new cluster 
					new_clust=0; 
					
					//compute euclidean distance 
					for(k=1; k<=n; k++)
					{
						edist = (float) Math.sqrt((i-clustx[k])*
									(j-clusty[k])*(j-clusty[k]));
						//test against cluster size desired 
						if(edist <= clust_size)
						{
							//set pixel to cluster number
							Color c = new Color(k,k,k);

							In.setRGB(j, i, c.getRGB()); 
							new_clust =1; 
							k= n+1; 
						
						}
					}
					// add a new cluster 
					if(new_clust ==0 && n<=255)
					{
						Color c = new Color(n,n,n);
						n=n+1; 
						clustx[n]=i; 
						clusty[n]=j; 
						In.setRGB(j, i, c.getRGB()); 
					}
				}
			}
		//I used this code displays the clusters of pixels within the image. 
		for(int c=0; c<In.getWidth(); c++)
		{
			for(int r=0; r<In.getHeight(); r++)
			{
				Color rgb = new Color(In.getRGB(c, r));
			System.out.print(" "+rgb.getRed());
				
			}
			System.out.println();
		}
				
		
	}
	
	
	// This code initially  asks to insert 4 different parameters 3 of which are the 
	// three channels of the image. Because it is impossible to seperate the image into 
	// three seperate images with individual channels. I decided to put in 1 parameter
	// which will be a bufferedimage and keep the saturation parameter. then within the class 
	//I will seperate the image into seperate channels 
	
	public static void Saturation(BufferedImage Image, int saturation )
	{
		int X, RY, BY, GY, RY1, BY1, GY1, S; 
		int Y, R , B ,G , B1, R1 , G1, rgb; 
		//I will remove the k value because it is used as a shorthand 
		//approuch to find the location of the pixel that will be modified. 
		
		//long  K; 
		BufferedImage newImage = new BufferedImage(Image.getWidth(),Image.getHeight(),BufferedImage.TYPE_INT_RGB);
		
		
		int count=0; 
		
		 
		
	
		for(Y=0; Y< Image.getHeight(); Y++)
		{
			for(X=0; X<Image.getWidth(); X++)
			{
				//this is within the old code. 
				//K=X+(long) Y*Red.getWidth(); 
				
				Color c= new Color(Image.getRGB(X, Y));
				R1= (int) c.getRed();
				G1= (int) c.getGreen();
				B1= (int) c.getBlue();  
				
				
				
				RY1= ((int)(70*(int)R1-59*(int)G1-11*(int)B1)/100);
				BY1= ((int)(-30*(int)R1-59*(int)G1+89*(int)B1)/100);
				GY1= ((int)(-30*(int)R1+41*(int)G1-11*(int)B1)/100);
				S=((int) (30*(int)R1+59*(int) G1+11*(int)B1)/100);
				BY=(BY1*saturation)/100;
				RY=(RY1*saturation)/100; 
				GY=(GY1*saturation)/100; 
				R=(int) (RY)+S; G=(int) (GY)+S; B= BY+S; 
				if(R<0) 
					R=0;
				if(B<0)
					B=0; 
				if(G<0)
					G=0; 
				if(R>255)
					R=255;  
				if(B>255)
					B=255;  
				if(G>255)
					G=255;
				
				//I used the color class to take the individual 
				// channels and set them back into the image. 
				
				Color newC= new Color(R,G,B);
				rgb= newC.getRGB();
				System.out.println(count);
				count++; 
				newImage.setRGB(X, Y, rgb);
			
				
				
			
			}
			
		}
		// I added this code to display the results of the modification of the image
		JFrame frame1 = new JFrame();
        frame1.setSize(400, 400);
       JLabel label1 = new JLabel(new ImageIcon(newImage));
        frame1.add(label1);
        frame1.setVisible(true);  
		
	}
	
	//I added this main and the code within the main to test the given algorithms
	public static void main(String[] args) throws IOException 
	{
		 File selectedFile= null; 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choose Image for Processing");
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION)
		 selectedFile = fileChooser.getSelectedFile();
	    
	    
	   
	   
		
			BufferedImage image =ImageIO.read(selectedFile);
			System.out.println(image.getHeight()+" "+image.getWidth());
			
			  //Saturation(image,88 );
			  //cluster(image, 5);
		
	}
}


