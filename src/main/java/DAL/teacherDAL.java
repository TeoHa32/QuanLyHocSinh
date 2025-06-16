/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import DTO.connection;
import DTO.teacher;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class teacherDAL {
    Connection conn = connection.getConnection();
    public ArrayList<teacher> readTeacher(){
        ArrayList<teacher> list = null;
        try{
            String sql = "SELECT * FROM teacher";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                teacher t= new teacher();
                t.setId(rs.getInt("id"));
                t.setFullname(rs.getString("fullname"));
                t.setGender(rs.getBoolean("Gender"));
                t.setDateOfBirth(rs.getDate("DateOfBirth"));
                t.setPhone(rs.getString("PhoneNumber"));
                t.setEmail(rs.getString("Email"));
                t.setSpecialization(rs.getString("Specialization"));
                list.add(t);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return list;
    }
    public boolean addTeacher(teacher t){
       try{
           String sql = "INSERT INTO teacher(fullname,Gender,DateOfBirth,PhoneNumber,Email,Specialization) VALUES(?, ?, ?, ?, ?, ?)";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,t.getFullname());
           stmt.setBoolean(2, true);
           stmt.setDate(3, (Date) t.getDateOfBirth());
           stmt.setString(4,t.getPhone());
           stmt.setString(5, t.getEmail());
           stmt.setString(6, t.getEmail());
           int row = stmt.executeUpdate();
           if(row > 0) return true;
           
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }
       return false;
    }
}
