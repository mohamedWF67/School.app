package com.mycompany.School_app.StatusSystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class StatusManager {

    ArrayList<Status> statusList;
    File statusFile;

    public StatusManager() {
        statusList = new ArrayList<>();
        statusFile = new File("StatusFile.txt");
    }

    public Status addStatus(Status status) {
        statusList.add(status);
        try {
            FileWriter fw = new FileWriter(statusFile);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.append(status.toString());

            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            return status;
        }
    }

}
