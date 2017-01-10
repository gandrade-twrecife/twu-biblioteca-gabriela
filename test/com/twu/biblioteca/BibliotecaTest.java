package com.twu.biblioteca;

import org.junit.Test;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class BibliotecaTest {
    BibliotecaApp library;
    ArrayList<Book> books;
    Book bookProgrammingInJava = new Book("Programming in Java", "Gabriela Andrade", 2005);
    Book bookProgrammingInCSharp = new Book("Programming in C#", "Gabriela Andrade", 2006);
    Book bookTDD = new Book("Test Driven Development", "Gabriela Andrade", 2007);


}