import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

class PairFinder {
    private static boolean periodsAreOverlapping(final TimePeriod period, final TimePeriod otherTimePeriod) {
        return !(period.getStartDate().isAfter(otherTimePeriod.getEndDate())
                || period.getEndDate().isBefore(otherTimePeriod.getStartDate()));
    }

    /**
     * @param periodsByEmployeeIdByProjectId Employees ordered by projects
     *                                       they worked on
     * @return The pair of employee who worked together on the same
     * projects for the longest time, if such pair exists
     */
    Optional<EmployeePair> getTheLongestWorkingTogether(final HashMap<Integer,
            HashMap<Integer, List<TimePeriod>>> periodsByEmployeeIdByProjectId) {

        HashMap<EmployeePair, EmployeePair> commonProjectsWorkedInDays = new HashMap<>();
        for (HashMap<Integer, List<TimePeriod>> peopleThatWorkedOnTheSameProject : periodsByEmployeeIdByProjectId.values()) {
            for (int employeeId : peopleThatWorkedOnTheSameProject.keySet()) {
                for (int otherEmployeeId : peopleThatWorkedOnTheSameProject.keySet()) {

                    if (employeeId == otherEmployeeId) {
                        continue;
                    }

                    for (TimePeriod period : peopleThatWorkedOnTheSameProject.get(employeeId)) {
                        for (TimePeriod otherTimePeriod : peopleThatWorkedOnTheSameProject.get(otherEmployeeId)) {
                            if (periodsAreOverlapping(period, otherTimePeriod)) {
                                EmployeePair pair = new EmployeePair(employeeId, otherEmployeeId);
                                commonProjectsWorkedInDays.computeIfPresent(pair, (k, v) -> {
                                    v.addDaysWorkedTogether(period.getOverlappingPeriod(otherTimePeriod));
                                    return v;
                                });
                                pair.addDaysWorkedTogether(period.getOverlappingPeriod(otherTimePeriod));
                                commonProjectsWorkedInDays.putIfAbsent(pair, pair);
                            }
                        }
                    }
                }
            }
        }
        return commonProjectsWorkedInDays.values()
                .stream()
                .max(Comparator.comparingInt(EmployeePair::getDaysWorkedTogether));
    }

}
