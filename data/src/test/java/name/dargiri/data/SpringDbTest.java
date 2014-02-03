package name.dargiri.data;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dionis on 2/3/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "txManager")
@ContextConfiguration(locations = "classpath:test-config.xml")
public abstract class SpringDbTest {
    @PersistenceContext
    protected EntityManager em;
//    @Autowired
//    private PlatformTransactionManager txManager;

//    private TransactionStatus transaction;

    @Before
    public void before() {
//        System.out.println("Creating transaction");
//        transaction = txManager.getTransaction(new DefaultTransactionDefinition());
//        System.out.println("Transaction created");
    }

    @After
    public void after() {
//        System.out.println("Rolling back transaction");
//        txManager.rollback(transaction);
//        System.out.println("Transaction roled back");
    }

    public void flushAndClear(){
        em.flush();
        em.clear();
    }
}
