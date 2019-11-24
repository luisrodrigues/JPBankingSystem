package pt.ulisboa.tecnico.learnjava.bank.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.CheckingAccount;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.domain.SalaryAccount;
import pt.ulisboa.tecnico.learnjava.bank.domain.SavingsAccount;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;

public class WithdrawMethodTest {

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
		Bank bank = new Bank(BANK_CODE);
		Client client = new Client(bank, this.FIRST_NAME, this.LAST_NAME, this.NIF, this.PHONE_NUMBER, this.ADDRESS);
		
		this.checking = new CheckingAccount(client, 100);
		this.savings = new SavingsAccount(client, 100, 10);
		this.salary = new SalaryAccount(client, 100, 1000);
	}

	@Test
	public void successForCheckingAccount() throws AccountException {
		this.checking.withdraw(50);

		assertEquals(50, this.checking.getBalance());
	}

	@Test
	public void negativeAmountForCheckingAccount() {
		try {
			this.checking.withdraw(-10);
			fail();
		} catch (AccountException e) {
			assertEquals(100, this.checking.getBalance());
		}
	}

	@Test
	public void notEnoughBalanceForCheckingAccount() {
		try {
			this.checking.withdraw(200);
			fail();
		} catch (AccountException e) {
			assertEquals(100, this.checking.getBalance());
		}
	}

	@Test
	public void successForSavingsAccount() throws AccountException {
		this.savings.withdraw(100);

		assertEquals(0, this.savings.getBalance());
	}

	@Test
	public void negativeAmountForSavingsAccount() {
		try {
			this.savings.withdraw(-10);
			fail();
		} catch (AccountException e) {
			assertEquals(100, this.checking.getBalance());
		}
	}

	@Test
	public void amountNotEqualToBalanceInSavingsAccount() {
		try {
			this.savings.withdraw(50);
			fail();
		} catch (AccountException e) {
			assertEquals(100, this.savings.getBalance());
		}
	}

	@Test
	public void successNegativeBalanceForSalaryAccount() throws AccountException {
		this.salary.withdraw(900);

		assertEquals(-800, this.salary.getBalance());
	}

	@Test
	public void negativeAmountForSalaryAccount() {
		try {
			this.salary.withdraw(-10);
			fail();
		} catch (AccountException e) {
			assertEquals(100, this.checking.getBalance());
		}
	}

	@Test
	public void failNegativeBalanceForSalaryAccount() throws AccountException {
		try {
			this.salary.withdraw(2000);
		} catch (AccountException e) {
			assertEquals(100, this.salary.getBalance());
		}
	}

	@After
	public void tearDown() {
		this.checking = null;
		this.savings = null;
		this.salary = null;
		Bank.clearBanks();
	}

}
