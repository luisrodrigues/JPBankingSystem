package pt.ulisboa.tecnico.learnjava.bank.account;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.domain.CheckingAccount;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.domain.SalaryAccount;
import pt.ulisboa.tecnico.learnjava.bank.domain.SavingsAccount;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;

public class GetAccountIdMethodTest {
	
	private Bank bank_one;
	private Client client;

	private CheckingAccount checking;
	private SavingsAccount savings;
	private SalaryAccount salary;
	
	private static final String BANK_CODE = "BPI";
	
	private final String FIRST_NAME = "Luis";
	private final String LAST_NAME = "Rodrigues";
	private final String NIF = "269034291";
	private final String PHONE_NUMBER = "918558941";
	private final String ADDRESS = "Rua do Pinheiro";

	@Before
	public void setUp() throws AccountException, ClientException, BankException {
		this.bank_one = new Bank(BANK_CODE);
		this.client = this.bank_one.addClient(this.FIRST_NAME, this.LAST_NAME, this.NIF, this.PHONE_NUMBER, this.ADDRESS);
				
		this.checking = new CheckingAccount(this.client, 100);
		this.savings = new SavingsAccount(this.client, 100, 10);
		this.salary = new SalaryAccount(this.client, 100, 1000);
	}

	@Test
	public void successForCheckingAccount() {
		assertTrue(this.checking.getAccountId().startsWith(AccountType.CHECKING.getPrefix()));
	}

	@Test
	public void successForSavingsAccount() {
		assertTrue(this.savings.getAccountId().startsWith(AccountType.SAVINGS.getPrefix()));
	}

	@Test
	public void successForSalaryAccount() {
		assertTrue(this.salary.getAccountId().startsWith(AccountType.SALARY.getPrefix()));
	}

	@After
	public void tearDown() {
		this.checking = null;
		this.savings = null;
		this.salary = null;
		Bank.clearBanks();
	}

}
