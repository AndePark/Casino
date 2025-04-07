import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const SignupPage = ({setUser}) => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [repeatPassword, setRepeatPassword] = useState("");
    const [birthdate, setBirthdate] = useState("");
    const [error, setError] = useState(null);
    const navigate = useNavigate();
  
    const handleSignup = async (e) => {
      e.preventDefault();
      try {
        const res = await axios.post('http://localhost:8080/api/players/signup', {
          username,
          password,
          repeatPassword,
          birthdate
        });
  
        if (res.data.success) {
          setUser(res.data.player);
          navigate("/login");
        } else {
          setError(res.data.message || 'Signup failed.');
        }
      } catch (err) {
        setError('Failed. Please try again.');
      }
    };
  
  return (
    <div style={{
      height: '100vh',
      display: 'flex',
      flexDirection: 'column',
      justifyContent: 'center',
      alignItems: 'center',
      textAlign: 'center',
      padding: '20px',
    }}>
      <h2 style={{ marginBottom: '20px' }}>Sign Up</h2>
      <form onSubmit={handleSignup} style={{ display: 'flex', flexDirection: 'column', gap: '12px', width: '300px' }}>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          style={{ padding: '10px', fontSize: '16px' }}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          style={{ padding: '10px', fontSize: '16px' }}
        />
        <input
          type="password"
          placeholder="Repeat Password"
          value={repeatPassword}
          onChange={(e) => setRepeatPassword(e.target.value)}
          style={{ padding: '10px', fontSize: '16px' }}
        />
        <input
          type="date"
          value={birthdate}
          onChange={(e) => setBirthdate(e.target.value)}
          style={{ padding: '10px', fontSize: '16px' }}
        />
        <button
          type="submit"
          style={{ padding: '10px 20px', fontSize: '16px', marginTop: '12px' }}
        >
          Sign Up
        </button>
      </form>

      {error && <p style={{ color: 'red', fontSize: '26px' }}>{error}</p>}
    </div>
  );
};
  
  export default SignupPage;