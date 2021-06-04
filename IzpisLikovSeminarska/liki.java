import java.util.*;
import java.io.*;
public class liki {
    public String narisanLik = ""; //v to lastnost bomo shranili lik, da ga lahko izrisemo

    public liki(){ //obvezen prazen konstruktor
        
    }
    public liki(int size){ //obvezen konstruktor z velikostjo, da lahko v dedovanih razredih uporabljamo konstruktorje z enakim parametrom
    }

    public void izpis(){ //izpis v temrinal
        System.out.println(narisanLik);
    }
    public void izpis(PrintWriter izpis){ //izpis v datoteko
        izpis.println();
        izpis.print(narisanLik);
        izpis.close();
    }
}

class krog extends liki{
    public krog(int size){
        int radius=size;
        String[][] coordinates = new String[radius*2+2][radius*2+2]; //ustvarimo koordinatni sistem, ki je dvakrat toliko velik kot vnesena velikost
        for(int i=0; (Math.PI*i)/1000<2*Math.PI; i++) //zanka, ki gre skozi celoten krog
        {
            coordinates[(int)(Math.cos((Math.PI*i)/1000)*(radius)+radius+1)][(int)(Math.sin((Math.PI*i)/1000)*(radius)+radius+1)]="**"; //vse celice, ki so na koordinatah, ki si izracunane preko kotnih funkcij, zapolnimo z zvezdicami
        }

        print(radius, coordinates);
    }
    private void print(int radius, String[][] coordinates)
    {
        for(int i=0; i<radius*2+1; i++)
        {
            for(int j=0; j<radius*2+1; j++)
            {
                if(coordinates[i][j]==null) narisanLik+="  "; //ce je celica prazna v niz dodamo presledke, drugace dodamo zvezdice
                else narisanLik+="**";
            }
            narisanLik+="\n";
        }
    }
}

class crta extends liki{
    public crta(int size){ //za crto samo izrisemo toliko likov, kakrsna je velikost
        for(int i=0; i<size; i++) narisanLik+="-";
    }
}
class trikotnik extends liki{
    public trikotnik(int size){ //pri trikotniku v vsako vrstico izrisemo eno celico zvezdic vec, dokler ne dosezemo zeljene velikosti
        for(int i=0; i<size; i++){
            narisanLik+="*";
            for(int j=0; j<i; j++){
                narisanLik+="**";
            }
            narisanLik+="\n";
        }
    }
}
class kvadrat extends liki{
    public kvadrat(int size){ //v vsako vrstico izrisemo toliko celic, kolikor je dolocena velikost
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                narisanLik+="**";
            }
            narisanLik+="\n";
        }
    }
}
class graf extends liki{
    String formulaZaIzpis;
    String stepZaIzpis;
    public graf(int size, String datoteka) throws IOException{ //graf je mnogo bolj zakompliciran kot ostali liki. Vanj moramo vnesti tako velikost kot tudi ime datoteke
        Scanner scan=new Scanner(System.in);
        double step;
        int radius=size;
        String formula;
        if(datoteka.length()<1){ //ce je ime datoteke krajse kot en simbol pomeni da ne bomo izpisovali v datoteko ampak v terminal, zato zacnemo izpisovati ukaze v terminal in iz tam beremo vrednosti spremenljivk
            /* System.out.println("Vnesi formulo (stevila manjsa kot 100) (format: nx^exp +- nx^exp +- ... +- nx + n):"); formula=scan.nextLine();
            System.out.println("Vnesi velikost koraka"); step=scan.nextDouble(); */
            /* try{ */
                System.out.println("Vnesi formulo (stevila manjsa kot 100) (format: nx^exp +- nx^exp +- ... +- nx + n):"); formula=scan.nextLine();
                System.out.println("Vnesi velikost koraka (npr. 0,1)"); step=scan.nextDouble(); //ce v procesu vnosa podatkov pride do napake ne nastane problem, saj je celotna metoda v drugi datoteki znotraj try-catch ukaza
            /* } catch(Exception e){
                System.out.println("Napaka pri vnosu podatkov");
                return;
            } */
        } else{ //ce ime datoteke ni krajse kot 1, pomeni da smo vnesli neko ime za datoteko, torej zelimo vanjo izpisovati. V tem primeru samo preberemo vrednosti spremenljivk iz datoteke
            BufferedReader vnos = new BufferedReader(new FileReader(datoteka));
            for(int i=0; i<5; i++) vnos.readLine(); //izpustimo prvih 5 vrstic, saj so tam napacni podatki.
            try{
                formula = vnos.readLine();
                step = Double.parseDouble(vnos.readLine());
            } catch(Exception e){
                System.out.println("Napaka pri vnosu podatkov");
                return; //pod pogojem, da je bila napaka pri vnosu uporabnika upozorimo in koncamo program
            }

        }
        this.formulaZaIzpis = formula;
        this.stepZaIzpis = step + "";
        String[][] coordinates = new String[radius*2][radius*2]; //ustvarimo tabelo, ki predstavlja celice grafa. Ta je v vsako smer dvakrat toliko velika kot vnesena velikost, saj imamo poleg pozitivnih tudi negativne vrednosti
        setCoordinateSystem(coordinates, radius, step); //Klicemo metodo, ki ustvari zacetno stanje koordinatnega sistema
        
        for(double x=-radius; x<radius; x+=0.01) //za vsako stotino x koordinate v grafu izracunamo tudi njegovo y koordinato
        {
            int y = -getFormula(formula, x, step); //klicemo funkcijo, ki prebere vneseno formulo in preko nje izracuna y koordinato
            if(!(y>=radius || y<-radius)) coordinates[y+radius][(int)x+radius]=" @"; //ce y koordinata ni izven dolocenega koordinatnega sistema, polovico celice zapolnimo z eno afno
        }
        print(radius, coordinates); //na koncu klicemo metodo print, ki vrednosti iz tabele prenese v nas niz za izpis
    }

    private static String[][] setCoordinateSystem(String[][] coordinates, int radius, double step)
    {
        //v pravilno dolocene celice izrisemo pike in crte, ki predstavljajo nas koordinatni sistem
        for(int x=-radius; x<radius; ++x)
        {
            for(int y = -radius; y<radius; ++y)
            {
                if((x%(1/step)==0 && y%(1/step)==0) || step>1) coordinates[y+radius][radius + x]=" .";
                //if(x%5==0 && y%5==0) coordinates[radius+x][(int)y+radius]=" -";
                coordinates[y+radius][radius]=" |";
                coordinates[radius][y+radius]="--";
            }
        }
        return coordinates;
    }

    private static int getFormula(String formula, double x, double step)
    {
        //preko vnesene formule izracunamo y koordinato
        double y1=1;
        double y=0;
        for(int i=formula.length()-1; i>=0; --i)
        {
            y1=ifStatements(i, y1, x, step, formula); //preko mnogo if stavkov dolocimo kaj moramo narediti
            if(formula.charAt(i)=='+' || formula.charAt(i)=='-') //vse stevke v formuli skupaj mnozimo/dajemo na eksponente, dokler ne dosezemo plusa ali minusa. Takrat to vrednost pristejemo y koordinati, in nadaljujemo na naslednji oddelek formule, kjer spet vrednosti mnozimo in ne sestevamo
            {
                if(formula.charAt(i)=='-') y-=y1;
                else y+=y1;
                if(i!=0)y1=1;
                else y1=0;
            }
        }
        y+=y1;
        return (int)(y*(1/step));
    }
    private static double ifStatements(int i, double y1, double x, double step, String formula)
    {
        //od konca formule do zacetka preverimo vsak simbol posebaj
        if(Character.isDigit(formula.charAt(i)) && i!=0 && formula.charAt(i-1)=='^') //ce je simbol stevka in je levo od tega simbola simbol ^, vemo, da zelimo x dati na nek eksponent
        {
            y1*=Math.pow(x*step, Character.getNumericValue(formula.charAt(i)));
        }
        else if(Character.isDigit(formula.charAt(i))) //ce zraven simbola ni simbola ^, pomeni da imamo ali navadno mnozenje, ali dvomestno stevilo
        {
            if(i!=0 && Character.isDigit(formula.charAt(i-1))) //ce imamo stevka levo od trenutne stevke, pomeni da je dvomestno stevilo
            {
                y1*= Character.getNumericValue(formula.charAt(i))+Character.getNumericValue(formula.charAt(i-1))*10;
            }
            else if(i!=formula.length()-1 && !Character.isDigit(formula.charAt(i+1))) y1*=Character.getNumericValue(formula.charAt(i)); //ce pa je stevka sama brez ^ in brez drugih stevk okoli, pomeni da zelimo mnoziti z stevko samo
        }
        else if(formula.charAt(i)=='x' && (i==formula.length()-1 || formula.charAt(i+1)!='^')) // ko dosezemo simbol x, vemo da zelimo dolociti neko vrednost
        {
            y1*=x*step;
        }
        return y1;
    }

    private void print(int radius, String[][] coordinates)
    {
        //iz tabele vse vrednosti postavimo v niz
        for(int i=0; i<radius*2; i++)
        {
            for(int j=0; j<radius*2; j++)
            {
                if(coordinates[i][j]==null) coordinates[i][j]="  "; //ce je celica prazna, jo zapolnimo z dvemi presledki
                narisanLik+=coordinates[i][j];
            }
            narisanLik+="\n";
        }
    }
}
class sestkotnik extends liki{
    public sestkotnik(int size){
        //pri temu liku moramo paziti, da so posevne stranice enako dolge kot horizontalne
        for(int i=0; i<size; i++)//zgornja polovica lika
        {
            for(int j=0; j<size-i; j++) narisanLik+=" ";//v vsaki vrsti izpisemo en manj presledek
            for(int j=0; j<size; j++) narisanLik+="***";//narisemo doloceno stevilo celic s tremi zvezdicami
            for(int j=0; j<i; j++) narisanLik+="**"; //ker mora vsaka vrstica biti malo daljsa kot prejsna, izrisemo celice z dvemi zvezdicami.
            narisanLik+="\n";
        }
        for(int i=size; i>=0; i--)//spodnja polovica lika
        {
            for(int j=0; j<size-i; j++) narisanLik+=" ";
            for(int j=0; j<size; j++) narisanLik+="***";
            for(int j=0; j<i; j++) narisanLik+="**";
            narisanLik+="\n";
        }
    }
}
