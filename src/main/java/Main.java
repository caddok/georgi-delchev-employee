import java.util.*;

public class Main {

    public static void main(String[] args) throws ParsingException {
        String fileName = "data.txt";
        if (args.length > 0) {
            fileName = args[0];
        }
        CommandLineParser parser = new CommandLineParser(fileName);
        HashMap<Integer, HashMap<Integer, List<TimePeriod>>> periodsByEmployeeIdByProjectId = parser.parseFile();
        PairFinder finder = new PairFinder();
        EmployeePair pair = finder.getTheLongestWorkingTogether(periodsByEmployeeIdByProjectId).orElse(new EmployeePair(-1, -1));
        //System.out.println(new String(" hello     there   ").trim().replaceAll("\\s{2,}", " "));
        System.out.println(pair);
    }
}
