package CatMatrix;

public class CatMatrix extends ListNodeMatrix{


    //default, no neural net
    private String name = null;

    public CatMatrix(String name)
    {
        ListNodeMatrix LMM = new ListNodeMatrix();
        this.name = name;
    }

}
