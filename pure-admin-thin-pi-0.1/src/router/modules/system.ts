// 最简代码，也就是这些字段必须有
export default {
  path: "/system",
  meta: {
    title: "系统管理",
    icon: "ant-design:setting-filled"
  },
  children: [
    {
      path: "/system",
      name: "SystemManage",
      component: () => import("@/views/system/index.vue"),
      meta: {
        title: "系统管理"
      }
    }
  ]
};
