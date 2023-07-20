package com.mykaneki.pureadminback.controller;

import com.mykaneki.pureadminback.pojo.PageBean;
import com.mykaneki.pureadminback.pojo.Result;
import com.mykaneki.pureadminback.pojo.TableData;
import com.mykaneki.pureadminback.service.TableDataService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RestController
@CrossOrigin // 添加CORS支持
@RequestMapping("/api")
public class TableDataController {

    @Resource
    TableDataService tableDataService;
    @Resource
    HttpServletRequest request;
    @Resource
    private ApplicationContext applicationContext;


    @GetMapping("/tableData")
    public Result page(@RequestParam(defaultValue = "1") Integer currentPage,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        System.out.println("分页查询 -->  currentPage: " + currentPage + ", pageSize: " + pageSize);
        PageBean pageBean = tableDataService.page(currentPage, pageSize);
        return Result.success(pageBean);
    }

    @PutMapping("/editRow/{id}")
    public Result editRow(@PathVariable String id, @RequestBody TableData data) {
        // System.out.println("editRow --> id: " + id + ", data: " + data);
        // System.out.println("调用api ： " + request.getRequestURL());
        // 返回受影响的行数
        Integer count = tableDataService.editRow(id, data);
        return Result.success(count);
    }

    @DeleteMapping("/deleteRow/{id}")
    public Result deleteRow(@PathVariable String id) {
        System.out.println("deleteRow -> " + id);
        Integer count = tableDataService.deleteRow(id);
        return Result.success(count);
    }

    @PostMapping("/upload")
    public Result handleFileUpload(@RequestParam("file") MultipartFile file) {
        return tableDataService.uploadFileProcess(file);
    }

    // @GetMapping(value = "/image/{imageName}")
    // public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
    //     ResponseEntity<byte[]> response = null;
    //     try {
    //         return tableDataService.getImage(imageName);
    //     } catch (IOException e) {
    //         System.out.println("图片不存在：" + e.getMessage());
    //     }
    //     return null;
    // }

    @GetMapping(value = "/image/{imageName}")
    public ResponseEntity<byte[]> getFile(@PathVariable String imageName) {
        ResponseEntity<byte[]> response = null;
        try {
            String fileExtension = imageName.substring(imageName.lastIndexOf("."));
            if (isImage(fileExtension)) {
                return tableDataService.getImage(imageName);
            } else if (isVideo(fileExtension)) {
                return tableDataService.getVideo(imageName);
            } else {
                throw new IOException("Unsupported file type");
            }
        } catch (IOException e) {
            System.out.println("文件不存在：" + e.getMessage());
        }
        return null;
    }

    private boolean isImage(String fileExtension) {
        String[] imageExtensions = {".jpg", ".jpeg", ".png", ".gif", ".bmp", ".webp"};
        return Arrays.asList(imageExtensions).contains(fileExtension.toLowerCase());
    }

    private boolean isVideo(String fileExtension) {
        String[] videoExtensions = {".mp4", ".avi", ".mkv", ".mov", ".flv", ".wmv"};
        return Arrays.asList(videoExtensions).contains(fileExtension.toLowerCase());
    }

    @PostMapping("/form")
    public Result handleFormSubmit(@RequestBody TableData form) {
        // System.out.println("调用api ： " + request.getRequestURL());
        System.out.println(form);
        // {"code":0,"msg":"success","data":{"id":null,"name":"123","gender":"","age":null,"idCard":"","video":"","image":""}}
        Integer insertId = tableDataService.insert(form);
        return Result.success(insertId);
    }
}
