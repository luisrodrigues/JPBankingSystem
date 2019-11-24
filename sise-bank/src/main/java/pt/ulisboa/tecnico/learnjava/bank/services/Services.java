package pt.ulisboa.tecnico.learnjava.bank.services;

import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;

public class Services {
	public static void deposit(String iban, int amount) throws AccountException, BankException {
		Account account = getAccountByIban(iban);

		account.deposit(amount);
	}

	public static void withdraw(String iban, int amount) throws AccountException, BankException {
		Account account = getAccountByIban(iban);

		account.withdraw(amount);
	}

	public static Account getAccountByIban(String iban) throws BankException, AccountException {
		
		if(iban == null) {
			throw new AccountException();
		}
		
		String code = iban.substring(0, 3);
		String accountId = iban.substring(3);

		Bank bank = Bank.getBankByCode(code);
		Account account = bank.getAccountByAccountId(accountId);

		return account;
	}

}
