package com.dc.investmentapplication.utils;

import lombok.Data;

@Data
class Pagination {
    private int page;
    private int size;
    private long total;
}
