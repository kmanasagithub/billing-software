
import './App.css'
import ManageCategory from './pages/ManageCategory/ManageCategory.jsx'
import ManageItems from './pages/ManageItems/ManageItems.jsx'
import ManageUsers from './pages/ManageUsers/ManageUsers.jsx'
import Explore from './pages/Explore/Explore.jsx'
import Menubar from './components/Menubar/Menubar'
import { Navigate, Route,Routes, useLocation } from 'react-router-dom'
import Dashboard from './pages/Dashboard/Dashboard.jsx'
import { Toaster } from 'react-hot-toast'
import Login from './pages/Login/Login.jsx'
import OrderHistory from './components/OrderHistory/OrderHistory.jsx'
import { useContext } from 'react'
import { AppContext } from './context/AppContext.jsx'
import NotFound from './pages/NotFound/NotFound.jsx'

function App() {

  const location = useLocation();
  const {auth} = useContext(AppContext);

  const LoginRoute = ({element}) => {
    if(auth.token) {
      return <Navigate to="/dashboard" replace/>;
    }
    return element;
  }

  const ProtectedRoute = ({element,alloweRoles}) => {
    if(!auth.token) {
      return <Navigate to="/login" replace/>;
    }

    if(alloweRoles && !alloweRoles.includes(auth.role)) {
      return <Navigate to="/dashboard" replace/>;
    }
    return element;
  }

  return (
    <div>
      {location.pathname !== "/login" && <Menubar/>}
      <Toaster/>
      <Routes>
        <Route path="/dashboard" element={<Dashboard/>}></Route>
        <Route path="/explore" element={<Explore/>}></Route>

        {/* Admin only routes */}
        <Route path="/category" element={<ProtectedRoute element={<ManageCategory/>} alloweRoles={['ROLE_ADMIN']}/>}></Route>
        <Route path="/users" element={<ProtectedRoute element={<ManageUsers/>} alloweRoles={['ROLE_ADMIN']}/>}></Route>
        <Route path="/items" element={<ProtectedRoute element={<ManageItems/>} alloweRoles={['ROLE_ADMIN']}/>}></Route>


        <Route path="/orders" element={<OrderHistory/>}></Route>
        <Route path='/login' element={<LoginRoute element={<Login/>} />}></Route>
        <Route path='/' element={<Dashboard/>}></Route>
        <Route path='*' element={<NotFound/>}></Route>
      </Routes>
    </div>
  )
}

export default App
