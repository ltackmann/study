package org.randompage.bookmarking.ws;

import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;

/**
 * Used when we cannot handle a human resource request
 * 
 * @author Lars Tackmann
 */
@SoapFault(faultCode = FaultCode.SERVER)
public class HumanResourceException extends Exception {
	private static final long serialVersionUID = 5511586007960057196L;

	public HumanResourceException(String message) {
		super(message);
	}
}
