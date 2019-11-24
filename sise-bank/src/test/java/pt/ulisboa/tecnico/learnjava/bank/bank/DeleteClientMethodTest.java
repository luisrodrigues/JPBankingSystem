package pt.ulisboa.tecnico.learnjava.bank.bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;

public class DeleteClientMethodTest {
	
	private final String BANK_CODE = "IIO";
	private Bank bank;
	private Client client1;
	private Client client2;
	
	private final String FIRST_NAME = "Luis";
	private final String LAST_NAME = "Rodrigues";
	private final String PHONE_NUMBER = "914500233";
	private final String NIF = "222333444";
	private final String ADDRESS = "Rua do Pinheiro";
	
	@Before
	public void setUp() throws BankException, ClientException, AccountException {
		this.bank = new Bank(this.BANK_CODE);
		this.client1 = this.bank.addClient(this.FIRST_NAME, this.LAST_NAME, this.NIF, this.PHONE_NUMBER, this.ADDRESS);
		this.client2 = this.bank.addClient(this.FIRST_NAME, this.LAST_NAME, "222333888", this.PHONE_NUMBER, this.ADDRESS);
		this.bank.createAccount(AccountType.CHECKING, this.client1, 100, 0);
		this.bank.createAccount(AccountType.CHECKING, this.client2, 100, 0);
	}
	
	
	@Test
	public void deleteClientSuccessTest() throws ClientException, BankException, AccountException {
		this.bank.deleteClient(this.NIF);
		assertEquals(1, this.bank.getNumberOfClients());
		assertNull(this.bank.getClientByNif(this.NIF));
	}
	
	@Test
	public void deleteClientAndAccountsSuccessTest() throws ClientException, BankException, AccountException {
		this.bank.deleteClient(this.NIF);
		assertEquals(1, this.bank.getNumberOfClients());
		assertNull(this.bank.getClientByNif(this.NIF));
		assertEquals(1, this.bank.getNumberOfAccounts());
	}
	
	@Test
	public void deleteGhostClientTest() throws ClientException, BankException, AccountException {
		try {
			this.bank.deleteClient("444555333");
			fail();
		} catch (ClientException e) {
			assertEquals(2, this.bank.getNumberOfClients());
		}
	}

	@After
	public void tearDown() {
		Bank.clearBanks();
	}
}
