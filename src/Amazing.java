/**
 * + The original program is by Jack Hauber, and the source is
 * "Basic Computer Games." Used with permission of David Ahl;
 * see www.SwapMeetDave.com.
 * + This exercise was inspired by Alan Hensel's use of Amazing
 * as a refactoring challenge.
 * + This transliteration to Java was created by Bill Wake, William.Wake@acm.org
 */


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
        int currentCol = mazeEnterColIndex;
        int currentRow = 1;
        GOTO(270);

        while (target != -1) {
            switch (target) {
                case 210:
                    do {
                        if (currentCol != colCount) {
                            currentCol++;
                        } else if (currentRow != rowCount) {
                            currentCol = 1;
                            currentRow++;
                        } else {
                            currentCol = 1;
                            currentRow = 1;
                        }
                    } while (wArray[currentCol][currentRow] == 0);
                    GOTO(270);
                    continue;
                case 270:
                    if (currentCol == 1 || wArray[currentCol - 1][currentRow] != 0)
                        GOTO(600);
                    else if (currentRow == 1 || wArray[currentCol][currentRow - 1] != 0) {
                        if (currentCol == colCount || wArray[currentCol + 1][currentRow] != 0) {
                            if (currentRow != rowCount) {
                                if (wArray[currentCol][currentRow + 1] != 0)
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
                        } else if (currentRow != rowCount) {
                            if (wArray[currentCol][currentRow + 1] != 0) {
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
                        if (currentCol == colCount || wArray[currentCol + 1][currentRow] != 0) {
                            if (currentRow != rowCount) {
                                if (wArray[currentCol][currentRow + 1] != 0) {
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
                    if (currentRow == 1 || wArray[currentCol][currentRow - 1] != 0) {
                        if (currentCol == colCount || wArray[currentCol + 1][currentRow] != 0) {
                            if (currentRow != rowCount) {
                                if (wArray[currentCol][currentRow + 1] != 0)
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
                        } else if (currentRow != rowCount) {
                            if (wArray[currentCol][currentRow + 1] != 0)
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
                    } else if (currentCol == colCount || wArray[currentCol + 1][currentRow] != 0) {
                        if (currentRow != rowCount) {
                            if (wArray[currentCol][currentRow + 1] != 0) {
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
                    } else if (currentRow != rowCount) {
                        if (wArray[currentCol][currentRow + 1] != 0) {
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
                    wArray[currentCol - 1][currentRow] = c;
                    c++;
                    mazeCell[currentCol - 1][currentRow] = CellState.OPENED_RIGHT;
                    currentCol--;
                    if (isTheEnd(rowCount, colCount, c))
                        GOTO(1200);
                    else {
                        strangeBoolean = false;
                        GOTO(270);
                    }
                    continue;
                case 990:
                    //'wArray[currentCol][currentRow - 1] = c;' is sytematically called before each GOTO(990) except 1 in the original version
                    //'updateWArray' is introduced to keep the same behaviour even if tests still pass without it
                    if (updateWArray) {

                        wArray[currentCol][currentRow - 1] = c;
                    }
                    updateWArray = true;
                    c++;
                    mazeCell[currentCol][currentRow - 1] = CellState.OPENED_BOTTOM;
                    currentRow--;
                    if (isTheEnd(rowCount, colCount, c))
                        GOTO(1200);
                    else {
                        strangeBoolean = false;
                        GOTO(270);
                    }
                    continue;
                case 1020:
                    wArray[currentCol + 1][currentRow] = c;
                    c++;
                    openRightOfCell(mazeCell,currentCol,currentRow);
                    currentCol++;
                    if (isTheEnd(rowCount, colCount, c))
                        GOTO(1200);
                    else
                        GOTO(600);
                    continue;
                case 1090:
                    if (strangeBoolean) {
                        evenMoreStrangeBoolean = true;
                        openBottomOfCell(mazeCell,currentCol,currentRow);
                        if (mazeCell[currentCol][currentRow]==CellState.CLOSED_RIGHT_AND_BOTTOM) {
                            strangeBoolean = false;
                            currentCol = 1;
                            currentRow = 1;
                            if (wArray[currentCol][currentRow] == 0)
                                GOTO(210);
                            else
                                GOTO(270);
                        } else {
                            strangeBoolean = false;
                            GOTO(210);
                        }
                    } else {
                        wArray[currentCol][currentRow + 1] = c;
                        c++;
                        openBottomOfCell(mazeCell,currentCol,currentRow);
                        currentRow++;
                        if (isTheEnd(colCount, rowCount, c))
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

    /**
     * Opens the right wall of the cell at the given position
     * @param cellState
     */
    private static void openRightOfCell(CellState[][] mazeCell, int currentCol, int currentRow) {
        if (mazeCell[currentCol][currentRow]==CellState.CLOSED_RIGHT_AND_BOTTOM) {
            mazeCell[currentCol][currentRow] = CellState.OPENED_RIGHT;
        } else {
            mazeCell[currentCol][currentRow] = CellState.OPENED_RIGHT_AND_BOTTOM;
        }
    }

    /**
     * Opens the bottom wall of the cell at the given position
     * @param cellState
     */
    private static void openBottomOfCell(CellState[][] maze, int colIndex,int rowIndex) {
        if (maze[colIndex][rowIndex]==CellState.CLOSED_RIGHT_AND_BOTTOM) {
            maze[colIndex][rowIndex] = CellState.OPENED_BOTTOM;
        } else {
            maze[colIndex][rowIndex] = CellState.OPENED_RIGHT_AND_BOTTOM;
        }
    }

    private static boolean isTheEnd(int colCount, int rowCount, int c) {
        return c == rowCount * colCount + 1;
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
