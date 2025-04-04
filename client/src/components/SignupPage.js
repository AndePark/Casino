import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';

const SignupPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    // const [repeatPassword, setRepeatPassword] = useState("");
    const [birthdate, setBirthdate] = useState("");
    const [error, setError] = useState(null);
    const navigate = useNavigate();
  
    const handleSignup = () => {
      fetch("/api/players/signup", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password, birthdate }),
      })
        .then((res) => res.json())
        .then((data) => {
          if (data.success) {
            navigate("/games");
          } else {
            setError(data.message);
          }
        });
    };
  
    return (
      <div className="p-4 max-w-md mx-auto text-center">
        <h2 className="text-xl font-bold">Sign Up</h2>
        <input type="text" placeholder="Username" className="p-2 border rounded w-full mt-2" value={username} onChange={(e) => setUsername(e.target.value)} />
        <input type="password" placeholder="Password" className="p-2 border rounded w-full mt-2" value={password} onChange={(e) => setPassword(e.target.value)} />
        <input type="date" className="p-2 border rounded w-full mt-2" value={birthdate} onChange={(e) => setBirthdate(e.target.value)} />
        <button className="p-2 bg-green-500 text-white rounded mt-2 w-full" onClick={handleSignup}>Sign Up</button>
        {error && <p className="text-red-500">{error}</p>}
      </div>
    );
  };
  
  export default SignupPage;