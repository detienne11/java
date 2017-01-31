package fr.dav.app.filescan;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileList {

	private final static Map<String, List<File>> mapFile = new HashMap<>();
	private final static Set<String> copySet = new HashSet<>();

	public final static void execute(String filepath) {

		List<File> failList = new ArrayList<>();

		File path = new File(filepath);
		if (path.exists()) {
			List<File> fileList = FileList.getFileList(path);
			for (File file : fileList) {
				try {
					final String hex = Checksum.generate(file);

					if (mapFile.containsKey(hex)) {
						final List<File> list = mapFile.get(hex);
						list.add(file);
						FileList.copySet.add(hex);
					} else {
						final List<File> list = new ArrayList<>();
						list.add(file);
						mapFile.put(hex, list);
					}

					System.out.printf("%s | %s | %s\n", hex, file.getName(), file.getAbsolutePath());
				} catch (NoSuchAlgorithmException | IOException e) {
					failList.add(file);
				}
			}

			System.out.println("Nombre de fichier : " + fileList.size());
			
			if (!FileList.copySet.isEmpty()) {
				System.err.printf("\n%d copied files :\n", FileList.copySet.size());
				for (String hex : FileList.copySet) {
					System.out.printf("%s : \n", hex);
					final List<File> list = mapFile.get(hex);					
					for (File file : list) {
						System.out.printf("%s | %s\n", file.getName(), file.getAbsolutePath());
					}
				}
			}
			
			if (!failList.isEmpty()) {
				System.err.printf("\n%d failed files :\n", failList.size());
				for (File failFile : failList) {
					System.out.print(failFile.getName() + " ");
					System.out.println(failFile.getAbsolutePath());
				}
			}

		}
	}

	private final static List<File> getFileList(File file) {

		final List<File> fileList = new ArrayList<>();

		if (file != null) {
			File[] subFileList = file.listFiles();
			for (File subFile : subFileList) {
				if (subFile.isDirectory()) {
					List<File> subFolderList = FileList.getFileList(subFile);
					fileList.addAll(subFolderList);
				} else {
					fileList.add(subFile);
				}
			}
		}

		return fileList;
	}

}
