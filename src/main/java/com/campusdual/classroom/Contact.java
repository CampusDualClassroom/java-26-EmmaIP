package com.campusdual.classroom;

import java.text.Normalizer;

public class Contact implements ICallActions{
    private String name;
    private String surnames;
    private String phone;


    public Contact(String name, String surnames, String phone) {
        this.name = name;
        this.surnames = surnames;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return this.surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getPhone() {
        return phone;
    }

    public void setNumberPhone(int numberPhone) {
        this.phone = phone;
    }

    public String getCode() {

        String lowerName = this.name.toLowerCase();
        String lowerSurnames = this.surnames.toLowerCase();

        String diacriticsName = Normalizer.normalize(lowerName, Normalizer.Form.NFD);
        String diacriticsSurnames = Normalizer.normalize(lowerSurnames, Normalizer.Form.NFD);

        String perfectName = diacriticsName.replaceAll("[^\\p{ASCII}]", "");
        String perfectSurnames = diacriticsSurnames.replaceAll("[^\\p{ASCII}]", "");

        String[] nameParts = perfectName.split(" ");
        String[] surnamesPart = perfectSurnames.split(" ");

        StringBuilder sb = new StringBuilder();

        sb.append(nameParts[0].charAt(0));

        if(perfectSurnames.contains(" ")) {

            sb.append(surnamesPart[0].charAt(0));
            for (int i = 1; i < surnamesPart.length; i++) {
                sb.append(surnamesPart[i]);
            }
        }
        else {
            sb.append(perfectSurnames);
        }

        return sb.toString();
    }



    @Override
    public void callMyNumber() {
        System.out.println(this.name + " " + this.surnames + " se está llamando a sí mismo al número " + this.phone);

    }

    @Override
    public void callOtherNumber(String number) {
        System.out.println(this.name + " " + this.surnames + " está llamando al número " + number);

    }

    @Override
    public void showContactDetails() {
        System.out.println("Los detalles del contacto son: " + this.name + " " + this.surnames
                            + " " + this.phone + " " + this.getCode());

    }
}
