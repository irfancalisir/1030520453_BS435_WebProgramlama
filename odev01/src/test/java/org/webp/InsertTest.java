package org.webp;

import org.webp.Novel;
import org.webp.Writer;
import org.webp.Genre;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

public class InsertTest {

    private EntityManagerFactory factory;
    private EntityManager em;

    @BeforeEach
    public void init() {
        factory = Persistence.createEntityManagerFactory("Hibernate");
        em = factory.createEntityManager();
    }

    @AfterEach
    public void tearDown() {
        em.close();
        factory.close();
    }

    private boolean persistInATransaction(Object obj) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.persist(obj);
            tx.commit();
        } catch (Exception e) {
            System.out.println("FAILED TRANSACTION: " + e.toString());
            tx.rollback();
            return false;
        }

        return true;
    }


    @Test
    public void insertNovel() {

        Novel novel = new Novel();
        novel.setNovelID(101L);
        novel.setName("abc");

        boolean persisted = persistInATransaction(novel);
        assertTrue(persisted);
    }

    @Test
    public void insertGenre() {

        Genre genre = new Genre();
        genre.setGenreID(1L);
        genre.setName("ASD");

        boolean persisted = persistInATransaction(genre);
        assertTrue(persisted);
    }

    @Test
    public void insertWriter() {

        Writer writer = new Writer();
        writer.setID(123L);
        writer.setName("aaa");

        boolean persisted = persistInATransaction(writer);
        assertTrue(persisted);

        Writer writer2 = new Writer();
        writer2.setID(123L);
        writer2.setName("AA");

        boolean persisted2 = persistInATransaction(writer2);
        assertTrue(persisted2); //hata alırız aynı id'ye sahip bir sürücü mevcuttur.

    }

    @Test
    public void testWriterWithGenre(){

        Genre genre = new Genre();
        assertTrue(persistInATransaction(genre));

        //henuz user tanimlanmadigi icin null olmalidir
        assertNull(genre.getWriter());

        Writer writer = new Writer();
        writer.setGenre(genre);
        genre.setWriter(writer);

        assertTrue(persistInATransaction(writer));
    }



}
