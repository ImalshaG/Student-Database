package com.mycompany.dbappp;

public class ColNames {
    private String Name;
    private String Maths;
    private String Chemistry;
    private String Physics;

    public String getName(){
        //System.out.println(Name);
        return Name;
    }
    public String getMaths(){
        //System.out.println(Maths);
        return Maths;
    }
    public String getChemistry(){
        //System.out.println(Chemistry);
        return Chemistry;
    }
    public String getPhysics(){
        //System.out.println(Physics);
        return Physics;
    }
    public void setName(String inName){
        this.Name=inName;
    }
    public void setMaths(String inMaths){
        this.Maths=inMaths;
    }
    public void setChem(String inChem){
        this.Chemistry=inChem;
    }
    public void setPhysics(String inPhysics){
        this.Physics=inPhysics;
    }
}
