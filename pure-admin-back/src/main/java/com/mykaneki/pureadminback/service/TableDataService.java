package com.mykaneki.pureadminback.service;


import com.mykaneki.pureadminback.pojo.PageBean;
import com.mykaneki.pureadminback.pojo.Result;
import com.mykaneki.pureadminback.pojo.TableData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TableDataService {

    PageBean page(Integer currentPage, Integer pageSize);

    Integer editRow(String id,TableData data);

    Integer deleteRow(String id);
    Integer insert(TableData formData);

    Result uploadFileProcess(MultipartFile file);

    ResponseEntity<byte[]> getImage(String imageName) throws IOException;
    ResponseEntity<byte[]> getVideo(String imageName) throws IOException;
}
