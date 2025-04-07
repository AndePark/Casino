import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const SignupPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [repeatPassword, setRepeatPassword] = useState("");
    const [birthdate, setBirthdate] = useState("");
    const [error, setError] = useState(null);
    const navigate = useNavigate();
  
    // const handleSignup = () => {
    //   fetch("/api/players/signup", {
    //     method: "POST",
    //     headers: { "Content-Type": "application/json" },
    //     body: JSON.stringify({ username, password, repeatPassword, birthdate }),
    //   })
    //     .then((res) => res.json())
    //     .then((data) => {
    //       if (data.success) {
    //         navigate("/games");
    //       } else {
    //         setError(data.message);
    //       }
    //     });
    // };

    // const isValidPassword = (pw) =>
    //   pw.length >= 5 &&
    //   /[A-Za-z]/.test(pw) &&
    //   /\d/.test(pw) &&
    //   /[^A-Za-z0-9]/.test(pw);
  
    // const isAdult = (birthdateStr) => {
    //   const birth = new Date(birthdateStr);
    //   const today = new Date();
    //   const age = today.getFullYear() - birth.getFullYear();
    //   const m = today.getMonth() - birth.getMonth();
    //   return age > 18 || (age === 18 && m >= 0);
    // };
  
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
          // setUser(res.data.player);
          navigate("/login");
        } else {
          setError(res.data.message || 'Signup failed.');
        }
      } catch (err) {
        setError('Failed. Please try again.');
      }
    };
  
    return (
      <div>
        <h2>Sign Up</h2>
        <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
        <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
        <input type="password" placeholder='Repeat Password' value={repeatPassword} onChange={(e) => setRepeatPassword(e.target.value)} />
        <input type="date" value={birthdate} onChange={(e) => setBirthdate(e.target.value)} />
        <button onClick={handleSignup}>Sign Up</button>
        {error && <p>{error}</p>}
      </div>
    );
  };
  
  export default SignupPage;