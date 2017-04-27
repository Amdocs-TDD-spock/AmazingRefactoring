/**
 * + The original program is by Jack Hauber, and the source is
 * "Basic Computer Games." Used with permission of David Ahl;
 * see www.SwapMeetDave.com.
 * + This exercise was inspired by Alan Hensel's use of Amazing
 * as a refactoring challenge.
 * + This transliteration to Java was created by Bill Wake, William.Wake@acm.org
 */
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Amazing {
    static int target = 0;      // where GOTO goes
    public static Random random = new Random(0);
    static StringBuffer result = new StringBuffer();

    private static final int CLOSED = 0;
    private static final int RIGHT_CLOSED = 1;
    private static final int BOTTOM_CLOSED = 2;
    private static final int OPENED = 3;

    public static void main(String[] args) {
        doit(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        System.out.println(result);
    }

    private static void clear() {
        result.setLength(0);
    }

    private static void println() {
        result.append("\n");
    }

    public static void print(String text) {
        result.append(text);
    }

    /**
     * Retourne un entier aléatoire entre 1 et count
     * @param count
     * @return
     */
    public static int rnd(int count) {
        return (int) (count * random.nextFloat()) + 1;
    }

    public static void GOTO(int lineno) {
        target = lineno;
    }

    public static void doit(int rowSize, int columnSize) {
        clear();
        print("Amazing - Copyright by Creative Computing, Morristown, NJ");
        println();


        if (rowSize == 1 || columnSize == 1) return;

        int[][] wArray = new int[rowSize + 1][columnSize + 1];
        int[][] vArray = new int[rowSize + 1][columnSize + 1];

        int q = 0;
        int z = 0;
        int currentLinePathIndex = rnd(rowSize);
        printFirstLine(rowSize, currentLinePathIndex);


        // 190
        int processedCellCount = 1;
        wArray[currentLinePathIndex][1] = processedCellCount;
        processedCellCount++;

        // 200
        int r = currentLinePathIndex;
        int s = 1;
        GOTO(270);

        while (target != -1) {
            switch (target) {
                case 210:
                    if (r != rowSize)
                        GOTO(250);
                    else
                        GOTO(220);
                    continue;
                case 220:
                    if (s != columnSize)
                        GOTO(240);
                    else
                        GOTO(230);
                    continue;
                case 230:
                    r = 1;
                    s = 1;
                    GOTO(260);
                    continue;
                case 240:
                    r = 1;
                    s++;
                    GOTO(260);
                    continue;
                case 250:
                    r++;
                    GOTO(260);
                    continue;
                case 260:
                    if (wArray[r][s] == 0)
                        GOTO(210);
                    else
                        GOTO(270);
                    continue;
                case 270:
                    if (r - 1 == 0)
                        GOTO(600);
                    else
                        GOTO(280);
                    continue;
                case 280:
                    if (wArray[r - 1][s] != 0)
                        GOTO(600);
                    else
                        GOTO(290);
                    continue;
                case 290:
                    if (s - 1 == 0)
                        GOTO(430);
                    else
                        GOTO(300);
                    continue;
                case 300:
                    if (wArray[r][s - 1] != 0)
                        GOTO(430);
                    else
                        GOTO(310);
                    continue;
                case 310:
                    if (r == rowSize)
                        GOTO(350);
                    else
                        GOTO(320);
                    continue;
                case 320:
                    if (wArray[r + 1][s] != 0)
                        GOTO(350);
                    else
                        GOTO(330);
                    continue;
                case 330:
                    currentLinePathIndex = rnd(3);
                    GOTO(340);
                    continue;
                case 340:
                    if (currentLinePathIndex == 1)
                        GOTO(940);
                    else if (currentLinePathIndex == 2)
                        GOTO(980);
                    else if (currentLinePathIndex == 3)
                        GOTO(1020);
                    else
                        GOTO(350);
                    continue;
                case 350:
                    if (s != columnSize)
                        GOTO(380);
                    else
                        GOTO(360);
                    continue;
                case 360:
                    if (z == 1)
                        GOTO(410);
                    else
                        GOTO(370);
                    continue;
                case 370:
                    q = 1;
                    GOTO(390);
                    continue;
                case 380:
                    if (wArray[r][s + 1] != 0)
                        GOTO(410);
                    else
                        GOTO(390);
                    continue;
                case 390:
                    currentLinePathIndex = rnd(3);
                    GOTO(400);
                    continue;
                case 400:
                    if (currentLinePathIndex == 1)
                        GOTO(940);
                    else if (currentLinePathIndex == 2)
                        GOTO(980);
                    else if (currentLinePathIndex == 3)
                        GOTO(1090);
                    else
                        GOTO(410);
                    continue;
                case 410:
                    currentLinePathIndex = rnd(2);
                    GOTO(420);
                    continue;
                case 420:
                    if (currentLinePathIndex == 1)
                        GOTO(940);
                    else if (currentLinePathIndex == 2)
                        GOTO(980);
                    else
                        GOTO(430);
                    continue;
                case 430:
                    if (r == rowSize)
                        GOTO(530);
                    else
                        GOTO(440);
                    continue;
                case 440:
                    if (wArray[r + 1][s] != 0)
                        GOTO(530);
                    else
                        GOTO(450);
                    continue;
                case 450:
                    if (s != columnSize)
                        GOTO(480);
                    else
                        GOTO(460);
                    continue;
                case 460:
                    if (z == 1)
                        GOTO(510);
                    else
                        GOTO(470);
                    continue;
                case 470:
                    q = 1;
                    GOTO(490);
                    continue;
                case 480:
                    if (wArray[r][s + 1] != 0)
                        GOTO(510);
                    else
                        GOTO(490);
                    continue;
                case 490:
                    currentLinePathIndex = rnd(3);
                    GOTO(500);
                    continue;
                case 500:
                    if (currentLinePathIndex == 1)
                        GOTO(940);
                    else if (currentLinePathIndex == 2)
                        GOTO(1020);
                    else if (currentLinePathIndex == 3)
                        GOTO(1090);
                    else
                        GOTO(510);
                    continue;
                case 510:
                    currentLinePathIndex = rnd(2);
                    GOTO(520);
                    continue;
                case 520:
                    if (currentLinePathIndex == 1)
                        GOTO(940);
                    else if (currentLinePathIndex == 2)
                        GOTO(1020);
                    else
                        GOTO(530);
                    continue;
                case 530:
                    if (s != columnSize)
                        GOTO(560);
                    else
                        GOTO(540);
                    continue;
                case 540:
                    if (z == 1)
                        GOTO(940);
                    else
                        GOTO(550);
                    continue;
                case 550:
                    q = 1;
                    GOTO(570);
                    continue;
                case 560:
                    if (wArray[r][s + 1] != 0)
                        GOTO(940);
                    else
                        GOTO(570);
                    continue;
                case 570:
                    currentLinePathIndex = rnd(2);
                    GOTO(580);
                    continue;
                case 580:
                    if (currentLinePathIndex == 2)
                        GOTO(1090);
                    else
                        GOTO(940);
                    continue;
                case 600:
                    if (s - 1 == 0)
                        GOTO(790);
                    else
                        GOTO(610);
                    continue;
                case 610:
                    if (wArray[r][s - 1] != 0)
                        GOTO(790);
                    else
                        GOTO(620);
                    continue;
                case 620:
                    if (r == rowSize)
                        GOTO(720);
                    else
                        GOTO(630);
                    continue;
                case 630:
                    if (wArray[r + 1][s] != 0)
                        GOTO(720);
                    else
                        GOTO(640);
                    continue;
                case 640:
                    if (s != columnSize)
                        GOTO(670);
                    else
                        GOTO(650);
                    continue;
                case 650:
                    if (z == 1)
                        GOTO(700);
                    else
                        GOTO(660);
                    continue;
                case 660:
                    q = 1;
                    GOTO(680);
                    continue;
                case 670:
                    if (wArray[r][s + 1] != 0)
                        GOTO(700);
                    else
                        GOTO(680);
                    continue;
                case 680:
                    currentLinePathIndex = rnd(3);
                    GOTO(690);
                    continue;
                case 690:
                    if (currentLinePathIndex == 1)
                        GOTO(980);
                    else if (currentLinePathIndex == 2)
                        GOTO(1020);
                    else if (currentLinePathIndex == 3)
                        GOTO(1090);
                    else
                        GOTO(700);
                    continue;
                case 700:
                    currentLinePathIndex = rnd(2);
                    GOTO(710);
                    continue;
                case 710:
                    if (currentLinePathIndex == 1)
                        GOTO(980);
                    else if (currentLinePathIndex == 2)
                        GOTO(1020);
                    else
                        GOTO(720);
                    continue;
                case 720:
                    if (s != columnSize)
                        GOTO(750);
                    else
                        GOTO(730);
                    continue;
                case 730:
                    if (z == 1)
                        GOTO(780);
                    else
                        GOTO(740);
                    continue;
                case 740:
                    q = 1;
                    GOTO(760);
                    continue;
                case 750:
                    if (wArray[r][s + 1] != 0)
                        GOTO(780);
                    else
                        GOTO(760);
                    continue;
                case 760:
                    currentLinePathIndex = rnd(2);
                    GOTO(770);
                    continue;
                case 770:
                    if (currentLinePathIndex == 1)
                        GOTO(980);
                    else if (currentLinePathIndex == 2)
                        GOTO(1090);
                    else
                        GOTO(780);
                    continue;
                case 780:
                    GOTO(980);
                    continue;
                case 790:
                    if (r == rowSize)
                        GOTO(880);
                    else
                        GOTO(800);
                    continue;
                case 800:
                    if (wArray[r + 1][s] != 0)
                        GOTO(880);
                    else
                        GOTO(810);
                    continue;
                case 810:
                    if (s != columnSize)
                        GOTO(840);
                    else
                        GOTO(820);
                    continue;
                case 820:
                    if (z == 1)
                        GOTO(870);
                    else
                        GOTO(830);
                    continue;
                case 830:
                    q = 1;
                    GOTO(990);
                    continue;
                case 840:
                    if (wArray[r][s + 1] != 0)
                        GOTO(870);
                    else
                        GOTO(850);
                    continue;
                case 850:
                    currentLinePathIndex = rnd(2);
                    GOTO(860);
                    continue;
                case 860:
                    if (currentLinePathIndex == 1)
                        GOTO(1020);
                    else if (currentLinePathIndex == 2)
                        GOTO(1090);
                    else
                        GOTO(870);
                    continue;
                case 870:
                    GOTO(1020);
                    continue;
                case 880:
                    if (s != columnSize)
                        GOTO(910);
                    else
                        GOTO(890);
                    continue;
                case 890:
                    if (z == 1)
                        GOTO(930);
                    else
                        GOTO(900);
                    continue;
                case 900:
                    q = 1;
                    GOTO(920);
                    continue;
                case 910:
                    if (wArray[r][s + 1] != 0)
                        GOTO(930);
                    else
                        GOTO(920);
                    continue;
                case 920:
                    GOTO(1090);
                    continue;
                case 930:
                    GOTO(1190);
                    continue;
                case 940:
                    wArray[r - 1][s] = processedCellCount;
                    GOTO(950);
                    continue;
                case 950:
                    processedCellCount++;
                    vArray[r - 1][s] = BOTTOM_CLOSED;
                    r--;
                    GOTO(960);
                    continue;
                case 960:
                    if (processedCellCount == rowSize * columnSize + 1)
                        GOTO(1200);// Exit operation
                    else
                        GOTO(970); // Restart switch
                    continue;
                case 970:
                    q = 0;
                    GOTO(270);
                    continue;
                case 980:
                    wArray[r][s - 1] = processedCellCount;
                    GOTO(990);
                    continue;
                case 990:
                    processedCellCount++;
                    GOTO(1000);
                    continue;
                case 1000:
                    vArray[r][s - 1] = RIGHT_CLOSED;
                    s--;
                    if (processedCellCount == rowSize * columnSize + 1)
                        GOTO(1200);
                    else
                        GOTO(1010);
                    continue;
                case 1010:
                    q = 0;
                    GOTO(270);
                    continue;
                case 1020:
                    wArray[r + 1][s] = processedCellCount;
                    GOTO(1030);
                    continue;
                case 1030:
                    processedCellCount++;
                    if (vArray[r][s] == CLOSED)
                        GOTO(1050);
                    else
                        GOTO(1040);
                    continue;
                case 1040:
                    vArray[r][s] = OPENED;
                    GOTO(1060);
                    continue;
                case 1050:
                    vArray[r][s] = BOTTOM_CLOSED;
                    GOTO(1060);
                    continue;
                case 1060:
                    r++;
                    GOTO(1070);
                    continue;
                case 1070:
                    if (processedCellCount == rowSize * columnSize + 1)
                        GOTO(1200);
                    else
                        GOTO(1080);
                    continue;
                case 1080:
                    GOTO(600);
                    continue;
                case 1090:
                    if (q == 1)
                        GOTO(1150);
                    else
                        GOTO(1100);
                    continue;
                case 1100:
                    wArray[r][s + 1] = processedCellCount;
                    processedCellCount++;
                    if (vArray[r][s] == CLOSED)
                        GOTO(1120);
                    else
                        GOTO(1110);
                    continue;
                case 1110:
                    vArray[r][s] = OPENED;
                    GOTO(1130);
                    continue;
                case 1120:
                    vArray[r][s] = RIGHT_CLOSED;
                    GOTO(1130);
                    continue;
                case 1130:
                    s++;
                    if (processedCellCount == columnSize * rowSize + 1)
                        GOTO(1200);
                    else
                        GOTO(1140);
                    continue;
                case 1140:
                    GOTO(270);
                    continue;
                case 1150:
                    z = 1;
                    GOTO(1160);
                    continue;
                case 1160:
                    if (vArray[r][s] == CLOSED)
                        GOTO(1180);
                    else
                        GOTO(1170);
                    continue;
                case 1170:
                    vArray[r][s] = OPENED;
                    q = 0;
                    GOTO(1190);
                    continue;
                case 1180:
                    vArray[r][s] = RIGHT_CLOSED;
                    q = 0;
                    r = 1;
                    s = 1;
                    GOTO(260);
                    continue;
                case 1190:
                    GOTO(210);
                    continue;
                case 1200:
                    target = -1;
                    continue;
            }

        }

        // 1200:
        for (int j = 1; j <= columnSize; j++) {
            print("I");        // 1210

            for (int i = 1; i <= rowSize; i++) {
                if (vArray[i][j] >= 2)
                    print("   ");  // 1240
                else
                    print("  I");  // 1260
            }

            print(" ");   // 1280
            println();

            for (int i = 1; i <= rowSize; i++) {
                if (vArray[i][j] == 0)
                    print(":--");   // 1300, 1340
                else if (vArray[i][j] == 2)
                    print(":--");  // 1310, 1340
                else
                    print(":  "); // 1320
            }

            print(":");    // 1360
            println();
        }
        System.out.println("warray:");

        for (int j = 1; j <= columnSize; j++) {

            for (int i = 1; i <= rowSize; i++) {
                System.out.print(wArray[i][j] + "\t");
            }
            System.out.println();
        }

            /*Stream.of(wArray).forEachOrdered(line -> {
            IntStream.of(line).forEachOrdered((i) -> System.out.print(i + "\t"));
            System.out.println();
                }
        );

        System.out.println("varray:");
        Stream.of(vArray).forEachOrdered(line -> {
                    IntStream.of(line).forEachOrdered((i) -> System.out.print(i + "\t"));
                    System.out.println();
                }
        );*/
    }

    private static void printFirstLine(int horizontal, int topDoorIndex) {

        for (int i = 1; i <= horizontal; i++) {
            if (i == topDoorIndex)
                print(":  ");
            else
                print(":--");
        }

        print(":");
        println();
    }
}
