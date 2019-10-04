import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {


    @Test
    public void testGetLocalNumber(){
        System.out.println("Test testGetLocalNumber is started");
        MainClass mainClass= new MainClass();
        int localNumber = mainClass.getLocalNumber();
        Assert.assertEquals("The result of execution getLocalNumber() function doesn't correspond to required value: 14 ", localNumber, 14);

    }

    @Test
    public void testGetClassNumber(){
        System.out.println("Test testGetClassNumber is started");
        MainClass mainClass= new MainClass();
        int classNumber = mainClass.getClassNumber();
        Assert.assertTrue("The class_number is not larger than 45. class_number is "+classNumber, mainClass.isNumberLargerThan(classNumber, 45));
    }

    @Test
    public void testGetClassString(){
        System.out.println("Test testGetClassNumber is started");
        MainClass mainClass= new MainClass();
        String classString = mainClass.getClassString();
        Assert.assertTrue("The class_string doesn't contain substring hello or Hello. The string is "+ classString, (mainClass.isContaining(classString, "hello"))||mainClass.isContaining(classString, "Hello"));
    }
}
