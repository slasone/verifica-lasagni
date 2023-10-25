package com.example;
import java.io.*;
import java.net.*;
/**
 * Hello world!
 * 
    Realizzare un'applicazione client-server che implementi il funzionamento di una calcolatrice:

    Il client si connette al server e gli invia operandi e operatore.

    Il server esegue l'operazione e invia il risultato al client.

    Le operazione richieste sono quelle di base (addizione, sottrazione, moltiplicazione e divisione).

    Resta libera la scelta di scambiare i dati in più passaggi oppure in un'unica stringa da parsare.

    Consegna:
    1. Link repository git
    2. File zip del progetto con nome file calcolatrice-tcp-cognome.zip (esclusa la cartella target)
    
 */
public class AppServer {

    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    
    String operatoreRicevuto = null;
    String risultato = null;


    public Socket attenti(){
            try{
                System.out.println("SERVER partito in esecuzione ...");

                server = new ServerSocket(6789);

                client = server.accept();

                server.close();

                inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));

                outVersoClient = new DataOutputStream(client.getOutputStream());
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Errore durante l'istanza del server!");
                System.exit(1);
            }
            return client; 
    }


    public void comunica(){
        try{
            System.out.println("Benvenuto client, scrivi operatore e operandi e faccio l'operazione richiesta. Attendo...");
            

            stringaRicevuta = inDalClient.readLine();
            int num1 = Integer.parseInt(stringaRicevuta);
            System.out.println("Ricevuto il primo numero dal client: " + stringaRicevuta);
            outVersoClient.writeBytes("Inserimento corretto" + '\n');


            stringaRicevuta = inDalClient.readLine();
            int num2 = Integer.parseInt(stringaRicevuta);
            System.out.println("Ricevuto il secondo numero dal client: " + stringaRicevuta);
            outVersoClient.writeBytes("Inserrimento corretto" + '\n');
            


            stringaRicevuta = inDalClient.readLine();
            String operatoreRicevuto = stringaRicevuta;
            System.out.println("Ricevuto l'operatore dal client: " + operatoreRicevuto);
            outVersoClient.writeBytes("Inserimento corretto" + '\n');
            

            if(operatoreRicevuto.equals("+")){
                risultato = String.valueOf(num1 + num2);
                System.out.println("Invio la risposta al client...");
                outVersoClient.writeBytes(risultato + '\n');

            }else if(operatoreRicevuto.equals("-")){
                risultato = String.valueOf(num1 - num2);
                System.out.println("Invio la risposta al client...");
                outVersoClient.writeBytes(risultato + '\n');

            }else if(operatoreRicevuto.equals("*")){
                risultato = String.valueOf(num1 * num2);
                System.out.println("Invio la risposta al client...");
                outVersoClient.writeBytes(risultato + '\n');

            }else if(operatoreRicevuto.equals(operatoreRicevuto)){
                risultato = String.valueOf(num1 / num2);
                System.out.println("Invio la risposta al client...");
                outVersoClient.writeBytes(risultato + '\n');
            }

            /*stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("7 Invio la risposta al client...");
            outVersoClient.writeBytes(stringaModificata + '\n');*/

            System.out.println("SERVER: fine elaborazione!");
            client.close();
        }catch(Exception e){

        }

    }

    public static void main( String[] args ){
        AppServer servente = new AppServer();
        servente.attenti();
        servente.comunica();
    }
}
