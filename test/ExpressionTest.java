import org.junit.*;
import static org.junit.Assert.*;


public class ExpressionTest {
    public ExpressionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    @Test
    public void evaluateConst(){
        F f = new F();
        assertFalse(f.evaluate());
        T t = new T();
        assertTrue(t.evaluate());
    }

    @Test
    public void testInheritance(){
        F f = new F();
        assertTrue(f instanceof ConstBoolean);
        T t = new T();
        assertTrue(t instanceof ConstBoolean);
        OR or = new OR(new T(),new F());
        assertTrue(or instanceof BinaryOperator);
        AND and = new AND(new T(),new F());
        assertTrue(and instanceof BinaryOperator);
        NOT not = new NOT(new T());
        assertTrue(not instanceof Expression);
    }

    @Test
    public void evaluateExpression(){
        OR or = new OR(new T(),new F());
        assertTrue(or.evaluate());
        or = new OR(new F(),new F());
        assertFalse(or.evaluate());
        AND and = new AND(new T(),new F());
        assertFalse(and.evaluate());
        and = new AND(new T(),new T());
        assertTrue(and.evaluate());
        NOT not = new NOT(new T());
        assertFalse(not.evaluate());
        not = new NOT(new F());
        assertTrue(not.evaluate());
    }

    @Test
    public void checkArray(){
        BooleanExpressions expressions = new BooleanExpressions();
        expressions.addExpression(new T());
        expressions.addExpression(new AND(new NOT(new T()), new NOT(new F())));
        expressions.addExpression(new OR(new T(), new AND(new F(), new F())));
        assertEquals(2,expressions.howManyAreTrue());
        expressions.addExpression(new NOT(new F()));
        expressions.addExpression(new OR(new T(),new OR(new T(),new T())));
        assertEquals(4,expressions.howManyAreTrue());
    }
}