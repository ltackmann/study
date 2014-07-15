package org.randompage.bookmarking.rest.testUtils;

import javax.ws.rs.core.Response.Status;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class Matchers {
	private static class HasHTTPStatus extends TypeSafeMatcher<Integer> {
		private final int expectedCode;
		private final String expectedMessage;
		private int actualCode;
		private String actualMessage;

		public HasHTTPStatus(Status status) {
			expectedCode = status.getStatusCode();
			expectedMessage = status.toString();
		}

		@Override
		public boolean matchesSafely(Integer statusCode) {
			if (expectedCode != statusCode) {
				actualCode = statusCode;
				actualMessage = TestUtils.getStatus(statusCode).toString();
				return false;
			}
			return true;
		}

		public void describeTo(Description description) {
			description.appendText("HTTP status code ").appendValue(
					expectedCode).appendText(" (" + expectedMessage + ")")
					.appendText("\n     got: ").appendValue(actualCode)
					.appendText(" (" + actualMessage + ")");
		}
	}

	/**
	 * Match HTTP status codes
	 * 
	 * @param <T>
	 * @param status
	 * @return
	 */
	@Factory
	public static <T> Matcher<Integer> hasHTTPStatus(Status status) {
		return new HasHTTPStatus(status);
	}
}
