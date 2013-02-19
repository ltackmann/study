package org.randompage.samples.seam.wiki.test.utils;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class NotNullOrEmpty extends TypeSafeMatcher<String> {
	@Override
	public boolean matchesSafely(String str) {
		return (str != null && str.length() > 0);
	}

	public void describeTo(Description description) {
		description.appendText("null or empty string");
	}

	@Factory
	public static <T> Matcher<String> notNullOrEmpty() {
		return new NotNullOrEmpty();
	}
}