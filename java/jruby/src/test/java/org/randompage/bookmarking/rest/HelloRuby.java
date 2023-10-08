package org.randompage.bookmarking.rest;

import static org.junit.Assert.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.BeforeClass;
import org.junit.Test;

public class HelloRuby {
	private static ScriptEngine rubyEngine;

	@BeforeClass
	public static void setUp() {
		ScriptEngineManager manager = new ScriptEngineManager();
		rubyEngine = manager.getEngineByName("jruby");
		assertNotNull(rubyEngine);
	}

	@Test
	public void helloRuby() throws Exception {
		assertEquals(3L, rubyEngine.eval("'abcdef' =~ /d/"));
	}

	/**
	 * TODO create and test simple external cocumber script 
	 * TODO use cocumber to test twitter service
	 * TODO update simple JAX-RS project to use this for integration test
	 * TODO use maven integration testing plugin
	 * TODO blog about it
	 * @throws Exception
	 */
	@Test
	public void helloGems() throws Exception {
		String s = String.format("%s\n%s\n%s\n", 
				"require 'rubygems'",
				"require 'rubygems/dependency_installer'",
				"Gem::DependencyInstaller.new.install('cucumber')");
		rubyEngine.eval(s);
	}
}
