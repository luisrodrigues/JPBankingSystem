package pt.ulisboa.tecnico.learnjava.bank.bank;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;

public class GetTotalBalanceMethodTest {
	private final String BANK_CODE = "CGD";
	private Bank bank;
	
	private Client client1;
	private Client client2;
	private Client client3;
	private Client client4;
	private Client client5;
	
	private final String FIRST_NAME = "Luis";
	private final String LAST_NAME = "Rodrigues";
	private final String PHONE_NUMBER = "918558941";
	private final String ADDRESS = "Rua do Pinheiro";
	
	private static final String NIF1 = "555666777";
	private static final String NIF2 = "888999000";
	private static final String NIF3 = "333000444";
	private static final String NIF4 = "666111444";
	private static final String NIF5 = "888899900";
	
	private static final int BALANCE = 100;
	
	@Before
	public void setUp() throws BankException, ClientException {
		this.bank = new Bank(this.BANK_CODE);
		this.client1 = new Client(this.bank, this.FIRST_NAME, this.LAST_NAME, NIF1, this.PHONE_NUMBER, this.ADDRESS);
		this.client2 = new Client(this.bank, this.FIRST_NAME, this.LAST_NAME, NIF2, this.PHONE_NUMBER, this.ADDRESS);
		this.client3 = new Client(this.bank, this.FIRST_NAME, this.LAST_NAME, NIF3, this.PHONE_NUMBER, this.ADDRESS);
		this.client4 = new Client(this.bank, this.FIRST_NAME, this.LAST_NAME, NIF4, this.PHONE_NUMBER, this.ADDRESS);
		this.client5 = new Client(this.bank, this.FIRST_NAME, this.LAST_NAME, NIF5, this.PHONE_NUMBER, this.ADDRESS);
	}

	@Test
	public void test4a() throws BankException, AccountException {
		this.bank.createAccount(AccountType.CHECKING, client1, BALANCE, 0);
		this.bank.createAccount(AccountType.CHECKING, client2, BALANCE, 0);
		this.bank.createAccount(AccountType.CHECKING, client3, BALANCE, 0);
		this.bank.createAccount(AccountType.CHECKING, client4, BALANCE, 0);
		this.bank.createAccount(AccountType.CHECKING, client5, BALANCE, 0);
		assertEquals(500, this.bank.getTotalBalance());
	}
	
	
	@Test
	public void test4b() {
		assertEquals(0, this.bank.getTotalBalance());
	}
	
	
	@After
	public void tearDown() {
		Bank.clearBanks();
	}
}
