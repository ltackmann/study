package jpa.spring;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.testng.annotations.Test;

import jpa.domain.Country;
import jpa.domain.User;
import jpa.domain.UserType;
import jpa.spring.api.CountryDao;
import jpa.spring.api.UserDao;
import jpa.spring.config.PersistenceTestJPAConfig;
import jpa.spring.utils.SpringInjector;

import static jpa.spring.utils.DbUtils.*;

public class SpringJpaTest extends SpringInjector {
	@Inject
    private UserDao userDao;
	@Inject
    private CountryDao countryDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public SpringJpaTest() {
		super(PersistenceTestJPAConfig.class);
	}

    @Test
    public void dataImportTest() {	
    	doInTransaction(new TransactionCallback<Object>() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				importData("database-data.xml", entityManager);
				return null;
			}
		});
    }
    
    @Test(dependsOnMethods = "dataImportTest")
    public void userDaoTest() {
		User user = userDao.findUser("donald");
        
        assertThat(user.getName(), is("Donald Duck"));
        assertThat(user.getUserType(), equalTo(UserType.CUSTOMER));
	}
    
    @Test(dependsOnMethods = "dataImportTest")
    public void countryDaoTest() {
		Country country = countryDao.findByCountryCode("US");
        assertThat(country.getName(), is("USA"));
        
        List<Country> countries = countryDao.findAll();
        assertThat(countries, is(not(empty())));
	}
}
