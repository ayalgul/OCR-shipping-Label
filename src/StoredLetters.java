import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class StoredLetters {
	
	Hashtable<String, int[][]> hash= new Hashtable<String, int[][]>();
	
	
	// checks if hashtable is occupied approprietly 
			public void check(String str)
			{
				System.out.println("letter "+str+" is in the hashtable");
				int array[][]= hash.get(str);
				for(int r=0; r<array.length; r++)
				{
					for(int c=0; c<array[0].length; c++)
					{
						
						System.out.print(array[r][c]);
					}
					System.out.println();
				}
			}
	
	
	public Hashtable<String, int[][]> HashTable()
	{
		//you have to set this directory to the directory of the image named refference.png in the folder that 
		//was given with the program.
		File f = new File("/Users/ayalgul/Desktop/refference.png");
		BufferedImage	Image = null;
		
		
		   try {
				Image = ImageIO.read(f);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
   		
		  
           //getting height and width of image
		   int letter=64; 
           int  HEIGHT= Image.getHeight();
           int  WIDTH= Image.getWidth();
           int p; 
           int reff[][]= new int[HEIGHT][WIDTH];
         //  System.out.println("start");
           for(int j=0; j<HEIGHT; j++)
		    {
		    	for(int i=0; i<WIDTH; i++)
		    	{
		    		p=Image.getRGB(i, j);
		    		Color c= new Color(p);
		    		int b=c.getBlue();
		    		int r=c.getRed();
		    		int g=c.getGreen();
		    		if(r==0 || g==0 || b==0)
		    			reff[j][i]=0; 
		    		else
		    			reff[j][i]=1; 
		    		
		    		//System.out.print(reff[j][i]);
	
		    	}
		    	//System.out.println();
		    }
           
           Components com= new Components(HEIGHT, WIDTH, reff);
          int comp[][]= com.components();
		
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
          
          
		for(int co=0; co<max;  co++)
		{
			
			//rows
			int mintop=HEIGHT;
			int minbot=0;
			//collumns 
			int minleft=WIDTH;
			int minright=0; 
			
			for(int r=0; r<HEIGHT; r++)
			{
				for(int c=0; c<WIDTH; c++)
				{
					if(co==comp[r][c]&& reff[r][c]==0)
					{
						if(mintop> r)
							mintop=r;
						if(minbot<r)
							minbot=r; 
						if(minleft>c)
							minleft=c; 
						if(minright<c)
							minright=c;
						
					}
				}
				
			}
			
			//System.out.println("for component "+co);
			//System.out.println("top "+mintop+" bottom "+minbot+" left "+minleft+" right "+minright);
			
			int col= Math.abs(minleft-minright); 
			int row=Math.abs(mintop-minbot);
			//System.out.println(row+" "+col);
			int array[][]= new int[row+1][col+1];
			int r1=0; 
			int c1=0; 
			
			//System.out.println(" col "+col+" row  "+row);
			for(int j=mintop; j<=minbot; j++)
			{
				for(int i=minleft; i<=minright; i++)
				{
				
					array[r1][c1]=reff[j][i];
					//System.out.println("component ["+j+"]["+i+"] = "+comp[j][i]+" row "+r1+" co l"+c1);
					//System.out.print(array[r1][c1]);
					c1++; 
				}
				r1++;
				c1=0; 
				//System.out.println();
			}
			//System.out.println();
			
			if(letter <91)
			{
				 String str = Character.toString((char) letter);
			hash.put(str, array);
			check(str);
			letter++; 
			}
			
			
		}
		return hash;
		
		
		
	}

}
