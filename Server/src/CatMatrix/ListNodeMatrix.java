package CatMatrix;

import java.io.File;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
//@param Adds an empty cat to the matrix
	public void addMain(String name)
	{
		if(lastNode == null){
			lastNode = new ListNode(null, name, lastNode);
			lastNode.setNext(lastNode);
		}else
		{
			lastNode.setNext((new ListNode(null, name, lastNode.getNext())));
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

	public void addSub(Object object, String name, String cat)
	{
		ListNode cur = lastNode.getNext();
		while(cur.getName() != cat) {
			if(cur == lastNode) {
				throw new NoSuchElementException("Bad");
			}
			cur = cur.getNext();
		}

		tLastNode = (ListNode) cur.getValue();

		if(tLastNode == null) {

			tLastNode = new ListNode(object, name, tLastNode);
			tLastNode.setNext(tLastNode);
			cur.setValue(tLastNode);

		} else {

			tLastNode.setNext(new ListNode(object, name, tLastNode.getNext()));
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
			PrintWriter writer = null;
			//writer.println("Hi Dan");
			//writer.close();

			ListNode cur = lastNode.getNext();

			while(cur != lastNode) {

//				System.out.println("Cat:" + cur.getName());
//				System.out.println("");
				tLastNode = (ListNode) cur.getValue();

				writer = new PrintWriter("C:\\Users\\happy\\Desktop\\ComputerVisionAPI\\ComputerVisionAPI\\Server\\src\\CatMatrix\\Storage\\" + cur.getName() + ".txt", "UTF-8");

				if(cur.getValue() == null)
				{
					//System.out.println("Cat is empty");
				}
				else {

					if(tLastNode.getNext() == null) throw new NoSuchElementException("Inserted wrong");

					ListNode curt = tLastNode.getNext();

					while(curt != tLastNode) {

						//System.out.println(curt.getName() + " contains object type:" + curt.getValue().getClass() + " :" + curt.getValue() );
						writer.print(curt.getName() + "," + curt.getValue() + ",");
						curt = curt.getNext();

					}

					//System.out.println(tLastNode.getName());
					writer.print(tLastNode.getName() + "," + tLastNode.getValue() + ",");

				}
				writer.close();
				cur = cur.getNext();

			}

//			System.out.println("Cat: " + lastNode.getName());
//			System.out.println("");
			tLastNode = (ListNode) cur.getValue();

			writer = new PrintWriter("C:\\Users\\happy\\Desktop\\ComputerVisionAPI\\ComputerVisionAPI\\Server\\src\\CatMatrix\\Storage\\" + lastNode.getName() + ".txt", "UTF-8");

			if(cur.getValue() == null) System.out.println("Cat is empty");
			else {

				if(tLastNode.getNext() == null) throw new NoSuchElementException("Inserted wrong");

				ListNode curt = tLastNode.getNext();

				while(curt != tLastNode) {

					//System.out.println(curt.getName() + " contains object type:" + curt.getValue().getClass() + " :" + curt.getValue() );
					writer.print(curt.getName() + "," + curt.getValue() + ",");
					curt = curt.getNext();

				}

				writer.print(tLastNode.getName() + "," + tLastNode.getValue() + ",");
				//System.out.println(tLastNode.getName());

			}
			writer.close();

			return true;
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		//return false;
	}


	public void load(String cat)
    {


    }

    public void loadCats()
    {
        try {
            Scanner sc = new Scanner(new File("C:\\Users\\happy\\Desktop\\ComputerVisionAPI\\ComputerVisionAPI\\Server\\src\\CatMatrix\\Storage\\Cats"));

            String temp = sc.next();

            char[] s = temp.toCharArray();
            for(int i = 0; i < s.length; i++)
            {
                if (s[i] != ',')
                {
                    temp+= s[i];
                }else
                {
                    addMain(temp);
                    temp = "";
                }
            }

        }catch(Exception e)
        {
            System.out.println(e);
        }
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
