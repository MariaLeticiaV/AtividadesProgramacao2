import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        System.out.print("\nInforme o número de contribuintes: ");
        int numTaxPayers = scanner.nextInt();

        List<Person> taxPayers = new ArrayList<>(numTaxPayers);
        scanner.nextLine();

        for (int i = 0; i < numTaxPayers; i++) {
            System.out.printf("Dados do contribuinte #%d:\n", i + 1);
            String type = "";
            while (!type.equals("F") && !type.equals("J")) {
                System.out.print("Pessoa física ou jurídica (F/J)? ");
                type = scanner.nextLine().toUpperCase();
            }
            System.out.print("Nome: ");
            String name = scanner.nextLine();

            System.out.print("Lucro anual: ");
            float annualIncome = scanner.nextFloat();

            if (type.equals("J")) {
                System.out.print("Número de funcionários: ");
                int employeeCount = scanner.nextInt();
                taxPayers.add(new Legal(name, annualIncome, employeeCount));
            } else {
                System.out.print("Despesas médicas: ");
                float healthExpenses = scanner.nextFloat();
                taxPayers.add(new Natural(name, annualIncome, healthExpenses));
            }
            scanner.nextLine();
            System.out.println();
        }
        scanner.close();
        float totalTaxes = 0;
        System.out.println("Impostos pagos:");

        for (Person p : taxPayers) {
            float tax = p.getTaxes();
            System.out.printf("%s: R$ %.2f\n", p.name, tax);
            totalTaxes += tax;
        }
        System.out.printf("\nTotal: R$ %.2f\n", totalTaxes);
    }
}
