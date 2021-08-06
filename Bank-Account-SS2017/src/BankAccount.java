public class BankAccount {

	private final double lineOfCredit;
	private final String accountNumber;
	private double balance;
	private AccountState state;

	public BankAccount(String accountNumber, double lineOfCredit) {
		if (lineOfCredit < 0) {
			throw new IllegalArgumentException("line of credit must be 0 or greater than 0.");
		}
		if (accountNumber == null) {
			throw new NullPointerException("account number must not be null");
		}
		if (accountNumber.isEmpty()) {
			throw new IllegalArgumentException("account number must not be empty");
		}

		this.lineOfCredit = lineOfCredit;
		this.accountNumber = accountNumber;
		state = new Positive();
	}

	public boolean payIn(double amount) {
		return state.payIn(amount);
	}

	public boolean payOff(double amount) {
		return state.payOff(amount);
	}

	public boolean close() {
		if (state instanceof Closed || balance != 0) {
			return false;
		}
		state = new Closed();
		return true;
	}

	public double getBalance() {
		return balance;
	}

	public String getState() {
		return state.toString();
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void printBalance() {
		state.printBalance();
	}

	public void payInterest() {
		state.payInterest();
	}

	abstract class AccountState {
		public boolean payIn(double amount) {
			return false;
		}

		public boolean payOff(double amount) {
			return false;
		}

		public void payInterest() {
		}

		abstract void printBalance();
	}

	class Positive extends AccountState {
		@Override
		public boolean payIn(double amount) {
			if (amount <= 0) {
				throw new IllegalArgumentException("amount must be greater than 0");
			}
			balance += amount;
			return true;
		}

		@Override
		public boolean payOff(double amount) {
			if (amount <= 0) {
				throw new IllegalArgumentException("amount must be greater than 0");
			}
			if (amount > lineOfCredit) {
				return false;
			}

			balance -= amount;
			if (balance < 0) {
				state = new Negative();
				if (balance == -lineOfCredit) {
					state = new Frozen();
				}
			}

			return true;
		}

		@Override
		public void payInterest() {
			balance += (balance * 0.01); // increase by 1%
			if (balance < 0) {
				state = new Negative();
			}
		}

		public void printBalance() {
			System.out.println("Balance is POSITIVE: +" + balance + ".");
		}


		@Override
		public String toString() {
			return "Positive";
		}
	}

	class Negative extends AccountState {
		@Override
		public boolean payIn(double amount) {
			if (amount <= 0) {
				throw new IllegalArgumentException("amount must be greater than 0");
			}

			balance += amount;
			if (balance >= 0) {
				state = new Positive();
			}

			return true;
		}


		@Override
		public boolean payOff(double amount) {
			if (amount <= 0) {
				throw new IllegalArgumentException("amount must be greater than 0");
			}

			double newBalance = balance - amount;
			if (newBalance < -lineOfCredit) {
				return false;
			}

			balance = newBalance;
			if (balance == -lineOfCredit) {
				state = new Frozen();
			}
			return true;
		}

		@Override
		public void payInterest() {
			balance -= (-balance * 0.03); // decrease by 3%
			if (balance <= -lineOfCredit) {
				state = new Frozen();
			}
		}

		public void printBalance() {
			System.out.println("Balance is NEGATIVE: " + balance + ".");
		}


		@Override
		public String toString() {
			return "Negative";
		}
	}

	class Frozen extends AccountState {
		@Override
		public boolean payIn(double amount) {
			if (amount <= 0) {
				throw new IllegalArgumentException("amount must be greater than 0");
			}

			balance += amount;
			if (balance > -lineOfCredit) {
				state = new Negative();
			}
			if (balance >= 0) {
				state = new Positive();
			}
			return true;
		}

		public void payInterest() {
			balance -= (-balance * 0.05); // decrease by 5%
		}

		public void printBalance() {
			System.out.println("Balance is NEGATIVE: " + balance + ". You need to pay in money.");
		}

		@Override
		public String toString() {
			return "Frozen";
		}
	}

	class Closed extends AccountState {
		@Override
		public void payInterest() {
			throw new IllegalStateException();
		}

		public void printBalance() {
			System.out.println("This account is CLOSED. The balance is 0.");
		}

		@Override
		public String toString() {
			return "Closed";
		}
	}
}
