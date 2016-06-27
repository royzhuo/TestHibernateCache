package uitl;

import domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * @author roy.zhuo
 */

final public class HibernateUtil {

    private static SessionFactory sessionFactory = null;
    //private static Session session;
    private static ThreadLocal<Session> threadLocal = new ThreadLocal<>();

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    private HibernateUtil() {
    }

    public static List getObjects(String sql) {
        Session session = getSession();
        List objects = session.createQuery(sql).list();
        return objects;
    }

    public static Session getSession() {


        return sessionFactory.openSession();

    }

    public static void add(Object o) {
        Transaction tx;
        Session session = null;
        session = getSession();
        tx = session.beginTransaction();
        session.save(o);
        tx.commit();
        if (session != null || session.isOpen()) session.close();
    }

    public static Object getObject(Integer id) {

        Session session = getCurrentSession();
        Object o = session.get(Student.class, id);
        return o;
    }

    public static Session getCurrentSession() {
        if (threadLocal.get() == null) {
            Session session = getSession();
            threadLocal.set(session);
        }
        return threadLocal.get();
    }

}
