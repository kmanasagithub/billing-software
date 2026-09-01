import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

export const latestOrders = async () => {
    return await axios.get(
        `${API_URL}/orders/latest`,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`
            }
        }
    );
};

export const createOrder = async (order) => {
    return await axios.post(
        `${API_URL}/orders`,
        order,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`
            }
        }
    );
};

export const deleteOrder = async (id) => {
    return await axios.delete(
        `${API_URL}/orders/${id}`,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`
            }
        }
    );
};
