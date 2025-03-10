import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class HotelReservation {
    private static List<HotelReservation> reservationList = new ArrayList<>();
    private LocalDate checkInDate, checkOutDate;
    private int roomId;

    public HotelReservation(int roomId, String checkInStr, String checkOutStr) throws Exception {
        if (HotelReservation.findReservation(roomId) != null) {
            throw new Exception("Quarto com este número já foi reservado");
        }
        try {
            this.setReservationDates(checkInStr, checkOutStr);
        } catch (DateTimeParseException e) {
            throw new Exception(e.getMessage());
        }
        this.roomId = roomId;
        reservationList.add(this);
    }
    public static LocalDate convertToDate(String dateStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateStr, formatter);
    }
    public static HotelReservation findReservation(int roomId) {
        for (HotelReservation reservation : reservationList) {
            if (reservation.roomId == roomId) {
                return reservation;
            }
        }
        return null;
    }
    public void setReservationDates(String checkInStr, String checkOutStr) {
        LocalDate newCheckIn, newCheckOut;
        try {
            newCheckIn = HotelReservation.convertToDate(checkInStr);
            newCheckOut = HotelReservation.convertToDate(checkOutStr);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Falha ao converter data: " + e.getMessage(),
                    checkInStr + ", " + checkOutStr, e.getErrorIndex());
        }
        LocalDate today = LocalDate.now();

        if (!newCheckIn.isAfter(today) || !newCheckOut.isAfter(today)) {
            throw new DateTimeException("Check-out ou check-in já passou");
        }
        if (newCheckIn.isAfter(newCheckOut)) {
            throw new DateTimeException("Check-out precisa ser depois do check-in");
        }
        this.checkInDate = newCheckIn;
        this.checkOutDate = newCheckOut;
    }
    public int calculateStayDuration() {
        long daysBetween = ChronoUnit.DAYS.between(this.checkInDate, this.checkOutDate);

        if (daysBetween > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (daysBetween < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) daysBetween;
    }
    public void displayReservation() {
        System.out.print(String.format("Quarto: %d, Check-in: %s, Check-out: %s, Duração: %d dia",
                this.roomId, this.checkInDate, this.checkOutDate, this.calculateStayDuration()));

        if (this.calculateStayDuration() != 1) {
            System.out.print("s");
        }
        System.out.println();
    }
}
