package jmx;

import java.lang.management.ManagementFactory;
import java.util.Scanner;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import jmx.metrics.beans.DatabaseMetricsImpl;
import jmx.metrics.beans.GuiMetricsImpl;
import jmx.metrics.beans.MetricsRegistryImpl;
import jmx.metrics.beans.ServiceMetricsImpl;
import jmx.metrics.beans.SystemMetricsImpl;

public class JmxServer {
	@SuppressWarnings("resource")
	public static void main(final String[] arguments) {
		final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		JmxServer.registerMBean(mbs, "example:type=DatabaseMetrics", DatabaseMetricsImpl.class);
		JmxServer.registerMBean(mbs, "example:type=GuiMetrics", GuiMetricsImpl.class);
		JmxServer.registerMBean(mbs, "example:type=MetricsRegistry", MetricsRegistryImpl.class);
		JmxServer.registerMBean(mbs, "example:type=ServiceMetrics", ServiceMetricsImpl.class);
		JmxServer.registerMBean(mbs, "example:type=SystemMetrics", SystemMetricsImpl.class);
		Scanner in = new Scanner(System.in);
		in.nextLine();
	}
	
	@SuppressWarnings("rawtypes")
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
