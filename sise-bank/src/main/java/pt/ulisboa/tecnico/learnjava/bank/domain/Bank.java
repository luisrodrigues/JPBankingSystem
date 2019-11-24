package pt.ulisboa.tecnico.learnjava.bank.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;

public class Bank {
	public enum AccountType {
		CHECKING("CK"), SALARY("SL"), SAVINGS("SV");

		private final String prefix;

		AccountType(String prefix) {
			this.prefix = prefix;
		}

		public String getPrefix() {
			return this.prefix;
		}
	}

	private static Set<Bank> banks = new HashSet<Bank>();

	public static Bank getBankByCode(String code) throws BankException {
		
		if(code == null) {
			throw new BankException();
		}
		
		return banks.stream().filter(obj -> obj.getCode().equals(code)).findAny().orElse(null);
	}

	public static void clearBanks() {
		banks.clear();
	}

	private final String code;
	private final Set<Account> accounts;
	private final Set<Client> clients;

	public Bank(String code) throws BankException {
		checkCode(code);

		this.code = code;
		this.accounts = new HashSet<Account>();
		this.clients = new HashSet<Client>();
		banks.add(this);
	}

	private void checkCode(String code) throws BankException {
		// code size is three
		if (code == null || code.length() != 3) {
			throw new BankException();
		}
		
		// banks have a unique code
		if (getBankByCode(code) != null) {
			throw new BankException();
		}
		
	}

	public String getCode() {
		return this.code;
	}

	public String createAccount(AccountType type, Client client, int amount, int value) throws BankException, AccountException {

		Account account;
		
		switch (type) {
		case CHECKING:
			account = new CheckingAccount(client, amount);
			break;
		case SAVINGS:
			account = new SavingsAccount(client, amount, value);
			break;
		case SALARY:
			account = new SalaryAccount(client, amount, value);
			break;
		default:
			throw new BankException();
		}
		
		if(this.code != client.getBank().getCode()) {
			throw new AccountException();
		}
		
		client.addAccount(account);
		
		this.accounts.add(account);

		return getCode() + account.getAccountId();
	}
	
	
	public Client addClient(String firstName, String lastName, String nif, String phoneNumber, String address) throws ClientException {
		checkUniqueNif(nif);
		
		Client client = new Client(this, firstName, lastName, nif, phoneNumber, address);
		
		this.clients.add(client);
		
		return client;
	}
	
	private void checkUniqueNif(String nif) throws ClientException {
		if(getClientByNif(nif) != null) {
			throw new ClientException();
		}		
	}

	public void deleteClient(String nif) throws ClientException, BankException, AccountException {
		Client client = getClientByNif(nif);

		if (client == null) {
			throw new ClientException();
		}
		
		this.accounts.removeIf(account -> account.getClient() == client);
			
		this.clients.remove(client);
	}

	public Client getClientByNif(String nif) {		
		return this.clients.stream().filter(cl -> cl.getNif().equals(nif)).findAny().orElse(null);
	}
	
	public int getNumberOfClients() {
		return this.clients.size();
	}

	public void deleteAccount(Account account) throws BankException, AccountException {
		if (account == null) {
			throw new AccountException();
		}
		
		Client client = account.getClient();
		client.deleteAccount(account);
		
		this.accounts.remove(account);
	}

	public Account getAccountByAccountId(String accountId) {
		return this.accounts.stream().filter(acc -> acc.getAccountId().equals(accountId)).findAny().orElse(null);
	}
	
	public int getTotalBalance() {
		return this.accounts.stream().map(acc -> acc.getBalance()).reduce(0, (a , b) -> a + b);
	}

	public int getNumberOfAccounts() {
		return this.accounts.size();
	}

	public static void main(String[] args) throws BankException, AccountException {
		Bank cgd = new Bank("CGD");
		System.out.println(cgd.getNumberOfAccounts());
	}

}
