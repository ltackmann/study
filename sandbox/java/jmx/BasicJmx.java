package jmx;

import java.lang.management.ManagementFactory;
import java.util.Scanner;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MalformedObjectNameException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import jmx.basic.*;

//http://www.javaworld.com/article/2072279/the-jmx-mxbean.html
public class BasicJmx {
	@SuppressWarnings("resource")
	public static void main(final String[] arguments) {
		final String mbeanObjectNameStr = "example:type=Status";
		final String mxbeanObjectNameStr = "example:type=StatusMX";
		// final String mbean3ObjectNameStr = "example:type=Status3";
		final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		registerMBean(mbs, mbeanObjectNameStr, Status.class);
		registerMBean(mbs, mxbeanObjectNameStr, StatusMX.class);
		// registerMBean(mbs, mbean3ObjectNameStr, Status3.class);
		Scanner in = new Scanner(System.in);
		in.nextLine();
	}
	
	public static void registerMBean(final MBeanServer mbs, final String mBeanObjectName, final Class mBeanClass) {
		try {
			final ObjectName name = new ObjectName(mBeanObjectName);
			final Object mBean = mBeanClass.newInstance();
			mbs.registerMBean(mBean, name);
		} catch (InstantiationException badInstance) // Class.newInstance()
		{
			System.err.println("Unable to instantiate provided class with class " + "name " + mBeanClass.getName()
					+ ":\n" + badInstance.getMessage());
		} catch (IllegalAccessException illegalAccess) // Class.newInstance()
		{
			System.err.println("Illegal Access trying to instantiate " + mBeanClass.getName() + ":\n"
					+ illegalAccess.getMessage());
		} catch (MalformedObjectNameException badObjectName) {
			System.err.println(mBeanObjectName + " is a bad ObjectName:\n" + badObjectName.getMessage());
		} catch (InstanceAlreadyExistsException duplicateMBeanInstance) {
			System.err
					.println(mBeanObjectName + " already existed as an MBean:\n" + duplicateMBeanInstance.getMessage());
		} catch (MBeanRegistrationException mbeanRegistrationProblem) {
			System.err.println(
					"ERROR trying to register " + mBeanObjectName + ":\n" + mbeanRegistrationProblem.getMessage());
		} catch (NotCompliantMBeanException badMBean) {
			System.err.println("ERROR: " + mBeanObjectName + " is not compliant:\n" + badMBean.getMessage());
		}
	}
}