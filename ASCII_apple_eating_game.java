import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;  // Import the Scanner class


class Igralec{
	String ime;
	int vrstica;
	int stolpec;
	String znak;
	int stSadezev = 0;

	public Igralec(String ime, String vrstica, String stolpec, String znak) 
	{ 	this.ime=ime;
		this.vrstica = Integer.parseInt(vrstica); 
		this.stolpec = Integer.parseInt(stolpec); 
		this.znak= " " + znak;
		
	}
	public int premikVrstica(String smerPremika){
		if(smerPremika.equalsIgnoreCase("s")) return 1;
		else if(smerPremika.equalsIgnoreCase("w")) return -1;
		else return 0;
	}
	public int premikStolpec(String smerPremika){
		if(smerPremika.equalsIgnoreCase("d")) return 1;
		else if(smerPremika.equalsIgnoreCase("a")) return -1;
		else return 0;
	}
	public void premik(int spremembaVrstice, int spremembaStolpec){
		vrstica+=spremembaVrstice;
		stolpec+=spremembaStolpec;
	}
}


public class ASCII_apple_eating_game {
	
	public static void main(String[] args)throws Exception {
		System.out.println("Igro igrata dva igralca, katera naprej svoje ime, pozicijo in znak vneseta v datoteko igralci.txt."
		+ "Prvi igralec se premika s tipkami WSAD, drugi igralec pa s tipkami IKJL ");
		polje polje1 = new polje(40, 4);
		File igralci = new File("igralci.txt");	
		BufferedReader br = new BufferedReader(new FileReader(igralci));
		br.readLine();
		
		String igralec1info = br.readLine();
		String[] tabIgralca1 = igralec1info.split(";");
		String igralec2info = br.readLine();
		String[] tabIgralca2 = igralec2info.split(";");
		br.close();

		Igralec igralec1 = new Igralec(tabIgralca1[0],tabIgralca1[1],tabIgralca1[2],tabIgralca1[3]);
		Igralec igralec2 = new Igralec(tabIgralca2[0],tabIgralca2[1],tabIgralca2[2],tabIgralca2[3]);
		polje1.polje[igralec1.stolpec][igralec1.vrstica] = igralec1.znak;
		polje1.polje[igralec2.stolpec][igralec2.vrstica] = igralec2.znak;
		

		Scanner scan = new Scanner(System.in);
		System.out.println("Vnesi koliko potez želiš izvesti?");
		int stPotez = scan.nextInt();
				
		int runda = 0;
		while(true){
			for(int i=0; i<stPotez; i++){
				polje1.izrisPolja(runda);
				System.out.println("Smer premika: (WASD)");
				String smerPremika = scan.next();
				int spremembaVrstice = igralec1.premikVrstica(smerPremika);
				int spremembaStolpec = igralec1.premikStolpec(smerPremika);
				if(runda%2==0){
					if(igralec1.stolpec+spremembaStolpec<0 || igralec1.stolpec+spremembaStolpec>=polje1.velikost){ //preveri ali gremo ven iz polja
						System.out.println("Igralec A izgubi");
						return; //gremo ven iz metode main in s tem koncamo program
					}
					if(igralec1.vrstica+spremembaVrstice<0 || igralec1.vrstica+spremembaVrstice>=polje1.velikost){ //preveri ali gremo ven iz polja
						System.out.println("Igralec A izgubi");
						return; //gremo ven iz metode main in s tem koncamo program
					}
					igralec1.stSadezev += polje1.preveriSadez(igralec1.vrstica, igralec1.stolpec, spremembaVrstice, spremembaStolpec);
					polje1.premikIgralca(igralec1.vrstica, igralec1.stolpec, spremembaVrstice, spremembaStolpec, igralec1.znak);
					igralec1.premik(spremembaVrstice, spremembaStolpec);
					polje1.ustvariSadez(igralec1.vrstica, igralec1.stolpec, igralec2.vrstica, igralec2.stolpec);
				}
				else{
					if(igralec2.stolpec+spremembaStolpec<0 || igralec2.stolpec+spremembaStolpec>=polje1.velikost){
						System.out.println("Igralec B izgubi");
						return;
					}
					if(igralec2.vrstica+spremembaVrstice<0 || igralec2.vrstica+spremembaVrstice>=polje1.velikost){ //preveri ali gremo ven iz polja
						System.out.println("Igralec A izgubi");
						return; //gremo ven iz metode main in s tem koncamo program
					}
					igralec2.stSadezev += polje1.preveriSadez(igralec2.vrstica, igralec2.stolpec, spremembaVrstice, spremembaStolpec);
					polje1.premikIgralca(igralec2.vrstica, igralec2.stolpec, spremembaVrstice, spremembaStolpec, igralec2.znak);
					igralec2.premik(spremembaVrstice, spremembaStolpec);
					polje1.ustvariSadez(igralec1.vrstica, igralec1.stolpec, igralec2.vrstica, igralec2.stolpec);
				}
				if(igralec1.stSadezev == 5 || igralec2.stSadezev == 5) break;
			}
			if(igralec1.stSadezev == 5){
				System.out.println("Zmagal je A");
				break;
			}
			else if(igralec1.stSadezev == 5){
				System.out.println("Zmagal je B");
				break;
			}
			runda++;
		}
	}
}
class polje{
	int velikost;
	int stSadez;
	String[][] polje;
	String narisanoPolje;

	public polje(int velikost, int stSadez){
		this.velikost=velikost;
		this.stSadez=stSadez;
		polje = new String[velikost][velikost];
		ustvariZacetnoPolje();
	}

	public void ustvariZacetnoPolje(){
		for(int t = 0; t < polje.length; t++){
			for(int u = 0; u < polje[0].length; u++)
			{
				polje[t][u]=" .";
			}
		}
		for(int i=0; i<stSadez; i++){
			polje[(int) (Math.random()*polje.length)][(int) (Math.random()*polje.length)]=" @";
		}
	}

	public void izrisPolja(int runda){
		String crta = "";
		for(int i=0; i< (velikost-6)/2;i++) crta+="--";
		if(runda%2==0) System.out.println(crta + "Na vrsti je A" + crta);
		else System.out.println(crta + "Na vrsti je B" + crta);
		narisanoPolje="";
		for(int t = 0; t < polje.length; t++){
			for(int u = 0; u < polje[0].length; u++)
			{
				narisanoPolje+=polje[t][u];
			}
			narisanoPolje+="\n";
		}
		System.out.println(narisanoPolje);
	}

	public int preveriSadez(int vrstica, int stolpec, int premikVrstica, int premikStolpec){
		if(polje[vrstica+premikVrstica][stolpec+premikStolpec].equals(" @")) return 1;
		else return 0;
	}
	public void premikIgralca(int vrstica, int stolpec, int premikVrstica, int premikStolpec, String znak){
		polje[vrstica+premikVrstica][stolpec+premikStolpec] = znak;
		polje[vrstica][stolpec] = " .";
	}

	public void ustvariSadez(int i1vrstica, int i1stolpec, int i2vrstica, int i2stolpec){
		int sadezVrstica = (int)(Math.random()*velikost);
		int sadezStolpec = (int)(Math.random()*velikost);
		while(!(sadezVrstica != i1vrstica || sadezStolpec != i1stolpec) && !(sadezVrstica != i2vrstica || sadezStolpec != i2stolpec)){ //preveri da ni na mestu kateregakoli igralca
			sadezVrstica = (int)(Math.random()*velikost);
			sadezStolpec = (int)(Math.random()*velikost);
		}
		polje[sadezVrstica][sadezStolpec] = " @";
	}
}
