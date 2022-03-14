package com.tpodg.hexagonal.framework.output;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class PostRepository implements PanacheRepository<PostEntity> {
}
