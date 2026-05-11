package org.pavan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        StudentDAO studentDAO = context.getBean("studentDAO", StudentDAO.class);

        studentDAO.selectAllRows();
        studentDAO.deleteStudentRecord(10);
    }
}