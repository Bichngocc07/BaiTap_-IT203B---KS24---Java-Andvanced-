import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class TestCalculate {
    @BeforeAll
    public static void notification(){

    }
    //test về add
    @Test
    public void testFeatureAdd(){
        int result = Calculate.add(5,7);
        Assert.assertEquals(result,12);
    }
    @Test
    public void testFeatureAdd2(){
        int result = Calculate.add(5,3);
        Assert.assertEquals(result,7);
    }

}
