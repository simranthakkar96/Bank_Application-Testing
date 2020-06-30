

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bankingApp.Currency;
import bankingApp.Money;

public class MoneyTest {
	protected Currency CAD, HKD, NOK, EUR;
	protected Money CAD100, EUR10, CAD200, EUR20, CAD0, EUR0, CADnegative100,CAD152;
	
	@Before
	public void setUp() throws Exception {
		// setup sample currencies
		CAD = new Currency("CAD", 0.75);
		HKD = new Currency("HKD", 0.13);
		EUR = new Currency("EUR", 1.14);
		
		// setup sample money amounts
		CAD100 = new Money(100, CAD);
		EUR10 = new Money(10, EUR);
		CAD200 = new Money(200, CAD);
		EUR20 = new Money(20, EUR);
		CAD0 = new Money(0, CAD);
		EUR0 = new Money(0, EUR);
		CAD152 = new Money(15.2, CAD);
		CADnegative100 = new Money(-100, CAD);
	}

	@Test
	public void testGetAmount() {

		assertEquals(100,CAD100.getAmount(),0.001);
		assertEquals(15.2,CAD152.getAmount(),0.001);
	}

	@Test
	public void testGetCurrency() {

		assertEquals(CAD,CAD100.getCurrency());
		assertEquals(EUR,EUR20.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("100.0CAD",CAD100.toString());

	}

	@Test
	public void testGetUniversalValue() {

		assertEquals(75,CAD100.getUniversalValue(),0.001);
		assertEquals(22.799,EUR20.getUniversalValue(),0.001);
	}

	@Test
	public void testEqualsMoney() {
		assertEquals(true,EUR10.equals(CAD152));
	}

	@Test
	public void testAdd() {
		assertEquals(0,Double.compare(20,EUR10.add(new Money(15.2,CAD)).getAmount()));
		assertEquals(0, CharSequence.compare("EUR",EUR10.add(new Money(15.2,CAD)).getCurrency().getName()));

		assertEquals(0,Double.compare(30,EUR10.add(EUR20).getAmount()));
		assertEquals(0, CharSequence.compare("EUR",EUR10.add(EUR20).getCurrency().getName()));
	}

	@Test
	public void testSubtract() {
		assertEquals(0, Double.compare(10, EUR20.subtract(new Money(15.2, CAD)).getAmount()));
		assertEquals(0, CharSequence.compare("EUR", EUR20.subtract(new Money(15.2, CAD)).getCurrency().getName()));

		assertEquals(0, Double.compare(10, EUR20.subtract(EUR10).getAmount()));
		assertEquals(0, CharSequence.compare("EUR", EUR20.subtract(EUR10).getCurrency().getName()));
	}
	@Test
	public void testIsZero() {
		assertEquals(true,CAD0.isZero());
		assertEquals(false,EUR20.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals(0, Double.compare(-10.,EUR10.negate().getAmount()));
		assertEquals(0, CharSequence.compare("EUR",EUR10.negate().getCurrency().getName()));
	}

	@Test
	public void testCompareTo() {
		assertEquals(0,CAD152.compareTo(EUR10));
		assertEquals(-1,CAD152.compareTo(CAD100));
		assertEquals(1,CAD152.compareTo(CAD0));

	}
}
