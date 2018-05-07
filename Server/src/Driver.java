import CatMatrix.ListNode;
import CatMatrix.ListNodeMatrix;
import NeuralNetwork.BitString;

import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {

        ListNodeMatrix LNM = new ListNodeMatrix();

        LNM.addMain("dan");
        LNM.addMain("jenna");
        LNM.addMain("Ronnie");
        LNM.addSub(new BitString("1001010101010101011110100010011110100010010010010010010010111110"), "Neel", "dan");
        LNM.addSub(new BitString("10010101010101010111101000100111101000100111111111111110010010010010010111110"), "45", "dan");
        LNM.addSub(new BitString("100101010101010101111010001001111010001000010111110"), "Neel4567654345", "jenna");
        LNM.addSub(new BitString("11111111111111111111111111111111111111111111111111111111111"), "neel2.0", "Ronnie");
//        LNM.saveData();

        LNM.printAll();

        LNM.loadBitString("dan");

        //LNM.printAll();


        System.out.println("Finished!");
        while(true)
        {
            break;
        }


    }

}