import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

class PairFinderTest {

    @Test
    @DisplayName("test with randomly generated data")
    void testExampleData() throws ParsingException {
        CommandLineParser commandLineParser = new CommandLineParser("data.txt");
        HashMap<Integer, HashMap<Integer, List<TimePeriod>>> periodsByEmployeeIdByProjectId = commandLineParser.parseFile();
        PairFinder finder = new PairFinder();
        EmployeePair pair = finder.getTheLongestWorkingTogether(periodsByEmployeeIdByProjectId).orElse(new EmployeePair(-1, -1));
        EmployeePair expectedPair = new EmployeePair(3, 5);
        Assertions.assertEquals(pair, expectedPair);
    }

    @Test
    @DisplayName("straight case with 3 people")
    void testStraightCaseWith3People() throws ParsingException {
        CommandLineParser commandLineParser = new CommandLineParser("test1.txt");
        HashMap<Integer, HashMap<Integer, List<TimePeriod>>> periodsByEmployeeIdByProjectId = commandLineParser.parseFile();
        PairFinder finder = new PairFinder();
        EmployeePair pair = finder.getTheLongestWorkingTogether(periodsByEmployeeIdByProjectId).orElse(new EmployeePair(-1, -1));
        EmployeePair expectedPair = new EmployeePair(2, 3);
        Assertions.assertEquals(pair, expectedPair);
    }

    @Test
    @DisplayName("test with 2 people on 2 different projects - no match")
    void testWith2PeopleAndNoMatch() throws ParsingException {
        CommandLineParser commandLineParser = new CommandLineParser("test2.txt");
        HashMap<Integer, HashMap<Integer, List<TimePeriod>>> periodsByEmployeeIdByProjectId = commandLineParser.parseFile();
        PairFinder finder = new PairFinder();
        EmployeePair pair = finder.getTheLongestWorkingTogether(periodsByEmployeeIdByProjectId).orElse(new EmployeePair(-1, -1));
        EmployeePair expectedPair = new EmployeePair(-1, -1);
        Assertions.assertEquals(pair, expectedPair);
    }

    @Test
    @DisplayName("test with 2 people on same project no match")
    void testWith2PeopleSameProjectNoMatch() throws ParsingException {
        CommandLineParser commandLineParser = new CommandLineParser("test3.txt");
        HashMap<Integer, HashMap<Integer, List<TimePeriod>>> periodsByEmployeeIdByProjectId = commandLineParser.parseFile();
        PairFinder finder = new PairFinder();
        EmployeePair pair = finder.getTheLongestWorkingTogether(periodsByEmployeeIdByProjectId).orElse(new EmployeePair(-1, -1));
        EmployeePair expectedPair = new EmployeePair(-1, -1);
        Assertions.assertEquals(pair, expectedPair);
    }

    @Test
    @DisplayName("test with 3 people on the same project and end date NULL")
    void testWith3PeopleSameProjectEndDateNull() throws ParsingException {
        CommandLineParser commandLineParser = new CommandLineParser("test4.txt");
        HashMap<Integer, HashMap<Integer, List<TimePeriod>>> periodsByEmployeeIdByProjectId = commandLineParser.parseFile();
        PairFinder finder = new PairFinder();
        EmployeePair pair = finder.getTheLongestWorkingTogether(periodsByEmployeeIdByProjectId).orElse(new EmployeePair(-1, -1));
        EmployeePair expectedPair = new EmployeePair(1, 2);
        Assertions.assertEquals(pair, expectedPair);
    }

    @Test
    @DisplayName("test with no data")
    void testWithEmptyFile() throws ParsingException {
        CommandLineParser commandLineParser = new CommandLineParser("test5.txt");
        HashMap<Integer, HashMap<Integer, List<TimePeriod>>> periodsByEmployeeIdByProjectId = commandLineParser.parseFile();
        PairFinder finder = new PairFinder();
        EmployeePair pair = finder.getTheLongestWorkingTogether(periodsByEmployeeIdByProjectId).orElse(new EmployeePair(-1, -1));
        EmployeePair expectedPair = new EmployeePair(-1, -1);
        Assertions.assertEquals(pair, expectedPair);
    }

    @Test
    @DisplayName("test with more then one whitespaces after each value")
    void testWithMoreThenOneWhitespace() throws ParsingException {
        CommandLineParser commandLineParser = new CommandLineParser("test6.txt");
        HashMap<Integer, HashMap<Integer, List<TimePeriod>>> periodsByEmployeeIdByProjectId = commandLineParser.parseFile();
        PairFinder finder = new PairFinder();
        EmployeePair pair = finder.getTheLongestWorkingTogether(periodsByEmployeeIdByProjectId).orElse(new EmployeePair(-1, -1));
        EmployeePair expectedPair = new EmployeePair(1, 2);
        Assertions.assertEquals(pair, expectedPair);
    }

    @Test
    @DisplayName("test with 3 people, 2 with multiple intervals and overlapping with 3rd person")
    void testWith3People_2MultipleIntervalsAndOverlappingWith3rdPerson() throws ParsingException {
        CommandLineParser commandLineParser = new CommandLineParser("test7.txt");
        HashMap<Integer, HashMap<Integer, List<TimePeriod>>> periodsByEmployeeIdByProjectId = commandLineParser.parseFile();
        PairFinder finder = new PairFinder();
        EmployeePair pair = finder.getTheLongestWorkingTogether(periodsByEmployeeIdByProjectId).orElse(new EmployeePair(-1, -1));
        EmployeePair expectedPair = new EmployeePair(1, 2);
        Assertions.assertEquals(pair, expectedPair);
    }
}