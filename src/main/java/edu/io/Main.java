package edu.io;

import java.io.*;

// należy podać trzeci argument w ramach funkcji TextSource.regexIterator() i działa tylko z plikiem
// w plikach znajduje się przykładowy tekst o nazwie text.txt
// niestety nie mam pojęcia czemu plik .exe nie działa

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

            case "s":
                while((line = br.readLine()) != null){
                    var it = new TextSource(line).sentenceIterator();
                    while(it.hasNext()){
                        System.out.println(it.next());
                    }
                }
                break;

            case "n":
                while((line = br.readLine()) != null){
                    var it = new TextSource(line).numberIterator();
                    while(it.hasNext()){
                        System.out.println(it.next());
                    }
                }
                break;

            case "r":
                if(args.length > 2){
                    while((line = br.readLine()) != null){
                        var it = new TextSource(line).regexIterator(args[2]);
                        while(it.hasNext()){
                            System.out.println(it.next());
                        }
                    }
                } else {
                    throw new IOException("There's no third argument");
                }


        }

        br.close();
    }
}