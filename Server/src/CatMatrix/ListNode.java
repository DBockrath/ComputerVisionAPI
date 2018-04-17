package CatMatrix;

public class ListNode {

	//Private members
    private Object value;
	private ListNode next;
	
	private String name;
	
	//Constructor
	public ListNode(Object value, ListNode next) {

		this.value = value;
		this.next = next;
		this.name = "n/a";

	}
	
	public ListNode(Object value, String name, ListNode next) {

		this.value = value;
		this.next = next;
		this.name = name;

	}

	public ListNode()
	{

	}
	
	//Getters and Setters
	public ListNode getNext() {
		return next;
	}
	
	public void setNext(ListNode next) {
		this.next = next;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

}
