package pt.ulisboa.tecnico.learnjava.bank.bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;

public class GetBankByCodeMethodTest {

	private static final String BANK_CODE = "CGD";
	private static final String BANK_CODE2 = "NBA";
	private Bank bank;
	private Bank bank_two;
	
	@Before
	public void setUp() throws BankException {
		this.bank = new Bank(BANK_CODE);
		this.bank_two = new Bank(BANK_CODE2);
	}

	@Test
	public void test1a() throws BankException {
		assertEquals(this.bank, Bank.getBankByCode(BANK_CODE));
		assertEquals(this.bank_two, Bank.getBankByCode(BANK_CODE2));
	}
	
	@Test
	public void test1b() throws BankException {
		assertNull(Bank.getBankByCode("BPI"));
	}
	
	@Test
	public void test1c() throws BankException {
		try {
			Bank.getBankByCode(null);
			fail();
		} catch (BankException e) {
			//passes
		}
		
	}

	@After
	public void tearDown() {
		Bank.clearBanks();
	}

}
