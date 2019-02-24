import java.util.Objects;

public final class EmployeePair {
    private int firstEmployeeID;
    private int secondEmployeeID;
    private int daysWorkedTogether;

    public EmployeePair(final int fEmployeeID,
                        final int sEmployeeID) {
        if (fEmployeeID < sEmployeeID) {
            this.firstEmployeeID = fEmployeeID;
            this.secondEmployeeID = sEmployeeID;
        } else {
            this.firstEmployeeID = sEmployeeID;
            this.secondEmployeeID = fEmployeeID;
        }
        daysWorkedTogether = 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstEmployeeID, secondEmployeeID);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmployeePair that = (EmployeePair) o;
        return firstEmployeeID == that.firstEmployeeID
                && secondEmployeeID == that.secondEmployeeID;
    }

    @Override
    public String toString() {
        return "EmployeePair{"
                + "firstEmployeeID=" + firstEmployeeID
                + ", secondEmployeeID=" + secondEmployeeID + '}';
    }

    public void addDaysWorkedTogether(final int numberOfDaysWorkedTogether) {
        this.daysWorkedTogether += numberOfDaysWorkedTogether;
    }

    public int getDaysWorkedTogether() {
        return daysWorkedTogether;
    }
}
