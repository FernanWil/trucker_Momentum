package org.example;
import Class.Message;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Scanner;
import com.google.gson.Gson;
import jdk.dynalink.StandardOperation;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        String rutaDoc = System.getProperty("user.dir") + "/" + "momentum.text";
        File ruta = new File(rutaDoc);

        if (!ruta.exists()) createDocMessage(rutaDoc, null);
        if (ruta.exists())  updateDoc(rutaDoc);

    }

    public static void updateDoc(String rutaDoc) {
//        Gson gson = new Gson();
//        Scanner scan = new Scanner(System.in);
//        Date fecha = new Date();
//        System.out.println("Ingrese Acción Audaz (Cosa que hiciste que te dio miedo o pereza): ");
//        String accionAudaz = scan.nextLine();
//
//        System.out.println("Fuga Dopamina (Si caiste en dopamina barata, porno, redes etc): ");
//        String fugaDopamina = scan.nextLine();
//
//        Message message = new Message(fecha, accionAudaz, fugaDopamina);
//        String messageJson = gson.toJson(message);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(rutaDoc, true))){
            String line;
            createDocMessage(null, writer);
//            createDocMessage(null);
//            while ((line = reader.readLine()) != null){
//                System.out.println("LINE: " + line);
                System.out.println(" Archivo actualizado ");
//            }
        }catch (IOException e){
            System.out.println("Error al leer el archivo " + e.getMessage());
        }
    }

    public static void  docExists(String rutaDoc) throws FileNotFoundException {
        FileReader fileReader = new FileReader(rutaDoc);
        BufferedReader reader = new BufferedReader(fileReader);
        updateDoc(rutaDoc);
    }

    public static void docNoExists(String rutaDoc, String messageJson)  {
        FileWriter doc = null;
        try {
            doc = new FileWriter(rutaDoc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Doc: " + doc);
        createDoc(doc, messageJson, rutaDoc);
    }

//    public static void createDocMessage(){
//        Gson gson = new Gson();
//        Scanner scan = new Scanner(System.in);
//        Date fecha = new Date();
//        System.out.println("Ingrese Acción Audaz (Cosa que hiciste que te dio miedo o pereza): ");
//        String accionAudaz = scan.nextLine();
//
//        System.out.println("Fuga Dopamina (Si caiste en dopamina barata, porno, redes etc): ");
//        String fugaDopamina = scan.nextLine();
//
//        Message message = new Message(fecha, accionAudaz, fugaDopamina);
//        String messageJson = gson.toJson(message);
//
//    }

    public static void createDocMessage(String rutaDoc, BufferedWriter bufferedWriter) throws IOException {
        Gson gson = new Gson();
        Scanner scan = new Scanner(System.in);
        Date fecha = new Date();
        System.out.println("Ingrese Acción Audaz (Cosa que hiciste que te dio miedo o pereza): ");
        String accionAudaz = scan.nextLine();

        System.out.println("Fuga Dopamina (Si caiste en dopamina barata, porno, redes etc): ");
        String fugaDopamina = scan.nextLine();

        Message message = new Message(fecha, accionAudaz, fugaDopamina);
        String messageJson = gson.toJson(message);
        if (fugaDopamina.equals("Si")) System.err.println("ERES UN FRACASADO");

        if (bufferedWriter != null){
            bufferedWriter.newLine();
            bufferedWriter.write(messageJson);
        }
        if (rutaDoc != null) docNoExists(rutaDoc, messageJson);
    }

    public static void createDoc(FileWriter doc, String messageJson, String rutaDoc){
        try(doc){
            doc.write(messageJson);
            System.out.println("Archivo guardado en" + rutaDoc);
        }catch (IOException e){
            System.out.println("Hubo algún problema: " + e);
            e.printStackTrace();
        }
    }
}