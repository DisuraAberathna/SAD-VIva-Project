/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author SINGER
 */
public class Customer {

    private String ID = "0";
    private String MOBILE = "None";
    private String FNAME = "None";
    private String LNAME = "None";
    private double POINTS = 0;

    public String getId() {
        return ID;
    }

    public void setId(String ID) {
        this.ID = ID;
    }

    public String getMobile() {
        return MOBILE;
    }

    public void setMobile(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    public double getPoints() {
        return POINTS;
    }

    public void setPoints(double POINTS) {
        this.POINTS = POINTS;
    }

    public String getFname() {
        return FNAME;
    }

    public void setFname(String FNAME) {
        this.FNAME = FNAME;
    }

    public String getLname() {
        return LNAME;
    }

    public void setLname(String LNAME) {
        this.LNAME = LNAME;
    }
}
