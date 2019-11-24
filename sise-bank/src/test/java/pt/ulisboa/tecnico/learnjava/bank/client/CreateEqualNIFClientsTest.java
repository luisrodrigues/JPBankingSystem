package pt.ulisboa.tecnico.learnjava.bank.client;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;

public class CreateEqualNIFClientsTest {
	private final String BANK_CODE = "BPI";
	private Bank bank;
	
	private final String FIRST_NAME = "Luis";
	private final String LAST_NAME = "Rodrigues";
	private final String PHONE_NUMBER = "918558941";
	private final String ADDRESS = "Rua do Pinheiro";
	
	@Before
	public void setUp() throws BankException {
		this.bank = new Bank(this.BANK_CODE);
	}
	
	@Test
	public void createTwoClientsEqualsNIFsTest() throws ClientException {
		this.bank.addClient(this.FIRST_NAME, this.LAST_NAME, "000111222", this.PHONE_NUMBER, this.ADDRESS);
		try {
			this.bank.addClient(this.FIRST_NAME, this.LAST_NAME, "000111222", this.PHONE_NUMBER, this.ADDRESS);
			fail();
		} catch (ClientException e) {
		 //passes
		}
	}

	@After
	public void tearDown() {
		Bank.clearBanks();
	}
}
