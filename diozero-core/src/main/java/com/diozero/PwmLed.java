package com.diozero;

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

import com.diozero.api.PwmOutputDevice;

public class PwmLed extends PwmOutputDevice {
	public PwmLed(int pinNumber) throws IOException {
		this(pinNumber, 0);
	}
	
	public PwmLed(int pinNumber, float initialValue) throws IOException {
		super(pinNumber, initialValue);
	}
	
	public void blink() throws IOException {
		blink(1, 1, INFINITE_ITERATIONS, true);
	}
	
	public void blink(float onTime, float offTime, int iterations, boolean background) throws IOException {
		onOffLoop(onTime, offTime, iterations, background);
	}
	
	public void pulse() throws IOException {
		fadeInOutLoop(1, 50, INFINITE_ITERATIONS, true);
	}
	
	public void pulse(float fadeTime, int steps, int iterations, boolean background) throws IOException {
		fadeInOutLoop(fadeTime, steps, iterations, background);
	}
	
	public boolean isLit() throws IOException {
		return isOn();
	}
}
