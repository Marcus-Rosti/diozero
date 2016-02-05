package com.diozero.sampleapps;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.diozero.api.DigitalOutputDevice;

public class GpioPerfTest {
	private static final Logger logger = LogManager.getLogger(GpioPerfTest.class);
	
	private static final int ITERATIONS = 100_000;

	public static void main(String[] args) {
		if (args.length < 1) {
			logger.error("Usage: " + GpioPerfTest.class.getName() + " <pin-number> [<iterations>]");
			System.exit(1);
		}
		
		int pin = Integer.parseInt(args[0]);
		
		int iterations = ITERATIONS;
		if (args.length > 1) {
			iterations = Integer.parseInt(args[1]);
		}
		
		try (DigitalOutputDevice gpio = new DigitalOutputDevice(pin)) {
			for (int j=0; j<5; j++) {
				long start_nano = System.nanoTime();
				for (int i=0; i<iterations; i++) {
					gpio.setValue(true);
					gpio.setValue(false);
				}
				long duration_ns = System.nanoTime() - start_nano;
				
				logger.info(String.format("Duration for %d iterations: %.4fs",
						Integer.valueOf(iterations), Float.valueOf(((float)duration_ns) / 1000 / 1000 / 1000)));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
