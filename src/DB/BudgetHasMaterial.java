
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class BudgetHasMaterial {
    private SQLiteConnection con = new SQLiteConnection();
    private int              Id;
    private int              Quantity;
    private int              TVA;
    private int              Price;
    private int              IdBudget;
    private int              IdMaterial;

    public BudgetHasMaterial() {}

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
            String    query = "DELETE FROM BudgetHasMaterials WHERE IdBudget=" + num;

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
     *
     * @param IdBudget
     * @param IdMaterial
     * @return
     */
    public int Delete(int IdBudget, int IdMaterial) {
        Connection new_connection = this.con.getConnection();
        int        status         = 0;

        try {
            Statement st    = new_connection.createStatement();
            String    query = "DELETE FROM BudgetHasMaterials WHERE IdBudget=" + IdBudget + " AND IdMaterial="+ IdMaterial;

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
     * read all the inormation about a speciic budget
     * @param id
     * @return
     */
    public ArrayList<Object[]> ReadBudgetInfo(int id) {
        ArrayList<Object[]> array = new ArrayList();
        String              query = "SELECT * from BudgetHasMaterials, Materials WHERE "
                                    + "Materials.Id = BudgetHasMaterials.IdMaterial AND "
                                    + "BudgetHasMaterials.IdBudget =" +id ;
        Connection con = this.con.getConnection();

        try {
            int       i  = 1;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            // rs.beforeFirst();
            while (rs.next()) {
                Object[] a = { i++, rs.getString("Designation"), rs.getInt("Quantity"), rs.getInt("UnitPrice"),
                               rs.getInt("Tva"), rs.getInt("Price") };

                array.add(a);
            }

            st.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BudgetHasMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return array;
    }

    /**
     *
     * @return
     */
    public int Save() {
        int    status = 0;
        String query  = " INSERT INTO BudgetHasMaterials " + "VALUES (NULL," + this.Quantity + "," + this.TVA + ","
                        + this.Price + "," + this.IdBudget + "," + this.IdMaterial + ")";
        Connection con = this.con.getConnection();

        try {
            Statement st = con.createStatement();

            if (!st.execute(query)) {
                status = 1;
            }

            st.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return status;
    }

    // getteur
    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdBudget() {
        return this.IdBudget;
    }

    public void setIdBudget(int IdBudget) {
        this.IdBudget = IdBudget;
    }

    public int getIdMaterial() {
        return this.IdMaterial;
    }

    public void setIdMaterial(int IdMaterial) {
        this.IdMaterial = IdMaterial;
    }

    public int getPrice() {
        return this.Price;
    }

    public void setPrice(int price) {
        this.Price = price;
    }

    public int getQuantity() {
        return this.Quantity;
    }

    public void setQuantity(int qt) {
        this.Quantity = qt;
    }

    public void setTVA(int tva) {
        this.TVA = tva;
    }

    public int getTva() {
        return this.TVA;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
