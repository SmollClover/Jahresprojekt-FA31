package de.hhbk.managers;

import de.hhbk.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class DatabaseManager {
    private final String ip;
    private final String port;
    private final String username;
    private final String password;
    private SessionFactory sessionFactory;
    


    public DatabaseManager(String ip, String port, String username, String password, String stage) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;


        try {
            System.out.println(System.getenv("DATABASE_IP"));
            this.sessionFactory = new Configuration()
                    .setProperties(getProperties())
                    .addAnnotatedClass(Ort.class)
                    .addAnnotatedClass(Kontoinhaber.class)
                    .addAnnotatedClass(Konto.class)
                    .addAnnotatedClass(Telefonnummer.class)
                    .addAnnotatedClass(Mietobjekt.class)
                    .addAnnotatedClass(Mieter.class)
                    .addAnnotatedClass(Einnahme.class)
                    .addAnnotatedClass(MietobjektMieter.class)
                    .addAnnotatedClass(Benutzer.class)
                    .buildSessionFactory();

            if (stage.equals("development")) this.generateDummyData();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        try {
            this.sessionFactory.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private Properties getProperties() {
        Properties sessionProperties = new Properties();

        sessionProperties.setProperty("hibernate.connection.url", String.format("jdbc:postgresql://%s:%s/tenzur", this.ip, this.port));
        sessionProperties.setProperty("hibernate.connection.username", this.username);
        sessionProperties.setProperty("hibernate.connection.password", this.password);
        sessionProperties.setProperty("hibernate.auto_quote_keyword", "true");
        sessionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        sessionProperties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        sessionProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        sessionProperties.setProperty("show_sql", "true");
        sessionProperties.setProperty("format_sql", "true");
        sessionProperties.setProperty("use_sql_comments", "true");

        return sessionProperties;
    }

    private void generateDummyData() {
        Session session = this.sessionFactory.openSession();

        if (!(
                new Ort().getList(session).isEmpty()
                        && new Kontoinhaber().getList(session).isEmpty()
                        && new Konto().getList(session).isEmpty()
                        && new Telefonnummer().getList(session).isEmpty()
                        && new Mietobjekt().getList(session).isEmpty()
                        && new Mieter().getList(session).isEmpty()
                        && new Einnahme().getList(session).isEmpty()
                        && new MietobjektMieter().getList(session).isEmpty()
                        && new Benutzer().getList(session).isEmpty()
        )) return;

        Ort OrtEssen = new Ort(45219, "Essen").save(session);
        Ort OrtBadbeuren = new Ort(6414, "Badbeuren").save(session);
        Ort OrtFunktown = new Ort(6969, "Funktown").save(session);
        Ort OrtRostock = new Ort(7527, "Rostock").save(session);
        Ort OrtBerlin = new Ort(78961, "Berlin").save(session);

        Kontoinhaber KontoinhaberTobias = new Kontoinhaber("Tobias", "Ochott").save(session);
        Kontoinhaber KontoinhaberLars = new Kontoinhaber("Lars", "Belitz").save(session);
        Kontoinhaber KontoinhaberLaura = new Kontoinhaber("Laura", "Boniecki").save(session);
        Kontoinhaber KontoinhaberRobin = new Kontoinhaber("Robin", "Walter").save(session);
        Kontoinhaber KontoinhaberBurak = new Kontoinhaber("Burak", "Kablan").save(session);

        Konto KontoTobias = new Konto("DE01 2345 6789 0123", "XECFG", List.of(new Kontoinhaber[]{KontoinhaberTobias}), "Postbank").save(session);
        Konto KontoLars = new Konto("DE01 2345 6789 0123", "GRRAS", List.of(new Kontoinhaber[]{KontoinhaberLars}), "Geno Bank").save(session);
        Konto KontoLaura = new Konto("DE01 2345 6789 0123", "DFRWA", List.of(new Kontoinhaber[]{KontoinhaberLaura}), "Sparkasse").save(session);
        Konto KontoRobinBurakTargo = new Konto("DE01 2345 6789 0123", "UCNNA", List.of(new Kontoinhaber[]{KontoinhaberRobin, KontoinhaberBurak}), "Targo Bank").save(session);
        Konto KontoRobinBurakING = new Konto("DE01 2345 6789 0123", "PQJJA", List.of(new Kontoinhaber[]{KontoinhaberRobin, KontoinhaberBurak}), "INGDiba").save(session);

        Telefonnummer TelefonnummerTobias = new Telefonnummer("02054134556", "").save(session);
        Telefonnummer TelefonnummerLars = new Telefonnummer("", "+4915113466721").save(session);
        Telefonnummer TelefonnummerLaura = new Telefonnummer("", "+54345364351").save(session);
        Telefonnummer TelefonnummerRobin = new Telefonnummer("036454693", "0099466430").save(session);
        Telefonnummer TelefonnummerBurak = new Telefonnummer("456086446313", "").save(session);

        Mietobjekt MietobjektTobias = new Mietobjekt("Zelt", 1, "Kleine scheiß hütte", 20, 5000, 2000, "", "Unter der Brück", "5a", OrtRostock).save(session);
        Mietobjekt MietobjektLars = new Mietobjekt("Villa", 69, "Krasse mega Villa", 9000, 20, 5, "Voll krasses Mietobjekt", "Königsalle", "420", OrtFunktown).save(session);
        Mietobjekt MietobjektLaura = new Mietobjekt("Wohnwagen", 47, "", 50, 1524, 254, "", "Jede Straße", "0", OrtBerlin).save(session);
        Mietobjekt MietobjektRobin = new Mietobjekt("Portal", 11, "Die Düsterheit", 100, 101, 241, "", "???", "666", OrtBadbeuren).save(session);
        Mietobjekt MietobjektBurak = new Mietobjekt("Haus", 12, "Normales Haus", 1100, 125, 340, "", "Am Brunnen", "12", OrtEssen).save(session);

        Mieter MieterTobias = new Mieter("", "Herr", "Tobias", "Ochott", List.of(new Telefonnummer[]{TelefonnummerTobias}), "tobegeiler@gmail.com", List.of(new Konto[]{KontoTobias})).save(session);
        Mieter MieterLars = new Mieter("Prof. Dr.", "Herr", "Lars", "Belitz", List.of(new Telefonnummer[]{TelefonnummerLars}), "lars.ist.cool@hotmail.com", List.of(new Konto[]{KontoLars})).save(session);
        Mieter MieterLaura = new Mieter("", "Frau", "Laura", "Boniecki", List.of(new Telefonnummer[]{TelefonnummerLaura}), "laura@web.de", List.of(new Konto[]{KontoLaura})).save(session);
        Mieter MieterRobin = new Mieter("Ing.", "Herr", "Robin", "Walter", List.of(new Telefonnummer[]{TelefonnummerRobin}), "ro@bot.email", List.of(new Konto[]{KontoRobinBurakING, KontoRobinBurakTargo})).save(session);
        Mieter MieterBurak = new Mieter("", "Herr", "Burak", "Kablan", List.of(new Telefonnummer[]{TelefonnummerBurak}), "burak@kablan.cool", List.of(new Konto[]{KontoRobinBurakING, KontoRobinBurakTargo})).save(session);

        Einnahme EinnahmeTobias = new Einnahme(List.of(new Mieter[]{MieterTobias}), List.of(new Mietobjekt[]{MietobjektTobias}), 2.02, 7000, 0).save(session);
        Einnahme EinnahmeLars = new Einnahme(List.of(new Mieter[]{MieterLars}), List.of(new Mietobjekt[]{MietobjektLars}), 2351, 20, 2331).save(session);
        Einnahme EinnahmeLaura = new Einnahme(List.of(new Mieter[]{MieterLaura}), List.of(new Mietobjekt[]{MietobjektLaura}), 234.046677511348643112, 1133, 0).save(session);
        Einnahme EinnahmeRobin = new Einnahme(List.of(new Mieter[]{MieterRobin}), List.of(new Mietobjekt[]{MietobjektRobin}), 121.06, 464.31, 0.13).save(session);
        Einnahme EinnahmeBurak = new Einnahme(List.of(new Mieter[]{MieterBurak}), List.of(new Mietobjekt[]{MietobjektBurak}), 202, 70, 100.3).save(session);

        MietobjektMieter MietobjektMieterTobias = new MietobjektMieter(new MietobjektMieterId(MietobjektTobias, MieterTobias), new Date()).save(session);
        MietobjektMieter MietobjektMieterLars = new MietobjektMieter(new MietobjektMieterId(MietobjektLars, MieterLars), new Date()).save(session);
        MietobjektMieter MietobjektMieterLaura = new MietobjektMieter(new MietobjektMieterId(MietobjektLaura, MieterLaura), new Date()).save(session);
        MietobjektMieter MietobjektMieterRobin = new MietobjektMieter(new MietobjektMieterId(MietobjektRobin, MieterRobin), new Date()).save(session);
        MietobjektMieter MietobjektMieterBurak = new MietobjektMieter(new MietobjektMieterId(MietobjektBurak, MieterBurak), new Date()).save(session);

        // Passwort = passwort
        Benutzer BenutzerTobias = new Benutzer("Tobias", "Ochott", "Unter der Brück", "5a", OrtRostock, List.of(new Telefonnummer[]{TelefonnummerTobias}), "tobegeiler@gmail.com", "tobias", "$2y$04$.v2xEfRENVoGd3YSt06OY.UN7HoeH4z46PoWZsOxKewqXJCQdoV5W", Rolle.MIETER).save(session);
        Benutzer BenutzerLars = new Benutzer("Lars", "Belitz", "Königsalle", "420", OrtFunktown, List.of(new Telefonnummer[]{TelefonnummerLars}), "lars.ist.cool@hotmail.com", "lars", "$2y$04$.v2xEfRENVoGd3YSt06OY.UN7HoeH4z46PoWZsOxKewqXJCQdoV5W", Rolle.ADMIN).save(session);
        Benutzer BenutzerLaura = new Benutzer("Laura", "Boniecki", "Jede Straße", "0", OrtBerlin, List.of(new Telefonnummer[]{TelefonnummerLaura}), "laura@web.de", "laura", "$2y$04$.v2xEfRENVoGd3YSt06OY.UN7HoeH4z46PoWZsOxKewqXJCQdoV5W", Rolle.VERMIETER).save(session);
        Benutzer BenutzerRobin = new Benutzer("Robin", "Walter", "???", "666", OrtBadbeuren, List.of(new Telefonnummer[]{TelefonnummerRobin}), "ro@bot.email", "robin", "$2y$04$.v2xEfRENVoGd3YSt06OY.UN7HoeH4z46PoWZsOxKewqXJCQdoV5W", Rolle.MITARBEITER).save(session);
        Benutzer BenutzerBurak = new Benutzer("Burak", "Kablan", "Am Brunnen", "12", OrtEssen, List.of(new Telefonnummer[]{TelefonnummerBurak}), "burak@kablan.cool", "burak", "$2y$04$.v2xEfRENVoGd3YSt06OY.UN7HoeH4z46PoWZsOxKewqXJCQdoV5W", Rolle.NONE).save(session);
    }
}
