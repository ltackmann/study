package jmx;

import java.lang.management.ManagementFactory;
import java.util.Scanner;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import jmx.metrics.DatabaseMetrics;
import jmx.metrics.beans.GuiMetricsImpl;
import jmx.metrics.beans.MetricsRegistryImpl;
import jmx.metrics.beans.ServiceMetricsImpl;
import jmx.metrics.beans.SystemMetricsImpl;

public class JmxServer {
	@SuppressWarnings("resource")
	public static void main(final String[] arguments) {
		if(System.getProperty("com.sun.management.jmxremote.port") == null) { 
			throw new RuntimeException("JMX remote is disabled, please start the program with -Dcom.sun.management.jmxremote");
		}
		
		final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		DatabaseMetrics databaseMetrics = registerMBean(mbs, "example:type=DatabaseMetrics", DatabaseMetrics.class);
		GuiMetricsImpl guiMetrics = registerMBean(mbs, "example:type=GuiMetrics", GuiMetricsImpl.class);
		ServiceMetricsImpl serviceMetrics = registerMBean(mbs, "example:type=ServiceMetrics", ServiceMetricsImpl.class);
		SystemMetricsImpl systemMetrics = registerMBean(mbs, "example:type=SystemMetrics", SystemMetricsImpl.class);
		
		registerMBean(mbs, "example:type=MetricsRegistry", new MetricsRegistryImpl(databaseMetrics, guiMetrics, serviceMetrics, systemMetrics));
		
		System.out.println("JMX Server is ready...");
		Scanner in = new Scanner(System.in);
		in.nextLine();
	}
	
	@SuppressWarnings("rawtypes")
	public static <T> T registerMBean(final MBeanServer mbs, final String mBeanObjectName, final Class<T> mBeanClass) {
		T mBean = null;
		try {
			mBean = mBeanClass.newInstance();
			registerMBean(mbs, mBeanObjectName, mBean);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("unable to instantiate");
		}
		return mBean;
	}
	
	@SuppressWarnings("rawtypes")
	public static void registerMBean(final MBeanServer mbs, final String mBeanObjectName, Object mBean) {
		try {
			final ObjectName name = new ObjectName(mBeanObjectName);
			mbs.registerMBean(mBean, name);
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
