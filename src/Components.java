
public class Components {
	
	
	public Components(int h, int w, int m[][])
	{
		HEIGHT=h;
		WIDTH=w; 
		this.mono=m; 
				
	}
	
	public int HEIGHT; 
	public int WIDTH; 
	public int mono[][];
	
	
	
	public int[][] components()
	{
	int [][]comp = new int[HEIGHT][WIDTH];
	int count=0; 
	for(int j=0; j<HEIGHT; j++)
    {
    	for(int i=0; i<WIDTH; i++)
    	{
			comp[j][i]= count; 
			count++; 
			//System.out.println(comp[j][i]+"= component ["+j+"]["+i+"] mono= "+mono[j][i]);
			
    	}
    	
    }

	
	//beginning of 8 components algorithm 
	
	int oldbackangle=-1; 
	int oldup=-1; 
	int oldforangle = -1; 
	int oldright=-1;

for(int x=0; x<2; x++)
{
	for(int j=0; j<HEIGHT; j++)
	{
		for(int i=0; i<WIDTH-1; i++)
		{
			//this is used to check any changes with the values in array if their are changes we would change all similar 
			//values to the appropriate value 
			if(j!=0 && i>1)
			{
			 oldbackangle= comp[j-1][i-1];
			 oldup= comp[j-1][i];
			 oldforangle= comp[j-1][i+1];
			 oldright=comp[j][i+1];
			}
			
			if(j==0 )
			{
				if(mono[j][i]==mono[j][i+1])
				{
					comp[j][i+1]=comp[j][i];
				}
			}
			if(j!=0)
			{
				
				// back left angle 
				if(i>0)
					if(mono[j][i]==mono[j-1][i-1])
						if(comp[j][i]<comp[j-1][i-1])
							comp[j-1][i-1]=comp[j][i];
						else
						comp[j][i]=comp[j-1][i-1];
				
				if(i==WIDTH-2)
					if(mono[j][i+1]==mono[j-1][i])
						if(comp[j][i+1]<comp[j-1][i])
							comp[j-1][i]=comp[j][i+1];
						else	
							comp[j][i+1]=comp[j-1][i];
				
				//top to bottom
				if(mono[j-1][i]==mono[j][i])
					if(comp[j][i]<comp[j-1][i])
						comp[j-1][i]=comp[j][i];
					else
					comp[j][i]=comp[j-1][i];
					
			   //front angle 
				if(mono[j][i]==mono[j-1][i+1])
				{
					if(comp[j][i]<comp[j-1][i+1])
						comp[j-1][i+1]=comp[j][i];
					else
						comp[j][i]=	comp[j-1][i+1];
				}
				
				//left to right
				 if(mono[j][i]==mono[j][i+1])
				{
					
					if(comp[j][i+1]<comp[j][i])
						comp[j][i]=comp[j][i+1];
					else
					comp[j][i+1]=comp[j][i];

				}
				 
				 
				
			  
			}
			
			if(j!=0 && i==WIDTH-2)
			{
				if(mono[j-1][i+1]==mono[j][i+1])
				{
					if(comp[j][i+1]<comp[j-1][i+1 ])
						comp[j-1][i+1]=comp[j][i+1];
					else
						comp[j][i+1]=comp[j-1][i+1];
					
				}
				if(mono[j][i]==mono[j][i+1])
				{
					if(comp[j][i+1]<comp[j][i])
						comp[j][i]=comp[j][i+1];
					else
						comp[j][i+1]=comp[j][i];
					
				}
			}	
			//this is the second part which is used to check any changes with the values in array if their are changes we would change all similar 
			//values to the appropriate value
			// for example if a pixel in the array was formely 5 and is now 2 then this part will change all values that
			//equal 5 to 2
			if(j!=0 && i>1)
			{
			int newbackangle= comp[j-1][i-1];
			int newup= comp[j-1][i];
			int newforangle= comp[j-1][i+1];
			int newright=comp[j][i+1];
			
			if(newbackangle!=oldbackangle || newup!=oldup || newforangle!= oldforangle || newright!=oldright)
			{
				for(int y=0; y<HEIGHT; y++)
				{
					for(int z=0; z<WIDTH-1; z++)
					{
						if(comp[y][z]==oldbackangle)
							comp[y][z]=newbackangle;
						if(comp[y][z]==oldup)
							comp[y][z]=newup;
						if(comp[y][z]==oldforangle)
							comp[y][z]=newforangle;
						if(comp[y][z]==oldright)
							comp[y][z]=newright; 
					}
				}	
			}
			}
			
			
				
		}
	
	}
}
	
	//This reconfigures the result of the 8 components to smaller component numbers
	int comp2[][]= new int[HEIGHT][WIDTH];
	int count1=0; 
	boolean flag=false; 
	for(int n=0; n<(WIDTH*HEIGHT); n++ )
	{
		for(int r=0; r<HEIGHT; r++)
		{
			for(int c=0; c<WIDTH; c++)
			{
				if(n==comp[r][c])
				{
					comp2[r][c]=count1; 
					flag=true; 
				}
			}
		}
		if(flag)
		{
		count1++;
		flag=false;
		}
	}
	
	return comp2; 
}	

}
