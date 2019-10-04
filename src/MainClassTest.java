import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {


    @Test
    public void testGetLocalNumber(){
        MainClass mainClass= new MainClass();
        int localNumber = mainClass.getLocalNumber();
        Assert.assertEquals("The result of execution getLocalNumber() function doesn't correspond to required value: 14 ", localNumber, 14);

    }
}
