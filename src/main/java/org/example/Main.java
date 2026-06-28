package org.example;
import Class.Message;

import java.io.*;
import java.util.Date;
import java.util.Scanner;
import com.google.gson.Gson;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner scan = new Scanner(System.in);
        Date fecha = new Date();
        Gson gson = new Gson();

        System.out.println("Ingrese Acción Audaz (Cosa que hiciste que te dio miedo o pereza): ");
        String accionAudaz = scan.nextLine();

        System.out.println("Fuga Dopamina (Si aiste en dopamina barata, porno, redes etc): ");
        String fugaDopamina = scan.nextLine();

        Message message = new Message(fecha, accionAudaz, fugaDopamina);
        String messageJson = gson.toJson(message);

        String rutaDoc = System.getProperty("user.dir") + "/" + "momentum.text";
        File ruta = new File(rutaDoc);

        if (!ruta.exists()) {
            FileWriter doc = null;
            doc = new FileWriter(rutaDoc);
            createDoc(doc, messageJson, rutaDoc);
        }

       if (ruta.exists()){
            updateDoc(doc);
       }

    }

    public static void updateDoc(FileWriter doc) throws FileNotFoundException {
        System.out.println("Doc "+doc);
        FileReader fileRead = new FileReader(String.valueOf(doc));
        System.out.println(fileRead + " llllll  ");
        BufferedReader reader = new BufferedReader(fileRead);
        System.out.println(reader);
//        try(reader){
//            System.out.println("Reader." +  reader);
//            String line;
//            while ((line = reader.readLine()) != null){
//                System.out.println(line);
//                System.out.println(" Archivo actualizado ");
//            }
//        }catch (IOException e){
//            System.out.println("Error al leer el archivo " + e.getMessage());
//        }
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