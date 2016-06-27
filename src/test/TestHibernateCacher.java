package test;

import dao.StudentDao;
import domain.Student;
import org.hibernate.Session;
import uitl.HibernateUtil;

/**
 * @author roy.zhuo
 */
public class TestHibernateCacher {
    static StudentDao studentDao = new StudentDao();

    public static void main(String args[]) {
        Session session = HibernateUtil.getCurrentSession();
        /*
        //hibernate的一级缓存也称session级缓存，当查询过一次，会将对象放到session缓存中。会话级缓存
        Student student1 = (Student) session.get(Student.class, 1);
        System.out.println(student1.toString());
        //session.clear();//清空session缓存中所有内容
        session.evict(student1);//清空一级缓存里的一个对象
        System.out.println("---------------");
        Student student2 = (Student) session.get(Student.class, 1);
        System.out.println(student2.toString());
        */
        /*
        //查询所有学生，把对象放入缓存
        String hql = "from Student";
        Query query = session.createQuery(hql);
        List<Student> students = query.list();
        for (Student student : students) {
            System.out.println(student.toString());
        }
        //通过查找所有id，从缓存中获取对象，如果没有该对象，则从数据获取
        Iterator iterator = query.iterate();
        while (iterator.hasNext()) {
            Student student = (Student) iterator.next();
            System.out.println(student.toString());
        }
        */
        //二级缓存
    }

    public static void queryStudentByID(Integer id) {

        Student s = studentDao.getStudent(id);
        System.out.println("id:" + s.getId() + " name:" + s.getName() + " age:" + s.getAge());
    }

    public static void addStudent() {
        Student student = new Student();
        student.setName("roy");
        student.setAge(15);

        Student student1 = new Student();
        student1.setName("alan");
        student1.setAge(12);

        studentDao.addStudent(student);
        studentDao.addStudent(student1);
    }

}
