import org.testng.Assert;
import org.testng.annotations.*;

public class CalculatorTest {

    protected Calculator calculator;

    @BeforeClass(groups = "config")
    public void setUp(){
        calculator = new Calculator();
        System.out.println("BeforeClass run");
    }

    @Parameters({"first", "second", "expected"})
    @Test(groups = {"positive"}, dataProvider = "dataForTestLongSumPositive")
    public void testLongSumPositive(long first, long second, long expected){
        long sum = calculator.sum(first, second);
        Assert.assertEquals(sum, expected);
    }

    @Test(groups = {"positive"}, description = "double sub: 2.7 - 0.4 = 2.3000000000000003")
    public void testDoubleSumPositive(){
        double sum = calculator.sum(5.0, 1.0);
        Assert.assertEquals(sum, 6.0, "expected 6.0");
    }

    @Test(groups = {"positive"}, description = "long difference: 500L - 400L = 100L")
    public void testLongDifferencePositive(){
        long sub = calculator.sub(500L, 400L);
        Assert.assertEquals(sub, 100L, "expected 100L");
    }

    @Test(groups = {"positive"}, description = "double sub: 2.7 - 0.4 = 2.3000000000000003")
    public void testDoubleDifferencePositive(){
        double sub = calculator.sub(2.7, 0.4);
        Assert.assertEquals(sub, 2.3000000000000003, "expected 2.3000000000000003");
    }

    @Test(groups = {"positive"}, description = "long multiplication: 50L * 20L = 1000L")
    public void testLongMultiplicationPositive(){
        long mult = calculator.mult(50L, 20L);
        Assert.assertEquals(mult, 1000L, "expected 1000L");
    }

    @Test(groups = {"positive"}, description = "double multiplication: 2.0 * 2.2 = 4.4")
    public void testDoubleMultiplicationPositive(){
        double mult = calculator.mult(2.0, 2.2);
        Assert.assertEquals(mult, 4.4, "expected 4.4");
    }

    @Test(groups = {"positive"}, description = "long division: 10000L / 5000L = 2L")
    public void testLongDivisionPositive(){
        long div = calculator.div(10000L, 5000L);
        Assert.assertEquals(div, 2L, "expected 2L");
    }

    @Test(groups = {"negative"}, expectedExceptions = NumberFormatException.class, expectedExceptionsMessageRegExp = "Attempt to divide by zero")
    public void testLongDivisionOnZeroNegative(){
        calculator.div(5000L, 0L);
    }

    @Test(groups = {"positive"}, description = "double division: 4.4 / 2.2 = 2.0")
    public void testDoubleDivisionPositive(){
        double div = calculator.div(4.4, 2.2);
        Assert.assertEquals(div, 2.0, "expected 2.0");
    }

    @Test(groups = {"negative"}, expectedExceptions = NumberFormatException.class, expectedExceptionsMessageRegExp = "Attempt to divide by zero")
    public void testDoubleDivisionOnZeroNegative(){
        calculator.div(5.0, 0);
    }

    @Test(groups = {"positive"}, description = "double pow: 2.0 pow 2.2 = 4.59479341998814")
    public void testDoublePowPositive(){
        double pow = calculator.pow(2.0, 2.2);
        Assert.assertEquals(pow, 4.59479341998814, "expected 4.59479341998814");
    }

    @Test(groups = {"positive"}, description = "double sqrt: sqrt(4.4) = 2.0976176963403033")
    public void testDoubleSqrtPositive(){
        double sqrt = calculator.sqrt(4.4);
        Assert.assertEquals(sqrt, 2.0976176963403033, "expected 2.0976176963403033");
    }

    @Test(groups = {"positive"}, description = "double tangens: tg(pi) = 0.0")
    public void testDoubleTgPositive(){
        double tg = calculator.tg(3.1415926535897932384626433832795); //(pi = 3.1415926535897932384626433832795      or 180 degree)
        Assert.assertEquals(tg, 0.0, "expected 0.0");
    }

    @Test(groups = {"positive"}, description = "double catangens: ctg(pi/2) = 0.0")
    public void testDoubleCtgPositive(){
        double ctg = calculator.ctg(1.5707963267948966192313216916398); // (pi/2 = 1.5707963267948966192313216916398    or 90 degree)
        Assert.assertEquals(ctg, 0.0, "expected 0.0");
    }

    @Test(groups = {"positive"}, description = "double cos: cos(pi/3) = 0.5")
    public void testDoubleCosPositive(){
        double cos = calculator.cos(1.0471975511965977461542144610932); // (pi/3 = 1.0471975511965977461542144610932    or 60 degree)
        Assert.assertEquals(cos, 0.5, "expected 0.5");
    }

    @Test(groups = {"positive"}, description = "double sin: sin(pi/6) = 0.5")
    public void testDoubleSinPositive(){
        double sin = calculator.sin(0.52359877559829887307710723054658); // (pi/6 = 0.52359877559829887307710723054658 or 30 degree)
        Assert.assertEquals(sin, 0.5, "expected 0.5");
    }

    ////////////////////////////////////testing of "isPositive method"

    @Test(groups = {"positive"}, description = "positive value = true: 55L")
    public void positiveTestOfBooleanIsPositiveWithPositiveValue(){
        boolean isPositive = calculator.isPositive(55L);
        Assert.assertEquals(isPositive, true, "expected true");
    }

    @Test(groups = {"negative"}, description = "zero = false: 0L")
    public void negativeTestOfBooleanIsPositiveWithZero(){
        boolean isPositive = calculator.isPositive(0L);
        Assert.assertEquals(isPositive, false, "expected false");
    }

    @Test(groups = {"negative"}, description = "negative value = false: -2L")
    public void negativeTestOfBooleanIsPositiveWithNegativeValue(){
        boolean isPositive = calculator.isPositive(-2L);
        Assert.assertEquals(isPositive, false, "expected false");
    }

    ////////////////////////////////////testing of "isNegative method"

    @Test(groups = {"negative"}, description = "positive value = false: 55L")
    public void negativeTestOfBooleanIsNegativeWithPositiveValue(){
        boolean isNegative = calculator.isNegative(55L);
        Assert.assertEquals(isNegative, false, "expected false");
    }

    @Test(groups = {"negative"}, description = "zero = false: 0L")
    public void negativeTestOfBooleanIsNegativeWithZero(){
        boolean isNegative = calculator.isNegative(0L);
        Assert.assertEquals(isNegative, false, "expected false");
    }

    @Test(description = "negative value = true: -2L")
    public void positiveTestOfBooleanIsNegativeWithNegativeValue(){
        boolean isNegative = calculator.isNegative(-2L);
        Assert.assertEquals(isNegative, true, "expected true");
    }

    @AfterClass(groups = "config")
    public void afterClass(){
        calculator = null;
        System.out.println("AfterClass run");
    }

    @DataProvider(name = "dataForTestLongSumPositive")
    public Object[][] dataForTestLongSumPositive() {
        return new Object[][] {
                {1L, 2L, 3L},
                {-2L, -8L, -10L},
                {-2L, 3L, 1L},
                {5L, -7L, -2L},
                {2L, -2L, 0L}
        };
    }
}
