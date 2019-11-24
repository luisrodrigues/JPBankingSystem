package pt.ulisboa.tecnico.learnjava.bank.bank;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;

public class DeleteAccountMethodTest {
	private Bank bank;
	private Client client;
	private Account account;
	
	private static final String BANK_CODE = "CGD";
	
	private final String FIRST_NAME = "Luis";
	private final String LAST_NAME = "Rodrigues";
	private final String NIF = "269034291";
	private final String PHONE_NUMBER = "918558941";
	private final String ADDRESS = "Rua do Pinheiro";
	
	@Before
	public void setUp() throws BankException, ClientException, AccountException {
		this.bank = new Bank(BANK_CODE);
		this.client = new Client(this.bank, this.FIRST_NAME, this.LAST_NAME, this.NIF, this.PHONE_NUMBER, this.ADDRESS);
		this.bank.createAccount(AccountType.CHECKING, this.client, 100, 0);
		String iban = this.bank.createAccount(AccountType.CHECKING, this.client, 100, 0);
		this.account = Services.getAccountByIban(iban);
	}
	
	@Test
	public void successDeleteAccount() throws BankException, AccountException {
		bank.deleteAccount(account);
		assertEquals(1, this.bank.getNumberOfAccounts());
		assertEquals(this.client.getNumberOfAccounts(), this.bank.getNumberOfAccounts());
	}
	
	@After
	public void tearDown() {
		Bank.clearBanks();
	}

}
