import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import model.Contact;

public class App {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        afficherMenu();
        while (true) {
            String choix = scan.nextLine();
            switch (choix) {
                case "1":
                    ajouterContact();
                    break;
                case "2":
                    listerContact();
                    break;
                case "3":
                    listerContact();
                    deleteContact();
                    break;
                case "4":
                    listerContact();
                    modifContact();
                    break;
                case "5":
                    listerContact();
                    searchContact1();
                    break;
                case "6":
                    sortAContact();
                    break;
                case "7":
                    sortDate();
                    break;
                case "q":
                    scan.close();
                    return;
                default:
                    System.out.println("Boulet!!!!");
                    break;
            }
            afficherMenu();
        }
    }

    private static void deleteContact()  {

        System.out.println("Entrez le prénom du contact que vous voulez supprimer :");
        String numeroASupprimer = scan.nextLine();
        try {
        ArrayList<Contact> liste = Contact.lister();

        // Utilisez la méthode indexOf() pour rechercher le contact à supprimer
        int index = -1;
        for (int i = 0; i < liste.size(); i++) {
        Contact contact = liste.get(i);
            if (contact.getPrenom().equals(numeroASupprimer)) {
                index = i;
                break;
            }
        }
        System.out.println(index);
        // Si l'index a été trouvé, supprimer le contact de la liste et écrire la liste
        // modifiée dans le fichier contacts.csv
        if (index != -1) {
            liste.remove(index);
            Contact.ecrire(liste);
        }
    }
    catch(IOException e) {
        System.out.println("erreur avec le fichier");
    }
    }
    
    private static void modifContact() {
        System.out.println("Entrez le prénom du contact que vous voulez modifier");
        String numeroASupprimer = scan.nextLine();
        try {
            ArrayList<Contact> liste = Contact.lister();
    
            // Utilisez la méthode indexOf() pour rechercher le contact à supprimer
            int index = -1;
            for (int i = 0; i < liste.size(); i++) {
                Contact contact = liste.get(i);
                if (contact.getPrenom().equals(numeroASupprimer)) {
                    index = i;
                    break;
                }
            }
            // Si l'index a été trouvé, supprimer le contact de la liste et écrire la liste
            // modifiée dans le fichier contacts.csv
            if (index != -1) {
                Contact contact = liste.get(index); // utilisez la variable 'i' déclarée précédemment
                System.out.println(contact);
                System.out.println("Que voulez-vous modifier parmi le nom, le prenom, le mail, le numéro et la date de naissance ?");
                String champAModifier = scan.nextLine(); // stockez la valeur entrée par l'utilisateur
                switch (champAModifier) {
                    case "nom":
                        System.out.println("Entrez le nouveau nom");
                        String nouveauNom = scan.nextLine(); // stockez le nouveau nom entré par l'utilisateur  
                        contact.setNom(nouveauNom); // utilisez la méthode setNom() pour mettre à jour le nom du contact
                        break;
                    case "prenom":
                        System.out.println("Entrez le nouveau prenom");
                        String nouveauprenom = scan.nextLine(); // stockez le nouveau nom entré par l'utilisateur  
                        contact.setPrenom(nouveauprenom); // utilisez la méthode setNom() pour mettre à jour le nom du contact
                        break;
                    case "mail":
                        System.out.println("Entrez le nouveau mail");
                        String nouveaumail = scan.nextLine(); // stockez le nouveau nom entré par l'utilisateur  
                        contact.setMail(nouveaumail); // utilisez la méthode setNom() pour mettre à jour le nom du contact
                        break;
                    case "numero":
                        System.out.println("Entrez le nouveau numero");
                        String nouveaunumero = scan.nextLine(); // stockez le nouveau nom entré par l'utilisateur  
                        contact.setNumero(nouveaunumero); // utilisez la méthode setNom() pour mettre à jour le nom du contact
                        break;
                    case "date":
                        System.out.println("Entrez la nouvelle date");
                        String nouveaudate = scan.nextLine(); // stockez le nouveau nom entré par l'utilisateur  
                        contact.setDateNaissance(nouveaudate); // utilisez la méthode setNom() pour mettre à jour le nom du contact
                        break;
                    default:
                        System.out.println("Gros débile !");
                        break;
                }
    
              
                Contact.ecrire(liste); // écrivez la liste modifiée dans le fichier contacts.csv
            }
            else{
                System.out.println("contact non trouvé");
            }
        }
        catch(IOException e) {
            System.out.println("erreur avec le fichier");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    
    
}
    
    
    
        
    

    private static void listerContact() {
        // Contact c = new Contact();
        try {
            ArrayList<Contact> liste = Contact.lister();

            for (Contact contact : liste) {
                System.out.println(contact.getPrenom() + " " + contact.getNom());
            }
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier");
        }

    }

    private static void ajouterContact() {

        Contact c = new Contact();
        System.out.println("Saisir le nom:");
        c.setNom(scan.nextLine());
        System.out.println("Saisir le prénom:");
        c.setPrenom(scan.nextLine());

        do {
            try {
                System.out.println("Saisir le téléphone:");
                c.setNumero(scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        do {
            try {
                System.out.println("Saisir le mail:");
                c.setMail(scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        do {
            try {
                System.out.println("Saisir la date de naissance:");
                c.setDateNaissance(scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("Error, try again!");
            }
        } while (true);

        try {
            c.enregistrer();
            System.out.println("Contact enregistré.");
        } catch (IOException e) {
            System.out.println("Erreur à l'enregistrement");
        }

    }

    private static void searchContact1() throws IOException {
        // Saisissez le prénom du contact que vous souhaitez afficher
        System.out.println("\nEntrez le prénom du contact que vous voulez afficher :");
        String numeroASupprimer = scan.nextLine();
    
        // Charger la liste des contacts du fichier dans un ArrayList
        ArrayList<Contact> liste = Contact.lister();
    
        // Utilisez la méthode indexOf() pour rechercher le contact à supprimer
        var index = -1;
        for (int i = 0; i < liste.size(); i++) {
            Contact contact = liste.get(i);
            if (contact.getPrenom().equals(numeroASupprimer)) {
                index = i;
                break;
            }
        }
    
        // Si l'index a été trouvé, afficher la ligne d'index dans le fichier contacts.csv
        if (index != -1) {
            
            try {
                FileInputStream inputStream = new FileInputStream("contacts.csv");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            
                // Sautez les premières lignes jusqu'à la ligne spécifiée par l'index
                for (int i = 0; i < index; i++) {
                    reader.readLine();
                }
            
                // Affichez la ligne d'index
                String line = reader.readLine();
                System.out.println("\nVoilà votre contact : " + "\n" + line + "\n");
                reader.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }         
        } else {
            System.out.println("Contact not found");
        }
    }

    private static void sortDate() {
        ArrayList<Contact> liste;
        try {
            liste = Contact.lister();
            Collections.sort(liste, (c1, c2) -> c1.getDateNaissance().compareTo(c2.getDateNaissance()));
            Contact.ecrire(liste);
            System.out.println("Votre fichier à été trié");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private static void sortAContact() throws IOException {
        ArrayList<Contact> liste = Contact.lister();

        System.out.println("Par quel moyen voulez-vous trier les conatcts ? nom, prenom, mail, numero");
        String champsATrier = scan.nextLine();
        switch (champsATrier) {
            case "nom":
                Collections.sort(liste, (c1, c2) -> c1.getNom().compareTo(c2.getNom()));
                break;
            case "prenom":
                Collections.sort(liste, (c1, c2) -> c1.getPrenom().compareTo(c2.getPrenom()));
                break;
            case "mail":
                Collections.sort(liste, (c1, c2) -> c1.getMail().compareTo(c2.getMail()));
                break;
            case "numero":
                Collections.sort(liste, (c1, c2) -> c1.getNumero().compareTo(c2.getNumero()));
                break;
            default:
                System.out.println("tu es bête mon ami ! Ton fichier ne va pas être trié");
                break;
        }
       
        Contact.ecrire(liste);
        System.out.println("Votre fichier à été trié");
    }
    
    
    

    public static void afficherMenu() {
        ArrayList<String> menus = new ArrayList<>();
        menus.add("-- MENU --");
        menus.add("1- Ajouter un contact");
        menus.add("2- Lister les contacts");
        menus.add("3- Supprimer un contact");
        menus.add("4- Modifier un contact");
        menus.add("5- Rechercher un contact sur nom");
        menus.add("6- Trier les contacts");
        menus.add("7- Trier les contacts par date");
        menus.add("q- Quitter");
        for (String s : menus) {
            System.out.println(s);
        }
    }
}
