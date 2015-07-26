import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Random;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

//This class is not used for the actual OCR program it is only used to debug the program
//as I build it. 

public class Testing {
	
 

	
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
	    

	
	public static void main(String[] args) {
		
		
          /* int a[]= new int[256];
           for(int i=0; i<256; i++)
           {
        	   a[i]=0; 
           }
           a[199]=1;
           a[216]=1;
           a[231]=1;
           a[255]=6;
           a[202]=3;
           a[178]=3;
           a[160]=1;
           a[188]=2;
           a[116]=1;
           a[112]=1;
           a[101]=2;
           a[78]=1;
           a[23]=1;
           a[64]=1;
          */ 
           
		 GreyScale grey= new GreyScale();
		 int red[][]={{255,221,127,211},{234,113,184,255},{255,28,200,199},{166,27,33,128}};
		 int green[][]={{252,196,169,254},{255,128,181,255},{254,16,169,200},{25,216,68,222}};
		 int blue[][]={{251,38,234,222},{216,64,177,255},{255,54,222,199},{78,81,44,116}};
         int r; 
         int g; 
         int b; 
         int a[][]= new int[5][5];
        for(int i=0; i<4; i++)
        {
        	for(int j=0; j<4; j++)
        	{
        		r=red[i][j];
        		g=green[j][i];
        		b= blue[j][i];
        		a[i][j]=(int) grey.Luminosity1(r, g, b);
        		System.out.print(a[i][j]+" ");
        		
        	}
        	System.out.println();
        }
           
           
          
	
	}

}