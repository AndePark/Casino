import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../App.css'; 



const GameLibrary = ({ user, setUser }) => {
  const [games, setGames] = useState([]);
  const [search, setSearch] = useState("");
  const [deposit, setDeposit] = useState("");
  const [visibleCount, setVisibleCount] = useState(8);
  let navigate = useNavigate();


  useEffect(() => {
    loadGames();
  },[]);


  const handleLogout = () => {
    // Clear user data from localStorage
    localStorage.removeItem('user');
    setUser(null);
    navigate('/login');
  };



  const loadGames = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/games", {
        validateStatus: () => {
          return true;
        },
      });
      if (response.status === 200) {
        setGames(response.data);
      }
  };

  const filteredGames = games.filter((game) => 
    game.name.toLowerCase().includes(search.toLowerCase())
  );

  const visibleGames = filteredGames.slice(0, visibleCount);

  const handleDeposit = async (deposit) => {
    try {
      const response = await axios.post("http://localhost:8080/api/players/deposit", {
        username: user.username,
        deposit: deposit,
      });
  
      if (response.data.success) {
        alert(`${response.data.message}\nNew Balance: ${response.data.balance}€`);
      } else {
        alert(response.data.message);
      }
    } catch (err) {
      console.error(err);
    }
  };
  

 return (
  <div>
    <h1 style={{
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      fontSize: '40px'
    }}> {user ? "Welcome," + user.username.charAt(0).toUpperCase() + user.username.slice(1) : 'Loading...'}</h1>
      <h2 style={{
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      fontSize: '40px'
    }}>Game Library</h2>
     <div style={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        gap: '10px', 
        marginBottom: '20px'
     }}>
      <input type="text" placeholder="Search games" value={search} onChange={(e) => {
        setSearch(e.target.value);
        setVisibleCount(8);
        }} 
        style={{ padding: '10px', fontSize: '16px', width: '250px', borderRadius: '8px' }}
       />

      <input 
        type="text" 
        placeholder="Enter deposit amount" 
        value={deposit} 
        onChange={(e) => setDeposit(e.target.value)} 
        style={{ padding: '10px', fontSize: '16px', width: '250px', borderRadius: '8px'}}
      />    
      
      <button type='submit' key={deposit} onClick={() => handleDeposit(deposit)} 
        style={{
          fontSize: '20px',
          backgroundColor: '#333',
          color: 'white',
          border: 'none',
          borderRadius: '8px',
          cursor: 'pointer'
          }}>Deposit</button>

        <div> 
          <button onClick={handleLogout} style={{
                fontSize: '20px',
                backgroundColor: '#333',
                color: 'white',
                border: 'none',
                borderRadius: '8px',
                cursor: 'pointer'
            }}>Log Out</button>
          </div>
          </div>
        
      <div className="container" >
        {visibleGames.map((game) => (
          <Link key={game.id} to={`/games/${game.id}`}>
            <div className='image-wrapper'>
              <img src={require(`../images/game.jpg`)} alt={game.name} style={{  objectFit: 'cover', borderRadius: '8px' }}/>
              <h1 className='image-text'>{game.name}</h1>
            </div>
          </Link>
        ))}
      </div>

      {visibleCount < filteredGames.length && (
        <div>
          <button onClick={() => setVisibleCount((prev) => prev + 8)} 
            style={{
              fontSize: '20px',
              backgroundColor: '#333',
              color: 'white',
              border: 'none',
              borderRadius: '10px',
              cursor: 'pointer'
            }}>See More</button>
        </div>)}
    </div>
);
};


export default GameLibrary;
