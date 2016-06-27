package dao;

import domain.Student;
import uitl.HibernateUtil;

import java.util.List;

/**
 * @author roy.zhuo
 */

public class StudentDao {

    public void addStudent(Student student) {
        HibernateUtil.add(student);
    }

    public Student getStudent(Integer id) {
        Student student = (Student) HibernateUtil.getObject(id);
        return student;
    }

    public List<Student> getStudents() {
        String sql = "from student";
        List<Student> students = HibernateUtil.getObjects(sql);
        return students;
    }
}
