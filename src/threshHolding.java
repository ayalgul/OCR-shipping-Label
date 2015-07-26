
public class threshHolding {
	
	 //histogram contains the information of the histogram that is produced
	// when running the image through the greyscale algorithms in the pipeline class 
	public static int histogram[];
	
	// population contains the area size of the image being used 
	public static int population; 
	
	public threshHolding(int frequency[])
	{
		histogram=frequency;
		population=population();
		
		
	}
	
		//population() finds the area of the image. 
		public int population()
		{
			int total=0; 
			for(int i=0; i<256; i++)
			{
				total=total+histogram[i];
			}
			return total; 
		}
	
	// finds the mean of the entire image.
		public static float mean()
		{
			float total=0; 
			float mean; 
			for(int i=0; i<256; i++)
			{
				
				total=total+i*histogram[i];
			}
			mean=total/(population);
			return mean; 
		}
		
		// finds the  mean of background
		public static  int meanBackground(int thresh)
		{
			double total= 0.0; 
		
			float population=backgroundPixels(thresh);
			
			
			for(int i=0; i<=thresh; i++)
			{  
				total=total+i*((float)histogram[i]/population);
				
			}
		
			return (int) Math.ceil(total); 
		}
		

		// finds the mean of the foreground 
		public static  int meanforeground(int thresh)
		{
			float total=0; 
			float population=foregroundPixels(thresh);
			
			
			
			for(int i=thresh+1; i<256; i++)
			{  
				//System.out.println(" ");

				total=total+i*((float)histogram[i]/population);
				
			}
			
			return (int) Math.ceil(total); 
		}
		
		

		
		//finds the total amount of  pixels in the background 
		public static float backgroundPixels(int t)
		{
			float total=0; 
			for(int i=0; i<=t; i++)
			{
			total=total+histogram[i];	
			}
			return total;
		}
		
		//finds the total amount of  pixels in the foreground
		public static float foregroundPixels(int t)
		{
			float total=0; 
			for(int i=t+1; i<256; i++)
			total=total+histogram[i];	
			return total; 
		}
		
		
		//finds the probability of a given color being choosen within an image
		public static float probability(int color)
		{
			float probability= ((float)histogram[color]/(population));		
			return probability;
		}
		//q1(t) the  probability that a given color is in the background 
		public static float backProbability(int t)
		{
			return (float)(backgroundPixels(t)/population);
			
		}
		//q2(t) probability that a given color is in the foreground
		public static float foregroundProbability(int t )
		{
			return (float)(foregroundPixels(t)/population);
			
		}
		
		
	
		
		//Gives the thresholding value using the kmeans algorithm. 
		public static int kmeans()
		{
			int max=0;
			 
			for(int t=0; t<256; t++)
			{
				int backmean= meanBackground(128);
				int foremean= meanforeground(128);
				
				for(int i=0; i<256; i++)
				{
					if(i>backmean && i< foremean)
					
						if(  (((i-backmean)<(foremean-i)) && i< t) || (((i-backmean)>(foremean-i)) && i> t))
							max=i; 
							
				}
				
			}
			
			return max; 
		}
		
		
			//gives the variance of the  entire image 
			public static float varianceImage()
			{
				 float variance=0;
				 for(int i=0; i<256; i++)
				 {
					 variance =variance + ((i-mean())*(i-mean()))*probability(i);
				 }
						 
						
				 return variance; 
			}
			
			//gives the variance of the background with a given threshold 
			public static float varianceBackground(int t)
			{
				 float variance=0;
				 for(int i=0; i<=t; i++)
				 {
					 variance =variance +(float) ((i-meanBackground(t))*(i-meanBackground(t)))*(probability(i));
				 }
						 
						
				 return variance; 
			}
			
			
			//gives the variance of foreground  with a given threshold 
			public static float varianceforeground(int t)
			{
				 float variance=0;
				 for(int i=t+1; i<256; i++)
				 {
					 variance =variance +(float) ((i-meanBackground(t))*(i-meanBackground(t)))*(probability(i));
				 }
						 
						
				 return variance; 
			}
		
		
		//gives the threshold value using otsus method
		public static int Otsu()
		{
			float ans[] = new float[256];
			
			for(int t=0; t<256; t++)
			{
					ans[t]= (varianceImage()-( ((float)backProbability(t)*(float)varianceBackground(t))+
							((float) foregroundProbability(t)*(float)varianceforeground(t))));
					
					
			}
			int min=0; 
			 
			for(int i=0; i<256; i++)
			{
				
				 if(ans[min]<ans[i])
					min=i; 
			}
			
			return min;
		}
		
		
		
		

		//The next 6 methods are used to create the Max entropy function
		
		
		
		// finds the normalized histogram of a given color 
		public static float  normalized(int color)
		{
			
			return ((float) histogram[color]/population);
		}
		//finds the normalized histogram sum of the background 
		public static float normalizedBackSum(int t)
		{
			float  back = 0; 
			for(int i=0; i<=t; i++)
			{
				back= back+normalized(i);
			}
			return back; 
		}
		
		//finds the normalized histogram sum of the foreground 
		public static float normalizedForeSum(int t)
		{
			float  fore = 0; 
			for(int i=t+1; i<256; i++)
			{
				fore= fore+normalized(i);
			}
			return fore; 
		}
		
		
		// finds the  entropy of the background 
		public static double EntropyBack(int t)
		{
			double inf = -(Double.POSITIVE_INFINITY);
			double sum=0; 
			double first; 
			double second; 
			//System.out.println("entropy Background ");
			//System.out.println(" ");
			for(int i=0; i<=t; i++)
			{
				first =(-((normalized(i)/normalizedBackSum(t))));
				second= Math.log((normalized(i)/normalizedBackSum(t)));
				if(first == -inf && second ==inf)
					return 0; 
				
				if(first!=-0.0 && second !=-inf )
				{
				sum=(double) sum+ first*second;
				}
			
			}
			return sum; 
		}
		
		//finds the entropy of the foreground
		public static double EntropyFore(int t)
		{
			double inf = (Double.POSITIVE_INFINITY);
			double sum=0; 
			double first; 
			double second; 
			
			
			
			for(int i=t+1; i<256; i++)
			{
				first =(-((normalized(i)/normalizedForeSum(t))));
				second= Math.log((normalized(i)/normalizedForeSum(t)));
				if(first == -inf && second ==inf)
					return 0; 
				if(first!=-0.0 && second !=-inf )
				{
					sum=(double) sum+ first*second;
				}
			
			}
			return sum; 
		}
		
		// returns the threshold using the max entropy method. 
		public static int  maxEntropy()
		{
			double max=0;
			int maxnum=0; 
			double total; 
			for(int t=0; t<256; t++)
			{   
				
				total=(EntropyFore(t)+EntropyBack(t));
				 System.out.println("thresh "+t+" entropy "+total);
				if(max<total)
				{
					max=total; 
					maxnum=t; 
				}
			}
			System.out.println(maxnum);
			return maxnum; 
			
		}
		
		
		
		
		
		
		//the next 4 methods are used to  make the  Gaussian Mixture Model method. 
		
		//finds the gaussian background 
		public static double gaussianBack(int t, int g )
		{
			double first= 1/Math.sqrt(2*3.14159265*varianceBackground(t));
			double second = Math.pow(Math.E, -((g-meanBackground(t))*(g-meanBackground(t)))/(2*varianceBackground(t)));
			System.out.println(first*second);
			return first*second; 
			
		}
		
		//finds the gaussian foreground 
		public static double gaussianFore(int t, int g)
		{
			double first= 1/Math.sqrt(2*3.14159265*varianceforeground(t));
			double second = Math.pow(Math.E,-((g-meanforeground(t))*(g-meanforeground(t)))/(2*varianceforeground(t)));
			return first*second; 
		}
		
		// returns the gaussian mixture model value using a given threshold and color. 
		public static double GMM(int t,int g)
		{
			return backProbability(t)*gaussianBack(t,g)+foregroundProbability(t)*gaussianFore(t,g );
		}
		
		//returns threshold value by finding the minimal error value  given  by the gaussian mixture model
		public static int GMMFinal()
		{
			double min=Double.MAX_VALUE; 
			int minnum=3 ;
			
			
			//keep track of threshold
			for(int i=0; i<256; i++)
			{
				double total=0; 
				for(int j=0; j<256; j++)
				{
					total= total+ (GMM(i,j)-(double)histogram[j])*(GMM(i,j)-(double)histogram[j]);
				}
				System.out.println("thresh =i "+i+" GMM ="+total);
				if(total!=Double.NaN && total<min)
				{
					min=total; 
					minnum=i; 
				}
			}
			System.out.print("GMM ="+minnum);
			return minnum; 
		}
		
		//takes i as the threshold and for each i we get the background and foreground mean and 
		//we find the avg of the background and foreground and we iterate this process until it 
		// is repeated three times. 
		public static int Vote()
		{
			
			int k= kmeans();
			int o= Otsu();
			int m= maxEntropy();
			int g= GMMFinal();
			int f=127; 
			int me= (int) mean();
			
			int thresh; 
			
			if(k==o || k==m || k==g || k==f || k==me)
				thresh=k; 
			else if(o==m || o==g || o==f || o==me || o==k)
				thresh=o; 
			else if(m==k || m==g || m==f || m==me || m==o)
				thresh=m; 
			else if(g==k || g==o || g==f || g==me || m==g)
				thresh=g;
			else if(f==k || f==o || f==m || f==g || f==me)
				thresh=f; 
			else if(me==k || me==o || me==m || me==g || m==f)
				thresh=me;
			else 
				thresh= (k+o+m+g+f+me)/6;
			
			System.out.println(k+" "+o+" "+m+" "+g+" "+f+" "+me+" "+thresh);
			return thresh; 			
			
		}
		
	
			
			
		
		
		
		

}
