package pt.ulisboa.tecnico.learnjava.sibs.sibs;

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
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class TransferMethodTest {
	
	private Bank bank;
	private Client client1;
	private Client client2;
	
	private final String BANK_CODE = "CGD";
	
	private final String FIRST_NAME1 = "Luis";
	private final String FIRST_NAME2 = "Bino";
	private final String LAST_NAME = "Rodrigues";
	private final String NIF1 = "269034291";
	private final String NIF2 = "269034295";
	private final String PHONE_NUMBER1 = "918558941";
	private final String PHONE_NUMBER2 = "918558945";
	private final String ADDRESS = "Rua do Pinheiro";
	
	String iban1;
	String iban2;
	
	Account account1;
	Account account2;
	
	private Sibs sibs;

	@Before
	public void setUp() throws OperationException, SibsException, BankException, ClientException, AccountException {
		this.sibs = new Sibs(3);
		this.bank = new Bank(this.BANK_CODE);
		
		
		this.client1 = new Client(this.bank, this.FIRST_NAME1, this.LAST_NAME, this.NIF1, this.PHONE_NUMBER1, this.ADDRESS);
		
		this.iban1 = this.bank.createAccount(AccountType.CHECKING, this.client1, 100, 0);
		account1 = Services.getAccountByIban(iban1);
		
		this.client2 = new Client(this.bank, this.FIRST_NAME2, this.LAST_NAME, this.NIF2, this.PHONE_NUMBER2, this.ADDRESS);
		
		this.iban2 = this.bank.createAccount(AccountType.CHECKING, this.client2, 100, 0);
		account2 = Services.getAccountByIban(iban2);
	}

	@Test
	public void successTransfer() throws SibsException, AccountException, BankException, OperationException {
		this.sibs.transfer(iban1, iban2, 80);
		assertEquals(1, this.sibs.getNumberOfOperations());
		assertEquals(20, account1.getBalance());
		assertEquals(180, account2.getBalance());
	}

	@After
	public void tearDown() {
		this.sibs = null;
		Bank.clearBanks();
	}

}
