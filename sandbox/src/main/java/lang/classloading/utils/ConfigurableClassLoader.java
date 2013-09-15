package lang.classloading.utils;

import java.io.*;

public class ConfigurableClassLoader extends ClassLoader {
	final boolean parentFirst;

	public ConfigurableClassLoader() {
		parentFirst = false;
	}

	public ConfigurableClassLoader(ClassLoader parent) {
		super(parent);
		parentFirst = true;
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if (name.startsWith("java.") || parentFirst) {
			// loading core java classes such as java.lang.String is
			// prohibited and should always be handles by parent
			return super.loadClass(name);
		}

		// we cannot load the same class twice in the same class loader
		Class<?> cls = findLoadedClass(name);
		if (cls != null) {
			return cls;
		}
		
		return getClass(name);
	}

	private Class<?> getClass(String className) throws ClassNotFoundException {
		String file = className.replace('.', File.separatorChar) + ".class";
		byte[] classBytes = null;
		Class<?> c;
		try {
			classBytes = loadClassData(file);
			// defineClass is inherited from the ClassLoader class
			// and converts the byte array into a Class
			c = defineClass(className, classBytes, 0, classBytes.length);
			resolveClass(c);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return c;
	}

	// This loads the byte code data from the file
	private byte[] loadClassData(String name) throws IOException {
		// Opening the file
		InputStream stream = getClass().getClassLoader().getResourceAsStream(
				name);
		int size = stream.available();
		byte buff[] = new byte[size];
		DataInputStream in = new DataInputStream(stream);
		// Reading the binary data
		in.readFully(buff);
		in.close();
		return buff;
	}
}
