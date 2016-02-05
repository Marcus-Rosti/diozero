package com.diozero.api;

/*
 * #%L
 * Device I/O Zero - Core
 * %%
 * Copyright (C) 2016 diozero
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */


import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.diozero.internal.spi.DeviceInterface;

public class DeviceStates {
	private static final Logger logger = LogManager.getLogger(DeviceStates.class);
	
	private Map<String, DeviceInterface> devices;
	
	public DeviceStates() {
		devices = new ConcurrentHashMap<>();
	}

	public boolean isOpened(String key) {
		return devices.containsKey(key);
	}
	
	public void opened(DeviceInterface device) {
		devices.put(device.getKey(), device);
	}
	
	public void closed(DeviceInterface device) {
		logger.debug("closed(" + device.getKey() + ")");
		devices.remove(device.getKey());
	}
	
	public void closeAll() {
		logger.debug("closeAll()");
		for (DeviceInterface device : devices.values()) {
			// No need to remove from the Map as close() should always call closed()
			try { device.close(); } catch (IOException e) { }
		}
	}

	public DeviceInterface getDevice(String key) {
		return devices.get(key);
	}
}
