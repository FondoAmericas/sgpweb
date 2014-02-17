package pe.com.fondam.sgp.core.commons;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:pe/com/fondam/sgp/core/resources/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public abstract class AbstractBaseUnitTest {
	
	protected static Logger logger = Logger.getLogger("pe.com.fondam.sgp.core");

	protected AbstractBaseUnitTest() {

	}
}
