import "./ManageUsers.css"
import UsersForm from "../../components/UsersForm/UsersForm.jsx";
import UsersList from "../../components/UsersList/UsersList.jsx";
import { useEffect, useState } from "react";
import { fetchUsers } from "../../Service/UserService.js";
import toast from "react-hot-toast";

const ManageUsers = () => {
    const [users,setUsers] = useState([]);
    const [loading,setLoading] = useState(false);

    useEffect(() => {
        async function loadUsers() {
            try {
                setLoading(true);
                const res = await fetchUsers();
                setUsers(res.data);
            }
            catch(error) {
                console.error(error);
                toast.error("Inable to fetch users");
            }
            finally {
                setLoading(false);
            }
        }
        loadUsers();
    },[]);

    return (
        <div className='users-container text-light'>
            <div className="left-column">
                <UsersForm setUsers = {setUsers}/>
            </div>
            <div className="right-column">
                <UsersList users={users} setUsers = {setUsers}/>
            </div>
        </div>
    )
}

export default ManageUsers;
