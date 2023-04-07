package com.company;

public class Main {

    public static void main(String[] args) {
        Compression obj = new Compression();
        /*obj.addWord("001 001 001 010 111 011 001 001 110 000 001 001 001 001");
        obj.compress();
        obj.getHeader();
        obj.getWord();*/

        obj.addWord("000 001 000 001 000 001 000 001 011 001 000 110 001 000 111 001 001 000 000 000 001");
        obj.compress();
        obj.getHeader();
        obj.getWord();
    }
}
