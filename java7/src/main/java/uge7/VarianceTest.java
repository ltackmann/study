package uge7;

import java.util.LinkedList;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VarianceTest {
	List<Foo> fooList = new LinkedList<>();
	List<FooBar> fooBarList = new LinkedList<>();
	
	@BeforeTest
	public void setUp() {
		fooList.add(new Foo());
		fooList.add(new FooBar());
		fooBarList.add(new FooBar());
	}
	
	@Test
	public void invarianceTest() {
		//List<Object> = fooBarList;
	}
	
	// covariance = unknown type -- that happens to be subclass of Foo
	@Test
	public void covarianceTest() {
		// covariance
		someFoo(fooList);
		someFoo(fooBarList);
		
		List<? extends Foo> covarianceList = new LinkedList<>();
		covarianceList.add(null);

		List<Foo> foos = (List<Foo>)covarianceList; 
		foos.add(new Foo());
		
		covarianceList = fooList;
		covarianceList = fooBarList;
	}
	
	public static void someFoo(List<? extends Foo> anyFooList) {
		for(Object anyFoo : anyFooList) {
			System.out.println(anyFoo);
		}
	}
	
	// contravariance = unknown type -- that happens to be super class of Foo (bounded by)
	@Test
	public void contravarianceTest() {
		List<? super Foo> contraVarianceList = new LinkedList<>();
		contraVarianceList.add(new Foo());
		contraVarianceList.add(new FooBar());
	}
}

class Foo {
	
}

class FooBar extends Foo {
	
}