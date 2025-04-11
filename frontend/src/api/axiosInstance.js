import axios from 'axios';

//todo: 모든 요청 작성 완료 후 instance로 교체하기
const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000,
});

axiosInstance.interceptors.request.use(
  config => {
    const token = localStorage.getItem('accessToken');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  error => Promise.reject(error)
);

export default axiosInstance;
