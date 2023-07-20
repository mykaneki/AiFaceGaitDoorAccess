<script setup lang="ts">
import { onMounted, reactive, ref } from "vue";
import myAxios from "@/plugins/myAxios";
import { PaginationProps } from "@pureadmin/table";
import { ElMessage } from "element-plus";
import { InfoFilled } from "@element-plus/icons-vue";

const pagination = reactive<PaginationProps>({
  pageSize: 5,
  currentPage: 2,
  total: 25,
  align: "center",
  background: true,
  small: false
});
const tableData = ref([]);
const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`);
  pagination.pageSize = val;
  getData();
};
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`);
  pagination.currentPage = val;
  getData();
};

async function getData() {
  const params = new URLSearchParams();
  params.append("currentPage", pagination.currentPage.toString());
  params.append("pageSize", pagination.pageSize.toString());
  myAxios
    .get("/tableData", {
      params
    })
    .then((res: any) => {
      tableData.value = res.rows;
      pagination.total = res.total;
      console.log(res);
    });
}

const handleDelete = async (index: number, row: any) => {
  // Add your code here to handle deleting the row
  console.log(`Deleting row ${index}`);
  try {
    const response = await myAxios.delete(`/deleteRow/${row.id}`);
    if (response === 0) {
      const msg = "删除失败，该用户不存在";
      openWarning(msg);
    } else {
      const msg = "删除成功，共删除" + response + "条数据";
      openSuccess(msg);
    }
    getData();
    console.log(response);
  } catch (error) {
    openError(error);
    console.error(error);
  }
};

const openSuccess = (msg: string) => {
  ElMessage({
    showClose: true,
    message: msg,
    type: "success"
  });
};
const openWarning = (msg: string) => {
  ElMessage({
    showClose: true,
    message: msg,
    type: "warning"
  });
};
const openError = (msg: string) => {
  ElMessage({
    showClose: true,
    message: msg,
    type: "error"
  });
};
const confirmEvent = (index: number, row: any) => {
  handleDelete(index, row);
  console.log("confirm!");
};
const cancelEvent = () => {
  console.log("cancel!");
};

onMounted(() => {
  getData();
});
</script>

<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="id" label="序号" align="center" width="80" />
    <el-table-column prop="name" label="姓名" align="center" width="80" />
    <el-table-column prop="gender" label="性别" align="center" width="80" />
    <el-table-column prop="age" label="年龄" align="center" width="80" />
    <el-table-column prop="idCard" label="身份证" align="center" width="250" />
    <el-table-column prop="image" label="人脸图片" align="center" width="120">
      <template #default="{ row }">
        <div style="display: flex; align-items: center">
          <el-image :src="row.image" />
        </div>
      </template>
    </el-table-column>
    <el-table-column prop="video" label="步态视频" align="center">
      <template #default="{ row }">
        <div style="display: flex; align-items: center">
          <video
            :src="row.video"
            controls
            controlsList="nodownload"
            preload="metadata"
          >
            您的浏览器不支持视频标签。
          </video>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="操作" align="center">
      <template #default="scope">
        <el-popconfirm
          confirm-button-text="是的，我确定"
          cancel-button-text="不，谢谢"
          :icon="InfoFilled"
          icon-color="#626AEF"
          title="您真的要删除这条信息吗？"
          @cancel="cancelEvent"
          @confirm="confirmEvent(scope.$index, scope.row)"
        >
          <template #reference>
            <el-button size="small" type="danger">删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <div class="pagination-container">
    <el-pagination small: true layout="total, prev, pager, next"
    v-model:current-page="pagination.currentPage"
    :page-size="pagination.pageSize" :total="pagination.total"
    @current-change="handleCurrentChange" @size-change="handleSizeChange" />
  </div>
</template>
<style scoped>
.pagination-container {
  display: flex;
  justify-content: center;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
