import java.util.Scanner;

public class BankAccountApp {
    public static void main(String[] args) throws Exception {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Digite os dados da sua conta");
        System.out.print("Número: ");
        int accountNumber = inputScanner.nextInt();
        inputScanner.nextLine();

        System.out.print("Nome: ");
        String accountHolder = inputScanner.nextLine();

        System.out.print("Saldo: ");
        double initialBalance = inputScanner.nextDouble();

        System.out.print("Limite de saque: ");
        double maxWithdrawLimit = inputScanner.nextDouble();
        BankAccount userAccount;
        try {
            userAccount = new BankAccount(accountNumber, accountHolder, initialBalance, maxWithdrawLimit);
        } catch (Exception e) {
            System.err.println("\nFalha ao criar conta: " + e.getMessage());
            System.out.println("Execute o programa novamente com os dados preenchidos corretamente");
            return;
        }
        System.out.println("\nEscolha entre as seguintes opções:");
        System.out.println("0 - Finalizar operação");
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");

        while (true) {
            System.out.print("\nOperação: ");
            int userChoice = inputScanner.nextInt();
            if (userChoice == 0) {
                break;
            }
            if (userChoice == 1) {
                System.out.print("Digite o valor que deseja depositar: ");
                double depositAmount = inputScanner.nextDouble();

                userAccount.deposit(depositAmount);

                System.out.println(String.format("Você depositou R$%.2f e agora tem R$%.2f na conta", 
                        depositAmount, userAccount.balance));
            }
            if (userChoice == 2) {
                System.out.print("Digite o valor que deseja sacar: ");
                double withdrawAmount = inputScanner.nextDouble();

                try {
                    userAccount.withdraw(withdrawAmount);
                    System.out.println(String.format("Você sacou R$%.2f e agora tem R$%.2f na conta", 
                            withdrawAmount, userAccount.balance));
                } catch (Exception e) {
                    System.err.println("Falha ao sacar: " + e.getMessage());
                }
            }
        }
        System.out.println(String.format("\nOperação finalizada, saldo final: R$%.2f", userAccount.balance));
        inputScanner.close();
    }
}
