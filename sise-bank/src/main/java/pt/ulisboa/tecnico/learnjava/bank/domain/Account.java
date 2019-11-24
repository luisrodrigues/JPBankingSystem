package pt.ulisboa.tecnico.learnjava.bank.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;

public abstract class Account {
	protected static int counter;

	private final String accountId;
	private Client client;
	private int balance;

	public Account(Client client) throws AccountException {
		this(client, 0);
	}

	public Account(Client client, int amount) throws AccountException {
		if (client == null) {
			throw new AccountException();
		}

		this.accountId = getNextAcccountId();
		this.client = client;		
		this.balance = amount;
	}

	protected abstract String getNextAcccountId();

	public Client getClient() {
		return this.client;
	}

	public int getBalance() {
		return this.balance;
	}

	public void deposit(int amount) throws AccountException {
		if (amount <= 0) {
			throw new AccountException(amount);
		}
		this.balance = this.balance + amount;

	}

	public void withdraw(int amount) throws AccountException {
		if (amount <= 0) {
			throw new AccountException();
		}

		this.balance = this.balance - amount;
	}

	public String getAccountId() {
		return this.accountId;
	}

}
