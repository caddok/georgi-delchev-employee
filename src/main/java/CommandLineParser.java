import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class CommandLineParser {
    private final HashMap<Integer, HashMap<Integer, List<TimePeriod>>> result;
    private final String fileName;

    CommandLineParser(final String name) {
        this.fileName = name;
        result = new HashMap<>();
    }
    /**
     *
     * @return The return type are the periods each person worked
     * grouped by projectId:
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
     * @throws ParsingException issued when an error occurs during
     * file parsing
     */
    HashMap<Integer, HashMap<Integer, List<TimePeriod>>> parseFile()
            throws ParsingException {
        Path path = Paths.get("tests", fileName);
        try (BufferedReader reader = new BufferedReader(
                new FileReader(new File(path.toUri())))) {
            String line = reader.readLine();
            while (line != null) {
                parseLine(line.trim().replaceAll("\\s{2,}", " ")
                        .split(", "));
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new ParsingException(e);
        }
        return result;
    }

    private void parseLine(final String[] dataTokens) {
        final int employeeIdIndex = 0;
        final int projectIdIndex = 1;
        final int startDateIndex = 2;
        final int endDateIndex = 3;

        Integer employeeId = Integer.parseInt(dataTokens[employeeIdIndex]);

        if (dataTokens[endDateIndex].equals("NULL")) {
            dataTokens[endDateIndex] = LocalDate.now().toString();
        }

        Integer projectId = Integer
                .parseInt(dataTokens[projectIdIndex]);

        LocalDate startDate = LocalDate.parse(dataTokens[startDateIndex]);
        LocalDate endDate = LocalDate.parse(dataTokens[endDateIndex]);
        TimePeriod period = new TimePeriod(startDate, endDate);

        if (result.containsKey(projectId)) {
            if (result.get(projectId).containsKey(employeeId)) {
                result.get(projectId).get(employeeId).add(period);
            } else {
                result.get(projectId).put(employeeId,
                        new ArrayList<>(Collections.singletonList(period)));
            }
        } else {
            result.put(projectId, new HashMap<>());
            result.get(projectId).put(employeeId,
                    new ArrayList<>(Collections.singletonList(period)));
        }
    }
}
