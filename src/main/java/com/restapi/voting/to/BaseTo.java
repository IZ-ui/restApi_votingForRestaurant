package com.restapi.voting.to;

import com.restapi.voting.HasId;

import javax.validation.constraints.NotNull;

public class BaseTo implements HasId {

    @NotNull
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
