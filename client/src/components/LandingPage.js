import React, { useState, useEffect } from 'react';
import { Link , useNavigate} from 'react-router-dom';
import { FaUser } from 'react-icons/fa';



const LandingPage = ({ user, setUser }) => {
  const [localUser, setLocalUser] = useState(user);
  let navigate = useNavigate();

  useEffect(() => {
    // If user is not passed as a prop, try loading from localStorage
    if (!user) {
      const storedUser = localStorage.getItem('user');
      if (storedUser) {
        setUser(JSON.parse(storedUser));
        setLocalUser(JSON.parse(storedUser));
      }
    }
  }, [user, setUser]);

  const handleLogout = () => {
    // Remove user data from localStorage on logout
    localStorage.removeItem('user');
    setUser(null);
    setLocalUser(null);
    navigate('/'); // Navigate back to landing page after logout
  };


  return (
    <div style={{
      height: '100vh',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
    }}>
      <div style={{ textAlign: 'center' }}>
     {localUser ? (
      <>
      <h2 style={{ fontSize: '50px'}}>Welcome back, {localUser.username.charAt(0).toUpperCase() + localUser.username.slice(1)}</h2>
        <Link to="/games" style={{
                display: 'inline-flex',
                alignItems: 'center',
                gap: '8px',
                fontSize: '18px',
                color: 'black',
                textDecoration: 'none',
                padding: '10px 20px',
                border: '1px solid black',
                borderRadius: '5px',
                marginTop: '12px',
              }}>
          <FaUser />
            Go To Games Library
            
            </Link>
          <div style={{ marginBottom: '10px', padding: '10px',}}> 
            <button style={{ padding: '10px', fontSize: '16px' }} onClick={handleLogout}>Log Out</button>
          </div>
      </>
     ) : (
      <>
        <h2>Welcome to the Mini Casino</h2>
        <Link to="/login" style={{
                display: 'inline-flex',
                alignItems: 'center',
                gap: '8px',
                fontSize: '18px',
                color: 'black',
                textDecoration: 'none',
                padding: '10px 20px',
                border: '1px solid black',
                borderRadius: '5px',
                marginTop: '12px',
              }}>
          <FaUser /> Login / Sign Up
        </Link>
      </>
     )}
    </div>
    </div>
  );
};

export default LandingPage;
