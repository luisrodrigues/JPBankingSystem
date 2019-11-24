package pt.ulisboa.tecnico.learnjava.bank.domain;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;

public class SalaryAccount extends Account {
	private final int salary;

	public SalaryAccount(Client client, int amount, int salary) throws AccountException {
		super(client, amount);
		this.salary = salary;
	}

	@Override
	protected String getNextAcccountId() {
		return AccountType.SALARY.getPrefix() + Integer.toString(++counter);
	}

	@Override
	public void withdraw(int amount) throws AccountException {
		if (getBalance() - amount + this.getSalary() < 0) {
			throw new AccountException();
		}

		super.withdraw(amount);
	}

	public int getSalary() {
		return this.salary;
	}
}
