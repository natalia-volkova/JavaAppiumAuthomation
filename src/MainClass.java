import java.util.SplittableRandom;

public class MainClass {

    private int class_number=20;
    private String class_string = "Hello, world";

    public int getLocalNumber(){
        return 14;
    }

    public int getClassNumber(){
        return this.class_number;
    }

    public boolean isNumberLargerThan(int number, int value) {
        if (number > value) return true;
        else return false;
    }

    public String getClassString(){
        return this.class_string;
    }





}
