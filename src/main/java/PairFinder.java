import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PairFinder {
    Optional<EmployeePair> getTheLongestWorkingTogether(HashMap<Integer,
            HashMap<Integer, List<TimePeriod>>> periodsByEmployeeIdByProjectId) {

        HashMap<EmployeePair, EmployeePair> commonProjectsWorkedInDays = new HashMap<>();
        for (Integer projectId : periodsByEmployeeIdByProjectId.keySet()) {
            HashMap<Integer, List<TimePeriod>> peopleWorkedOnTheSameProject =
                    periodsByEmployeeIdByProjectId.get(projectId);
            for (int employeeID : peopleWorkedOnTheSameProject.keySet()) {
                for (int otherEmployee : peopleWorkedOnTheSameProject.keySet()) {

                    if (employeeID == otherEmployee) {
                        continue;
                    }

                    for (TimePeriod period : peopleWorkedOnTheSameProject.get(employeeID)) {
                        for (TimePeriod otherTimePeriod : peopleWorkedOnTheSameProject.get(otherEmployee)) {
                            if (periodsAreOverlapping(period, otherTimePeriod)) {
                                EmployeePair pair = new EmployeePair(employeeID, otherEmployee);
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

    private static boolean periodsAreOverlapping(TimePeriod period, TimePeriod otherTimePeriod) {
        return !(period.getStartDate().isAfter(otherTimePeriod.getEndDate())
                || period.getEndDate().isBefore(otherTimePeriod.getStartDate()));
    }

}
