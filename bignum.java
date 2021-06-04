import java.util.*;
public class bignum {
    static String[] line = new String[6];

    static void space()
    {
        line[0]+="       ";
        line[1]+="       ";
        line[2]+="       ";
        line[3]+="       ";
        line[4]+="       ";
        line[5]+="       ";
    }
    static void zero()
    {
        line[0]+="  ******  ";
        line[1]+=" *    * * ";
        line[2]+=" *   *  * ";
        line[3]+=" *  *   * ";
        line[4]+=" * *    * ";
        line[5]+="  ******  ";
    }
    static void one()
    {
        line[0]+="     *    ";
        line[1]+="   * *    ";
        line[2]+=" *   *    ";
        line[3]+="     *    ";
        line[4]+="     *    ";
        line[5]+="  ******* ";
    }
    static void letterA()
    {
        line[0]+="  ******  ";
        line[1]+=" *      * ";
        line[2]+=" *      * ";
        line[3]+=" ******** ";
        line[4]+=" *      * ";
        line[5]+=" *      * ";
    }
    static void letterC()
    {
        line[0]+="  ***** ";
        line[1]+=" *      ";
        line[2]+=" *      ";
        line[3]+=" *      ";
        line[4]+=" *      ";
        line[5]+="  ***** ";
    }
    static void letterD()
    {
        line[0]+=" ******  ";
        line[1]+=" *     * ";
        line[2]+=" *     * ";
        line[3]+=" *     * ";
        line[4]+=" *     * ";
        line[5]+=" ******  ";
    }
    static void letterE()
    {
        line[0]+=" ****** ";
        line[1]+=" *      ";
        line[2]+=" ****   ";
        line[3]+=" *      ";
        line[4]+=" *      ";
        line[5]+=" ****** ";
    }
    static void letterF()
    {
        line[0]+=" ****** ";
        line[1]+=" *      ";
        line[2]+=" ****   ";
        line[3]+=" *      ";
        line[4]+=" *      ";
        line[5]+=" *      ";
    }
    static void letterG()
    {
        line[0]+="  *****  ";
        line[1]+=" *       ";
        line[2]+=" *       ";
        line[3]+=" *   *** ";
        line[4]+=" *     * ";
        line[5]+="  *****  ";
    }
    static void letterH()
    {
        line[0]+=" *     * ";
        line[1]+=" *     * ";
        line[2]+=" ******* ";
        line[3]+=" *     * ";
        line[4]+=" *     * ";
        line[5]+=" *     * ";
    }
    static void letterI()
    {
        line[0]+=" ***** ";
        line[1]+="   *   ";
        line[2]+="   *   ";
        line[3]+="   *   ";
        line[4]+="   *   ";
        line[5]+=" ***** ";
    }
    static void letterN()
    {
        line[0]+=" *    * ";
        line[1]+=" **   * ";
        line[2]+=" * *  * ";
        line[3]+=" *  * * ";
        line[4]+=" *   ** ";
        line[5]+=" *    * ";
    }
    static void letterO()
    {
        line[0]+="  ******  ";
        line[1]+=" *      * ";
        line[2]+=" *      * ";
        line[3]+=" *      * ";
        line[4]+=" *      * ";
        line[5]+="  ******  ";
    }
    static void letterR()
    {
        line[0]+=" *****  ";
        line[1]+=" *    * ";
        line[2]+=" *****  ";
        line[3]+=" *    * ";
        line[4]+=" *    * ";
        line[5]+=" *    * ";
    }
    static void letterS()
    {
        line[0]+="  *****  ";
        line[1]+=" *       ";
        line[2]+="  *****  ";
        line[3]+="       * ";
        line[4]+="       * ";
        line[5]+="  *****  ";
    }
    static void letterT()
    {
        line[0]+=" ******* ";
        line[1]+="    *    ";
        line[2]+="    *    ";
        line[3]+="    *    ";
        line[4]+="    *    ";
        line[5]+="    *    ";
    }
    static void letterU()
    {
        line[0]+=" *      * ";
        line[1]+=" *      * ";
        line[2]+=" *      * ";
        line[3]+=" *      * ";
        line[4]+=" *      * ";
        line[5]+="  ******  ";
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        scan.close();
        for(int i=0; i<6; i++) line[i]="";
        for(int i=0; i<input.length(); ++i)
        {
            char currentChar = input.charAt(i);
            switch(currentChar)
            {
                case ' ': space(); break;
                case '0': zero(); break;
                case '1': one(); break;
                case 'a': letterA(); break;
                case 'c': letterC(); break;
                case 'd': letterD(); break;
                case 'e': letterE(); break;
                case 'f': letterF(); break;
                case 'g': letterG(); break;
                case 'h': letterH(); break;
                case 'i': letterI(); break;
                case 'n': letterN(); break;
                case 'o': letterO(); break;
                case 'r': letterR(); break;
                case 's': letterS(); break;
                case 't': letterT(); break;
                case 'u': letterU(); break;
            }
        }
        for(int i=0; i<6; i++) System.out.println(line[i]);
    }
}
