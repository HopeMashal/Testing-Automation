package testng;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static testng.App.CalculateSum;;

public class AppTest 
{
    @Parameters({"first","second"})
    @Test
    public void checkCalculateSum(int first, int second){
        assertEquals(CalculateSum(first, second),Integer.sum(first,second));
    }
}
