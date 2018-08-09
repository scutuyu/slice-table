package com.tuyu.dao;

import com.tuyu.po.UserAction;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import static org.junit.Assert.*;

/**
 * 用户行为测试
 * <p>
 *     刚开始一致报错org.apache.ibatis.exceptions.PersistenceException，说是空指针异常，
 *     经查，是因为我我使用的mysql驱动，是6.x版本，驱动名改了，不叫com.mysql.jdbc.Driver,
 *     而叫com.mysql.cj.jdbc.Driver,当控制台输出这行提示时，我错误地以为不需要驱动的配置了，
 *     于是我就将驱动配置给注释了，结果导致一致报空指针异常，正确配置驱动后就好了
 * </p>
 * @author tuyu
 * @date 8/8/18
 * Talk is cheap, show me the code.
 */
public class UserActionDaoTest {


    private static int MAC_NUM = 100;
    private static String[] macs = new String[MAC_NUM];
    private static File FILE = new File("src/main/resources/radio-mac.txt");

    static {
        try {
            Scanner scanner = new Scanner(FILE);
            int i = 0;
            while (scanner.hasNextLine()) {
                if (i < MAC_NUM) {
                    macs[i] = scanner.nextLine();
                }
                i++;
            }
            System.out.println("get " + i + " radioMacs");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private UserActionDao userActionDao;
    private SqlSession sqlSession;
    private Random randoms = new Random(6L);



    /**
     * 加载mybatis配置文件，通过sqlSessionFactory创建sqlSession
     * 通过sqlSession获取dao接口的实现类
     *
     * @throws FileNotFoundException
     */
    @Before
    public void before() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("src/main/resources/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        userActionDao = sqlSession.getMapper(UserActionDao.class);
    }

    /**
     * 提交事务，关闭sqlSession
     */
    @After
    public void after() {
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    /**
     * 新增 测试
     *
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        int insert = insert(10000);
        System.out.println("add " + insert + " userActions");
    }

    private int insert(int num) {
        int hash = 0;
        int count = 0;
        UserAction userAction = null;
        for (int i = 0; i < num; i++) {
            userAction = random();
            hash = hashUserAction(userAction);
            int add = userActionDao.add(getTableNum(hash), userAction);
            if (add <= 0) {
                System.out.println("add userAction fail: " + userAction);
            } else {
                count++;
            }
        }
        return count;
    }

    /**
     * 通过hash取模获得存入的表的序号
     */
    private String getTableNum(int hash) {
        return hash % 5 + "";
    }

    /**
     * 生成一个随机的数据
     */
    private UserAction random() {
        UserAction userAction = new UserAction();
        userAction.setDate(getRandomDate());
        userAction.setGroupId(getRandomGroupId());
        userAction.setRadioMac(getRandomRadioMac());
        userAction.setUser(getRandomRadioMac());
        userAction.setUserName(getRandomUserName());
        userAction.setDevId(getRandomDevId());
        userAction.setHostIp(getRandomDstIp());
        userAction.setDstIp(getRandomDstIp());
        userAction.setIpVersion(getRandomIpVersion());
        userAction.setSite(getRandomSite());
        userAction.setTmType(getRandomTmType());
        userAction.setServ(getRandomServ());
        userAction.setApp(getRandomApp());
        userAction.setSrcPort(getRandomSrcPort());
        userAction.setServPort(getRandomServPort());
        userAction.setNetAction("记录");
        userAction.setRecordTime(getRandomRecordTime());
        userAction.setResult("");
        return userAction;
    }

    private String getRandomDate() {

        return getDate(randomTime());
    }

    private long randomTime() {
        // 2017-01-01
        final long start = 1483228800000L;
        // 2017-08-08
        final long end = 1533686400000L;
        // (end - start) / 100000
        final int cha = 504576;
        return start + randoms.nextInt(cha) * 100000L;
    }

    private String getDate(long a) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date(a));
    }

    private Integer getRandomGroupId() {
        return randoms.nextInt(100) + 1;
    }

    private String getRandomRadioMac() {
        return macs[randoms.nextInt(100)];
    }

    private String getRandomUserName() {
        StringBuilder stringBuilder = new StringBuilder(randoms.nextInt(10) + 1);
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(randoms.nextInt(10));
        }
        return stringBuilder.toString();
    }

    private Long getRandomDevId() {
        return Math.abs(randoms.nextLong());
    }

    private String getRandomDstIp() {
        return "" + Math.abs(randoms.nextInt());
    }

    private String getRandomIpVersion() {
        return Math.abs(randoms.nextInt()) + "";
    }

    private String getRandomSite() {
        return "";
    }

    private String getRandomTmType() {
        final String[] tm = new String[]{"iphone", "android", "winPhone"};
        return tm[randoms.nextInt(3)];
    }

    private String getRandomServ() {
        final String[] serv = new String[]{"访问网站", "Web流媒体", "移动终端应用", "P2P流媒体"};
        return serv[randoms.nextInt(4)];
    }

    private String getRandomApp() {
        final String[] app = new String[]{"优酷", "微信", "QQ", "支付宝"};
        return app[randoms.nextInt(4)];
    }

    private Integer getRandomSrcPort() {
        return randoms.nextInt(25535);
    }

    private Integer getRandomServPort() {
        return randoms.nextInt(25535);
    }

    private Time getRandomRecordTime() {
        return new Time(randomTime());
    }

    /**
     * 根据date, groupId, radioMac,userName生成hash值
     */
    private int hashUserAction(UserAction userAction) {
        if (userAction == null) {
            return 0;
        }
        int result = 0;
        result += 31 * hashObject(userAction.getDate()) + 123456;
        result += 31 * hashObject(userAction.getGroupId()) + 123456;
        result += 31 * hashObject(userAction.getRadioMac()) + 123456;
        result += 31 * hashObject(userAction.getUserName()) + 123456;

        return (result >>> 1) ^ (result >>> 16);
    }

    private int hashObject(Object o) {
        int h;
        return (o == null) ? 0 : (h = o.hashCode()) ^ (h >>> 16);
    }


    @Test
    public void testList100() {
        List<UserAction> list = userActionDao.list100();
        for (UserAction userAction : list) {
            System.out.println(userAction);
        }
    }
}