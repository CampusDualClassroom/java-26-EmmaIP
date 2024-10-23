package com.campusdual.classroom;

import com.campusdual.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Phonebook {

    private Map<String,Contact> phoneContacts;

    public Phonebook() {
        this.phoneContacts = new HashMap<>();
    }

    public void menu() {

        int option;

        do {
            System.out.println("\nMenú");
            System.out.println("1. Añadir un contacto al listín telefónico");
            System.out.println("2. Mostar los contactos del listín telefónico");
            System.out.println("3. Seleccionar un contacto y mostrar su menú de acciones");
            System.out.println("4. Eliminar un contacto");
            System.out.println("5. Salir");

            option = Utils.integer("Seleccione una opción: ");

            switch (option) {
                case 1:
                    String name = Utils.string("Escriba el nombre: ");
                    String surnames = Utils.string("Escriba los apellidos: ");
                    String phone = Utils.string("Escriba el teléfono: ");

                    Contact contact = new Contact(name, surnames, phone);

                    addContact(contact);
                    break;
                case 2:
                    showPhonebook();
                    break;
                case 3:
                    String selected = Utils.string("Escriba el código del contacto: ");
                    selectContact(selected);
                    break;
                case 4:
                    String deleted = Utils.string("Escriba el código del contacto a eliminar: ");
                    deleteContact(deleted);
                    break;
                case 5:
                    System.out.println("Chao");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }

        }
        while(option != 5);

    }

    public void addContact(Contact contact) {
        String code = contact.getCode();
        if(!phoneContacts.containsKey(code)){
            phoneContacts.put(code, contact);
            System.out.println("Contacto añadido correctamente");
        }
        else {
            System.out.println("El contacto ya existe");
        }
    }

    public void showPhonebook() {

        if (phoneContacts.isEmpty()) {
            System.out.println("No hay ningún contacto");
        }
        else{
            System.out.println("\nLos contactos son: ");
            for (Map.Entry<String, Contact> entry : phoneContacts.entrySet()) {
                System.out.println(entry.getValue().getName() + " " + entry.getValue().getSurnames() + " "
                                    + entry.getValue().getPhone() + " " + entry.getValue().getCode());
            }
        }
    }

    public void selectContact(String contactCode) {

        for (Map.Entry<String, Contact> entry : phoneContacts.entrySet()) {
            if(contactCode.equals(entry.getValue().getCode())) {
                Contact selectedContact = entry.getValue();
                contactMenu(selectedContact);
            }
            else {
                System.out.println("El código no es correcto");
            }
        }
    }

    public void deleteContact(String contactCode) {

        for (Map.Entry<String, Contact> entry : phoneContacts.entrySet()) {
            if(contactCode.equals(entry.getValue().getCode())) {
                phoneContacts.remove(entry.getKey());
                System.out.println("El contacto ha sido eliminado");

            }
            else {
                System.out.println("El código no es correcto");
            }
        }
    }

    public Map<String, Contact> getData() {

        Map<String, Contact> contactList = new HashMap<>();
        for (Map.Entry<String, Contact> entry : phoneContacts.entrySet()) {
            String code = entry.getKey();
            Contact contact = entry.getValue();
            contactList.put(code,contact);
        }
        return contactList;
    }

    public void contactMenu(Contact contact){

        System.out.println("1. Llamar a mi número");
        System.out.println("2. Llamar a otro número");
        System.out.println("3. Detalles del contacto");

        int option = Utils.integer("Seleccione una opción: ");

        switch (option) {
            case 1:
                contact.callMyNumber();
                break;
            case 2:
                String number = Utils.string("Escriba el número a llamar: ");
                contact.callOtherNumber(number);
                break;
            case 3:
                contact.showContactDetails();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

}
