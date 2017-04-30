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
    public static final int LEFT_WALL = 940;
    public static final int TOP_WALL = 990;
    public static final int RIGHT_WALL = 1020;
    public static final int BOTTOM_WALL = 1090;
    private static final int FERME_EN_BAS_A_DROITE = 0;
    public static Random random = new Random(0);
    static int target = 0;      // where GOTO goes
    static StringBuffer result = new StringBuffer();
    // maintains which cells have been processed at least once until now
    private static boolean[][] cellWasProcessed;

    private static int currentCol;
    private static int currentRow;

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

    public static void OPEN(int lineno) {
        GOTO(lineno);
    }

    public static void openRandomWallOfCurrentCell(int... possibleWays) {
        GOTO(possibleWays[rnd(possibleWays.length) - 1]);
    }

    public static void doit(int colCount, int rowCount) {
        initMaze();

        int mazeEnterColIndex = rnd(colCount);

        if (colCount == 1 || rowCount == 1) return;

        displayFirstLine(colCount, mazeEnterColIndex);

        // maintains which cells have been processed at least once until now
        cellWasProcessed = new boolean[colCount + 1][rowCount + 1];
        CellState[][] mazeCell = new CellState[colCount + 1][rowCount + 1];

        for (int i = 0; i <= colCount; i++) {
            cellWasProcessed[i] = new boolean[rowCount + 1];
            mazeCell[i] = new CellState[rowCount + 1];
            for (int j = 0; j <= rowCount; j++) {
                mazeCell[i][j] = CellState.CLOSED_RIGHT_AND_BOTTOM;
            }
        }

        boolean mazeExitIsDefined = false; //boolean we don't know much about


        boolean updateWArray = true;

        // 190
        int c = 1;
        cellWasProcessed[mazeEnterColIndex][1] = true;
        c++;

        // 200
        currentCol = mazeEnterColIndex;
        currentRow = 1;
        GOTO(270);

        while (target != -1) {
            switch (target) {
                case 210: //move to next already processed column
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
                    } while (!cellWasProcessed[currentCol][currentRow]);
                    GOTO(270);
                    continue;
                case 270:
                    if (currentCol == 1 || cellAtLeftWasProcessed())
                        GOTO(600);
                    else if (currentRow == 1 || cellAboveWasProcessed()) {
                        if (currentCol == colCount || cellAtRightWasProcessed()) {
                            if (currentRow != rowCount) {
                                if (cellBelowWasProcessed()) // we cannot go down because another wall of the cell below is open
                                    OPEN(LEFT_WALL);
                                else {
                                    openRandomWallOfCurrentCell(LEFT_WALL, BOTTOM_WALL);
                                }
                            } else if (mazeExitIsDefined)
                                OPEN(LEFT_WALL);
                            else {
                                openRandomWallOfCurrentCell(LEFT_WALL, BOTTOM_WALL);
                            }
                        } else if (currentRow != rowCount) {
                            if (cellBelowWasProcessed()) {
                                openRandomWallOfCurrentCell(LEFT_WALL, RIGHT_WALL);
                            } else {
                                openRandomWallOfCurrentCell(LEFT_WALL, RIGHT_WALL, BOTTOM_WALL);
                            }
                        } else if (mazeExitIsDefined) {
                            openRandomWallOfCurrentCell(LEFT_WALL, RIGHT_WALL);
                        } else {
                            openRandomWallOfCurrentCell(LEFT_WALL, RIGHT_WALL, BOTTOM_WALL);
                        }
                    } else {
                        if (currentCol == colCount || cellAtRightWasProcessed()) {
                            if (currentRow != rowCount) {
                                if (cellBelowWasProcessed()) {
                                    openRandomWallOfCurrentCell(LEFT_WALL, TOP_WALL);
                                } else {
                                    openRandomWallOfCurrentCell(LEFT_WALL, TOP_WALL, BOTTOM_WALL);
                                }
                            } else if (mazeExitIsDefined) {
                                openRandomWallOfCurrentCell(LEFT_WALL, TOP_WALL);
                            } else {
                                openRandomWallOfCurrentCell(LEFT_WALL, TOP_WALL, BOTTOM_WALL);
                            }
                        } else {
                            openRandomWallOfCurrentCell(LEFT_WALL, TOP_WALL, RIGHT_WALL);
                        }
                    }
                    continue;
                case 600:
                    if (currentRow == 1 || cellAboveWasProcessed()) {
                        if (currentCol == colCount || cellAtRightWasProcessed()) {
                            if (currentRow != rowCount) {
                                if (cellBelowWasProcessed())
                                    GOTO(210);
                                else
                                    OPEN(BOTTOM_WALL);
                            } else if (mazeExitIsDefined)
                                GOTO(210);
                            else {
                                OPEN(BOTTOM_WALL);
                            }
                        } else if (currentRow != rowCount) {
                            if (cellBelowWasProcessed())
                                OPEN(RIGHT_WALL);
                            else {
                                openRandomWallOfCurrentCell(RIGHT_WALL, BOTTOM_WALL);
                            }
                        } else if (mazeExitIsDefined)
                            OPEN(RIGHT_WALL);
                        else {
                            updateWArray = false;
                            OPEN(TOP_WALL);
                        }
                    } else if (currentCol == colCount || cellAtRightWasProcessed()) {
                        if (currentRow != rowCount) {
                            if (cellBelowWasProcessed()) {
                                OPEN(TOP_WALL);
                            } else {
                                openRandomWallOfCurrentCell(TOP_WALL, BOTTOM_WALL);
                            }
                        } else if (mazeExitIsDefined) {
                            OPEN(TOP_WALL);
                        } else {
                            openRandomWallOfCurrentCell(TOP_WALL, BOTTOM_WALL);
                        }
                    } else if (currentRow != rowCount) {
                        if (cellBelowWasProcessed()) {
                            openRandomWallOfCurrentCell(TOP_WALL, RIGHT_WALL);
                        } else {
                            openRandomWallOfCurrentCell(TOP_WALL, RIGHT_WALL, BOTTOM_WALL);
                        }
                    } else if (mazeExitIsDefined) {
                        openRandomWallOfCurrentCell(TOP_WALL, RIGHT_WALL);
                    } else {
                        openRandomWallOfCurrentCell(TOP_WALL, RIGHT_WALL, BOTTOM_WALL);
                    }
                    continue;
                case LEFT_WALL://open left wall of current cell
                    cellWasProcessed[currentCol - 1][currentRow] = true;
                    c++;
                    mazeCell[currentCol - 1][currentRow] = CellState.OPENED_RIGHT;
                    currentCol--;
                    if (isTheEnd(rowCount, colCount, c))
                        GOTO(1200);
                    else {
                        GOTO(270);
                    }
                    continue;
                case TOP_WALL: // open top wall of current cell and move to the cell above
                    //'cellWasProcessed[currentCol][currentRow - 1] = c;' is sytematically called before each GOTO(990) except 1 in the original version
                    //'updateWArray' is introduced to keep the same behaviour even if tests still pass without it
                    if (updateWArray) {

                        cellWasProcessed[currentCol][currentRow - 1] = true;
                    }
                    updateWArray = true;
                    c++;
                    mazeCell[currentCol][currentRow - 1] = CellState.OPENED_BOTTOM;
                    currentRow--;
                    if (isTheEnd(rowCount, colCount, c))
                        GOTO(1200);
                    else {
                        GOTO(270);
                    }
                    continue;
                case RIGHT_WALL: // open right wall of current cell and move to cell on the right
                    cellWasProcessed[currentCol + 1][currentRow] = true;
                    c++;
                    openRightOfCell(mazeCell, currentCol, currentRow);
                    currentCol++;
                    if (isTheEnd(rowCount, colCount, c))
                        GOTO(1200);
                    else
                        GOTO(600);
                    continue;
                case BOTTOM_WALL: // open bottom wall of current cell

                    if (currentRow == rowCount) {
                        mazeExitIsDefined = true;
                        if (mazeCell[currentCol][currentRow] == CellState.CLOSED_RIGHT_AND_BOTTOM) {
                            openBottomOfCell(mazeCell, currentCol, currentRow);
                            currentCol = 1;
                            currentRow = 1;
                            if (!cellWasProcessed[currentCol][currentRow])
                                GOTO(210);
                            else
                                GOTO(270);
                        } else {
                            GOTO(210);
                        }
                    } else {
                        // move to cell below
                        cellWasProcessed[currentCol][currentRow + 1] = true;
                        c++;
                        openBottomOfCell(mazeCell, currentCol, currentRow);
                        currentRow++;
                        if (isTheEnd(colCount, rowCount, c))
                            GOTO(1200);
                        else
                            GOTO(270);
                    }
                    continue;

                case 1200:
                    target = -1;

            }

        }
        display(colCount, rowCount, mazeCell);


    }

    private static boolean cellAtLeftWasProcessed() {
        return cellWasProcessed[currentCol - 1][currentRow];
    }

    private static boolean cellBelowWasProcessed() {
        return cellWasProcessed[currentCol][currentRow + 1];
    }

    private static boolean cellAtRightWasProcessed() {
        return cellWasProcessed[currentCol + 1][currentRow];
    }

    private static boolean cellAboveWasProcessed() {
        return cellWasProcessed[currentCol][currentRow - 1];
    }

    /**
     * Opens the right wall of the cell at the given position
     *
     */
    private static void openRightOfCell(CellState[][] mazeCell, int currentCol, int currentRow) {
        if (mazeCell[currentCol][currentRow] == CellState.CLOSED_RIGHT_AND_BOTTOM) {
            mazeCell[currentCol][currentRow] = CellState.OPENED_RIGHT;
        } else {
            mazeCell[currentCol][currentRow] = CellState.OPENED_RIGHT_AND_BOTTOM;
        }
    }

    /**
     * Opens the bottom wall of the cell at the given position
     *
     */
    private static void openBottomOfCell(CellState[][] maze, int colIndex, int rowIndex) {
        if (maze[colIndex][rowIndex] == CellState.CLOSED_RIGHT_AND_BOTTOM) {
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
        CLOSED_RIGHT_AND_BOTTOM("  I", ":--"),
        OPENED_BOTTOM("  I", ":  "),
        OPENED_RIGHT("   ", ":--"),
        OPENED_RIGHT_AND_BOTTOM("   ", ":  ");


        private String firstLineRepresentation;
        private String secondLineRepresentation;

        CellState(String firstLineRepresentation, String secondLineRepresentation) {
            this.firstLineRepresentation = firstLineRepresentation;
            this.secondLineRepresentation = secondLineRepresentation;
        }

        public void printFirstLine() {
            print(firstLineRepresentation);
        }

        public void printSecondLine() {
            print(secondLineRepresentation);
        }

    }

}
