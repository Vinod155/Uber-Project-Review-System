package com.example.UberReviewService.models;

import lombok.*;

@Getter
@Setter
public class CustomDriver {

    Long id;
    String name;

    public CustomDriver(Long id,String name)
    {
        this.id=id;
        this.name=name;
    }
}
