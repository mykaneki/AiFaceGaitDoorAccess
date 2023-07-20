import axios from "axios";

const instance = axios.create({
  // baseURL: "http://10.100.157.62:8080/api/",
  baseURL: "http://localhost:8080/api/",
  timeout: 10000,
  headers: {}
});

// 添加响应拦截器
instance.interceptors.response.use(
  function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    const data = response.data;
    if (data.code === 0) {
      return data.data;
    }
    console.error("request error", data);
    return response.data;
  },
  function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
  }
);
// 在你的 axios 实例文件中添加 login 函数
export const login = async (username: string, password: string) => {
  const response = await instance.post("login", null, {
    params: {
      username,
      password
    }
  });
  console.log("login response", response);
  return response;
};

export default instance;
