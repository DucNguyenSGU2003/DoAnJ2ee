
const token = 'eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJkZXZ0ZXJpYS5jb20iLCJzdWIiOiJuZ3V5ZW5BQGdtYWlsLmNvbSIsInJvbGUiOiJhZG1pbiIsImlkIjoxLCJleHAiOjE3MzI5NTMxMDEsImlhdCI6MTczMjk0OTUwMX0.Hl1ukiGGwwsSC0fUzD3rHPbwYJZx1heFS5Q9XFoL9TSk-8OBGJnKYjNyLWaqL5b_KiqI9BQawtl28uuKMykEug'
import axios from 'axios';

const BASE_URL = import.meta.env.VITE_BASE_URL || 'http://localhost:8080/UniCharity';
const axiosInstance = axios.create({
  baseURL: BASE_URL, 
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
    'Authorization': token ? `Bearer ${token}` : '', // Thêm Bearer token nếu có
 }
});





export default axiosInstance;