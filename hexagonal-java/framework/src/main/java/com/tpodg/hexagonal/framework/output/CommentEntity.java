package com.tpodg.hexagonal.framework.output;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "comment")
class CommentEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_generator")
    @SequenceGenerator(name = "comment_generator", sequenceName = "comment_seq")
    @Column(name = "id")
    Long id;

    @Column(name = "content")
    String content;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    PostEntity post;
}
