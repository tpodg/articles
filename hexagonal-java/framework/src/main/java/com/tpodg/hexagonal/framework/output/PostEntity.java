package com.tpodg.hexagonal.framework.output;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "post")
class PostEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_generator")
    @SequenceGenerator(name = "post_generator", sequenceName = "post_seq")
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "content")
    String content;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL)
    Collection<CommentEntity> comments = new HashSet<>();

    Collection<CommentEntity> getComments() {
        return this.comments;
    }
}
