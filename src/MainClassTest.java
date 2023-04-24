import org.junit.Test;
import org.junit.Assert;

public class MainClassTest extends MainClass {
    public MainClassTest() {
    }

    @Test
    public void testGetLocalNumber() {
        int result = this.getLocalNumber();
        Assert.assertEquals("Метод getLocalNumber не вернул число 14", 14, result);
    }

    @Test
    public void testGetClassNumber() {
        int classResult = this.getClassNumber();
        Assert.assertTrue("Метод getClassNumber вернул число меньше либо равное 45", classResult > 45);
    }
    @Test
    public void testGetClassString(){
        String StringResult = this.getClassString();
        Assert.assertTrue("Метод getClassString не вернул строку с подстрокой hello или Hello",
                StringResult.contains("Hello") || StringResult.contains("hello"));
    }
    @Test
    public void TestGetClassNumber() {
        int ClassResult = this.getClassNumber();
        Assert.assertTrue("Метод getClassNumber вернул число большее либо равное 45", ClassResult < 45);
    }

}
