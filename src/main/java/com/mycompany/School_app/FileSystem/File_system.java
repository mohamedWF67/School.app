package com.mycompany.School_app.FileSystem;

import com.mycompany.School_app.User.User;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class File_system {

    //Write and ArrayList to a File Using its pathname
    public static void writeAllToFile(String filename, ArrayList<Object> objects) throws IOException {

        File file = new File(filename);//Creates a File From the File path
        FileWriter fw = new FileWriter(file);//Creates a FileWriter using the File
        BufferedWriter bw = new BufferedWriter(fw);//Creates a BufferedWriter using the FileWriter

        //iterate through all of the Objects of the ArrayList using a Range-Based For-Loop
        for (Object obj : objects) {

            Class clazz = obj.getClass();//Creates a reference to the Class of the object
            ArrayList<Field> fields = fillFields(clazz);//creates an Arraylist for the class's Fields using the fillFields function

            bw.write(clazz.getName() + "{");//Writes the Class Name to the File
            if (!fields.isEmpty()) {//Check's if the fields is not empty
                //iterate through the fields using a Range-Based For-Loop
                for (Field field : fields) {
                    try {
                        if (Modifier.isStatic(field.getModifiers())) continue;//Skips if the Field is Static
                        field.setAccessible(true);// Allow access to private fields
                        bw.write("@#" + field.getName().toUpperCase() + "=" + field.get(obj) + ",");//Writes the Field to the File in a Special format
                    } catch (IOException | IllegalAccessException e) {throw new RuntimeException(e);}
                }
                System.out.println("Printing " + clazz.getName());//Console confirmation
            } else System.err.println("Failed to print " + clazz.getName());//Console Error message
            bw.write("}@#END");//Writes the End of the Class to the File
            bw.newLine();//Writes a new line to the File
        }
        bw.close();//Closes the BufferedWriter
        fw.close();//Closes the FileWriter
        System.out.println(filename + " written to " + file.getAbsolutePath());
    }

    public static void writeToFile(String filename, Object obj) throws IOException {
        File file = new File(filename);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write(obj.getClass().getName() +"{");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Field[] fields = obj.getClass().getSuperclass().getDeclaredFields();
        if (fields.length > 0) {
            for (Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
                try {
                    if (Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }
                    field.setAccessible(true);
                    bw.write("@#"+field.getName().toUpperCase()+"=" + field.get(obj)+",");
                } catch (IOException | IllegalAccessException  e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Printing " + obj.getClass().getSuperclass().getName());
        }else{
            System.out.println("Failed to print " + obj.getClass().getSuperclass().getName());
        }
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                if (Modifier.isStatic(field.getModifiers())) continue;
                field.setAccessible(true);
                bw.write("@#"+field.getName().toUpperCase()+"=" + field.get(obj)+",");
            } catch (IllegalAccessException  e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Printing " + obj.getClass().getName());
        bw.write("}@#END");
        bw.close();
        fw.close();
    }

    public static ArrayList<Object> readAllFromFile(String filename) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        ArrayList<Object> arrayofobjects = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String className = line.substring(0, line.indexOf("{"));
            Class<?> c = Class.forName(className);
            ArrayList<Field> fields = fillFields(c);
            System.out.println("Class Name is " + c.getName());
            ArrayList<Object> values = new ArrayList<>();
            int i = 0;
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    i++;
                    continue;
                }
                int indexForStart = (line.indexOf("@#"+field.getName().toUpperCase()+"=")+field.getName().length()+3);
                int indexForEnd=(i < fields.size()-1?line.indexOf(",@#"+fields.get(i+1).getName().toUpperCase()+"="):(line.indexOf(",}@#END")));
                System.out.println(field.getName().toUpperCase());
                if (i<fields.size()-1){
                    System.out.println("@#"+field.getName().toUpperCase()+"="+"|||"+",@#"+fields.get(i+1).getName().toUpperCase()+"="+"|||"+ (field.getName().length()+3));
                }else{
                    System.out.println("@#"+field.getName().toUpperCase()+"="+"|||"+",}@#END"+"|||"+ (field.getName().length()+3));
                }
                String fieldValue = line.substring(indexForStart , indexForEnd);
                values.add(convertToFieldType(field.getType(), fieldValue));
                System.out.println(fieldValue);
                i++;
            }
            Object obj = c.newInstance();
            assignValues(obj, values);
            System.out.println(obj.getClass().getSuperclass().getName()+": ");
            System.out.println(obj.toString());
            arrayofobjects.add(obj);
        }

        return arrayofobjects;
    }

    public static void readFromFile(String filename) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        line = br.readLine();
        String className = line.substring(0, line.indexOf("{"));
        Class<?> c = Class.forName(className);
        ArrayList<Field> fields = fillFields(c);
        System.out.println("Class Name is " + c.getName());
        ArrayList<Object> values = new ArrayList<>();
        int i = 0;
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                i++;
                continue;
            }
            int indexForStart = (line.indexOf("@#"+field.getName().toUpperCase()+"=")+field.getName().length()+3);
            int indexForEnd=(i < fields.size()-1?line.indexOf(",@#"+fields.get(i+1).getName().toUpperCase()+"="):(line.indexOf(",}@#END")));
            System.out.println(field.getName().toUpperCase());
            if (i<fields.size()-1){
                System.out.println("@#"+field.getName().toUpperCase()+"="+"|||"+",@#"+fields.get(i+1).getName().toUpperCase()+"="+"|||"+ (field.getName().length()+3));
            }else{
                System.out.println("@#"+field.getName().toUpperCase()+"="+"|||"+",}@#END"+"|||"+ (field.getName().length()+3));
            }
            String fieldValue = line.substring(indexForStart , indexForEnd);
            values.add(convertToFieldType(field.getType(), fieldValue));
            System.out.println(fieldValue);
            i++;
        }
        Object obj = c.newInstance();
        assignValues(obj, values);
        System.out.println(obj.getClass().getSuperclass().getName()+": ");
        System.out.println(obj.toString());

    }

    //Function for Coverting from string to the FieldType.
    private static Object convertToFieldType(Class<?> fieldType, String value) {
        if (fieldType == int.class || fieldType == Integer.class) {
            return Integer.parseInt(value);
        } else if (fieldType == double.class || fieldType == Double.class) {
            return Double.parseDouble(value);
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (fieldType == String.class) {
            return value;
        }else if (fieldType == User.class) {
            System.out.println("Found user");
            return null;
        }
        //if the type wasn't found it throws an error
        throw new IllegalArgumentException("Unsupported field type: " + fieldType);
    }

    //function to assign Values From an ArrayList to the desired Object.
    public static void assignValues(Object obj, ArrayList<Object> values) {
        Class<?> clazz = obj.getClass();//Identify the Class

        ArrayList<Field> fields = fillFields(clazz);//creates an Arraylist for the class's Fields using the fillFields function

        int j = 0;//Used to iterate through the Values ArrayList
        for (int i = 0; i < fields.size() && j < values.size(); i++) {
            fields.get(i).setAccessible(true);// Allow access to private fields
            System.out.println(fields.get(i).getName());
            try {
                boolean isStatic = Modifier.isStatic(fields.get(i).getModifiers());//Checks if the Field is Static
                if(!isStatic){//IF the Field isn't static it Assigns it's value if not it skips it
                    fields.get(i).set(obj, values.get(j));// Assign value to the field
                    System.out.println("value of "+fields.get(i).getName()+" is "+fields.get(i).get(obj).toString());
                    j++;
                }
            } catch (IllegalAccessException | IllegalArgumentException e) {
                System.out.println("Error assigning value to " + fields.get(i).getName());
            }
        }
    }

    private static ArrayList<Field> fillFields(Class c) {
        ArrayList<Field> fields = new ArrayList<>(Arrays.asList(c.getSuperclass().getDeclaredFields()));//Add the Class's parent Fields to an ArrayList if available
        fields.addAll(Arrays.asList(c.getDeclaredFields()));//Add the Class's Fields to the Fields ArrayList
        return fields;
    }

    public static void saveObjectToFile(String filename,ArrayList<?>values) {
        try {
            File file = new File(filename);
            FileOutputStream fos = new FileOutputStream(file);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(values);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Saved to " + filename);
    }

    public static void saveObjectToFile(String filename, HashSet<?> values) {
        try {
            File file = new File(filename);
            FileOutputStream fos = new FileOutputStream(file);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(values);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Saved to " + filename);
    }

    public static ArrayList<?> readObjectFromFile(String filename) {
        ArrayList<?>studentList2 = new ArrayList<>();
        try {
            File file = new File(filename);
            if (!file.exists()) {
                return null;
            }
            FileInputStream fis = new FileInputStream(file);

            ObjectInputStream ois = new ObjectInputStream(fis);
             studentList2 = (ArrayList<?>) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Read from "+filename);
        return studentList2;
    }
}
