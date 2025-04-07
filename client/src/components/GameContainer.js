import React, { useState, useEffect } from 'react';
import { useParams , useNavigate, Link} from 'react-router-dom';
import axios from 'axios';

const GameContainer = ({ user, setUser }) => {
  const { id } = useParams();
  const [gameName, setGameName] = useState("");
  const [minBet, setMinBet] = useState(0);
  const [maxBet, setMaxBet] = useState(0);
  const [balance, setBalance] = useState(null);
  const [gameResult, setGameResult] = useState("");
  const [message, setMessage] = useState("");
  const username = user ? user.username : null;
  const [loading, setLoading] = useState(true);
  let navigate = useNavigate();

  const betOptions = [1, 3, 5, 10];

  // load game data
  useEffect(() => {
    setLoading(true);
    axios.get(`http://localhost:8080/api/games/${id}`)
    .then(response => {
      setGameName(response.data.name);
      setMinBet(response.data.minBet);
      setMaxBet(response.data.maxBet);
    })
    .catch(err => {
      console.error(err);
    });
  }, [id]);

  // load player balance data, once its fetched loading is set to false to persist game data
  useEffect(() => {
    if (username) {
      axios.get(`http://localhost:8080/api/players/balance/${username}`)
        .then(response => {
          setBalance(response.data.balance);
          setLoading(false); // Once data is fetched, set loading to false
        })
        .catch(err => {
          console.error(err);
        });
    }
  }, [username, balance]);


  const handleLogout = () => {
    // Clear user data from localStorage
    localStorage.removeItem('user');
    setUser(null);
    navigate('/login');
  };


  // handle betting logic 
  const handleBet = async (amount) => {
    try {
      const response = await axios.post("http://localhost:8080/api/players/place", {
        username: username,
        betAmount: amount,
        gamename: gameName,
      });

      if (response.data.success) {
        setBalance(response.data.balance);
        setGameResult(response.data.message);
        setMessage("");
      } else {
        setGameResult("");
        if (response.data.type) {
          setMessage(response.data.message);
        } else {
          alert(response.data.message);
        }
      }
    } catch (err) {
      setMessage('Error occurred while placing the bet: ', err);
      console.error(err);
    }
};


// shows loading state while waiting for real data that has already been fetched 
if (loading) {
  return (
    <div className="spinner-container">
      <div className="spinner"></div>
    </div>
  );
}

return (
  <div
    style={{
      height: '100vh',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
    }}
  >
    <div style={{ textAlign: 'center' }}>
      <div>
        <img src={require(`../images/gamecontainer.jpg`)} alt={gameName} style={{ width: '100%', height: 'auto', borderRadius: '8px' }} />
      </div>
      <h1 style={{ color: 'black' }}>{gameName}</h1>
      <div>
        <p style={{ color: 'black' }}>
          <strong>Balance: {balance}€</strong>
        </p>
      </div>

      <div>
        <p style={{color: 'black'}}><strong>Min Bet: {minBet}€ / Max Bet: {maxBet}€</strong></p>
      </div>

      <div>
        <p style={{ color: 'black' }}><strong>Place your bet:</strong></p>
        <div>
          {betOptions.map((bet) => {
              const buttonColors = {
                1: 'red',
                3: 'blue',
                5: 'green',
                10: 'black'
             };
          return (
            <button
              key={bet}
              onClick={() => handleBet(bet)}
              style={{
                backgroundColor: buttonColors[bet],
                color: 'white',
                fontSize: '20px',
                padding: '15px 30px',
                borderRadius: '10px',
                border: 'none',
                cursor: 'pointer'
              }}
            >
              ${bet}
            </button>
          );
        })}
            </div>
          </div>

      {gameResult && (
        <div>
          <p style={{ color: 'darkblue', fontSize: '20px'}}><strong >{gameResult}</strong></p>
        </div>
      )}

      {message && (
        <div>
          <p style={{ color: 'darkorange', fontSize: '20px'}}><strong>{message}</strong></p>
        </div>
      )}
       <Link to="/games" >
          <button style={{
            fontSize: '15px',
            marginTop: '20px',
            backgroundColor: '#333',
            color: 'white',
            border: 'none',
            borderRadius: '6px',
            cursor: 'pointer'
            }}>Game Library</button></Link>
          <div> 
          <button onClick={handleLogout} style={{
                fontSize: '15px',
                marginTop: '10px',
                backgroundColor: '#333',
                color: 'white',
                border: 'none',
                borderRadius: '6px',
                cursor: 'pointer'
            }}>Log Out</button>
          </div>
    </div>
  </div>
);
};


export default GameContainer;
