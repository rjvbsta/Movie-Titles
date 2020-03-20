//author : Rajeev Basanta

import java.util.*;
import java.io.*;

public class MovieTitles {
	
	class Node {
		Node right;	//creating the right side of the node
		Node left; //creating the left side of the node
		
		String title; //creating the title variable
		String year; //creating the year variable
		
		//Constructor to read the title and year into Node
		public Node (String title, String year) {
			this.title = title;
			this.year = year;
			left = null;
			right = null;
		}
		
		//equals method to find if two objects are equal in title
		public boolean equals(Object obj) {
			return this.title.equals(((Node)obj).title);
		}
		
		//toString method to print the title and year
		public String toString() {
			return title + " " + year;
		}
		
		//compareTo method to return an int when comparing two objects
		public int compareTo (Object obj) {
			return this.title.compareTo(((Node)obj).title);
		}
	}
	
	Node root;	//creating the root variable
	
	//method to insert movie nodes into the subset
	public void insert(String title, String year) {        
        Node node = new Node(title, year);
        
        if(root == null)
            root = node;        
        else 
            side(root, node);
    }

    public Node getRoot() {
        return root;
    }
    
private void side(Node root, Node node) {        
        
        if(node.compareTo(root) < 0) {
            if(root.left == null) {
                root.left = node;
            } else {                
                side(root.left, node);
            }        
        } else if(node.compareTo(root) > 0) {
            if(root.right == null) {
                root.right = node;
            } else {                
                side(root.right, node);
            }
        } else {
            return;
        }
    }

    public LinkedList<Node> subset(String first, String last) {
        LinkedList<Node> list = new LinkedList<>();
        Node start = new Node(first, "");
        Node end = new Node(last, "");
        addToSubset(list, start, end, root);
        return list;
    }

    
    private LinkedList<Node> addToSubset(LinkedList<Node> list, Node start, Node end, Node current) {        
        if(current == null) {
        
        } 
        else if(current.compareTo(start) >= 0 && current.compareTo(end) <= 0) {            
            addToSubset(list, start, end, current.left);            
            list.add(current);            
            addToSubset(list, start, end, current.right);
        }
        return list;
    }   
	
	public static void main (String[] args) throws IOException {
		MovieTitles binaryTree = new MovieTitles();
        File input = new File("movies.csv");
        BufferedReader read = new BufferedReader(new FileReader(input));
        read.readLine();	//skips the first line
        String readLine = read.readLine();
        while(readLine != null) {
            String title;
            String year;
            if(readLine.lastIndexOf("(") == -1) {	//determines if there is a year listed
                title = readLine.substring(readLine.indexOf(",") + 1, readLine.lastIndexOf(","));
                year = "(Unknown)";
            } 
            else {	//if there is a year, finds both movie title and year 
                title = readLine.substring(readLine.indexOf(",") + 1, readLine.lastIndexOf("(") - 1);
                year = readLine.substring(readLine.lastIndexOf("("), readLine.lastIndexOf(")") + 1);
            }
            binaryTree.insert(title, year);	//inserting the movie title and year into the tree
            readLine = read.readLine(); //reads the next line
        }        
        LinkedList<Node> movies = binaryTree.subset("Jumanji", "What's Eating Gilbert Grape");
        for(Node o : movies) {
            System.out.println(o);
        }       
       
    }
}
	

