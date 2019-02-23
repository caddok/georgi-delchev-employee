import java.util.Objects;

public class EmployeePair {
    private int firstEmployeeID;
    private int secondEmployeeID;
    private int daysWorkedTogether;

    public EmployeePair(int firstEmployeeID, int secondEmployeeID) {
        if (firstEmployeeID < secondEmployeeID) {
            this.firstEmployeeID = firstEmployeeID;
            this.secondEmployeeID = secondEmployeeID;
        } else {
            this.firstEmployeeID = secondEmployeeID;
            this.secondEmployeeID = firstEmployeeID;
        }
        daysWorkedTogether = 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstEmployeeID, secondEmployeeID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePair that = (EmployeePair) o;
        return firstEmployeeID == that.firstEmployeeID &&
                secondEmployeeID == that.secondEmployeeID;
    }

    @Override
    public String toString() {
        return "EmployeePair{" +
                "firstEmployeeID=" + firstEmployeeID +
                ", secondEmployeeID=" + secondEmployeeID +
                '}';
    }

    public void addDaysWorkedTogether(int daysWorkedTogether) {
        this.daysWorkedTogether += daysWorkedTogether;
    }

    public int getDaysWorkedTogether() {
        return daysWorkedTogether;
    }
}
