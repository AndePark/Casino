import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const LoginPage = () => {
  let navigate = useNavigate();
  // const [username, setInputUsername] = useState("");
  // const [password, setPassword] = useState("");
  // const [error, setError] = useState(null);
 

  const [user, setUser] = useState({
    username: "",
    password: "",
  });

  const {
    username, 
    password,
  } = user;
  

  // const handleLogin = () => {
  //   fetch("/api/players/login", {
  //     method: "POST",
  //     headers: { "Content-Type": "application/json" },
  //     body: JSON.stringify({ username, password }),
  //   })
  //     .then((res) => res.json())
  //     .then((data) => {
  //       if (data.success) {
  //         setUsername(username);
  //         navigate("/api/games");
  //       } else {
  //         setError("Invalid username or password.");
  //       }
  //     });
  // };

  const handleInputChange = (e) => {
    setUser({
      ...user,
      [e.target.name] : e.target.value,
    });
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    console.log(user);
    await axios.post("http://localhost:8080/api/players/login", user, {
      headers: { "Content-Type": "application/json" },
    });
    navigate("/api/games");
  };

  return (
    <div className="p-4 max-w-md mx-auto text-center">
      <h2 className="text-xl font-bold">Login</h2>
      <form onSubmit={(e) => handleLogin(e)}> 
        <div>
        <label>User Name</label>
        <input 
        type = "text"
        name = "username"
        value = {username}
        onChange = {(e) => handleInputChange(e)}
        /> 
        </div>
        
        <div>
        <label>Password</label>
        <input 
        type = "text"
        name = "password"
        value = {password}
        onChange={(e) => handleInputChange(e)}
        />
        </div>

        <div>
      {/* <button type="submit" className="p-2 bg-blue-500 text-white rounded mt-2 w-full">
        Login
        </button> */}
        </div>

        <div>
      <Link to={"/games"}>Cancel</Link>
      </div>
      </form>
    </div>
  );
};

export default LoginPage;
