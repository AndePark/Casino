
import React, { useState, useEffect } from 'react';
import { useParams , Link} from 'react-router-dom';
import axios from 'axios';

const GameContainer = ({ user }) => {
  const { id } = useParams();
  const [game, setGame] = useState(null);
  const [balance, setBalance] = useState(user.balance);
  const [gameResult, setGameResult] = useState("");
  const [message, setMessage] = useState("");
  const username = user.username;
  

  const betOptions = [1, 3, 5, 10];

  useEffect(() => {
    axios.get(`http://localhost:8080/api/games/${id}`)
    .then(response => {
      setGame(response.data.name);
    })
    .catch(err => {
      console.error(err);
    });
  }, [id]);


  const handleBet = async (amount) => {
    try {
      const response = await axios.post("http://localhost:8080/api/players/place", {
        username: username,
        betAmount: amount,
        gamename: game,
      });

      if (response.data.success) {
        setBalance(response.data.balance);
        setGameResult(response.data.message);
        setMessage("");
      } else {
        setGameResult("");
        setMessage(response.data.message);
      }
    } catch (err) {
      setMessage('Error occurred while placing the bet: ', err);
      console.error(err);
    }
};


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
        <img src={require(`../images/gamecontainer.jpg`)} alt={game} style={{  objectFit: 'cover', borderRadius: '8px' }} />
      </div>
      <h1 style={{ color: 'black' }}>{game}</h1>
      <div>
        <p style={{ color: 'black' }}>
          <strong>Balance: ${balance}</strong>
        </p>
      </div>

      <div>
        <p style={{ color: 'black' }}><strong>Place your bet:</strong></p>
        <div>
          {betOptions.map((bet) => (
            <button key={bet} onClick={() => handleBet(bet)}>
              ${bet}
            </button>
          ))}
        </div>
      </div>

      {gameResult && (
        <div>
          <p style={{ color: 'black' }}><strong>{gameResult}</strong></p>
        </div>
      )}

      {message && (
        <div>
          <p style={{ color: 'red' }}><strong>{message}</strong></p>
        </div>
      )}
       <Link to="/games"><button>Game Library</button></Link>
    </div>
  </div>
);
};


export default GameContainer;
