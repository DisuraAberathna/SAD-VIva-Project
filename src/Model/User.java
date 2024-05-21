/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author SINGER
 */
public class User {

    private String UID;
    private String FNAME;
    private String LNAME;
    private int UTYPE;

    public String getFname() {
        return FNAME;
    }

    public void setFname(String fname) {
        this.FNAME = fname;
    }

    public String getuID() {
        return UID;
    }

    public void setuID(String uID) {
        this.UID = uID;
    }

    public String getLname() {
        return LNAME;
    }

    public void setLname(String lname) {
        this.LNAME = lname;
    }

    public int getuType() {
        return UTYPE;
    }

    public void setuType(int uType) {
        this.UTYPE = uType;
    }
}
