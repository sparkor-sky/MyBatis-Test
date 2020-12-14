package test;

import dao.UserDao;
import entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class MybatisTest {

    SqlSession sqlSession;

    @Before
    public void init() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = factory.openSession();
    }

    @Test
    public void queryById() throws Exception{
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        System.out.println(userDao.queryById(1));
    }

    @Test
    public void queryByIdWithStatementId() throws Exception{
        User user = sqlSession.selectOne("dao.UserDao.queryById", 1);
        System.out.println(user);
    }

    @Test
    public void cache1(){
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        System.out.println(userDao.queryById(1));
        System.out.println(userDao.queryById(2));
        System.out.println(userDao.queryById(1));

        sqlSession.clearCache();
    }
}
