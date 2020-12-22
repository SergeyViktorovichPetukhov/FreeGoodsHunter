package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.CompanyDto;
import com.sergo.wic.dto.ProductDto;

public class ShareInfoResponse extends ResponseContent {

    private CompanyDto company;

    private ProductDto product;

    public ShareInfoResponse(){ }

    public ShareInfoResponse(CompanyDto company, ProductDto product) {
        this.company = company;
        this.product = product;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
