import java.util.*;

public class Main {

    public static void main(String[] args) throws ParsingException {
        CommandLineParser parser = new CommandLineParser("test2.txt");
        HashMap<Integer, HashMap<Integer, List<TimePeriod>>> periodsByEmployeeIdByProjectId = parser.parseFile();
        PairFinder finder = new PairFinder();
        finder.getTheLongestWorkingTogether(periodsByEmployeeIdByProjectId).ifPresent((System.out::println));
    }

}
