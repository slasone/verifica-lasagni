package com.example;
import java.io.*;
import java.net.*;
/**
 * Hello world!
 *
 */
public class AppClient 
{
    
    String nomeServer = "localHost";
    int portaServer = 6789;
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    int rispostaServer = 0;
    int risultato = 0;

    public Socket connetti(){
        System.out.println("CLIENT partito in esecuzione..." + '\n');
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));

            mioSocket = new Socket(nomeServer, portaServer);

            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Host sconosciuto! \n");
        }
            catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Errore durante la connessione! \n");
                System.exit(1);
            }
            return mioSocket;
        }

    public void comunica(){
        try {
            System.out.println("Inserisci il PRIMO numero da trasmettere al server! ");
            stringaUtente = tastiera.readLine();
            outVersoServer.writeBytes(stringaUtente + '\n');
            System.out.println("\nInvio il numero al server e attendo... \n");

            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("Risposta dal server: " + stringaRicevutaDalServer + '\n');



            System.out.println("Inserisci il SECONDO numero da trasmettere al server! ");
            stringaUtente = tastiera.readLine();
            outVersoServer.writeBytes(stringaUtente + '\n');
            System.out.println("\nInvio il numero al server e attendo... \n");
            
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("Risposta dal server: " + stringaRicevutaDalServer + '\n');



            System.out.println("Inserisci l'OPERATORE da trasmettere al server! ");
            stringaUtente = tastiera.readLine();
            outVersoServer.writeBytes(stringaUtente + '\n');
            System.out.println("\nInvio l'operatore al server e attendo... \n");
            
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("Risposta dal server: " + stringaRicevutaDalServer + '\n');




            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("Risultato operazione = " + stringaRicevutaDalServer + '\n');

            System.out.println("CLIENT: termina elaborazione e chiude connessione");
            mioSocket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server!");
            System.exit(1);
        }
    }

    public static void main( String[] args ){
        AppClient cliente = new AppClient();
        cliente.connetti();
        cliente.comunica();
    }
}