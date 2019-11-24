package pt.ulisboa.tecnico.learnjava.bank.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;

public class ClientConstructorMethodTest {
	
	private final String BANK_CODE1 = "BPI";
	private final String BANK_CODE2 = "NBA";
	private final String BANK_CODE3 = "BEF";
	private Bank bank;
	
	private final String FIRST_NAME = "Luis";
	private final String LAST_NAME = "Rodrigues";
	private final String NIF = "269034291";
	private final String PHONE_NUMBER = "918558941";
	private final String ADDRESS = "Rua do Pinheiro";
	
	
	@Test
	public void createClientSuccessTest() throws ClientException, BankException {
		this.bank = new Bank(this.BANK_CODE1);
		Client client = new Client(this.bank, this.FIRST_NAME, this.LAST_NAME, this.NIF, this.PHONE_NUMBER, this.ADDRESS);
		
		assertEquals(this.BANK_CODE1, client.getBank().getCode());
		assertEquals(this.FIRST_NAME, client.getFirstName());
		assertEquals(this.LAST_NAME, client.getLastName());
		assertEquals(this.NIF, client.getNif());
		assertEquals(this.PHONE_NUMBER, client.getPhoneNumber());
		assertEquals(this.ADDRESS, client.getAddress());
	}
	
	@Test
	public void createIncorrectNIFTest() throws ClientException, BankException {
		try {
			new Client(new Bank(this.BANK_CODE2), this.FIRST_NAME, this.LAST_NAME, "12345678", this.PHONE_NUMBER, this.ADDRESS);
			fail();
		} catch (ClientException e) {
		 //passes
		}
	}
	
	@Test
	public void createIncorrectPhoneNumberTest() throws ClientException, BankException {
		try {
			new Client(new Bank(this.BANK_CODE3), this.FIRST_NAME, this.LAST_NAME, "873465873", "12A456789", this.ADDRESS);
			fail();
		} catch (ClientException e) {
		 //passes
		}
	}

}
