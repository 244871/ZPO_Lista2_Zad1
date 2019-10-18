package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //D:\zpo\lab_2\ATP_ranking_7_10_2019_top50.txt

        ArrayList<Zawodnik> zawodnicy = new ArrayList();

        FileReader fileReader = null; //czyta znak po znaku
        Scanner sc = new Scanner(System.in);
        System.out.println("Wprowadź ścieżkę do pliku: ");
        //String sciezka = sc.nextLine();
        String sciezka = ("C:\\Users\\agnie\\Desktop\tenis.txt");
        sc.close();

        //↑ wziął sobie nazwe z konsoli

        File file = new File(sciezka);

        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //chcemy czytać liniami nie znak po znaku
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = null;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                //dodajemy do ArrayListy zawodników
                String[] elements = line.split("\t");
                zawodnicy.add(elements[0]);
                zawodnicy.add(elements[1]);
                zawodnicy.add(elements[2]);
                zawodnicy.add(elements[3]);
                zawodnicy.add(elements[4]);
                zawodnicy.add(elements[5]);
            }
            bufferedReader.close();
            fileReader.close(); //można zamknąc tu lub w finally
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Pozycja(zawodnicy, "Guido Pella"));
        liczbaPunktowTxt(zawodnicy);

    }

    public static int Pozycja(ArrayList<String> zawodniks, String imieNazwisko) { //zmienić zeby szuakl p samym nazwsku split kropka

        int pozycja = 0;

        for (zawodnik : zawodniks) {
            if (zawodnik.getImieNazwisko().equals(imieNazwisko)) {
                pozycja = zawodnik.getPozycja();
            }
        }
        return pozycja;
    }

    public static int IluGraczyZKraju(ArrayList<Zawodnik> zawodniks, String kraj) {

        int liczbaGraczy = 0;

        for (Zawodnik zawodnik : zawodniks) {
            if (zawodnik.getKraj().equals(kraj)) {
                liczbaGraczy++;
            }
        }

        return liczbaGraczy;
    }

    public static double[] sredniaOdchylenie(ArrayList<Zawodnik> zawodniks) {

        double[] srOd = new double[2]; //tablica, którą zwróci
        int[] wiek = new int[10]; //tu zapisze wiek pierwszych 10 żeby potem policzyć odchylenie standardowe
        int n = 0; //licznik
        int suma = 0;
        double srednia;
        double odchylenie;

        for (Zawodnik zawodnik : zawodniks) {
            suma = suma + zawodnik.getWiek();
            wiek[n] = zawodnik.getWiek();
            n++;

            if (n == 10) { //wychodzi z pętl po 10 zawodniku
                break;
            }
        }

        for (int i = 0; i < 10; i++) {

        }

        srednia = (double) suma / 10;
        double s = 0; //suma do odchylenia standardowego


        for (int i = 0; i < wiek.length; i++) {

            s = s + Math.pow((wiek[i] - srednia), 2);

        }

        odchylenie = Math.sqrt(s / wiek.length);

        srOd[0] = srednia;
        srOd[1] = odchylenie;

        return srOd;

    }

    public static int roznicaPonktow(ArrayList<Zawodnik> zawodniks, String zawodnik1, String zawodnik2) {

        int a = 0; //liczba punktów zawodnika 1
        int b = 0; //liczba puktów zawodnika 2
        int c; //różnica punktów

        for (Zawodnik zawodnik : zawodniks) {
            if (zawodnik.getImieNazwisko().equals(zawodnik1)) {
                a = zawodnik.getLiczbaPunktow();
                break;
            }
        }
        for (Zawodnik zawodnik : zawodniks) {
            if (zawodnik.getImieNazwisko().equals(zawodnik2)) {
                b = zawodnik.getLiczbaPunktow();
                break;
            }
        }

        c = a - b;

        return c;
    }

    public static void liczbaPunktowTxt(ArrayList<Zawodnik> zawodniks) {

        BufferedWriter writer = null;

        for (Zawodnik zawodnik : zawodniks) {

            int turnieje = zawodnik.getLiczbaZagranychTurniejow();
            try {
                writer = new BufferedWriter(new FileWriter("liczbaTurniejówWSezonie.txt"));
                writer.append(' ');
                writer.append(String.valueOf(turnieje));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
