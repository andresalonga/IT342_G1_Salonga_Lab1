import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth';

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Register a new user
export const register = async (email, password, firstName = '', lastName = '') => {
  try {
    const response = await api.post('/register', {
      email,
      password,
      firstName,
      lastName,
    });
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

// Login user
export const login = async (email, password) => {
  try {
    const response = await api.post('/login', {
      email,
      password,
    });
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

// Get current user profile (if needed)
export const getProfile = async (token) => {
  try {
    const response = await api.get('/me', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

const authService = {
  register,
  login,
  getProfile,
};

export default authService;
