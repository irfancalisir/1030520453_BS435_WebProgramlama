package org.webp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Table(name = "genres")
@Entity
public class Genre {

    @Column(name = "genreID")
    @Id
    @GeneratedValue
    private Long genreID;

    @Column(name = "Name")
    @NotBlank
    @Size(max=50)
    private String Name;

    @OneToOne(mappedBy ="genre",fetch = FetchType.EAGER)
    private Writer writer;


    public Genre(){}

    public Long getGenreID() {
        return genreID;
    }

    public void setGenreID(Long genreID) {
        this.genreID = genreID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}

