package pt.ulisboa.tecnico.learnjava.bank.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;

public class Client {
	private Bank bank; 
	private String firstName;
	private String lastName;
	private String nif;
	private String phoneNumber;
	private String address;
	private Set<Account> accounts;
	
	public Client(Bank bank, String firstName, String lastName, String nif, String phoneNumber, String address) throws ClientException {		
		
		if(!nif.matches("[0-9]+") || nif.length() != 9 || !phoneNumber.matches("[0-9]+") || phoneNumber.length() != 9) {
			throw new ClientException();
		}
		
		this.bank = bank;
		this.firstName = firstName;
		this.lastName = lastName;		
		this.nif = nif;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.accounts = new HashSet<Account>();
	}

	public Bank getBank() {
		return bank;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getNif() {
		return nif;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void addAccount(Account account) throws AccountException {
		
		if(this.getNumberOfAccounts() == 5) {
			throw new AccountException();
		}		
		this.accounts.add(account);
	}
	
	public void deleteAccount(Account account) throws AccountException {
		
		if(account == null) {
			throw new AccountException();
		}		
		this.accounts.remove(account);
	}
	
	public int getNumberOfAccounts() {
		return this.accounts.size();
		
	}
	
}
