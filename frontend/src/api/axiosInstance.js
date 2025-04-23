import axios from "axios";

const baseURL = process.env.REACT_APP_API_BASE_URL;

const api = axios.create({
  //localBaseURL: `${LOCAL_BASE_URL}/api` 로컬 실행 테스트용
  baseURL: `${baseURL}/api`,
});

export default api;
