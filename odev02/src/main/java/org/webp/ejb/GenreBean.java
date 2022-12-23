package org.webp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class Genre {
    public Genre(){}

    @PersistenceContext
    private EntityManager em;
    public Long createGenre(String name){

        Genre genre = new Genre();
        genre.setName(name);

        em.persist(genre);

        return genre.getId();
    }

    public List<Genre> getAllGenres(){

        TypedQuery<Genre> query = em.createQuery("select s from Genre s", Genre.class);
        List<Genre> genres = query.getResultList();
        if(genres.size() == 0){
            throw new IllegalArgumentException("There is no genre in database");
        }

        return genres;
    }

    public Genre getGenre(long id){

        Genre genre = em.find(Genre.class, id);
        if(genre == null){
            throw new IllegalArgumentException("Genre with id "+id+" does not exist");
        }


        return genre;
    }



}

