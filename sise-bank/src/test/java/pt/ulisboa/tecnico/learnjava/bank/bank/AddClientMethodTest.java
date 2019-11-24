package pt.ulisboa.tecnico.learnjava.bank.bank;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;

public class AddClientMethodTest {

	private static final String BANK_CODE = "BPI";
	private Bank bank;
	
	private final String FIRST_NAME = "Luis";
	private final String LAST_NAME = "Rodrigues";
	private final String NIF = "012345678";
	private final String PHONE_NUMBER = "918558941";
	private final String ADDRESS = "Rua do Pinheiro";
	
	@Before
	public void setUp() throws BankException {
		this.bank = new Bank(BANK_CODE);
	}
	
	@Test
	public void createClientSuccessTest() throws ClientException {
		Client client = this.bank.addClient(this.FIRST_NAME, this.LAST_NAME, this.NIF, this.PHONE_NUMBER, this.ADDRESS);
		
		assertEquals(BANK_CODE, client.getBank().getCode());
		assertEquals(this.FIRST_NAME, client.getFirstName());
		assertEquals(this.LAST_NAME, client.getLastName());
		assertEquals(this.NIF, client.getNif());
		assertEquals(this.PHONE_NUMBER, client.getPhoneNumber());
		assertEquals(this.ADDRESS, client.getAddress());
	}

	@After
	public void tearDown() {
		Bank.clearBanks();
	}
	
}
