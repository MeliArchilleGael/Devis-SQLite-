
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package DB;

/**
 *
 * @author user
 */
import java.sql.*;

public class SQLiteConnection {
    private Connection C = null;

    // deault constructor
    public SQLiteConnection() {
        this.Connecter();
    }

    /**
     * initialise a new connection to ours database Devis.db
     */
    private void Connecter() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.C = DriverManager.getConnection("jdbc:sqlite:Devis.db");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");
    }

    /**
     * return the Connection to ours database
     * @return
     */
    public Connection getConnection() {
        return this.C;
    }
    
    
    
     public static void main(String args[]) {
      new SQLiteConnection().Connecter();
     System.out.println(" e uis la");
 }
}


//~ Formatted by Jindent --- http://www.jindent.com
