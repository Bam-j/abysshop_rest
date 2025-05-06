import axios from 'axios';

const localBaseUrl = process.env.REACT_APP_LOCAL_API_BASE_URL;
const baseURL = process.env.REACT_APP_API_BASE_URL;

const api = axios.create({
  baseURL: `${localBaseUrl}/api`
  //baseURL: `${baseURL}/api`,
});

export default api;
