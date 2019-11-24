package pt.ulisboa.tecnico.learnjava.bank.bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.domain.CheckingAccount;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.domain.SalaryAccount;
import pt.ulisboa.tecnico.learnjava.bank.domain.SavingsAccount;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;

public class CreateAccountMethodTest {

	private Bank bank;
	private Client client;
	
	private static final String BANK_CODE = "CGD";
	private static final String BANK_CODE2 = "NBA";
	
	private final String FIRST_NAME = "Luis";
	private final String LAST_NAME = "Rodrigues";
	private final String NIF = "269034291";
	private final String PHONE_NUMBER = "918558941";
	private final String ADDRESS = "Rua do Pinheiro";

	@Before
	public void setUp() throws BankException, ClientException {
		this.bank = new Bank(BANK_CODE);
		this.client = new Client(this.bank, this.FIRST_NAME, this.LAST_NAME, this.NIF, this.PHONE_NUMBER, this.ADDRESS);
	}

	@Test
	public void successCheckingAccount() throws BankException, AccountException {
		String iban = this.bank.createAccount(AccountType.CHECKING, this.client, 100, 0);

		Account account = Services.getAccountByIban(iban);

		assertTrue(account instanceof CheckingAccount);
		assertEquals(1, this.bank.getNumberOfAccounts());
		assertEquals(this.client, account.getClient());
		assertEquals(100, account.getBalance());
	}
	
	@Test
	public void successSavingsAccount() throws BankException, AccountException {
		String iban = this.bank.createAccount(AccountType.SAVINGS, this.client, 100, 100);

		Account account = Services.getAccountByIban(iban);

		assertTrue(account instanceof SavingsAccount);
		assertEquals(1, this.bank.getNumberOfAccounts());
		assertEquals(this.client, account.getClient());
		assertEquals(100, account.getBalance());
		assertEquals(100, ((SavingsAccount) account).getBase());
	}
	
	@Test
	public void successSalaryAccount() throws BankException, AccountException {
		String iban = this.bank.createAccount(AccountType.SALARY, this.client, 100, 100);

		Account account = Services.getAccountByIban(iban);

		assertTrue(account instanceof SalaryAccount);
		assertEquals(1, this.bank.getNumberOfAccounts());
		assertEquals(this.client, account.getClient());
		assertEquals(100, account.getBalance());
		assertEquals(100, ((SalaryAccount) account).getSalary());
	}
	
	@Test
	public void successAddAccountToClient() throws BankException, AccountException {
		String iban = this.bank.createAccount(AccountType.CHECKING, this.client, 100, 0);

		Account account = Services.getAccountByIban(iban);

		assertTrue(account instanceof CheckingAccount);
		assertEquals(1, this.bank.getNumberOfAccounts());
		assertEquals(this.client, account.getClient());
		assertEquals(100, account.getBalance());
		assertEquals(1, this.client.getNumberOfAccounts());
	}
	
	@Test
	public void limitNrOfAccountsToFive() throws BankException, AccountException {
		this.bank.createAccount(AccountType.CHECKING, this.client, 100, 0);
		this.bank.createAccount(AccountType.CHECKING, this.client, 100, 0);
		this.bank.createAccount(AccountType.CHECKING, this.client, 100, 0);
		this.bank.createAccount(AccountType.CHECKING, this.client, 100, 0);
		this.bank.createAccount(AccountType.CHECKING, this.client, 100, 0);
		try {
			this.bank.createAccount(AccountType.CHECKING, this.client, 100, 0);
			fail();
		} catch (AccountException e) {
			//passes
			assertEquals(5, this.client.getNumberOfAccounts());
		}
	}
	
	@Test
	public void AddAccountsDifferentBanksSameClientTest() throws BankException, AccountException {
		Bank bank_two = new Bank(BANK_CODE2);
		this.bank.createAccount(AccountType.SALARY, this.client, 100, 100);
		
		try {
			bank_two.createAccount(AccountType.CHECKING, this.client, 100, 0);
			fail();
		} catch (AccountException e) {
			//passes
			assertEquals(1, this.client.getNumberOfAccounts());
		}
	}
	
	
	@After
	public void tearDown() {
		Bank.clearBanks();
	}

}
