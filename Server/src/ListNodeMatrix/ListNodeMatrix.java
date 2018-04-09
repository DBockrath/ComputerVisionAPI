package ListNodeMatrix;

import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class ListNodeMatrix {
	
	private ListNode lastNode;
	private ListNode tLastNode;
	
	private ListNode defualtCat; //I think there is a way not to have this but whatever not important
	
	public ListNodeMatrix() {

		lastNode = null;
		tLastNode = null;

	}
	
	public void addMain(ListNode Node, String n) {

		if (lastNode == null) {

			lastNode = new ListNode(Node, n, lastNode);
			lastNode.setNext(lastNode);

		} else {

			lastNode.setNext(new ListNode(Node, n, lastNode.getNext()));
			lastNode = lastNode.getNext();

		}

	}
	
	public void addSub(Object o, String name, int pos) { //adds to the sub of the pos
		
		int curPos = 0;
		ListNode cur = lastNode.getNext();

		while(curPos  < pos) {

			if(cur == lastNode) {

				throw new NoSuchElementException("Bad");
				
			}

			cur = cur.getNext();
			curPos++;

		}
		
		tLastNode = (ListNode) cur.getValue();
		
		if(tLastNode == null) {
			
			tLastNode = new ListNode(o, name, tLastNode);
			tLastNode.setNext(tLastNode);
			cur.setValue(tLastNode);

		} else {

			tLastNode.setNext(new ListNode(o, name, tLastNode.getNext()));
			tLastNode = tLastNode.getNext();

		}
		
	}
	
	public Object getMainAdress(int pos) {

		ListNode cur = lastNode;
		for(int i = 0; i < pos ; i++) cur = cur.getNext();

		return cur.getValue();

	}
	
	public Object getMainName(int pos) {

		ListNode cur = lastNode;
		for(int i = 0; i < pos; i++) cur = cur.getNext();

		return cur.getName();

	}

	public Object getSubObject(int cat, int pos)
	{//Does not stop if a pos is larger than the number of nodes! it will circle around at the moment
		int curPos = 0;
		ListNode cur = lastNode.getNext();

		while(curPos < cat) {



			cur = cur.getNext();
			curPos++;

		}

		tLastNode = (ListNode) cur.getValue();
		cur = tLastNode.getNext();
		curPos = 0;
		while(curPos < pos)
		{

			curPos++;
			cur = cur.getNext();

		}

		return cur.getValue();

	}
	
	public void removeMain(int pos) { //removes a main node
		
	}
	
	public void removeMain(String o) {
		
	}
	
	public void removeSub(int posM, int posSub) { //removes a sub at a certain pos in the sub
		
	}
	
	public void removeSub(int posM, Object o) { //removes a sub that matches o\
		
	}
	
	public void printAll() {

		ListNode cur = lastNode.getNext();

		while(cur != lastNode) {
			
			System.out.println("Cat:" + cur.getName());
			System.out.println("");
			tLastNode = (ListNode) cur.getValue();

			if(cur.getValue() == null) System.out.println("Cat is empty");
			else {
				
				if(tLastNode.getNext() == null) throw new NoSuchElementException("Inserted wrong");
				
				ListNode curt = tLastNode.getNext();

				while(curt != tLastNode) {

					System.out.println(curt.getName() + " contains object type:" + curt.getValue().getClass() + " :" + curt.getValue() );
					curt = curt.getNext();

				}
				
				System.out.println(tLastNode.getName());
				
			}

			cur = cur.getNext();

		}
		
		System.out.println("Cat: " + lastNode.getName());
		System.out.println("");
		tLastNode = (ListNode) cur.getValue();

		if(cur.getValue() == null) System.out.println("Cat is empty");
		else {
			
			if(tLastNode.getNext() == null) throw new NoSuchElementException("Inserted wrong");
			
			ListNode curt = tLastNode.getNext();

			while(curt != tLastNode) {

				System.out.println(curt.getName() + " contains object type:" + curt.getValue().getClass() + " :" + curt.getValue() );
				curt = curt.getNext();

			}
			
			System.out.println(tLastNode.getName());
			
		}
		
	}

	/*
	Each cat is broken into a string with each
	 */

	public boolean saveData()
	{
		try {
			PrintWriter writer = new PrintWriter("C:\\Users\\happy\\Desktop\\ComputerVisionAPI\\ComputerVisionAPI\\Server\\src\\ListNodeMatrix\\cat1.txt", "UTF-8");
			writer.println("Hi Dan");
			writer.close();

		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		return false;
	}

	
	public int getPos(String in) {
		//replace this
		//takes a category and returns the position in the list
		switch (in) {

			case "Box":
				return 1;

			default:
				return -1; //no such cat will prompt an add cat
		}

	}

	public void printCat(int pos)
	{

	}
	
}
