package com.mykaneki.pureadminback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mykaneki.pureadminback.mapper.TableDataMapper;
import com.mykaneki.pureadminback.pojo.PageBean;
import com.mykaneki.pureadminback.pojo.Result;
import com.mykaneki.pureadminback.pojo.TableData;
import com.mykaneki.pureadminback.service.TableDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class TableDataServiceImpl implements TableDataService {
    private final TableDataMapper tableDataMapper;

    public TableDataServiceImpl(TableDataMapper tableDataMapper) {
        this.tableDataMapper = tableDataMapper;
    }

    public PageBean page(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<TableData> tableDataList = tableDataMapper.list();
        Page<TableData> p = (Page<TableData>) tableDataList;
        return new PageBean(p.getTotal(), p.getResult());
    }

    @Override
    public Integer editRow(String id, TableData data) {
        System.out.println("editRow --> id: " + id + ", data: " + data);
        if ("男".equals(data.getGender())) {
            data.setGender("1");
        } else if ("女".equals(data.getGender())) {
            data.setGender("0");
        }
        if (data.getImage() != null && data.getImage().length() > 0) {
            String[] split = data.getImage().split("/");
            if (split.length > 0) {
                data.setImage(split[split.length - 1]);
            }
        }
        if (data.getVideo() != null && data.getVideo().length() > 0) {
            String[] split = data.getVideo().split("/");
            if (split.length > 0) {
                data.setVideo(split[split.length - 1]);
            }
        }
        return tableDataMapper.editRow(data);
    }

    @Override
    public Integer deleteRow(String id) {
        Integer idInt = Integer.parseInt(id);
        tableDataMapper.deleteRowFromBasicInfo(idInt);
        return tableDataMapper.deleteRowFromImgInfo(idInt);
    }

    @Override
    public Integer insert(TableData formData) {
        System.out.println("addRow --> formData: " + formData);
        if ("男".equals(formData.getGender())) {
            formData.setGender("1");
        } else if ("女".equals(formData.getGender())) {
            formData.setGender("0");
        }
        // 基本信息
        Integer insertId = -1;
        Integer insertId2 = -1;
        tableDataMapper.insertIntoBasicInfo(formData);
        insertId = tableDataMapper.selectMaxId();
        formData.setId(insertId);
        // 根据插入的基本信息的id插入图片信息
        tableDataMapper.insertIntoImgInfo(formData);
        insertId2 = tableDataMapper.selectMaxId();
        Integer updated = tableDataMapper.updateImageAndVideo(insertId);
        List<TableData> tableDataList = tableDataMapper.list(insertId);
        if(insertId != insertId2){
            System.out.println("数据库表ID不一致，请查看数据库");
        }
        return insertId;
    }

    @Value("${file.uploadVideoFolder}")
    private String uploadVideoFolder;
    @Value("${file.uploadImgFolder}")
    private String uploadImgFolder;

    @Override
    public Result uploadFileProcess(MultipartFile file){
        // 1. 获取到文件的名称
        String fileName = file.getOriginalFilename();
        System.out.println("获取到文件名称: " + fileName);
        // 2. 给文件一个新的名称 候选id.png
        String candiId = String.valueOf(tableDataMapper.selectMaxId() + 1);
        System.out.println("下一个插入数据的id: " + candiId);
        String newFileName = null;
        File newPath = null;
        if (fileName != null) {
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            newFileName = candiId + fileExtension;
            // 3. 根据文件类型选择文件的存储路径
            String selectedUploadFolder;
            if (isImage(fileExtension)) {
                System.out.println("文件类型为图片");
                selectedUploadFolder = uploadImgFolder;
            } else if (isVideo(fileExtension)) {
                System.out.println("文件类型为视频");
                selectedUploadFolder = uploadVideoFolder;
            } else {
                return Result.fail("Unsupported file type");
            }
            // 4. 创建文件对象
            newPath = new File(selectedUploadFolder, newFileName);
            try {
                File newFile = newPath;
                file.transferTo(newFile);
                System.out.println("文件存储在: " + newFile.getAbsolutePath());
            } catch (IOException e) {
                return Result.fail(e.getMessage());
            }
        }
        return Result.success(newFileName);
    }

    private boolean isImage(String fileExtension) {
        String[] imageExtensions = {".jpg", ".jpeg", ".png", ".gif", ".bmp", ".webp"};
        return Arrays.asList(imageExtensions).contains(fileExtension.toLowerCase());
    }

    private boolean isVideo(String fileExtension) {
        String[] videoExtensions = {".mp4", ".avi", ".mkv", ".mov", ".flv", ".wmv"};
        return Arrays.asList(videoExtensions).contains(fileExtension.toLowerCase());
    }

    @Override
    public ResponseEntity<byte[]> getImage(String imageName) throws IOException {
        String imagePath = uploadImgFolder + imageName;
        System.out.println("读取图片路径: " + imagePath);
        // 使用FileInputStream读取图片文件的内容
        byte[] imageData = Files.readAllBytes(Paths.get(imagePath));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }

    @Override
    public ResponseEntity<byte[]> getVideo(String imageName) throws IOException {
        String videoPath = uploadVideoFolder + imageName;
        System.out.println("读取图片路径: " + videoPath);
        // 使用FileInputStream读取图片文件的内容
        byte[] imageData = Files.readAllBytes(Paths.get(videoPath));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }
}
