package jmx;

import java.io.IOException;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import jmx.metrics.MetricsRegistry;

public class JmxClient {
	public static void main(String[] args) {
		MBeanServerConnection jmxServerConnection = getJmxServerConnection("", 9999); // or 127.0.0.1
		MetricsRegistry metrics =  getJmxBean(MetricsRegistry.class, "example", jmxServerConnection);
		System.out.println(metrics.getServiceMetrics().getMostCalledServices().get(0).getServiceName());
	}
	
	private static MBeanServerConnection getJmxServerConnection(String ip, int jmxPort) {
		MBeanServerConnection jmxServerConnection;
		try {
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://"+ip+":"+jmxPort+"/jmxrmi");
			JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
			jmxServerConnection = jmxc.getMBeanServerConnection();
			System.out.println("Obtained connection to JMX server at " + url.getURLPath());
		} catch(IOException e) {
			throw new RuntimeException("");
		}
        return jmxServerConnection;
	}
	
	private static <T> T getJmxBean(Class<T> beanClass, String domainName, MBeanServerConnection jmxServerConnection) {
		String objectName = String.format("%s:type=%s", domainName, beanClass.getSimpleName());
		T mbean = null;
		try {
			ObjectName mxbeanName = new ObjectName(objectName);
	        mbean = JMX.newMXBeanProxy(jmxServerConnection, mxbeanName, beanClass, true);
		} catch (MalformedObjectNameException e) {
			throw new RuntimeException(e);
		}
        return mbean;
	}
}
