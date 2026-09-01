
import axios from "axios";

const API_URL = import.meta.env.VITE_API_URL;

export const createRazorPayOrder = async (data) => {
    return await axios.post(
        `${API_URL}/payments/create-order`,
        data,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`
            }
        }
    );
};

export const verifyPayment = async (paymentData) => {
    return await axios.post(
        `${API_URL}/payments/verify`,
        paymentData,
        {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`
            }
        }
    );
};
