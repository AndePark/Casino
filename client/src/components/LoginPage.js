import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const LoginPage = ({setUser}) => {
  let navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
 

  // const [user, setUser] = useState({
  //   username: "",
  //   password: "",
  // });

  // const {
  //   username, 
  //   password,
  // } = user;
  


  // const handleInputChange = async (e) => {
  //   setUser({
  //     ...user,
  //     [e.target.name] : e.target.value,
  //   });
  // };

  const handleLogin = async (e) => {
    e.preventDefault();
    // console.log(user);
    try {
      const response = await axios.post("http://localhost:8080/api/players/login", {username, password}, {
        headers: { "Content-Type": "application/json" },
      });

      if (response.data.success) {
        setUser(response.data.player);
        navigate('/games');

      
      } else {
        setError('Invalid username or password');
      }
    } catch (err) {
      setError('Login failed. Try again');
    }
  };


  return (
    <div className="p-4 max-w-md mx-auto text-center">
      <h2 className="text-xl font-bold">Login</h2>
      <form onSubmit={handleLogin}> 
        <input 
        type = "text"
        placeholder='UserName'
        value = {username}
        onChange = {(e) => setUsername(e.target.value)}
        /> 

        <input 
        type = "text"
        placeholder='Password'
        value = {password}
        onChange={(e) => setPassword(e.target.value)}
        />
  

        <div>
      <button type="submit" className="p-2 bg-blue-500 text-white rounded mt-2 w-full">
        Login
        </button>
        </div>
        

        {/* <div>
      <Link to={"/games"}>Cancel</Link>
      </div> */}
      </form>
      {error && (
        <div>
          {error}. <Link to="/signup"> Create an Account</Link>
        </div>
      )}
    </div>
  );
};

export default LoginPage;
