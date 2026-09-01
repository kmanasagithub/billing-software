import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

export const addUser = async (user) => {
    return await axios.post(
        `${API_URL}/admin/register`,
        user,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`
            }
        }
    );
};

export const deleteUser = async (id) => {
    return await axios.delete(
        `${API_URL}/admin/users/${id}`,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`
            }
        }
    );
};

export const fetchUsers = async () => {
    return await axios.get(
        `${API_URL}/admin/users`,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`
            }
        }
    );
};
