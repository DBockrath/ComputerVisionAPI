import CatMatrix.ListNode;
import CatMatrix.ListNodeMatrix;

import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {

     /*   ArrayList<ListNode> list = new ArrayList<>();
        for(int k = 0; k < 7500000; k++)
        {
            list.add(new ListNode(k, "" + k,null));
        } */


        ListNodeMatrix LNM = new ListNodeMatrix();
        for(int i = 0; i < 1; i++)
        {
            LNM.addMain("" + i);
            for(int k = 0; k < 5000; k++)
                {
                LNM.addSub(k, "" + k, i);
            }
        }

       // LNM.saveData();
       // LNM.printAll();



        System.out.println("Finished!");
        while(true)
        {

        }
    }

}