/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.util.ArrayList;
import java.util.List;
import model.Autor;
import model.Knjiga;
import model.Zanr;

/**
 *
 * @author MY-PC
 */
public class Controller {

    private DBBroker dbb;
    private List<Knjiga> listaKnjiga = new ArrayList<>();
    private List<Autor> listaAutora = new ArrayList<>();

    private static Controller instance;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {
        dbb = new DBBroker();
        /* Autor autor1 = new Autor("Ivo", "Andric", 1892, "Biografija autora Ive Andrica bla bla");
        Autor autor2 = new Autor("Danilo", "Kis", 1935, "Biografija autora Danila Kisa bla bla");
        Autor autor3 = new Autor("Mesa", "Selimovic", 1910, "Biografija autora Mese Selimovica  bla bla");

        Knjiga knjiga1 = new Knjiga("Na drini cuprija", autor1, "123443565", 1945, Zanr.ROMAN);
        Knjiga knjiga2 = new Knjiga("Basta pepeo", autor2, "231452141", 1945, Zanr.ISTORIJSKI);
        Knjiga knjiga3 = new Knjiga("Tvrdjava", autor3, "45354435", 1945, Zanr.NAUCNA_FANTASTIKA);

        listaKnjiga.add(knjiga1);
        listaKnjiga.add(knjiga2);
        listaKnjiga.add(knjiga3);

        listaAutora.add(autor1);
        listaAutora.add(autor2);
        listaAutora.add(autor3); */

    }

    public List<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }

    public void setListaKnjiga(List<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
    }

    public List<Autor> getListaAutora() {
        return listaAutora;
    }

    public void setListaAutora(List<Autor> listaAutora) {
        this.listaAutora = listaAutora;
    }

    public void obrisiKnjigu(int id) {
        
        dbb.obrisiKnjigu(id);
        
       // listaKnjiga.remove(selektovaniRed);
    }

    public void dodajKnjigu(Knjiga novaKnjiga) {
        
        dbb.dodajKnjigu(novaKnjiga);
       // listaKnjiga.add(novaKnjiga);
    }

    public List<Knjiga> getListaKnjigaIzBaze() {
        return dbb.ucitajListuKnjigaIzBaze();
    }

    public List<Autor> ucitajListuAutoraIzBaze() {
        return dbb.ucitajListuAutoraIzBaze();
    }

    public void azurirajKnjigu(Knjiga knjigaZaIzmenu) {
        dbb.azurirajKnjigu(knjigaZaIzmenu);
    }

}
