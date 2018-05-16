package CatMatrix;

import NeuralNetwork.BitString;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

class ListNodeMatrix {

    // TODO: Need to make body of getSubNodes() and numCats()
	
	private ListNode lastNode;
	private ListNode tLastNode;

	public ListNode getLastNode()
	{
		return lastNode;
	}

	
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
		while(!cur.getName().equals(cat)) {

			if(cur == lastNode && !cur.getName().equals(cat)) {
				throw new NoSuchElementException("Bad");
			}else if(cur.getName().equals(cat))
            {
                break;
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
	
	public Object getMainValue(int pos) {

		ListNode cur = lastNode;
		for(int i = 0; i < pos; i++) cur = cur.getNext();

		return cur.getValue();

	}

	public Object getMainValue(String name)
	{
		ListNode cur = lastNode.getNext();
		while(cur != lastNode) {
			if (cur.getName().equals(name))
				return cur.getName();
		}

		if(lastNode.getName().equals(name))
		{
			return cur.getName();
		}

		return null; //TODO change to another statement
	}

	public Object getMainName(String name)
	{
		ListNode cur = lastNode.getNext();
		while(cur != lastNode) {
			if (cur.getName().equals(name))
				return cur.getName();
		}

		if(lastNode.getName().equals(name))
		{
			return cur.getName();
		}

		return null; //TODO change to another statement
	}

	public String getMainName(int pos) {

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

		loadString(cur.getName());

		tLastNode = (ListNode) cur.getValue();
		cur = tLastNode.getNext();
		curPos = 0;
		while(curPos < pos)
		{

			curPos++;
			cur = cur.getNext();

		}

		deloadAllCats();

		return cur.getValue();

	}

	public Object getSubObject(String cat, String name)
	{
		ListNode cur = lastNode.getNext();

		while(!cur.getName().equals(cat)) {
			if(cur == lastNode && !lastNode.getName().equals(cat))
			{
				throw new NoSuchElementException("that cat does not exist");

			}else if(lastNode.getName().equals(cat) && cur == lastNode)
			{
				break;
			}
			cur = cur.getNext();

		}

		loadString(cur.getName()); //loads in the cat so it can be searched

		tLastNode = (ListNode) cur.getValue();
		cur = tLastNode.getNext();

		while(!cur.getName().equals(name))
		{

			if(cur == tLastNode && !tLastNode.getName().equals(name))
			{
				throw new NoSuchElementException("That item does not exist");
			}else if(tLastNode == cur && tLastNode.getName().equals(name))
			{
				break;
			}

			cur = cur.getNext();

		}

		deloadAllCats();
		return cur.getValue();

	}

	public Object getSubName(String cat, Object input)
	{
		return null;
	}

	public void removeCat(String o) {
		
	}

	public void removeSub(String cat, String name) { //removes a sub that matches o\
		ListNode cur = lastNode.getNext();

		while(!cur.getName().equals(cat))
		{
			if(lastNode.getName().equals(cat))
			{
				cur = lastNode;
				break;
			}
			cur = cur.getNext();
		}

		loadString(cur.getName());

		tLastNode = (ListNode) cur.getValue();
		cur = tLastNode.getNext();



	}
	
	public void printAll() {

		ListNode cur = lastNode.getNext();

		while(cur != lastNode) {

			System.out.println("Cat:" + cur.getName());
			System.out.println("");
			tLastNode = (ListNode) cur.getValue();

			if (cur.getValue() == null) System.out.println("Cat is empty");
			else {

				if (tLastNode.getNext() == null) throw new NoSuchElementException("Inserted wrong");

				ListNode curt = tLastNode.getNext();

				while (curt != tLastNode) {

					System.out.println(curt.getName() + " contains object type:" + curt.getValue().getClass() + " :" + curt.getValue());
					curt = curt.getNext();

					System.out.println(tLastNode.getName());

				}

				cur = cur.getNext();

			}

			System.out.println("Cat: " + lastNode.getName());
			System.out.println("");
			tLastNode = (ListNode) cur.getValue();

			if (cur.getValue() == null) System.out.println("Cat is empty");
			else {

				if (tLastNode.getNext() == null) throw new NoSuchElementException("Inserted wrong");

				ListNode curt = tLastNode.getNext();

				while (curt != tLastNode) {

					System.out.println(curt.getName() + " contains object type:" + curt.getValue().getClass() + " :" + curt.getValue());
					curt = curt.getNext();

				}

				System.out.println(tLastNode.getName());

			}
		}
		
	}

	/*
	Each cat is broken into a string with each
	 */

	public boolean saveData()
	{
		try {
			PrintWriter writer = null;

			String cats = "";
			ListNode cur = lastNode.getNext();

			while(cur != lastNode) {

//				System.out.println("Cat:" + cur.getName());
//				System.out.println("");
				tLastNode = (ListNode) cur.getValue();
				cats += cur.getName();
				cats += ",";

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
            cats += cur.getName();
            cats+= ',';

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

			//prints out the cats
            writer = new PrintWriter("C:\\Users\\happy\\Desktop\\ComputerVisionAPI\\ComputerVisionAPI\\Server\\src\\CatMatrix\\Storage\\cats.txt", "UTF-8");
            writer.print(cats);
            writer.close();

			return true;
		}catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		//return false;
	}

	public void reClassify(String cat, String name, String newCat)
	{ //not done yet

	}

	public void loadString(String cat)
    { //loads in the cats as string
        ArrayList<String> input = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File("C:\\Users\\happy\\Desktop\\ComputerVisionAPI\\ComputerVisionAPI\\Server\\src\\CatMatrix\\Storage\\" + cat + ".txt"));

            String temp = "";
            char[] s = null;
            while(sc.hasNext())
            {
                s = sc.next().toCharArray();

                for(int i = 0; i < s.length; i++)
                {
                    if (s[i] != ',')
                    {
                        temp += s[i];
                    }else
                    {
                        //System.out.println(temp);
                        input.add(temp);
                        temp = "";
                    }
                }

            }

            while(!input.isEmpty())
            {
                String temp2 = input.remove(0);
                addSub(input.remove(0), temp2,"" + cat);
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    public void loadBitString(String cat)
	{
		ArrayList<BitString> input = new ArrayList<>();

		try {
			Scanner sc = new Scanner(new File("C:\\Users\\happy\\Desktop\\ComputerVisionAPI\\ComputerVisionAPI\\Server\\src\\CatMatrix\\Storage\\" + cat + ".txt"));

			String temp = "";
			char[] s = null;
			while(sc.hasNext())
			{
				s = sc.next().toCharArray();

				for(int i = 0; i < s.length; i++)
				{
					if (s[i] != ',')
					{
						temp += s[i];
					}else
					{
						//System.out.println(temp);
						input.add(new BitString(temp));
						temp = "";
					}
				}

			}

			while(!input.isEmpty())
			{
				String temp2 = input.remove(0).toString();
				addSub(input.remove(0), temp2,"" + cat);
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

    public void loadCats()
    {
        try {
            Scanner sc = new Scanner(new File("C:\\Users\\happy\\Desktop\\ComputerVisionAPI\\ComputerVisionAPI\\Server\\src\\CatMatrix\\Storage\\cats.txt"));

            String temp = "";

            char[] s = sc.next().toCharArray();
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


    public void deloadAllCats()
    {
        ListNode cur = lastNode.getNext();

        while(cur != lastNode)
        {
            cur.setValue(null);
            cur = cur.getNext();
        }
        lastNode.setValue(null);
    }

    public void deloadCat(String cat)
    {

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

	public void printCat(String cat)
	{

	}
	
}
