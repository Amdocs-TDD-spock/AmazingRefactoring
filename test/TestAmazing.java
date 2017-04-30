// Copyright 2003, William C. Wake. All rights reserved.

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@RunWith(Parameterized.class)
public class TestAmazing extends TestCase {

    @Parameterized.Parameter(0)
    public int seed;

    @Parameterized.Parameter(1)
    public int colCount;

    @Parameterized.Parameter(2)
    public int rowCount;


    @Parameterized.Parameters(name = "Test maze {1}x{2} with seed {0}")
    public static Collection<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        Random rnd = new Random(27);
        data.add(new Object[]{50,4,5});
        for (int colCount = 1; colCount <= 30; colCount += 4) {
            for (int rowCount = 1; rowCount <= 30; rowCount += 3) {
                for (int seedIndex = 0; seedIndex < 5; seedIndex ++) {

                    data.add(new Object[]{rnd.nextInt(15000), colCount, rowCount});
                }
            }
        }
        return data;
    }

    @Test
    public void test() throws IOException, URISyntaxException {
        Amazing.random = new Random(seed);
        Amazing.doit(colCount, rowCount);

        assertEquals("Should have the maze that was expected", getExpectedResult(), Amazing.result.toString());

    }

    private String getExpectedResult() throws URISyntaxException, IOException {
        String fileName = "testSeed" + seed + "size" + colCount + "x" + rowCount;
        try {
            Path path = Paths.get(TestAmazing.class.getResource("expectedResults/" + fileName).toURI());
            return new String(Files.readAllBytes(path), "UTF-8");
        } catch (NullPointerException e) {
            //result file does not exist
            Path path = Paths.get(TestAmazing.class.getResource("expectedResults/").toURI()).resolve(fileName);
            Files.write(path, Amazing.result.toString().getBytes("UTF-8"));
            fail("Result file did not exist. It was created and has to be added to expectedResultFolder after validation by human being");
            return null;
        }
    }
}
