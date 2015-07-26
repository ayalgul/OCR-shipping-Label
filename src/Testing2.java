
public class Testing2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][]mono = {{0 , 0, 0, 0 ,1, 1 , 1, 1 , 1, 1,0,0,0,0}, {0 , 0, 0, 0 ,1, 1 , 1, 1 , 1, 1,0,0,0,0},{0 , 0, 0, 0 ,1, 1 , 1, 1 , 1, 1,0,0,0,0},
				{0 , 0, 0, 0 ,0, 1 , 1, 1 , 1, 0,0,0,0,0}, {0 , 0, 1, 0 ,1, 1 , 1, 1 , 1, 0,0,0,0,0}, 
				{0 , 0, 1, 0 ,0, 1 , 1, 1 , 1, 0,0,0,0,0},{0 , 0, 1, 0 ,0, 0 , 1, 1 , 0, 0,1,0,0,0},{0 , 0, 1, 1 ,0, 0 , 1, 1 , 0, 0,1,0,0,0},
				{0 , 0, 1, 1 ,0, 0 , 1, 1 , 0, 0,1,0,0,0},{0 , 0, 1, 1 ,0, 0 , 0, 0 , 0, 1,1,0,0,0},{0,0,1,1,1,0,0,0,0,1,1,0,0,0,},{0,0,1,1,1,0,0,0,0,1,1,0,0,0,},
				{0,0,1,1,1,0,0,0,1,1,1,0,0,0,}};
		
		int HEIGHT=13; 
		int WIDTH=14; 
		int [][]comp = new int[13][14];
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
							 
							 
							 //used for letter m, n, w,v 
								if(i>1)
								{
								 if( mono[j][i]==mono[j][i-1])
								 {
									 
									 comp[j][i-1]=comp[j][i];
								 }
								 
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
						//rechecks all values
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
				
				//This reconfigures the result of the 8 components
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
				
				//displays result
				for(int j=0; j<HEIGHT; j++)
			    {
			    	for(int i=0; i<WIDTH; i++)
			    	{
						System.out.print(mono[j][i]);
						
			    	}
					System.out.println();

			    	
			    }
		
				System.out.println();

				for(int j=0; j<HEIGHT; j++)
			    {
			    	for(int i=0; i<WIDTH; i++)
			    	{
			    		
						System.out.print(comp2[j][i]);
						
			    	}
					System.out.println();

			    	
			    }
		
	}

	}
