package com.unisa.seedify.model;

import com.unisa.seedify.exceptions.NotImplementedException;

public abstract class BaseBean {
    public EntityPrimaryKey getPrimaryKey() {
        throw new NotImplementedException();
    }
}
