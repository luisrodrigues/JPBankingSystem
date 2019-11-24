package pt.ulisboa.tecnico.learnjava.bank.domain;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;

public class CheckingAccount extends Account {

	public CheckingAccount(Client client, int amount) throws AccountException {
		super(client, amount);
	}

	@Override
	protected String getNextAcccountId() {
		return AccountType.CHECKING.getPrefix() + Integer.toString(++counter);
	}

	@Override
	public void withdraw(int amount) throws AccountException {
		if (amount > getBalance()) {
			throw new AccountException();
		}

		super.withdraw(amount);
	}

}
