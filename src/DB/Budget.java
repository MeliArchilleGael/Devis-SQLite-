
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Budget {
    private int              Id;
    private int              price;
    private String           date;
    private SQLiteConnection con;

    public Budget() {
        con = new SQLiteConnection();
        this.price = 0;
        
    }

    /**
     *
     * @param num
     * @return
     */
    public int Delete(int num) {
        Connection new_connection = this.con.getConnection();
        int        status         = 0;

        try {
            Statement st    = new_connection.createStatement();
            String    query = "DELETE FROM BUDGET WHERE ID=" + num;

            if (!st.execute(query)) {
                status = 1;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                new_connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Budget.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return status;
    }

    /**
     * Read a speciic budget with the Id
     * @param Id
     * @return
     */
    public ArrayList<Budget> ReadBudget(int Id) {
        Connection        con         = this.con.getConnection();
        ArrayList<Budget> ArrayBudget = new ArrayList();
        Budget            budget;

        try {
            String    query = "SELECT * FROM BUDGET WHERE Id =  '" + Id + "'";
            Statement st    = con.createStatement();
            ResultSet rs    = st.executeQuery(query);

           // rs.beforeFirst();

            while (rs.next()) {
                budget = new Budget();
                budget.setId(rs.getInt("Id"));
                budget.setPrice(rs.getInt("Price"));
                budget.setDate(rs.getString("DateBudget"));
                ArrayBudget.add(budget);
               
            } st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                                          "Exception during the reading of data. \n Exception: " + ex,
                                          "Error #004",
                                          JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Budget.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ArrayBudget;
    }

    /**
     * read all the budget o the
     * @param num
     * @return
     */
    public ArrayList<Budget> ReadBudgets() {
        Connection        con         = this.con.getConnection();
        ArrayList<Budget> ArrayBudget = new ArrayList();
        Budget            budget;

        try {
            String    query = "SELECT * FROM BUDGET ORDER BY DateBudget ASC";
            Statement st    = con.createStatement();
            ResultSet rs    = st.executeQuery(query);

            //rs.beforeFirst();

            while (rs.next()) {
                budget = new Budget();
                budget.setId(rs.getInt("Id"));
                budget.setPrice(rs.getInt("Price"));
                budget.setDate(rs.getString("DateBudget"));
                ArrayBudget.add(budget);
                
            }
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                                          "Exception during the reading of data. \n Exception: " + ex,
                                          "Error #004",
                                          JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Budget.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ArrayBudget;
    }

    /**
     * save a budget on the database
     * @return
     */
    public int Save() {
        int status = 0;

        // DateTimeFormat dt =
        Date       d   = new Date();
        Connection C = this.con.getConnection();
        
        try {
            String query = "INSERT INTO `Budget` (`Id`, `DateBudget`, `Price`) " + "VALUES (" + this.Id + ", '" + d.toString()
                           + "', '" + this.price + "');";
            Statement st = C.createStatement();

            if (!st.execute(query)) {
                status = 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                C.close();
            } catch (SQLException ex) {
                Logger.getLogger(Budget.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return status;
    }

    /**
     * genere un numero de devis 
     * @return
     */
    public  int genere() {
        int        r       = 0;
        Connection c     = this.con.getConnection();
        boolean    trouver = false;

        try {
            String    query = "SELECT Id FROM BUDGET";
            Statement st    = c.createStatement();
            ResultSet rs    = st.executeQuery(query);

            while (!trouver) {
                r = (int) (Math.random() * ((9999 - 1111) + 1)) + 1111;
               // rs.beforeFirst();
                rs.first();
                while (rs.next()) {
                    if (rs.getInt(1) != r) {
                        trouver = true;

                        break;
                    } else {
                        trouver = false;
                    }
                }
            }
            st.close();
        } catch (Exception ex) {
            ex.printStackTrace();
           /* JOptionPane.showMessageDialog(null,
                                          "Erreur lors de la generation du numéro\n"+ex.getMessage(),
                                          "ERREUR: #005",
                                          JOptionPane.ERROR_MESSAGE);*/
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Budget.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return r;
    }
 

 public static void main(String args[]) {
     int t =  new Budget().genere();
     System.out.println(" e uis la"+t);
 }
//  geteur
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return this.Id;
    }

    // setteur
    public void setId(int Id) {
        this.Id = Id;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
