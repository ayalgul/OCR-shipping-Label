import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;



public class Measurements {
	
	 
	public int HEIGHT;
	public int WIDTH; 
	public int mono[][]; 
	
	public Measurements(int h, int w, int i[][])
	{
		HEIGHT=h; 
		WIDTH=w; 
		mono=i; 
		  
	}
	
	
	public LinkedList<int[][]> convexHull(int comp[][], int max)
	{
		return null;
		  
	
	}
	
	
	public LinkedList<int[][]> boundingBox(int comp[][], int max)
	{
		LinkedList <int[][]> link= new LinkedList<int[][]>();
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
					if(co==comp[r][c]&& mono[r][c]==0)
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
			
			System.out.println("for component "+co);
			System.out.println("top "+mintop+" bottom "+minbot+" left "+minleft+" right "+minright);
			
			int col= Math.abs(minleft-minright); 
			int row=Math.abs(mintop-minbot);
			System.out.println(row+" "+col);
			int array[][]= new int[row+1][col+1];
			int r1=0; 
			int c1=0; 
			
			System.out.println(" col "+col+" row  "+row);
			for(int j=mintop; j<=minbot; j++)
			{
				for(int i=minleft; i<=minright; i++)
				{
				
					array[r1][c1]=mono[j][i];
					//System.out.println("component ["+j+"]["+i+"] = "+comp[j][i]+" row "+r1+" co l"+c1);
					//System.out.print(array[r1][c1]);
					c1++; 
				}
				r1++;
				c1=0; 
				//System.out.println();
			}
			//System.out.println();
			link.add(array);
			
			
			
		}
		return link; 
	}
	
	// the next couple of algorithms is for convex hull and is from http://www.sanfoundry.com/java-program-implement-quick-hull-algorithm-find-convex-hull/
	 public ArrayList<Point> quickHull(ArrayList<Point> points)
	    {
	        ArrayList<Point> convexHull = new ArrayList<Point>();
	        if (points.size() < 3)
	            return (ArrayList<Point>) points.clone();
	 
	        int minPoint = -1, maxPoint = -1;
	        int minX = Integer.MAX_VALUE;
	        int maxX = Integer.MIN_VALUE;
	        for (int i = 0; i < points.size(); i++)
	        {
	            if (points.get(i).x < minX)
	            {
	                minX = points.get(i).x;
	                minPoint = i;
	            }
	            if (points.get(i).x > maxX)
	            {
	                maxX = points.get(i).x;
	                maxPoint = i;
	            }
	        }
	        Point A = points.get(minPoint);
	        Point B = points.get(maxPoint);
	        convexHull.add(A);
	        convexHull.add(B);
	        points.remove(A);
	        points.remove(B);
	 
	        ArrayList<Point> leftSet = new ArrayList<Point>();
	        ArrayList<Point> rightSet = new ArrayList<Point>();
	 
	        for (int i = 0; i < points.size(); i++)
	        {
	            Point p = points.get(i);
	            if (pointLocation(A, B, p) == -1)
	                leftSet.add(p);
	            else if (pointLocation(A, B, p) == 1)
	                rightSet.add(p);
	        }
	        hullSet(A, B, rightSet, convexHull);
	        hullSet(B, A, leftSet, convexHull);
	 
	        return convexHull;
	    }
	 
	    public int distance(Point A, Point B, Point C)
	    {
	        int ABx = B.x - A.x;
	        int ABy = B.y - A.y;
	        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
	        if (num < 0)
	            num = -num;
	        return num;
	    }
	 
	    public void hullSet(Point A, Point B, ArrayList<Point> set,
	            ArrayList<Point> hull)
	    {
	        int insertPosition = hull.indexOf(B);
	        if (set.size() == 0)
	            return;
	        if (set.size() == 1)
	        {
	            Point p = set.get(0);
	            set.remove(p);
	            hull.add(insertPosition, p);
	            return;
	        }
	        int dist = Integer.MIN_VALUE;
	        int furthestPoint = -1;
	        for (int i = 0; i < set.size(); i++)
	        {
	            Point p = set.get(i);
	            int distance = distance(A, B, p);
	            if (distance > dist)
	            {
	                dist = distance;
	                furthestPoint = i;
	            }
	        }
	        Point P = set.get(furthestPoint);
	        set.remove(furthestPoint);
	        hull.add(insertPosition, P);
	 
	        // Determine who's to the left of AP
	        ArrayList<Point> leftSetAP = new ArrayList<Point>();
	        for (int i = 0; i < set.size(); i++)
	        {
	            Point M = set.get(i);
	            if (pointLocation(A, P, M) == 1)
	            {
	                leftSetAP.add(M);
	            }
	        }
	 
	        // Determine who's to the left of PB
	        ArrayList<Point> leftSetPB = new ArrayList<Point>();
	        for (int i = 0; i < set.size(); i++)
	        {
	            Point M = set.get(i);
	            if (pointLocation(P, B, M) == 1)
	            {
	                leftSetPB.add(M);
	            }
	        }
	        hullSet(A, P, leftSetAP, hull);
	        hullSet(P, B, leftSetPB, hull);
	 
	    }
	 
	    public int pointLocation(Point A, Point B, Point P)
	    {
	        int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
	        if (cp1 > 0)
	            return 1;
	        else if (cp1 == 0)
	            return 0;
	        else
	            return -1;
	    }
	    
	   
	 
	   
	



	

}
