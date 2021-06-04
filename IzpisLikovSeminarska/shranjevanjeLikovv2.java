import java.io.*;
import java.util.*;
public class shranjevanjeLikovv2 {
    public static void main(String[] args) throws IOException {
        //SEMINARSKA NALOGA: Izrisovanje in shranjevanje likov z razredi v Javi; Program nam omogoca shranjevanje sestih likov v tabelo. Iz tabele lahko nato klicemo poljuben lik. Lik lahko tudi izrisemo v datoteko
        //Na zacetku dolocimo nacin vnosa podatkov v program
        Scanner scan = new Scanner(System.in);
        System.out.println("Vnos preko datoteke ali preko terminala? \ndatoteka/cmd");
        String tipVnosa = scan.nextLine();
        if(tipVnosa.equals("datoteka")) datoteka(scan); //ce izberem "datoteka", nadaljujemo na metodo datoteka. Enako za "cmd". ce izbor ni nic od tega, nam izpise "neveljaven izbor"
        else if(tipVnosa.equals("cmd")) cmd(scan);
        else System.out.println("Neveljaven izbor");
        
        scan.close();
    }

    public static void datoteka(Scanner scan) throws IOException{
        //Vnos dobimo preko datoteke in vanjo tudi izpisujemo
        String datoteka = "datoteka.txt"; //dolocimo ime datoteke
        File ustvariDatoteko = new File("datoteka.txt");
        ustvariDatoteko.createNewFile(); //ce datoteka se ne obstaja, jo ustvarimo
        System.out.println("Izpis navodil v datoteko? (y/n)"); //y - yes, n - no
        String navodila = scan.nextLine();

        if(navodila.equals("y")) izpisNavodil(datoteka); //ce izberemo, da zelimo izpis navodil, nadaljujemo v to metodo.
        else branjeDatoteke(datoteka); //ce uporabnik izbere karkoli razen "y", nadaljujemo program za izpis lika
    }
    public static void izpisNavodil(String datoteka) throws IOException{
        PrintWriter izpis = new PrintWriter(new FileWriter(datoteka)); //napis nastavimo na nacin, kjer pobrise predhodnje podatke, da lahko zagotovimo pravilen izpis
        izpis.println("V naslednjo vrstico vpisite tip lika (krog, crta, trikotnik, kvadrat, graf, sestkotnik):\n");
        izpis.println("V naslednjo vrstico velikost lika:\n");
        izpis.println("ce ste izbrali graf, v naslednji dve vrstici vpisite tudi formulo (format: nx^exp +- nx^exp +- ... +- nx + n) in velikost koraka(npr. 0.2):\n\n");
        izpis.print("Lik bo izpisan v naslednje vrstice:");
        izpis.close(); //PrintWriter zapremo, da izpise vrstice
    }
    public static void branjeDatoteke(String datoteka) throws IOException{
        BufferedReader vnos = new BufferedReader(new FileReader(datoteka)); //ustvarimo bralec, da lahko pridobimo podatke iz datoteke
        vnos.readLine(); //prvo vrstico izpustimo, saj je ta napoljena z navodili.
        String tipLika = vnos.readLine(); //preberemo kaksen lik uporabnik zeli
        vnos.readLine();
        String sizeInput = vnos.readLine(); //preberemo kaksno velikost lika uporabnik zeli
        int size=0;
        PrintWriter izpis = new PrintWriter(new FileWriter(datoteka, true)); //izpisovanje nastavimo na nacin, ki ne pobrise predhodnji podatkov
        try{
            size = Integer.parseInt(sizeInput); //ce je uporabnik slucajno vnesel vrednost, ki ni celo stevilo, bo program izpisal error. Da preprecimo napake uporabimo try-catch.
        } catch(Exception e){ //pod pogojem, da je bil vnos neveljaven izpisemo ERROR in program izstopi izven metode in se s tem tudi konca
            izpis.println("\nERROR: Neveljaven vnos");
            izpis.close();
            return; //izstopimo iz metode
        }

        switch(tipLika){ //glede na vnos se izbere pravilen lik. Za izpis uporabimo metodo izpis(FilePrinter x), ki izpisuje v datoteko namesto v terminal
            case "krog": krog krog1 = new krog(size); krog1.izpis(izpis); break;
            case "crta": crta crta1 = new crta(size); crta1.izpis(izpis); break;
            case "trikotnik": trikotnik trikotnik1 = new trikotnik(size); trikotnik1.izpis(izpis); break;
            case "kvadrat": kvadrat kvadrat1 = new kvadrat(size); kvadrat1.izpis(izpis); break;
            case "graf": graf graf1 = new graf(size, datoteka); graf1.izpis(izpis); break; //v graf poleg velikosti posljemo tudi ime datoteke v obliki nase spremenljivke z imenom datoteke
            case "sestkotnik": sestkotnik sestkotnik1 = new sestkotnik(size); sestkotnik1.izpis(izpis); break;
            default: System.out.println("Lik ne obstaja"); izpis.println("\nERROR: Lik ne obstaja"); break; //ce je bila napaka pri vnosu nas program opozori, da smo vnesli neveljaven lik preden se ugasne
        }
    }

    public static void cmd(Scanner scan) throws IOException{
        //ce uporabnik zeli uporabljati terminal, se zacne izvajati drug nacin programa
        int stLikov = 0;
        while(stLikov<1){ //uporabnika prosimo za st. likov, ki jih zeli. V primeru napacnega vnosa, uporabnika ponovno prosimo za isto vrednost. To naredimo, da ne bi rabil uporabnik zaceti celoten program ponovno
            try{
                System.out.println("\nKoliko likov zelite?"); String stLikovString = scan.next();
                stLikov = Integer.parseInt(stLikovString);
                if(stLikov<1) System.out.println("Premajhen vnos");
            }catch(Exception e){
                System.out.println("Neveljaven vnos");
                stLikov=0;
            }
        }
        krog krogi[] = new krog[stLikov]; //inicializiramo toliko posameznega lika, kolikor naj bi bilo vseh skupnih. To naredimo, saj ne vemo ali bo uporabnik zelel 6 krogov, ali 1 lik vsake vrste.
        crta crte[] = new crta[stLikov];
        trikotnik trikotniki[] = new trikotnik[stLikov];
        kvadrat kvadrati[] = new kvadrat[stLikov];
        graf grafi[] = new graf[stLikov];
        sestkotnik sestkotniki[] = new sestkotnik[stLikov];
        String vsiLiki[][] = new String[stLikov][2]; //Ustvarimo tudi tabelo imenov likov, ki doloca kaksna sorta lika je shranjena na dolocenem mestu
        for(int i=0; i<stLikov; i++){ //na zacetku napolnimo vsa mesta z "Not available", saj na njih ni shranjeno nic
            vsiLiki[i][0] = "N/A";
            vsiLiki[i][1] = "0";
        }

        
        while(true){ //dokler uporabnik program ne zeli koncati, se bo stalno izvajala zanka, ki nam omogoca izpis ali vnos lika
            System.out.println("\nKateri lik zelite? (0-" + (stLikov-1) + " ali ? ali end)");
            String trenutenLikString = scan.next();
            int trenutenLik=0;
            try{ //celoten program je zaprt v velik try-catch, da se lahko izognemo vsem napakam pri vnosu naenkrat. Takih napak je lahko ogromno, saj imamo veliko vnosov
                if(trenutenLikString.contains("end")) break; //ce je uporabnik napisal end, bomo zapustili zanko in program se bo koncal
                else if(Character.isDigit(trenutenLikString.charAt(0))){ //ce uporabnik ni vnesel "end", preverimo ce je na prvem mestu stevka. V upanju, da uporabnik ni naredil napake pri vnosu, probamo zacetki sekvenco vnosa/izpisa
                    trenutenLik=Integer.parseInt(trenutenLikString); //vneseno vrednost probamo vnesti kot celo stevilo. Ce je poleg stevke bil se kaksen simbol, program ne bo deloval in vrnili se bomo na zacetek zanke
                    System.out.println("\nzelite lik spremeniti ali izpisati? (vnos/izpis)"); String input = scan.next();// uporabniki doloci ali zeli mesto v tabelo zapolniti z likom, ali zeli ze zapisan lik narisati na zaslon
                    if(input.equals("vnos")){
                        System.out.println("\nKaksen lik zelite? \nkrog, crta, trikotnik, kvadrat, graf, sestkotnik");
                        vsiLiki[trenutenLik][0] = scan.next(); //pod pogojem, da zeli uporabnik vnesti lik, ga program vprasa kaksen lik zeli. To shrani v tabelo shranjenih likov
                        System.out.println("Vnesite velikost lika:"); 
                        int size = scan.nextInt(); //program nato prosi za velikost lika, ki jo bo poslalo v preracunavanje
                        vsiLiki[trenutenLik][1]=size + "";
                        switch(vsiLiki[trenutenLik][0]){ //najdemo pravilen lik, razen ce ta ne obstaja. V konstruktor posljemo tudi velikost lika
                            case "krog": krogi[trenutenLik] = new krog(size); break;
                            case "crta": crte[trenutenLik] = new crta(size); break;
                            case "trikotnik": trikotniki[trenutenLik] = new trikotnik(size); break;
                            case "kvadrat": kvadrati[trenutenLik] = new kvadrat(size); break;
                            case "graf": grafi[trenutenLik] = new graf(size, ""); //v graf poleg velikosti posljemo tudi ime datoteke. Ker ne bomo izpisovali v datoteko, je to ime prazno
                                            vsiLiki[trenutenLik][1] += " --> " + grafi[trenutenLik].formulaZaIzpis + " --> " + grafi[trenutenLik].stepZaIzpis; 
                                            break; 
                            case "sestkotnik": sestkotniki[trenutenLik] = new sestkotnik(size); break;
                            default: System.out.println("Lik ne obstaja"); break;
                        }
                    }else if(input.equals("izpis")){
                        switch(vsiLiki[trenutenLik][0]){ //ce je uporabnik izbral izpis probamo izpisati lik preko metode izpis(). Ta izpise v terminal namesto v datoteko.
                            case "krog": krogi[trenutenLik].izpis(); break;
                            case "crta": crte[trenutenLik].izpis(); break;
                            case "trikotnik": trikotniki[trenutenLik].izpis(); break;
                            case "kvadrat": kvadrati[trenutenLik].izpis(); break;
                            case "graf": grafi[trenutenLik].izpis(); break;
                            case "sestkotnik": sestkotniki[trenutenLik].izpis(); break;
                            default: System.out.println("Lik ne obstaja"); break;
                        }
                    }
                }
                else{ //v primeru da uporabnik ni izbral niti en izmed likov, niti "end", mu izpisemo tabelo likov, da lahko vidi katera mesta je ze zapolnil
                    for(int i=0; i<vsiLiki.length; i++){
                        System.out.println(i + " - " + vsiLiki[i][0] + " (" + vsiLiki[i][1] + ")");
                    }
                }
            }catch(Exception e){ //ce je v kateremkoli vnosu prislo do napake, bo program javil napako in ponovno sel na zacetek zanke
                System.out.println("Napaka pri vnosu");
            }
        }
    }
}
