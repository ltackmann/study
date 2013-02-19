package org.randompage.samples.seam.wiki.client;

/**
 * Access to various system information
 * 
 * @author Lars Tackmann
 */
public interface SystemManager {
	String getDatabaseStatistics();
	
	String getApplicationName();
}
