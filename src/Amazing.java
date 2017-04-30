/**
 * + The original program is by Jack Hauber, and the source is
 * "Basic Computer Games." Used with permission of David Ahl;
 * see www.SwapMeetDave.com.
 * + This exercise was inspired by Alan Hensel's use of Amazing
 * as a refactoring challenge.
 * + This transliteration to Java was created by Bill Wake, William.Wake@acm.org
 */


import java.util.Arrays;
import java.util.Random;

public class Amazing {
    private static final int FERME_EN_BAS_A_DROITE = 0;
    public static Random random = new Random(0);
    static int target = 0;      // where GOTO goes
    static StringBuffer result = new StringBuffer();

    private static void println() {
        result.append("\n");
    }

    public static void print(String text) {
        result.append(text);
    }

    /**
     * Returns a random int between 1 and count inclusive
     *
     * @param count
     * @return
     */
    public static int rnd(int count) {
        return (int) (count * random.nextFloat()) + 1;
    }

    public static void GOTO(int lineno) {
        target = lineno;
    }

    public static void goRandomlyTo(int... possibleWays) {
        GOTO(possibleWays[rnd(possibleWays.length) - 1]);
    }

    public static void doit(int colCount, int rowCount) {
        initMaze();

        int mazeEnterColIndex = rnd(colCount);

        if (colCount == 1 || rowCount == 1) return;

        displayFirstLine(colCount, mazeEnterColIndex);

        // Way array ?
        int[][] wArray = new int[colCount + 1][rowCount + 1];
        CellState[][] mazeCell = new CellState[colCount + 1][rowCount + 1];

        for (int i = 0; i <= colCount; i++) {
            wArray[i] = new int[rowCount + 1];
            mazeCell[i] = new CellState[rowCount + 1];
            for (int j=0; j<= rowCount;j++) {
                mazeCell[i][j]=CellState.CLOSED_RIGHT_AND_BOTTOM;
            }
        }

        boolean strangeBoolean = false; //boolean we don't know much about

        boolean evenMoreStrangeBoolean = false; //boolean we don't know much about


        boolean updateWArray = true;

        // 190
        int c = 1;
        wArray[mazeEnterColIndex][1] = c;
        c++;

        // 200
        int r = mazeEnterColIndex;
        int s = 1;
        GOTO(270);

        while (target != -1) {
            switch (target) {
                case 210:
                    do {
                        if (r != colCount) {
                            r++;
                        } else if (s != rowCount) {
                            r = 1;
                            s++;
                        } else {
                            r = 1;
                            s = 1;
                        }
                    } while (wArray[r][s] == 0);
                    GOTO(270);
                    continue;
                case 270:
                    if (r - 1 == 0 || wArray[r - 1][s] != 0)
                        GOTO(600);
                    else if (s - 1 == 0 || wArray[r][s - 1] != 0) {
                        if (r == colCount || wArray[r + 1][s] != 0) {
                            if (s != rowCount) {
                                if (wArray[r][s + 1] != 0)
                                    GOTO(940);
                                else {
                                    goRandomlyTo(940, 1090);
                                }
                            } else if (evenMoreStrangeBoolean)
                                GOTO(940);
                            else {
                                strangeBoolean = true;
                                goRandomlyTo(940, 1090);
                            }
                        } else if (s != rowCount) {
                            if (wArray[r][s + 1] != 0) {
                                goRandomlyTo(940, 1020);
                            } else {
                                goRandomlyTo(940, 1020, 1090);
                            }
                        } else if (evenMoreStrangeBoolean) {
                            goRandomlyTo(940, 1020);
                        } else {
                            strangeBoolean = true;
                            goRandomlyTo(940, 1020, 1090);
                        }
                    } else {
                        if (r == colCount || wArray[r + 1][s] != 0) {
                            if (s != rowCount) {
                                if (wArray[r][s + 1] != 0) {
                                    goRandomlyTo(940, 990);
                                } else {
                                    goRandomlyTo(940, 990, 1090);
                                }
                            } else if (evenMoreStrangeBoolean) {
                                goRandomlyTo(940, 990);
                            } else {
                                strangeBoolean = true;
                                goRandomlyTo(940, 990, 1090);
                            }
                        } else {
                            goRandomlyTo(940, 990, 1020);
                        }
                    }
                    continue;
                case 600:
                    if (s - 1 == 0 || wArray[r][s - 1] != 0) {
                        if (r == colCount || wArray[r + 1][s] != 0) {
                            if (s != rowCount) {
                                if (wArray[r][s + 1] != 0)
                                    GOTO(210);
                                else
                                    GOTO(1090);
                            } else {
                                if (evenMoreStrangeBoolean)
                                    GOTO(210);
                                else {
                                    strangeBoolean = true;
                                    GOTO(1090);
                                }
                            }
                        } else if (s != rowCount) {
                            if (wArray[r][s + 1] != 0)
                                GOTO(1020);
                            else {
                                goRandomlyTo(1020, 1090);
                            }
                        } else if (evenMoreStrangeBoolean)
                            GOTO(1020);
                        else {
                            strangeBoolean = true;
                            updateWArray = false;
                            GOTO(990);
                        }
                    } else if (r == colCount || wArray[r + 1][s] != 0) {
                        if (s != rowCount) {
                            if (wArray[r][s + 1] != 0) {
                                GOTO(990);
                            } else {
                                goRandomlyTo(990, 1090);
                            }
                        } else if (evenMoreStrangeBoolean) {
                            GOTO(990);
                        } else {
                            strangeBoolean = true;
                            goRandomlyTo(990, 1090);
                        }
                    } else if (s != rowCount) {
                        if (wArray[r][s + 1] != 0) {
                            goRandomlyTo(990, 1020);
                        } else {
                            goRandomlyTo(990, 1020, 1090);
                        }
                    } else if (evenMoreStrangeBoolean) {
                        goRandomlyTo(990, 1020);
                    } else {
                        strangeBoolean = true;
                        goRandomlyTo(990, 1020, 1090);
                    }
                    continue;
                case 940:
                    wArray[r - 1][s] = c;
                    c++;
                    mazeCell[r - 1][s] = CellState.OPENED_RIGHT;
                    r--;
                    if (c == colCount * rowCount + 1)
                        GOTO(1200);
                    else {
                        strangeBoolean = false;
                        GOTO(270);
                    }
                    continue;
                case 990:
                    //'wArray[r][s - 1] = c;' is sytematically called before each GOTO(990) except 1 in the original version
                    //'updateWArray' is introduced to keep the same behaviour even if tests still pass without it
                    if (updateWArray) {

                        wArray[r][s - 1] = c;
                    }
                    updateWArray = true;
                    c++;
                    mazeCell[r][s - 1] = CellState.OPENED_BOTTOM;
                    s--;
                    if (c == colCount * rowCount + 1)
                        GOTO(1200);
                    else {
                        strangeBoolean = false;
                        GOTO(270);
                    }
                    continue;
                case 1020:
                    wArray[r + 1][s] = c;
                    c++;
                    if (mazeCell[r][s]==CellState.CLOSED_RIGHT_AND_BOTTOM) {
                        mazeCell[r][s] = CellState.OPENED_RIGHT;
                        r++;
                    } else {
                        mazeCell[r][s] = CellState.OPENED_RIGHT_AND_BOTTOM;
                        r++;

                    }
                    if (c == colCount * rowCount + 1)
                        GOTO(1200);
                    else
                        GOTO(600);
                    continue;
                case 1090:
                    if (strangeBoolean) {
                        evenMoreStrangeBoolean = true;
                        if (mazeCell[r][s]==CellState.CLOSED_RIGHT_AND_BOTTOM) {
                            mazeCell[r][s] = CellState.OPENED_BOTTOM;
                            strangeBoolean = false;
                            r = 1;
                            s = 1;
                            if (wArray[r][s] == 0)
                                GOTO(210);
                            else
                                GOTO(270);
                        } else {
                            mazeCell[r][s] = CellState.OPENED_RIGHT_AND_BOTTOM;
                            strangeBoolean = false;
                            GOTO(210);
                        }
                    } else {
                        wArray[r][s + 1] = c;
                        c++;
                        if (mazeCell[r][s]==CellState.CLOSED_RIGHT_AND_BOTTOM) {
                            mazeCell[r][s] = CellState.OPENED_BOTTOM;
                        } else {
                            mazeCell[r][s] = CellState.OPENED_RIGHT_AND_BOTTOM;
                        }
                        s++;
                        if (c == rowCount * colCount + 1)
                            GOTO(1200);
                        else
                            GOTO(270);
                    }
                    continue;

                case 1200:
                    target = -1;
                    continue;
            }

        }
        display(colCount, rowCount, mazeCell);


    }

    private static void initMaze() {
        result.setLength(0);
        print("Amazing - Copyright by Creative Computing, Morristown, NJ");
        println();
    }

    private static void displayFirstLine(int horizontal, int x) {
        // 130:170
        for (int i = 1; i <= horizontal; i++) {
            if (i == x)
                print(":  ");
            else
                print(":--");
        }
        // 180
        print(":");
        println();
    }

    private static void display(int colCount, int rowCount, CellState[][] maze) {

        for (int j = 1; j <= rowCount; j++) {
            print("I");

            for (int i = 1; i <= colCount; i++) {
                maze[i][j].printFirstLine();
            }

            print(" ");
            println();

            for (int i = 1; i <= colCount; i++) {
                maze[i][j].printSecondLine();
            }

            print(":");    // 1360
            println();
        }

    }

    private enum CellState {
        CLOSED_RIGHT_AND_BOTTOM(0,"  I",":--"),
        OPENED_BOTTOM(1,"  I",":  "),
        OPENED_RIGHT(2,"   ",":--"),
        OPENED_RIGHT_AND_BOTTOM(3,"   ",":  ");

        private int value;
        private String firstLineRepresentation;
        private String secondLineRepresentation;

        CellState(int value,String firstLineRepresentation, String secondLineRepresentation) {

            this.value = value;
            this.firstLineRepresentation = firstLineRepresentation;
            this.secondLineRepresentation = secondLineRepresentation;
        }

        public int getValue() {
            return value;
        }

        public void printFirstLine() {
            print(firstLineRepresentation);
        }
        public void printSecondLine() {
            print(secondLineRepresentation);
        }

    }

}
