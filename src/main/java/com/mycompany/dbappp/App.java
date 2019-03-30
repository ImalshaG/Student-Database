package com.mycompany.dbappp;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class App {
     static  ArrayList<Object> objectList =new ArrayList<Object>();

    public void addTitle(String firstEntry){
        ColNames columns=new ColNames();
        String array1[]=firstEntry.split(",");
        columns.setName(array1[0]);
        columns.setMaths(array1[1]);
        columns.setChem(array1[2]);
        columns.setPhysics(array1[3]);

        objectList.add(columns);
    }
    public void addStudent(String entryLine){
        Student student = new Student();
        String lineElements[]=entryLine.split(",");
        student.setName(lineElements[0]);
        student.setMaths(Integer.parseInt(lineElements[1]));
        student.setChem(Integer.parseInt(lineElements[2]));
        student.setPhysics(Integer.parseInt(lineElements[3]));
        objectList.add(student);
    }
    public void getEntries(){
        ColNames col=(ColNames) objectList.get(0);
        System.out.println(col.getName()+"  "+ col.getMaths()+" "+ col.getChemistry()+" "+col.getPhysics());

        for(int i = 1; i< objectList.size(); i++){
            Student stud=(Student) objectList.get(i);
            System.out.println(stud.getName()+"  "+ stud.getMaths()+" "+ stud.getChem()+" "+stud.getPhysics());
        }
    }
    public void setList()throws Exception{
        FileReader fr = new FileReader("C:\\Users\\DELL\\Desktop\\Test1.csv");
        BufferedReader br = new BufferedReader(fr);

        String inCol = br.readLine();
        this.addTitle(inCol);
        String line;
        while((line = br.readLine())!=null){
            this.addStudent(line);
        }

        br.close();
        fr.close();
    }
    static void addToDB(){
        String URL="jdbc:mysql://localhost:3306/students";
        String UserNm="root";
        String Passwrd="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            e.printStackTrace();

        }
        Connection con1= null;
        try{
            con1 = DriverManager.getConnection(URL,UserNm, Passwrd);
            Statement stat=con1.createStatement();

            stat.executeUpdate("DROP TABLE marks");

            ColNames columns=(ColNames) objectList.get(0);
            String crtTableQuery="CREATE TABLE marks("+columns.getName()+" varchar(255),"+columns.getMaths()+" INT ,"+columns.getChemistry()+" INT ,"+columns.getPhysics()+" INT );";
            stat.executeUpdate(crtTableQuery);
            System.out.println("Table marks created successfully");

            for (int i = 1; i< objectList.size(); i++){
                Student student=(Student) objectList.get(i);

                String instTableQry="INSERT INTO marks VALUES (?,?,?,?);";
                PreparedStatement stmt=con1.prepareStatement(instTableQry);
                stmt.setString(1,student.getName());
                stmt.setInt(2,student.getMaths());
                stmt.setInt(3,student.getChem());
                stmt.setInt(4,student.getPhysics());
                stmt.executeUpdate();
            }

            stat.close();
            con1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        App app = new App();

        try {
            app.setList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        app.addToDB();
    }
}
