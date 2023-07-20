<template>
  <el-form :model="form" label-width="120px">
    <el-form-item :label="label.name">
      <el-input v-model="form.name" />
    </el-form-item>
    <el-form-item :label="label.age">
      <el-input v-model="form.age" />
    </el-form-item>
    <el-form-item :label="label.gender">
      <el-radio-group v-model="form.gender">
        <el-radio label="男" />
        <el-radio label="女" />
      </el-radio-group>
    </el-form-item>
    <el-form-item :label="label.idCard">
      <el-input v-model="form.idCard" />
    </el-form-item>
    <el-form-item>
      <el-space direction="vertical" alignment="start" :size="30">
        <el-upload
          ref="uploadRefImg"
          class="upload-demo"
          action="http://127.0.0.1:8080/api/upload"
          :auto-upload="false"
          list-type="picture-card"
          limit="1"
          :on-exceed="handleExceed"
          :on-success="handleSuccessImage"
        >
          <template #trigger>
            <el-button type="primary">选择人脸图片</el-button>
          </template>
          <el-button class="ml-3" type="success" @click="submitUploadImg">
            上传至服务器
          </el-button>
          <template #tip>
            <div class="el-upload__tip">
              jpg/png files with a size less than 500kb
            </div>
          </template>
        </el-upload>
      </el-space>
    </el-form-item>
    <el-form-item>
      <el-space direction="vertical" alignment="start" :size="30">
        <el-upload
          ref="uploadRefVideo"
          class="upload-demo"
          action="http://127.0.0.1:8080/api/upload"
          :auto-upload="false"
          list-type="text"
          limit="1"
          :on-exceed="handleExceed"
          :on-success="handleSuccessVideo"
        >
          <template #trigger>
            <el-button type="primary">选择步态视频</el-button>
          </template>
          <el-button class="ml-3" type="success" @click="submitUploadVideo">
            上传至服务器
          </el-button>
          <template #tip>
            <div class="el-upload__tip">
              mp4/webm files with a size less than 100MB
            </div>
          </template>
        </el-upload>
      </el-space>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">提交</el-button>
      <el-button @click="resetForm">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import { reactive, ref } from "vue";
import myAxios from "@/plugins/myAxios";
import type { UploadInstance } from "element-plus";
import { ElMessage } from "element-plus";
import axios from "axios";

const uploadRefImg = ref<UploadInstance>();

const submitUploadImg = () => {
  uploadRefImg.value!.submit();
};
const uploadRefVideo = ref<UploadInstance>();

const submitUploadVideo = () => {
  uploadRefVideo.value!.submit();
};

const label = reactive({
  name: "姓名",
  age: "年龄",
  gender: "性别",
  idCard: "身份证",
  video: "步态视频",
  image: "人脸图片"
});

const form = reactive({
  name: "",
  age: "",
  gender: "",
  idCard: "",
  video: "",
  image: ""
});

const onSubmit = async () => {
  try {
    const response1 = await myAxios.post("/form", form);
    const formData = new FormData();
    const id = response1.toString();
    // const rootPath = "D:\\project\\ideaprj\\pure-admin-back\\img\\";
    const rootPath = "/home/pi/ACS_pi/data/";
    // const rootPath = "E:\\Microstar\\python\\ACS\\data\\img\\";
    const video = rootPath + form.video;
    const image = rootPath + form.image;
    // D:\project\ideaprj\pure-admin-back\img\1686295275668.mp4
    console.log("video/" + video);
    console.log("img/" + image);
    formData.append("id", id);
    formData.append("video", video);
    formData.append("image", image);
    const response2 = await axios.post(
      "http://127.0.0.1:5000/api/process",
      formData
    );
    // {"code":0,"msg":"success","data":115}
    console.log(response1);
    console.log(response2);
  } catch (error) {
    console.error(error);
  }
};

const resetForm = () => {
  form.name = "";
  form.age = "";
  form.gender = "";
  form.idCard = "";
  form.video = "";
  form.image = "";
};

const handleExceed = () => {
  openError("只能上传一张图片哦~");
};

const handleSuccessImage = response => {
  form.image = response.data;
  console.log(form.image);
};
const handleSuccessVideo = response => {
  form.video = response.data;
  console.log(form.video);
};

const openError = (msg: string) => {
  ElMessage({
    showClose: true,
    message: msg,
    type: "error"
  });
};
</script>
