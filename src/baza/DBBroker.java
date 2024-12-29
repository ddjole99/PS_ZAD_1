/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Knjiga;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import model.Autor;
import model.Zanr;
import java.sql.PreparedStatement;

/**
 *
 * @author MY-PC
 */
public class DBBroker {

    public List<Knjiga> ucitajListuKnjigaIzBaze() {
        List<Knjiga> lista = new ArrayList<>();
        try {

            String upit = "SELECT * FROM KNJIGA K JOIN AUTOR A ON k.autorId=a.id";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("k.id");
                String naslov = rs.getString("k.naslov");
                int godIz = rs.getInt("k.godinaIzdanja");
                String ISBN = rs.getString("k.ISBN");
                String zanr = rs.getString("k.zanr");
                Zanr z = Zanr.valueOf(zanr);
                int idA = rs.getInt("a.id");
                String ime = rs.getString("a.ime");
                String prezime = rs.getString("a.prezime");
                int godR = rs.getInt("a.godinaRodjenja");

                Autor a = new Autor(idA, ime, prezime, godR, prezime);

                Knjiga k = new Knjiga(id, naslov, a, ISBN, godIz, z);
                lista.add(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Autor> ucitajListuAutoraIzBaze() {
        List<Autor> lista = new ArrayList<>();
        try {

            String upit = "SELECT * FROM AUTOR A";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                int idA = rs.getInt("a.id");
                String ime = rs.getString("a.ime");
                String prezime = rs.getString("a.prezime");
                int godR = rs.getInt("a.godinaRodjenja");

                Autor a = new Autor(idA, ime, prezime, godR, prezime);

                lista.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void obrisiKnjigu(int id) {
        try {
            String upit = "DELETE FROM KNJIGA WHERE id=?"; // indeksi idu od 1
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, id);
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void dodajKnjigu(Knjiga novaKnjiga) {

        try {
            String upit = "INSERT INTO KNJIGA(id,naslov,autorId,godinaIzdanja,ISBN,zanr)"
                    + "VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, novaKnjiga.getId());
            ps.setString(2, novaKnjiga.getNaslov());
            ps.setInt(3, novaKnjiga.getAutor().getId());
            ps.setInt(4, novaKnjiga.getGodinaIzdanja());
            ps.setString(5, novaKnjiga.getISBN());
            ps.setString(6, String.valueOf(novaKnjiga.getZanr()));

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void azurirajKnjigu(Knjiga knjigaZaIzmenu) {
        try {
            String upit = "UPDATE KNJIGA SET naslov=?, autorId=?, godinaIzdanja=?,"
                    + "zanr=? where id=?"
                    ;
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, knjigaZaIzmenu.getNaslov());
            ps.setInt(2, knjigaZaIzmenu.getAutor().getId());
            ps.setInt(3, knjigaZaIzmenu.getGodinaIzdanja());
            ps.setString(4,String.valueOf(knjigaZaIzmenu.getZanr()));
            ps.setInt(5, knjigaZaIzmenu.getId());
          

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
