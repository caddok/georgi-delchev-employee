import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class CommandLineParser {
    private HashMap<Integer, HashMap<Integer, List<TimePeriod>>> result;
    private String fileName;

    CommandLineParser(String fileName) {
        this.fileName = fileName;
        result = new HashMap<>();
    }

    /**
     *
     * @return The return type are the periods each person worked grouped by projectId:
     * <pre>
     * {@code
     * {
     *   projectId: {
     *     personId: [
     *       range1,
     *       range2
     *     ]
     *   }
     * }
     * }
     *
     * </pre>
     * @throws ParsingException
     */
    HashMap<Integer, HashMap<Integer, List<TimePeriod>>> parseFile() throws ParsingException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                parseLine(line.split(", "));
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new ParsingException(e);
        }
        return result;
    }

    private void parseLine(String[] dataTokens) {
        Integer employeeId = Integer.parseInt(dataTokens[0]);

        if (dataTokens[3].equals("NULL")) {
            dataTokens[3] = LocalDate.now().toString();
        }

        Integer projectId = Integer.parseInt(dataTokens[1]);
        TimePeriod period = new TimePeriod(LocalDate.parse(dataTokens[2]), LocalDate.parse(dataTokens[3]));

        if (result.containsKey(projectId)) {
            if (result.get(projectId).containsKey(employeeId)) {
                result.get(projectId).get(employeeId).add(period);
            } else {
                result.get(projectId).put(employeeId, new ArrayList(Arrays.asList(period)));
            }
        } else {
            result.put(projectId, new HashMap<>());
            result.get(projectId).put(employeeId, new ArrayList<>(Arrays.asList(period)));
        }
    }
}
