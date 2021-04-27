
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

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Material {
    SQLiteConnection con = new SQLiteConnection();

    // Connection con;
    private int    Id;
    private String designation;
    private int    UnitPrice;

    // constructor
    public Material() {}

    /**
     *
     * @return
     */
    public ArrayList<Material> ReadMaterials() {
        Connection          con           = this.con.getConnection();
        ArrayList<Material> ArrayMaterial = new ArrayList();
        Material            material;

        try {
            String    query = "SELECT * FROM MATERIALS";
            Statement st    = con.createStatement();
            ResultSet rs    = st.executeQuery(query);

           // rs.beforeFirst();

            while (rs.next()) {
                material = new Material();
                material.setId(rs.getInt("Id"));
                material.setDesignation(rs.getString("Designation"));
                material.setUnitPrice(rs.getInt("UnitPrice"));
                ArrayMaterial.add(material);
                
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
        
         ArrayMaterial.stream().forEach((Material mat) -> {
            System.out.println(mat.getDesignation());
        });

        return ArrayMaterial;
    }

    /**
     * save a new material
     * @return
     */
    public int Save() {
        int        status = 0;
        String     query  = " INSERT INTO Materials VALUES (NULL,'" + this.designation + "','" + this.UnitPrice + "')";
        Connection con    = this.con.getConnection();

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

        public int Update() {
        int        status = 0;
        String     query  = " UPDATE Materials SET UnitPrice="+ this.UnitPrice +", Designation = '" + this.designation + "'  WHERE id = " + this.Id ;
        Connection con    = this.con.getConnection();

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
    
    /**
     * delete a speciic material on the database 0 lost 1 succes
     * @param num
     * return the status o the query
     */
    public int Delete(int num) {
        int        status         = 0;
        Connection new_connection = this.con.getConnection();

        try {
            Statement st    = new_connection.createStatement();
            String    query = "DELETE FROM MATERIALS WHERE ID=" + num;

            if (!st.execute(query)) {
                status = 1;
            }

            st.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                new_connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return status;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    // getteur
    public int getId() {
        return this.Id;
    }

    // setteur
    public void setId(int Id) {
        this.Id = Id;
    }

    public int getUnitPrice() {
        return this.UnitPrice;
    }

    public void setUnitPrice(int UnitPrice) {
        this.UnitPrice = UnitPrice;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
