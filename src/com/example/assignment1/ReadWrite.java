package com.example.assignment1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

public class ReadWrite {
	public void saveInFile(String text, String filename, Context c){
		try {
			FileOutputStream fos = c.openFileOutput(filename, c.MODE_PRIVATE);
			fos.write(text.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
