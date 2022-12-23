package org.webp;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Table(name = "writers")
@Entity
public class Writer {

    @Column(name = "ID")
    @Id
    @GeneratedValue
    private Long ID;

    @Column(name = "Name")
    @NotBlank
    @Size(max = 50)
    private String Name;

    @OneToMany(mappedBy = "writer",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
    private List<Novel> novels=new ArrayList<Novel>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genreId")
    private Genre genre;


    public Writer() {
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public List<Novel> getNovels() {
        return novels;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setNovels(List<Novel> novels) {
        this.novels = novels;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
