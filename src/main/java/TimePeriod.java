import java.time.LocalDate;
import java.time.Period;

public final class TimePeriod {
    private LocalDate sDate;
    private LocalDate eDate;

    TimePeriod(final LocalDate startDate, final LocalDate endDate) {
        this.sDate = startDate;
        this.eDate = endDate;
    }

    LocalDate getStartDate() {
        return sDate;
    }

    LocalDate getEndDate() {
        return eDate;
    }

    Integer getOverlappingPeriod(final TimePeriod otherTimePeriod) {
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

        return Period.between(start, end).getDays();
    }
}
