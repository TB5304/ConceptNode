package com.testbook.base.entity;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

/**
 * @param <ID> Type of primary key (String, Long, UUID, etc.)
 */
@Getter 
@Setter
public abstract class BaseEntity<ID> {
    protected ID id;
    protected Instant createdAt;
    protected Instant updatedAt;
    public Object blank;
    public void prePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
    
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}