package com.shdatalink.common.model;

import lombok.Data;

@Data
public class TodoInfo {
    private String title;
    private boolean completed;
    private int order;
}
