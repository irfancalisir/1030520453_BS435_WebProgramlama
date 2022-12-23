package org.webp;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class Novel {

    @PersistenceContext
    private EntityManager em;

    public NovelBean(){}

    @EJB
    private WriterBean writerBean;

    public Long createNovel(String name, long writerId){


        Writer writer = writerBean.getWriter(writerId);


        Novel novel = new Novel();
        novel.setName(name);
        novel.setWriter(writer);

        em.persist(novel);
        em.persist(writer);

        return novel.getId();
    }

    public List<Novel> getAllNovels(){

        TypedQuery<Novel> query = em.createQuery("select n from Novel n", Novel.class);
        List<Novel> novels = query.getResultList();
        if(novels.size() == 0){
            throw new IllegalArgumentException("There is no novel in database");
        }

        return novels;
    }

    public Novel getNovel(long id){
        Novel novel = em.find(Novel.class, id);
        if(novel == null){
            throw new IllegalArgumentException("Novel with id "+id+" does not exist");
        }

        return novel;
    }

}
