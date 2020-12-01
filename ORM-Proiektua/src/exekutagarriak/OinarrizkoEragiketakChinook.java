/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exekutagarriak;

import java.util.List;
import model.Artista;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Artista;
import org.hibernate.ObjectNotFoundException;
/**
 *
 * @author maneru.mikel
 */
public class OinarrizkoEragiketakChinook {
    public static SessionFactory sf = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {

        //datuaGorde(new Mendia(0, "Kilimanjaro", 5000, "AfrikaEast"));
        datuakIkusi();
//        datuaEzabatu(1);
//        datuakIkusi();
    }

    public static void datuaGorde(Artista m) {

        Session saioa = sf.openSession();
        saioa.beginTransaction();
        saioa.save(m);
        saioa.getTransaction().commit();
        saioa.close();

    }

    public static void datuakIkusi() {

        Session saioa = sf.openSession();
        saioa.beginTransaction();
        List result = saioa.createQuery("from Artista").list(); // HQL deitzen dan lengoaia idatziko dugu Querya ES EL NOMBRE DE LA KLASE
        for (Artista m : (List<Artista>) result) {
            System.out.println(m);
        }
        saioa.getTransaction().commit();
        saioa.close();
    }

    public static void datuaEzabatu(int idMendia) {

        Session saioa = null;
        Artista ik = null;
        Transaction tx = null;
        try {
            saioa = sf.openSession();
            tx = saioa.beginTransaction();
            ik = (Artista) saioa.load(Artista.class, idMendia);
            //ik = (Ikaslea)saioa.load("Ikaslea", idIkaslea); //horrela ere bai, ezta?
            //get metodoa antzekoa da baina ez du eszepziorik eragiten erregistroa existitzen ez bada.
            saioa.delete(ik);
            tx.commit();
        } catch (ObjectNotFoundException onfe) {
            System.out.println("Artista hori ez dago");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            saioa.close();
        }
    }
}
