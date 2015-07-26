

import java.util.Random;


public class GreyScale {
	
	//gives the minimum decomposition of a color image.
	int minDecomp(int r, int g, int b) 
	{
		
		int  smallest;
		if(r<g && r<b){
		    smallest = r;
		}else if(g<b && g<r){
		    smallest = g;
		}else{
		    smallest = b;
		}
	   
		return smallest;
	}
	//gives the maximum decomposition of a color image. 
	int maxDecomp(int r, int g, int b) 
	{
		
		int  biggest;
		if(r>g && r>b){
		    biggest = r;
		}else if(g>b && g>r){
		    biggest = g;
		}else{
		    biggest = b;
		}
	   
		return biggest;
	}
	//gives the desaturation of a color image. 
	public int Desaturation(int r, int g, int b)
	{
		return ((maxDecomp(r, g, b)+ minDecomp(r, g, b))/2);
	}
	
	// gives the avg color of  a color image. 
	public int Avg(int r, int g, int b)
	{
		return ((r+b +g)/3);
	}
	
	//gives the luminsoity of a color image. 
	public double Luminosity1(int r , int g, int b)
	{
		return (.2126*r+ .7152*g+ .0722*b);
	}
	


	//gives the luminsoity of a color image slight variation of the first luminosity method. 
	public double Luminosity2(int r , int g, int b)
	{
		return (.3*r+ .59*g+ .11*b);
	}
	
	
	//gives the single channel value of color red. 
	public int SingleChannelR(int r, int g, int b)
	{
		
			return r; 
	}
	//gives the single channel value of color blue. 
	public int SingleChannelB(int r, int g, int b)
	{
		
			return b; 
	}
	//gives the single channel value of color green. 
	public int SingleChannelG(int r, int g, int b)
	{
		
			return g; 
	}
	
	
	
	//gives the custom number of grays 
	public int CustonNumberOfGrays(int r, int g, int b,int numberOfShades)
	{
		
		int ConversionFactor = 255 / numberOfShades ;
		
		int AverageValue = (r + g + b) / 3;
		int Gray = (int) (((AverageValue / (ConversionFactor) + 0.5)));
		return Gray;
		
	}
	

	

		
	
	

}
