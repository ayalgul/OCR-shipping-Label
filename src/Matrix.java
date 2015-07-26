import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class Matrix {
	
	private int row;
	private int col;
    private  int[][] matrix;
    
    
    public static void print(Matrix m)
    {
    	for(int r=0; r<m.row; r++)
    	{
    		for(int c=0; c<m.col; c++)
    		{
    			System.out.print(m.matrix[r][c]+" ");
    		}
    		System.out.println();
    	}
    		System.out.println();
    }
    
   
    public static int getRow(File file) throws IOException
    {
    	int rowsInFile=0; 
    	
         BufferedReader bufRdr;
         bufRdr = new BufferedReader(new FileReader(file));
        

         //get rows in the file
         
         while(bufRdr.readLine() != null)
         {
             rowsInFile++;
            
         }
         
         bufRdr.close();
         return rowsInFile;
    }
    
    public static int getCollumn(File file) throws IOException
    {
        int collumnsInFile=0; 
        String line= null; 
        
        BufferedReader bufRdr;
        bufRdr = new BufferedReader(new FileReader(file));
        
        	 line = bufRdr.readLine();
             StringTokenizer st = new StringTokenizer(line," ");
             

             while (st.hasMoreTokens())
             {
                 //get next token and store it in the array
            	collumnsInFile++;
            	st.nextToken();
             }
             
             bufRdr.close();
		return collumnsInFile;
    	
    }
    
    
    public Matrix addition(Matrix m)
    {
    	Matrix result = new Matrix(row, col);
    	
    	if(m.row!= row || m.col != col)
    	{
    		System.out.println("matrix is undefined");
    	}
    	else
    	{
    	    for(int r=0; r< m.row; r++)	
    	    {
    	    	for(int c=0; c<m.col; c++)
    	    	{
    	    		result.matrix[r][c]= matrix[r][c]+ m.matrix[r][c];
    	    	}
    	    }
    	}
    	print(result);
    	return result; 
    }
    
    
    public Matrix subtraction(Matrix m)
    {
    	Matrix result = new Matrix(row, col);
    	
    		if(m.row!= row || m.col != col)
    		{
    			System.out.println("matrix is undefined");
    		}
    		else
    		{
    			for(int r=0; r< m.row; r++)	
    			{
    				for(int c=0; c<m.col; c++)
    				{
    					result.matrix[r][c]= matrix[r][c]- m.matrix[r][c];
    				}
    			}
    			print(result);
    	    	
    		}
    		return result; 
    }
    
    public Matrix multiplication(Matrix m)
    {
    	Matrix result = new Matrix(row, m.col);
    	
    	if(col!= m.row)
    	{
    		System.out.println("matrix is undefined");
    	}
    	else
    	{
    		
    		for(int j=0; j< row; j++)
    		{
    			for(int r=0; r<m.col; r++)
    			{
    				int x=0; 
    				for(int i=0; i<col; i++ )
    				{
    					x= x+ (matrix[j][i]* m.matrix[i][r]);
    				}
    				result.matrix[j][r]=x;
    			}
    		}
    	}
    	print(result);
    	return result; 
    }
    
    public static Matrix transposition(Matrix m)
    {
    	Matrix result = new Matrix(m.col, m.row);
    	
    	for(int r=0; r<m.row; r++)
    	{
    		for(int c=0; c<m.col; c++)
    		{
    			result.matrix[r][c]= m.matrix[c][r];
    		}
    	}
    	print(result);
    	return result; 
    }
    
    public static Matrix Identity(Matrix m)
    {
    	Matrix result = new Matrix(m.row, m.row);
    	
    	for(int i=0; i<m.row; i++)
    	{
    		for(int j=0; j<m.row; j++)
    		{
    			if(i==j)
    				result.matrix[j][i]=1; 
    			else
    				result.matrix[j][i]=0; 
    		}
    	}
    	print(result);
    	return result; 
    }
    
    
   public Matrix(int r, int c) 
   {
	   row=r; 
	   col=c;
	  matrix = new int[r][c]; 
   }
    
	
	public Matrix(File file) throws IOException
	{
		row= getRow(file);
		col= getCollumn(file);
		
		matrix = new int[row][col];
	
       
         String line; 
         int j=0; 
         
         
         BufferedReader bufRdr;
         bufRdr = new BufferedReader(new FileReader(file));
         
         
         while((line= bufRdr.readLine()) != null)
         {
              
              StringTokenizer st = new StringTokenizer(line," ");
              
              for(int i=0; i<col; i++)
              {
            	 matrix[j][i] = Integer.parseInt(st.nextToken());
                 System.out.print(matrix[j][i]+" ");
             	
              }
              j++; 
              System.out.println();
            
         }   
         System.out.println();
         bufRdr.close();
         
           
		
		
	}


	public int[][] getMatrix() {
		return matrix;
	}


	public void setMatrix()
	{
		
		
		
	}
	
	
	
	
	public static void main (String [] args) throws IOException
	{
	    
		File file = new File("/Users/ayalgul/Desktop/a.txt");
		File file1 = new File("/Users/ayalgul/Desktop/b.txt");
	 
		
		
		Matrix A= new Matrix(file); 
		Matrix B= new Matrix(file1);
		
		A.multiplication(B);
			
		
		
    }

}
