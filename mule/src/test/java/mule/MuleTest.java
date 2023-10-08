package mule;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class MuleTest extends FunctionalTestCase {
	@Override
	protected String getConfigResources() {
		return "src/main/app/mule-config.xml";
	}

	@Test
	public void testMuleConfigFlow1() throws Exception {
		MuleClient client = muleContext.getClient();
		MuleMessage message = client.request("http://0.0.0.0:8081", 3000L);
		String payload = message.getPayloadAsString();
		System.err.println("payload is: " + payload);
		Assert.assertThat(payload, Is.is("Hello World!"));
	}
}