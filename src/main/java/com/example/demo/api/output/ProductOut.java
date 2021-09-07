package com.example.demo.api.output;

import com.example.demo.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductOut {

    private int page;
    private int totalPage;
    private List<ProductDTO> listResult = new ArrayList<>();
    private int totalItem;
    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ProductDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<ProductDTO> listResult) {
        this.listResult = listResult;
    }
}
