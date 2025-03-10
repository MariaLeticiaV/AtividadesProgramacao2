import java.util.Scanner;

public class HotelBookingSystem {
    public static void main(String[] args) throws Exception {
        System.out.println("Seja bem-vindo ao hotel!");
        System.out.println("Escolha entre as seguintes opções:");
        System.out.println("0 - Sair");
        System.out.println("1 - Fazer reserva");
        System.out.println("2 - Editar reserva");

        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nDigite a opção: ");
            int userChoice = inputScanner.nextInt();
            if (userChoice == 0) {
                break;
            }
            if (userChoice == 1) {
                System.out.print("Número do quarto: ");
                int selectedRoom = inputScanner.nextInt();

                inputScanner.nextLine();
                System.out.println("Data de check-in (dd-mm-yyyy): ");
                String checkInDate = inputScanner.nextLine();

                System.out.println("Data de check-out (dd-mm-yyyy): ");
                String checkOutDate = inputScanner.nextLine();
                try {
                    Reservation newReservation = new Reservation(selectedRoom, checkInDate, checkOutDate);
                    newReservation.printData();
                } catch (Exception e) {
                    System.err.println("Erro ao criar reserva: " + e.getMessage());
                }
            }
            if (userChoice == 2) {
                System.out.print("Digite o número do quarto que deseja editar: ");
                int roomToEdit = inputScanner.nextInt();

                try {
                    Reservation existingReservation = Reservation.findRoom(roomToEdit);

                    if (existingReservation == null) {
                        throw new Exception("Quarto não encontrado");
                    }
                    inputScanner.nextLine();

                    System.out.println("Data de check-in (dd-mm-yyyy): ");
                    String newCheckInDate = inputScanner.nextLine();

                    System.out.println("Data de check-out (dd-mm-yyyy): ");
                    String newCheckOutDate = inputScanner.nextLine();

                    existingReservation.setDate(newCheckInDate, newCheckOutDate);
                    existingReservation.printData();
                } catch (Exception e) {
                    System.err.println("Falha ao atualizar reserva: " + e.getMessage());
                }
            }
        }
        System.out.println("\nObrigado por requisitar nossos serviços!");
        inputScanner.close();
    }
}
