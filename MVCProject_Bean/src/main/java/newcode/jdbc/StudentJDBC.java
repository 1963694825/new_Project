package newcode.jdbc;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
@Configuration
public class StudentJDBC {

    public boolean addStudent(String username, String password,String name) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        JDBCControll jdbcControll = applicationContext.getBean("JDBCControll",JDBCControll.class);
        try {
            String sqlString = "INSERT INTO school.s_student(name, username, password) VALUES ('" + name + "','"  + username + "','" + password + "')";
            return jdbcControll.excuteUpdateSQL(sqlString);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jdbcControll.closeMysql();
        }
    }

    public boolean submitHomework(String username,String title,String content){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        JDBCControll jdbcControll = applicationContext.getBean("JDBCControll",JDBCControll.class);
        try {
            String sqlString = "INSERT INTO s_student_homework(student_username,homework_title,homework_content) VALUES('" + username + "','"  + title + "','" + content + "')";
            return jdbcControll.excuteUpdateSQL(sqlString);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jdbcControll.closeMysql();
        }
    }


    public Boolean getStudentIdentity(String studentName,String passWord){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        JDBCControll jdbcControll = applicationContext.getBean("JDBCControll",JDBCControll.class);


        try {
            String sqlString = "select * from s_student WHERE username = '"+studentName+"'";


            jdbcControll.excuteSQL(sqlString);
            while(jdbcControll.getResultSet().next()) {


                if(jdbcControll.getResultSet().getString(3).equals(passWord)&&jdbcControll.getResultSet().getString(2).equals(studentName)){
                    System.out.println("yes");
                    return true;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcControll.closeMysql();
        }



        return false;
    }

    public static void main(String[] args){
        StudentJDBC teacherJDBC = new StudentJDBC();
        teacherJDBC.submitHomework("1234","444","555");
    }

    public static void s(Object s){
        System.out.println(s);
    }

}