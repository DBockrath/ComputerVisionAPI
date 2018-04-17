package CatMatrix;

public class DoubleNode extends ListNode{

    //it is not possible to directly create a doubleNode only convert an exsisting node

    private ListNode orig = null;
    private ListNode par = null;

    public DoubleNode(ListNode in, ListNode par)
    {
        orig = in;
        this.par = par;
    }

    public ListNode getPar()
    {
        return par;
    }

}
