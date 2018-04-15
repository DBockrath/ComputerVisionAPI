import CatMatrix.ListNode;
import CatMatrix.ListNodeMatrix;

import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {

        ListNodeMatrix LNM = new ListNodeMatrix();

        LNM.loadCats();
        LNM.printAll();
        LNM.loadString("0");
        LNM.loadString("1");
        LNM.loadString("2");
        LNM.printAll();
        LNM.deloadAllCats();
        LNM.printAll();

    //    LNM.loadCats();
   //     LNM.printAll();
   /*    ArrayList<ListNode> list = new ArrayList<>();
        for(int k = 0; k < 5000000; k++)
        {
            list.add(new ListNode(k, "" + k,null));
        }
*/
/*

        for(int i = 0; i < 5; i++)
        {

            LNM.addMain("" + i);
            System.out.println("At cat "+ i);
            for(int k = 0; k < 10; k++)
                {
                LNM.addSub(new BitString("1111111101010101010101010101010101010101010101010101000000000011111111010010100000101010"), "" + k, i);

            }
        }

        LNM.saveData();
        //LNM.printAll();

*/

        System.out.println("Finished!");
        while(true)
        {
            break;
        }


    }

}