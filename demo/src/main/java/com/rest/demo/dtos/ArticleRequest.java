package com.rest.demo.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequest {
    private String title;
    private String content;
    private List<Long> categoryIds;
}
