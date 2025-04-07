import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const LoginPage = ({setUser}) => {
  let navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");


  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/players/login", {username, password}, {
        headers: { "Content-Type": "application/json" },
      });

      if (response.data.success) {
        const user = {username, password};
        localStorage.setItem('user', JSON.stringify(user));
        setUser(user);
        navigate('/');
      } else {
        setError('Invalid username or password');
      }
    } catch (err) {
      setError('Login failed. Try again');
    }
  };


  return (
    <div style={{
      height: '100vh',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center'
    }}>
      <div style={{ textAlign: 'center' }}>
        <h2 style={{
          marginBottom: '20px',
          color: 'black',
          fontSize: '28px',
        }}>LOGIN</h2>

        <form onSubmit={handleLogin} style={{
          display: 'flex',
          flexDirection: 'column',
          gap: '12px',
          alignItems: 'center'
        }}>
          <input
            type="text"
            placeholder="User Name"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            style={{ padding: '10px', fontSize: '16px', width: '250px' }}
          />

          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            style={{ padding: '10px', fontSize: '16px', width: '250px' }}
          />

          <button type="submit" style={{ padding: '10px', fontSize: '16px' }}>
            Login
          </button>
        </form>

        {error && (
          <div style={{ color: 'red', fontSize: '25px' }}>
            {error}.
            <div style={{ marginTop: '10px' }}>
              <Link to="/signup" style={{ color: 'darkblue', fontSize: '20px' }}>
                Create an Account
              </Link>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default LoginPage;
