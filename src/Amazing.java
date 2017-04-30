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

    public static void doit(int horizontal, int vertical) {
        initMaze();

        int x = rnd(horizontal);

        if (horizontal == 1 || vertical == 1) return;

        displayFirstLine(horizontal, x);

        // Way array ?
        int[][] wArray = new int[horizontal + 1][vertical + 1];
        int[][] matrice = new int[horizontal + 1][vertical + 1];

        for (int i = 0; i <= horizontal; i++) {
            wArray[i] = new int[vertical + 1];
            matrice[i] = new int[vertical + 1];
        }

        boolean strangeBoolean= false; //boolean we don't know much about

        boolean evenMoreStrangeBoolean = false; //boolean we don't know much about


        // 190
        int c = 1;
        wArray[x][1] = c;
        c++;

        // 200
        int r = x;
        int s = 1;
        GOTO(270);

        while (target != -1) {
            switch (target) {
                case 210:
                    do {
                        if (r != horizontal) {
                            r++;
                        } else if (s != vertical) {
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
                        if (r == horizontal || wArray[r + 1][s] != 0) {
                            if (s != vertical) {
                                if (wArray[r][s + 1] != 0)
                                    GOTO(940);
                                else {
                                    x = rnd(2);
                                    if (x == 1)
                                        GOTO(940);
                                    else
                                        GOTO(1090);
                                }
                            } else if (evenMoreStrangeBoolean)
                                GOTO(940);
                            else {
                                strangeBoolean=true;
                                x = rnd(2);
                                if (x == 1)
                                    GOTO(940);
                                else
                                    GOTO(1090);
                            }
                        } else if (s != vertical) {
                            if (wArray[r][s + 1] != 0) {
                                x = rnd(2);
                                if (x == 1)
                                    GOTO(940);
                                else
                                    GOTO(1020);
                            } else {
                                x = rnd(3);
                                if (x == 1)
                                    GOTO(940);
                                else if (x == 2)
                                    GOTO(1020);
                                else
                                    GOTO(1090);
                            }
                        } else if (evenMoreStrangeBoolean) {
                            x = rnd(2);
                            if (x == 1)
                                GOTO(940);
                            else
                                GOTO(1020);
                        } else {
                            strangeBoolean=true;
                            x = rnd(3);
                            if (x == 1)
                                GOTO(940);
                            else if (x == 2)
                                GOTO(1020);
                            else
                                GOTO(1090);
                        }
                    } else {
                        if (r == horizontal || wArray[r + 1][s] != 0) {
                            if (s != vertical) {
                                if (wArray[r][s + 1] != 0) {
                                    x = rnd(2);
                                    if (x == 1)
                                        GOTO(940);
                                    else {
                                        wArray[r][s - 1] = c;
                                        GOTO(990);
                                    }
                                } else {
                                    x = rnd(3);
                                    if (x == 1)
                                        GOTO(940);
                                    else if (x == 2) {
                                        wArray[r][s - 1] = c;
                                        GOTO(990);
                                    } else
                                        GOTO(1090);
                                }
                            } else if (evenMoreStrangeBoolean) {
                                x = rnd(2);
                                if (x == 1)
                                    GOTO(940);
                                else {
                                    wArray[r][s - 1] = c;
                                    GOTO(990);
                                }
                            } else {
                                strangeBoolean=true;
                                x = rnd(3);
                                if (x == 1)
                                    GOTO(940);
                                else if (x == 2) {
                                    wArray[r][s - 1] = c;
                                    GOTO(990);
                                } else
                                    GOTO(1090);
                            }
                        } else {
                            x = rnd(3);
                            if (x == 1)
                                GOTO(940);
                            else if (x == 2) {
                                wArray[r][s - 1] = c;
                                GOTO(990);
                            } else
                                GOTO(1020);
                        }
                    }
                    continue;
                case 600:
                    if (s - 1 == 0 || wArray[r][s - 1] != 0) {
                        if (r == horizontal) {
                            if (s != vertical) {
                                if (wArray[r][s + 1] != 0)
                                    GOTO(210);
                                else
                                    GOTO(1090);
                            } else {
                                if (evenMoreStrangeBoolean)
                                    GOTO(210);
                                else {
                                    strangeBoolean=true;
                                    GOTO(1090);
                                }
                            }
                        } else {
                            if (wArray[r + 1][s] != 0) {
                                if (s != vertical) {
                                    if (wArray[r][s + 1] != 0)
                                        GOTO(210);
                                    else
                                        GOTO(1090);
                                } else {
                                    if (evenMoreStrangeBoolean)
                                        GOTO(210);
                                    else {
                                        strangeBoolean=true;
                                        GOTO(1090);
                                    }
                                }
                            } else {
                                if (s != vertical) {
                                    if (wArray[r][s + 1] != 0)
                                        GOTO(1020);
                                    else {
                                        x = rnd(2); //x=1 or 2
                                        if (x == 1)
                                            GOTO(1020);
                                        else
                                            GOTO(1090);
                                    }
                                } else if (evenMoreStrangeBoolean)
                                    GOTO(1020);
                                else {
                                    strangeBoolean=true;
                                    GOTO(990);
                                }
                            }
                        }
                    } else if (r == horizontal || wArray[r + 1][s] != 0) {
                        if (s != vertical) {
                            if (wArray[r][s + 1] != 0) {
                                wArray[r][s - 1] = c;
                                GOTO(990);
                            } else {
                                x = rnd(2);
                                if (x == 1) {
                                    wArray[r][s - 1] = c;
                                    GOTO(990);
                                } else
                                    GOTO(1090);
                            }
                        } else if (evenMoreStrangeBoolean) {
                            wArray[r][s - 1] = c;
                            GOTO(990);
                        } else {
                            strangeBoolean=true;
                            x = rnd(2);
                            if (x == 1) {
                                wArray[r][s - 1] = c;
                                GOTO(990);
                            } else
                                GOTO(1090);
                        }
                    } else if (s != vertical) {
                        if (wArray[r][s + 1] != 0) {
                            x = rnd(2);
                            if (x == 1) {
                                wArray[r][s - 1] = c;
                                GOTO(990);
                            } else
                                GOTO(1020);
                        } else {
                            x = rnd(3);
                            if (x == 1) {
                                wArray[r][s - 1] = c;
                                GOTO(990);
                            } else if (x == 2)
                                GOTO(1020);
                            else
                                GOTO(1090);
                        }
                    } else if (evenMoreStrangeBoolean) {
                        x = rnd(2);
                        if (x == 1) {
                            wArray[r][s - 1] = c;
                            GOTO(990);
                        } else
                            GOTO(1020);
                    } else {
                        strangeBoolean=true;
                        x = rnd(3);
                        if (x == 1) {
                            wArray[r][s - 1] = c;
                            GOTO(990);
                        } else if (x == 2)
                            GOTO(1020);
                        else
                            GOTO(1090);
                    }
                    continue;
                case 940:
                    wArray[r - 1][s] = c;
                    c++;
                    matrice[r - 1][s] = 2;
                    r--;
                    if (c == horizontal * vertical + 1)
                        GOTO(1200);
                    else {
                        strangeBoolean=false;
                        GOTO(270);
                    }
                    continue;
                case 990:
                    c++;
                    matrice[r][s - 1] = 1;
                    s--;
                    if (c == horizontal * vertical + 1)
                        GOTO(1200);
                    else {
                        strangeBoolean=false;
                        GOTO(270);
                    }
                    continue;
                case 1020:
                    wArray[r + 1][s] = c;
                    c++;
                    if (isClosedBottomRight(matrice[r][s])) {
                        matrice[r][s] = 2;
                        r++;
                    } else {
                        matrice[r][s] = 3;
                        r++;

                    }
                    if (c == horizontal * vertical + 1)
                        GOTO(1200);
                    else
                        GOTO(600);
                    continue;
                case 1090:
                    if (strangeBoolean) {
                        evenMoreStrangeBoolean=true;
                        if (isClosedBottomRight(matrice[r][s])) {
                            matrice[r][s] = 1;
                            strangeBoolean=false;
                            r = 1;
                            s = 1;
                            if (wArray[r][s] == 0)
                                GOTO(210);
                            else
                                GOTO(270);
                        } else {
                            matrice[r][s] = 3;
                            strangeBoolean=false;
                            GOTO(210);
                        }
                    } else {
                        wArray[r][s + 1] = c;
                        c++;
                        if (isClosedBottomRight(matrice[r][s])) {
                            matrice[r][s] = 1;
                        } else {
                            matrice[r][s] = 3;
                        }
                        s++;
                        if (c == vertical * horizontal + 1)
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
        display(horizontal, vertical, matrice);


    }

    private static boolean isClosedBottomRight(int i) {
        return i == 0;
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

    private static void display(int horizontal, int vertical, int[][] vArray) {

        for (int j = 1; j <= vertical; j++) {
            print("I");

            for (int i = 1; i <= horizontal; i++) {
                if (vArray[i][j] >= 2)
                    print("   ");
                else
                    print("  I");
            }

            print(" ");
            println();

            for (int i = 1; i <= horizontal; i++) {
                if (isClosedBottomRight(vArray[i][j]) || isClosedBottom(vArray[i][j]))
                    print(":--");
                else
                    print(":  ");
            }

            print(":");    // 1360
            println();
        }
    }

    private static boolean isClosedBottom(int i) {
        return i == 2;
    }
}
