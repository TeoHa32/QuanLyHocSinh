
package BLL;

import DAL.teacherDAL;
import DTO.teacher;
import java.util.ArrayList;

/**
 *
 * @author L380
 */
public class studentBLL {
    teacherDAL td;
    public ArrayList<teacher> list(){
        ArrayList<teacher> list = td.readTeacher();
        return list;
    }
    public boolean addTeacher(teacher t){
        return td.addTeacher(t);
    }
    
}
