package h2.beispiel05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RechteVergeben {
  public static void main( String... args ) {
    try {

      // Treiber laden
      Class.forName( "org.h2.Driver" );

      // Verbindung aufbauen ( Datenbank, User, Passwort )
      // sollte die Datenbank nicht existieren wird eine erstellt.
      Connection adminConn = DriverManager.getConnection( "jdbc:h2:./db/test", "DBAdmin", "P@ssw0rd" );

      // Statement Objekt erstellen (auf diesem k�nnen dann SQL statements ausgef�hrt werden)
      Statement statement = adminConn.createStatement();

      // Rechte f�r Tabelle f�r neuen Nutzer vergeben
      // hier: Alle Rechte auf die Tabelle.
      statement.execute( "GRANT ALL ON MESSAGES TO B_IX_60_00" );

      // Statement schlie�en
      statement.close();
      // Verbindung schlie�en
      adminConn.close();

    } catch ( SQLException e ) {
      e.printStackTrace();
    } catch ( ClassNotFoundException e1 ) {
      e1.printStackTrace();
    }
  }
}
