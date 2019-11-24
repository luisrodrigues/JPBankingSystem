package pt.ulisboa.tecnico.learnjava.bank.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.CheckingAccount;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;

public class ConstructorMethodTest {
	private static final int AMOUNT = 100;
	private static final String BANK_CODE = "BPI";
	
	private final String FIRST_NAME = "Luis";
	private final String LAST_NAME = "Rodrigues";
	private final String NIF = "269034291";
	private final String PHONE_NUMBER = "918558941";
	private final String ADDRESS = "Rua do Pinheiro";

	@Test
	public void success() throws AccountException, ClientException, BankException {
		Bank bank = new Bank(BANK_CODE);
		Client client = new Client(bank, this.FIRST_NAME, this.LAST_NAME, this.NIF, this.PHONE_NUMBER, this.ADDRESS);
		Account account = new CheckingAccount(client, AMOUNT);	

		assertEquals(FIRST_NAME, account.getClient().getFirstName());
		assertEquals(AMOUNT, account.getBalance());
	}

	@Test
	public void nullClientName() throws ClientException, BankException {
		try {
			new CheckingAccount(null, AMOUNT);
			fail();
		} catch (AccountException e) {
			// passes
		}

	}
}
