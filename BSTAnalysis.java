import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class BSTAnalysis<Key extends Comparable<Key>,Value>
{

private Node root;
public int sum=0;
	private class Node
	{
		private Key key;
		private Value val;
		private Node left,right;
				
		public Node(Key key,Value val)
		{
			this.key=key;
			this.val=val;
		}
	}
	
	public void put(Key key,Value val)
	{
		root=put(root,key,val);
	}

	private Node put(Node x,Key key,Value val)
	{
		if(x==null)
			return new Node(key,val);
		int cmp=key.compareTo(x.key);
		if(cmp<0)
			x.left=put(x.left,key,val);
		else if (cmp>0) 
			x.right=put(x.right,key,val);
		else
			x.val=val;
		return x;
	}

	public Value get(Key key)
	{
		return get(root,key);
	}

	private Value get(Node x,Key key)
	{
		if(x==null) return null;
		int cmp=key.compareTo(x.key);
		if(cmp<0) return get(x.left,key);
		else if(cmp>0) return get(x.right,key);
		else return x.val;
	}
	

void depth(Node node,int depth)
{
if(node==null)
return ;
depth(node.left,depth+1);
depth(node.right,depth+1);
sum+=depth;
}

int getPathLength()
{
	depth(root,0);
	return sum;
}

public static void main(String[] args) {

	String COMMA_DELIMITER = ",";
	String NEW_LINE_SEPARATOR = "\n";
	String fileName="output.csv";
	//CSV file header
	String FILE_HEADER = "operations,experimental compares,expected";
	FileWriter fileWriter = null;
				
	try 
	{
		
	fileWriter = new FileWriter(fileName);
	fileWriter.append(FILE_HEADER.toString());
	//Add a new line separator after the header
	fileWriter.append(NEW_LINE_SEPARATOR);
	int n;
	float compares=0;
	float avgsum=0;
	for(n=100;n<=10000;n+=100)
{		
		
		//b.sum=0;
		avgsum=0;
		//if(n%100==0)
		System.out.println("n="+n);
		int i=0;
		for(i=0;i<1000;i++)
		{	
			BSTAnalysis<Integer,Character> b=new BSTAnalysis<Integer,Character>();
	
			
			for(int j=n;j>0;j--)
		{

			Random rand = new Random(); 
			int key = rand.nextInt(Integer.MAX_VALUE)+Integer.MIN_VALUE;//random numbers from 1 to n
			char value = (char)(rand.nextInt(25)+97);
			b.put(key,value);
  		}
  		
  		int l=b.getPathLength();
  		compares=(float)l/n+1;
  		avgsum+=compares;

  	}
  			String c="";
  			String ns="";
  			ns+=n;
  			//c+=compares;
  			//if(n%100==0)
  			//System.out.println("avgsum/1000="+avgsum/1000);
  			c+=avgsum/1000;
  			double ex=1.39*Math.log10(n)/Math.log10(2)-1.85;
  			String e="";
  			e+=ex;
			fileWriter.append(ns);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(c);
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(e);
			fileWriter.append(NEW_LINE_SEPARATOR);
		

  		
  	//	avgsum+=compares;

  		//}
  		//bst is created
  		//putting compares and N in a csv file
  	// 		String c="";
  	// 		String ns="";
  	// 		ns+=n;
  	// 		//if(n%100==0)
  	// 		//System.out.println("avgsum/1000="+avgsum/1000);
  	// 		//c+=avgsum/1000;
  	// 		double ex=1.39*Math.log10(n)/Math.log10(2)-1.85;
  	// 		String e="";
  	// 		e+=ex;
			// fileWriter.append(ns);
			// fileWriter.append(COMMA_DELIMITER);
			// fileWriter.append(c);
			// fileWriter.append(COMMA_DELIMITER);
			// fileWriter.append(e);
			// fileWriter.append(NEW_LINE_SEPARATOR);
			// avgsum=0;
		//	compares=0;
}


} 
catch (Exception e) 
{
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} 

finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}	
} 
	
}


}