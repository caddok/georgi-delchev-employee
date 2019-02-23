import java.time.LocalDate;
import java.time.Period;

public class TimePeriod {
    private LocalDate startDate;
    private LocalDate endDate;

    TimePeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    LocalDate getStartDate() {
        return startDate;
    }

    LocalDate getEndDate() {
        return endDate;
    }

    Integer getOverlappingPeriod(TimePeriod otherTimePeriod) {
        LocalDate start;
        LocalDate end;
        if (this.getStartDate().isBefore(otherTimePeriod.getStartDate())) {
            start = otherTimePeriod.getStartDate();
        } else {
            start = this.getStartDate();
        }

        if (this.getEndDate().isAfter(otherTimePeriod.getEndDate())) {
            end = otherTimePeriod.getEndDate();
        } else {
            end = this.getEndDate();
        }

        return Period.between(start,end).getDays();
    }
}
