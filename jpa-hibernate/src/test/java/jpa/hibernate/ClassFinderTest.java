package jpa.hibernate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.annotations.Test;

import jpa.hibernate.utils.ClassFinder;

public class ClassFinderTest {
	@Test
	public void findAllClasses() {
		List<Class<?>> classes = ClassFinder.find("jpa.domain");
		assertThat(classes, notNullValue());
		assertThat(classes.size(), is(20));
	}
	
	@Test
	public void findOnlyEntities() {
		List<Class<?>> entities = ClassFinder.findEntities("jpa.domain");
		assertThat(entities, notNullValue());
		assertThat(entities.size(), is(15));
	}
}
