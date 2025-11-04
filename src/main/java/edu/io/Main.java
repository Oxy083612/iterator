package edu.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {

        Reader reader;

        if (args.length == 0){
            System.out.println("Provided no arguments.");
            throw new RuntimeException();
        }

        try {
            if( args.length > 1 ){
                reader = new FileReader(args[1]);
            } else {
                reader = new InputStreamReader(System.in);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedReader br = new BufferedReader(reader);
        String line;

        switch (args[0]){
            case "c":
                while((line = br.readLine()) != null){
                    TextSource textSource = new TextSource(line);
                    for(var c : textSource) {
                        System.out.println(c);
                    }
                }
                break;

            case "w":
                while((line = br.readLine()) != null){
                    var it = new TextSource(line).wordIterator();
                    while(it.hasNext()){
                        System.out.println(it.next());
                    }
                }
                break;


        }

        br.close();
    }
}