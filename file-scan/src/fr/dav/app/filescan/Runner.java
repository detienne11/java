package fr.dav.app.filescan;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public final class Runner {

    public static void main(String arg[]) {
        /*File file = new File("X:/_david/request.sql");
        File file2 = new File("X:/_david/request1.sql");
//        File file2 = new File("X:/_david/jordanjordan2015.wmv"); 
        String hex = null;
		try {
			hex = Checksum.generate(file);
			System.out.printf("hex=%s\n", hex);
			hex = Checksum.generate(file2);
			System.out.printf("hex=%s\n", hex);
		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}*/
    	
    	String filepath;
    	filepath = "X:/pictures";
//    	filepath = "X:/_david/jobs";
    	
    	FileList.execute(filepath);
                    
    }

}
