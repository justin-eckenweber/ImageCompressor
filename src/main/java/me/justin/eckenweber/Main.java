package me.justin.eckenweber;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String folderName = args[0];
            boolean resize = true;
            float quality = 0.7f;
            boolean customSize = false;
            int customHeight = -1;
            int customWidth = -1;

            for(String arg : args) {
                if (arg.startsWith("quality")) {
                    quality = Float.parseFloat(arg.split(":")[1]);
                    continue;
                }

                if (arg.startsWith("custom-size")) {
                    var sizeInformation = arg.split(":")[1].split("x");
                    customWidth = Integer.parseInt(sizeInformation[0]);
                    customHeight = Integer.parseInt(sizeInformation[1]);
                    customSize = true;
                    continue;
                }

                if (arg.equalsIgnoreCase("no-resize")) resize = false;
            }

            if (folderName.equalsIgnoreCase("all")) {
                var folders = getAllFilesOfFolder("");
                int folderCount = 0;

                for (File folder : folders) if (folder.isDirectory()) folderCount++;

                int count = 1;
                for (File folder : folders) {
                    if (folder.isDirectory()) {
                        System.out.println();
                        System.out.println("Compressing Folder "+ folder.getName() + " [" + count++ + "/" + folderCount +  "]");
                        System.out.println();
                        var files = getAllFilesOfFolder(folder.getName());

                        for (File file : files) {
                            if (file.isFile()) {
                                ImageCompressor.compress(new File(getJarPath() + File.separator + folder.getName() + File.separator + file.getName()), quality, resize, customSize, customWidth, customHeight);
                            }
                        }
                        System.out.println();
                        System.out.println("Done! All files were compressed.");
                        System.out.println();
                    }
                }

                return;
            } else {
                var files = getAllFilesOfFolder(folderName);

                for (File file : files) {
                    if (file.isFile()) {
                        ImageCompressor.compress(new File(getJarPath() + File.separator + folderName + File.separator + file.getName()), quality, resize, customSize, customWidth, customHeight);
                    }
                }
            }


        }
    }

    private static File[] getAllFilesOfFolder(final String folderName) {
        // Construct the path to the folder
        File folder = new File(getJarPath() + File.separator + folderName);

        // Check if the folder exists and is a directory
        if (folder.exists() && folder.isDirectory()) {
            // Get all the files in the folder
            return folder.listFiles();

        } else {
            System.out.println("Folder " + (getJarPath() + File.separator + folderName) + " does not exist.");
            return new File[0];
        }
    }

    private static String getJarPath() {
        return System.getProperty("java.class.path") + File.separator + ".." + File.separator;
    }
}