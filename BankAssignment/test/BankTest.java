

import static org.junit.Assert.*;

import bankingApp.*;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
	protected Currency CAD;
	protected Currency HKD;
	protected Bank RBC;
	protected Bank TD;
	protected Bank HSBC;
	
	
	@Before
	public void setUp() throws Exception {
		
		// setup some test currencies
		this.HKD = new Currency("HKD", 0.13);
		this.CAD = new Currency("CAD", 0.75);
		
		// setup test banks
		this.RBC = new Bank("Royal Bank of Canada", CAD);
		this.TD = new Bank("TD Bank", CAD);
		this.HSBC = new Bank("Hong Kong Shanghai Banking Corporation", HKD);
		
		// add sample customers to the banks
		
		
		// HINT:  uncomment these lines AFTER you test the openAccount() function
		// You can quickly uncomment / comment by highlighting the lines of code and pressing 
		// CTRL + / on your keyboard  (or CMD + / for Macs)
		
		this.RBC.openAccount("Marcos");
		this.RBC.openAccount("Albert");
		this.TD.openAccount("Jigesha");
		this.HSBC.openAccount("Pritesh");
		RBC.openAccount("Simran");
		TD.openAccount("Naina");
		RBC.openAccount("Farzaad");
		RBC.deposit("Simran",new Money(200,CAD));
		RBC.deposit("Farzaad",new Money(500,CAD));
		TD.deposit("Naina",new Money(300,CAD));
	}

	@Test
	public void testGetName() {

		assertEquals("Royal Bank of Canada",RBC.getName());
		assertEquals("TD Bank",TD.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(CAD,RBC.getCurrency());
		assertEquals(HKD,HSBC.getCurrency());

	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.
		assertEquals(200,RBC.getBalance("Simran"),0.001);
		assertEquals(500,RBC.getBalance("Farzaad"),0.001);
		assertEquals(300,TD.getBalance("Naina"),0.001);

	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.

		RBC.deposit("Simran",new Money(100,CAD));
		assertEquals(300,RBC.getBalance("Simran"),0.001);

		RBC.deposit("Marcos",new Money(100,CAD));
		assertEquals(300,RBC.getBalance("Simran"),0.001);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.

		RBC.withdraw("Simran",new Money(50,CAD));
		assertEquals(150.0,RBC.getBalance("Simran"),0.001);

		RBC.withdraw("Marcos",new Money(50,CAD));
		assertEquals(150.0,RBC.getBalance("Simran"),0.001);
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.

		assertEquals(200,RBC.getBalance("Simran"),0.001);
		assertEquals(500,RBC.getBalance("Farzaad"),0.001);
		assertEquals(300,TD.getBalance("Naina"),0.001);
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		// Note: You should test both types of transfers:
		// 1. Transfer from account to account
		// 2. Transfer between banks
		// See the Bank.java file for more details on Transfers
		RBC.transfer("Simran","Albert",new Money(105,CAD));
		assertEquals(105,RBC.getBalance("Albert"),0.01);

		RBC.transfer("Simran",HSBC,"Pritesh",new Money(50,CAD));
		assertEquals(288.46,HSBC.getBalance("Pritesh"),0.01);


	}
	
}
