import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Kartik
 *
 */

public class bst {
	public static  Node root;
	public bst(){
		this.root = null;
	}
	
	public boolean find(int id){
		Node current = root;
		while(current!=null){
			if(current.data==id){
				return true;
			}else if(current.data>id){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
	}
	public boolean delete(int id){
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while(current.data!=id){
			parent = current;
			if(current.data>id){
				isLeftChild = true;
				current = current.left;
			}else{
				isLeftChild = false;
				current = current.right;
			}
			if(current ==null){
				return false;
			}
		}
		//We reach here if we find a node.
		//Case 1: if there are no children to the node to be deleted
		if(current.left==null && current.right==null){
			if(current==root){
				root = null;
			}
			if(isLeftChild ==true){
				parent.left = null;
			}else{
				parent.right = null;
			}
		}
		//Case 2 : if there is only one child to the node to be deleted
		else if(current.right==null){
			if(current==root){
				root = current.left;
			}else if(isLeftChild){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}
		else if(current.left==null){
			if(current==root){
				root = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
		}else if(current.left!=null && current.right!=null){
			
			
			Node successor	 = getSuccessor(current);
			if(current==root){
				root = successor;
			}else if(isLeftChild){
				parent.left = successor;
			}else{
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		return true;		
	}
	
	public Node getSuccessor(Node deleleNode){
		Node successsor =null;
		Node successsorParent =null;
		Node current = deleleNode.right;
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		
		if(successsor!=deleleNode.right){
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}
	public void insert(int id){
		Node newNode = new Node(id);
		if(root==null){
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			if(id<current.data){				
				current = current.left;
				if(current==null){
					parent.left = newNode;
					return;
				}
			}else{
				current = current.right;
				if(current==null){
					parent.right = newNode;
					return;
				}
			}
		}
	}
	public void display(Node root){
		if(root!=null){
			display(root.left);
			System.out.print(" " + root.data);
			display(root.right);
		}
	}
	
	public static void main(String arg[]) throws IOException, NumberFormatException{
		
		long startTime = System.nanoTime();
		
		bst b = new bst();
		
		try{
		BufferedReader br = new BufferedReader(new FileReader("C:\\Kartik\\inputfile.txt"));
		String sCurrentLine; 
				
		
			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.startsWith("I")){
					b.insert(Integer.parseInt(sCurrentLine.substring(2)));
					
					System.out.println("Inserted: "+sCurrentLine.substring(2));
					
				
				}
				else if(sCurrentLine.startsWith("D")){
					b.delete(Integer.parseInt(sCurrentLine.substring(2)));
					System.out.println("Deleted: "+Integer.parseInt(sCurrentLine.substring(2)));
					
				}
				else if(sCurrentLine.startsWith("F")){
					b.find(Integer.parseInt(sCurrentLine.substring(2)));
					if(b.find(Integer.parseInt(sCurrentLine.substring(2)))==true){
						System.out.println("Found: "+sCurrentLine.substring(2));
					}else{
						System.out.println("Not Found: "+sCurrentLine.substring(2));
					}
					
					
				}
				
			}br.close();
			
		
		}
		catch(NullPointerException np){
		}
		catch (FileNotFoundException e) {
            System.err.println("Unable to find the file: fileName");
        } catch (IOException e) {
            System.err.println("Unable to read the file: fileName");
        }catch (NumberFormatException nfe) {
            System.err.println("Incorrect format");
        }
			long endTime = System.nanoTime();
		long duration = endTime - startTime;
		double seconds = (double)duration / 1000000000.0;
		System.out.println("Duration(s)= " + seconds);
		}
}


class Node{
	int data;
	Node left;
	Node right;	
	public Node(int data){
		this.data = data;
		left = null;
		right = null;
	}
}

