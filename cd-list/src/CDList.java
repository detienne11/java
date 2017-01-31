import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CDList {

	public static void main(String[] args) {

//		String filepath = "X:/habitat";
		String filepath = "K:/musics/_musics-david";
		File file = new File(filepath);
		if (file.exists() && file.isDirectory()) {
			List<String> folderList = CDList.getFolderList(file,true);
			for (String foldername : folderList) {
				System.out.println(foldername);
			}
		}		
	}
	
	public final static List<String> getFolderList (File folder, boolean subfolder){
		
		List<String> folderList = new ArrayList<String>();
		
		if (folder != null) {
			File [] subFileList = folder.listFiles();
			for (File subFile : subFileList) {	
				if (subFile.isDirectory()) {					
					if (subfolder) {
						List<String> subfolderList = getFolderList(subFile,false);
						for (String foldername : subfolderList) {
							folderList.add(subFile.getName() + ";" + foldername);
						}
					}
					else {
						folderList.add(subFile.getName());
					}
				}
			}
		}

		return folderList;
	}

}
