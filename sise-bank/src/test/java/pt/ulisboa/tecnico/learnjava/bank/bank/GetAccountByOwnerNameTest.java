package pt.ulisboa.tecnico.learnjava.bank.bank;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;

public class GetAccountByOwnerNameTest {
	
	//changed after 6.1
	
	private static final String BANK_CODE = "CGD";
	private Bank bank;
	private Client client;
	
	private final String FIRST_NAME = "Luis";
	private final String LAST_NAME = "Rodrigues";
	private final String NIF = "269034291";
	private final String PHONE_NUMBER = "918558941";
	private final String ADDRESS = "Rua do Pinheiro";
	
	@Before
	public void setUp() throws BankException, AccountException, ClientException {
		this.bank = new Bank(BANK_CODE);
		this.client = new Client(this.bank, this.FIRST_NAME, this.LAST_NAME, this.NIF, this.PHONE_NUMBER, this.ADDRESS);
		this.bank.createAccount(AccountType.CHECKING, client, 1000, 0);
	}
	
	@Test
	public void NullIBANtest() throws AccountException, BankException {
		try {
			Services.getAccountByIban(null);
			fail();
		} catch (AccountException e) {
			//passes
		}
	}
	
	@After
	public void tearDown() {
		Bank.clearBanks();
	}
	
}
