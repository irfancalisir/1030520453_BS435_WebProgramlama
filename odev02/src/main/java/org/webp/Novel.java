package org.webp;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Table(name = "novels")
@Entity
public class Novel {

    @Column(name = "novelID")
    @Id
    @GeneratedValue
    private Long NovelID;

    @Column(name = "Name")
    @NotBlank
    @Size(max = 50)
    private String Name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="writerId")
    private Writer writer;

    public Novel() {
    }

    public Long getNovelID() {
        return NovelID;
    }

    public void setNovelID(Long novelID) {
        NovelID = novelID;
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
