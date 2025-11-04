package edu.io;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextSource implements Iterable<String> {

    private final String text;

    public TextSource(String text){
        this.text = text;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < text.length();
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return String.valueOf(text.charAt(index++));
            }
        };
    }

    public Iterator<String> wordIterator(){
        String[] words = text.trim().isEmpty() ? new String[0] : text.trim().split("\\s+");
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return words[index++];
            }
        };
    }

    public Iterator<String> sentenceIterator(){
        if (text.isEmpty()) {
            return Collections.emptyIterator();
        }
        String[] words = text.split("(?<=[.!?;:])\\s*");
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return words[index++];
            }
        };
    }

    public Iterator<String> numberIterator(){
        if (text.isEmpty()) {
            return Collections.emptyIterator();
        }
        Pattern pattern = Pattern.compile("[-+]?(?:\\d*\\.\\d+|\\d+)(?:[eE][-+]?\\d+)?");
        Matcher matcher = pattern.matcher(text);
        List<String> numbers = new ArrayList<>();
        while (matcher.find()) {
            numbers.add(matcher.group());
        }
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < numbers.size();
            }

            @Override
            public String next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return numbers.get(index++);
            }
        };
    }

    public Iterator<String> regexIterator(String regex){

        if (text.isEmpty()) {
            return Collections.emptyIterator();
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return new Iterator<>() {
            private boolean hasNext = matcher.find();

            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public String next() throws NoSuchElementException {
                if (!hasNext) throw new NoSuchElementException();
                String result = matcher.group();
                hasNext = matcher.find();
                return result;
            }
        };
    }

    public Iterator<String> regexIterator() {
        return regexIterator("");
    }
}
