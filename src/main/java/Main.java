import java.util.HashMap;
import java.util.List;

public final class Main {
    private Main() { }
    public static void main(final String[] args) throws ParsingException {
        String fileName = "data.txt";
        if (args.length > 0) {
            fileName = args[0];
        }
        CommandLineParser parser = new CommandLineParser(fileName);
        HashMap<Integer, HashMap<Integer, List<TimePeriod>>> periodsByEmployeeIdByProjectId = parser.parseFile();
        PairFinder finder = new PairFinder();
        EmployeePair pair = finder
                .getTheLongestWorkingTogether(periodsByEmployeeIdByProjectId)
                .orElse(new EmployeePair(-1, -1));
        System.out.println(pair);
    }
}
