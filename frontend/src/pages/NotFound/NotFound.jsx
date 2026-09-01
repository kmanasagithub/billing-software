import { useNavigate } from 'react-router-dom';
import './NotFound.css'

const NotFound = () => {

    const navigate = useNavigate();
    
  return (
    <div className='not-found-container'>
      <div className="not-fonund-content">
        <h1 className="not-found-title">404</h1>
        <h2 className="not-found-subtitle">Oops ! Page not Found</h2>
        <p className='not-found-message'>
            The page you're looking for  doesn't exist or had been moved.
        </p>
        <button className='not-found-button' onClick={() => navigate("/")}>
            Go to Homepage
        </button>
      </div>
    </div>
  )
}

export default NotFound;
