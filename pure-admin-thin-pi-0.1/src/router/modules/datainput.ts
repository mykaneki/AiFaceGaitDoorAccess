// 最简代码，也就是这些字段必须有
export default {
  path: "/datainput",
  meta: {
    title: "数据录入",
    icon: "fa-solid:address-card"
  },
  children: [
    {
      path: "/datainput",
      name: "DataInput",
      component: () => import("@/views/datainput/index.vue"),
      meta: {
        title: "数据录入"
      }
    }
  ]
};
