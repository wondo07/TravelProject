package com.example.TravelProject.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PageResponseDto {

    private Object data;

    private DataInfo dataInfo;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class DataInfo{

        int page;
        int size;
        int totalPages;
        Long totalElement;
    }

    static public PageResponseDto of(List list, Page page) {
        DataInfo dataInfo = new DataInfo(
                page.getNumber() + 1 ,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );

        return new PageResponseDto(list, dataInfo);
    }
}
