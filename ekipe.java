import java.util.*;
public class ekipe {
    public static void main(String[] args) {
        System.out.println("1  - Peter");    int p1=100;
        System.out.println("2  - Leon");     int p2=100;
        System.out.println("3  - Uroš");     int p3=85;
        System.out.println("4  - Beniman");  int p4=90;
        System.out.println("5  - Blaž");     int p5=60;
        System.out.println("6  - Adam");     int p6=70;
        System.out.println("7  - Nik");      int p7=60;
        System.out.println("8  - Živa");     int p8=65;
        System.out.println("9  - Lenart");   int p9=45;
        System.out.println("10 - Jure");    int p10=30;
        System.out.println("11 - Tomi");    int p11=30;
        System.out.println("12 - Ceki");    int p12=100;

        Scanner scan = new Scanner(System.in);
        int[] players = new int[10];
        for(int i=0; i<10; i++) players[i] = scan.nextInt();
        scan.close();
        Random RNG = new Random();



        int[] team1 = new int[5];
        int[] team2 = new int[5];
        
        while(true)
        {
            for(int i=0; i<5; i++) //sestavi naključno ekipo 1
            {
                int currPlayer = RNG.nextInt(9);
                boolean badPlayer=false;
                for(int j=0; j<i; j++)
                {
                    if(currPlayer==team1[j])
                    {
                        i--;
                        badPlayer=true;
                        break;
                    }
                }

                if(!badPlayer)
                {
                    team1[i]=currPlayer;
                }
            }

            int g=0;
            for(int i=0; i<10; i++) //generira ekipo 2 s ostalimi st.
            {
                boolean goodNum=true;
                for(int j=0; j<5; j++)
                {
                    if(team1[j]==i)
                    {
                        goodNum=false;
                        break;
                    }
                }
                if(goodNum)
                {
                    team2[g]=i;
                    g++;
                }
            }

            for(int i=0; i<5; i++) //outputa obe ekipi
            {
                System.out.println("Dokaz naključnih st ekip: " + team1[i] + " " + team2[i]);
            }

            for(int i=0; i<5; i++) //pretvori igralce v njihovo st.
            {
                team1[i]=players[team1[i]];
                team2[i]=players[team2[i]];
            }
            for(int i=0; i<5; i++) //outputa obe ekipi
            {
                System.out.println("Stevilke igralcev v ekipi: " + team1[i] + " " + team2[i]);
            }

            for(int i=0; i<5; i++) //pretvori st. v ime
            {
                switch(team1[i])
                {
                    case 1 : System.out.print("Peter    "); break;
                    case 2 : System.out.print("Leon     "); break;
                    case 3 : System.out.print("Uroš     "); break;
                    case 4 : System.out.print("Beniman  "); break;
                    case 5 : System.out.print("Blaž     "); break;
                    case 6 : System.out.print("Adam     "); break;
                    case 7 : System.out.print("Nik      "); break;
                    case 8 : System.out.print("Živa     "); break;
                    case 9 : System.out.print("Lenart   "); break;
                    case 10: System.out.print("Jure     "); break;
                    case 11: System.out.print("Tomi     "); break;
                    case 12: System.out.print("Ceki     "); break;
                    default: System.out.print("Invalid player budalo"); break;
                }
                switch(team2[i])
                {
                    case 1 : System.out.println(" Peter"); break;
                    case 2 : System.out.println(" Leon"); break;
                    case 3 : System.out.println(" Uroš"); break;
                    case 4 : System.out.println(" Beniman"); break;
                    case 5 : System.out.println(" Blaž"); break;
                    case 6 : System.out.println(" Adam"); break;
                    case 7 : System.out.println(" Nik"); break;
                    case 8 : System.out.println(" Živa"); break;
                    case 9 : System.out.println(" Lenart"); break;
                    case 10: System.out.println(" Jure"); break;
                    case 11: System.out.println(" Tomi"); break;
                    case 12: System.out.println(" Ceki"); break;
                    default: System.out.println(" Invalid player budalo"); break;
                }
            }

            for(int i=0; i<5; i++) //pretvori igralce v njihove vrednosti
            {
                switch(team1[i])
                {
                    case 1 : team1[i]=p1 ; break;
                    case 2 : team1[i]=p2 ; break;
                    case 3 : team1[i]=p3 ; break;
                    case 4 : team1[i]=p4 ; break;
                    case 5 : team1[i]=p5 ; break;
                    case 6 : team1[i]=p6 ; break;
                    case 7 : team1[i]=p7 ; break;
                    case 8 : team1[i]=p8 ; break;
                    case 9 : team1[i]=p9 ; break;
                    case 10: team1[i]=p10; break;
                    case 11: team1[i]=p11; break;
                    case 12: team1[i]=p12; break;
                    default: team1[i]= 0 ; break;
                }
                switch(team2[i])
                {
                    case 1: team2[i]=p1 ; break;
                    case 2: team2[i]=p2 ; break;
                    case 3: team2[i]=p3 ; break;
                    case 4: team2[i]=p4 ; break;
                    case 5: team2[i]=p5 ; break;
                    case 6: team2[i]=p6 ; break;
                    case 7: team2[i]=p7 ; break;
                    case 8: team2[i]=p8 ; break;
                    case 9: team2[i]=p9 ; break;
                    case 10: team2[i]=p10; break;
                    case 11: team2[i]=p11; break;
                    case 12: team1[i]=p12; break;
                    default: team2[i]= 0 ; break;
                }
            }
            /*for(int i=0; i<5; i++) //Vrednosti igralcev
            {
                System.out.println("Vrednosti igralcev: " + team1[i] + " " + team2[i]);
            }*/

            int team1sestevek=0;
            int team2sestevek=0;
            for(int i=0; i<5; i++)
            {
                team1sestevek+=team1[i];
                team2sestevek+=team2[i];
            }
            System.out.println(team1sestevek + " " + team2sestevek);
            
            if((team1sestevek+2)>=team2sestevek && (team1sestevek-2)<=team2sestevek || (team2sestevek+2)>=team1sestevek && (team2sestevek-2)<=team1sestevek)
            {
                break;
            }
        }
    }
}
