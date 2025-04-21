package com.mycompany.School_app.StatusSystem;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Date;

public class StatusManager {

    static ArrayList<Status> statusList;
    File statusFile;

    public StatusManager() {
        statusList = new ArrayList<>();
        statusFile = new File("StatusFile.txt");
    }

    public Status addStatus(Status status) {
        statusList.add(status);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(statusFile, true))) {
            bw.write(status.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return status;
        }
    }

    public void clearFile(){
        if (statusFile.exists()) {
            try (PrintWriter pw = new PrintWriter(statusFile)) {
                // file is cleared immediately upon opening
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createLogFile(){
        String logFileName = "StatusLog" +   new Date().toString().replace(" ", "").replace(":", "") + ".txt";

        Path folderPath = Paths.get("StatusLogs");

        try {
            Files.createDirectories(folderPath); // safe to call even if folder exists

            Path filePath = folderPath.resolve(logFileName);
            Files.createFile(filePath);

            System.out.println("File created: " + filePath);
        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exists.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File logFile = new File(String.valueOf(folderPath.resolve(logFileName)));

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(logFile))) {
           for (Status status : statusList) {
               bw.write(status.toString() + "\n");
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
