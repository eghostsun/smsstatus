package com.slf.engine.main;

import com.slf.engine.threads.MainThread;
public class MainEngine {

	
	public static void main(String[] args) {
		new MainThread().start();
	}

}
