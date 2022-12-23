package org.webp;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class Writer {

    public Writer(){}

    @PersistenceContext
    private EntityManager em;
    public Long createWriter(String name){

        Writer writer = new Writer();
        writer.setName(name);

        em.persist(writer);

        return writer.getId();
    }

    public List<Writer> getAllWriters(){

        TypedQuery<Writer> query = em.createQuery("select s from Writer s", Writer.class);
        List<Writer> writers = query.getResultList();
        if(writers.size() == 0){
            throw new IllegalArgumentException("There is no writer in database");
        }

        return writers;
    }

    public Writer getWriter(long id){

        Writer writer = em.find(Writer.class, id);
        if(writer == null){
            throw new IllegalArgumentException("Writer with id "+id+" does not exist");
        }


        return writer;
    }
}
