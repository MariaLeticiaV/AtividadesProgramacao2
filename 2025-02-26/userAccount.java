public class BankAccount {
    int accountNumber;
    String accountHolder;
    double accountBalance, maxWithdrawLimit;

    public BankAccount(int accountNumber, String accountHolder, double accountBalance, double maxWithdrawLimit) throws Exception {
        if (maxWithdrawLimit < 0) {
            throw new Exception("O limite de saque não pode ser menor que 0");
        }
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountBalance = accountBalance;
        this.maxWithdrawLimit = maxWithdrawLimit;
    }
    public void deposit(double depositAmount) {
        this.accountBalance += depositAmount;
    }
    public void withdraw(double withdrawAmount) throws Exception {
        if (withdrawAmount > this.maxWithdrawLimit) {
            throw new Exception("Valor escolhido acima do limite de saque");
        }
        if (withdrawAmount > this.accountBalance) {
            throw new Exception("Saldo insuficiente");
        }
        this.accountBalance -= withdrawAmount;
    }
}
