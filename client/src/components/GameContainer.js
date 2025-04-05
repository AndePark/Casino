import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

const GameContainer = ({ user }) => {
  const { id } = useParams();
  const [game, setGame] = useState(null);
  const [balance, setBalance] = useState(100);
  const [betAmount, setBetAmount] = useState(0);
  const [gameResult, setGameResult] = useState(null);
  const [message, setMessage] = useState("");

  const betOptions = [1, 3, 5, 10];

  useEffect(() => {
    axios.get(`http://localhost:8080/api/games/${id}`)
    .then(response => {
      setGame(response.data);
    })
    .catch(err => {
      console.error(err);
    });
  }, [id, user])


  const handleBet = async (amount) => {
    if (amount > user.balance) {
      alert('Insufficient balance for this bet');
      return;
    }

  try {
    const response = await axios.post("http://localhost:8080/api/bets/place", {
      username: user.username,
      betAmount: amount,
    });

    if (response.data.success) {
      setBalance(response.data.newBalance);
      setGameResult(response.data.message);
    } else {
      setMessage(response.data.message);
    }
  } catch (err) {
    setMessage('Error occurred while placing the bet');
  }
};

return (
  <div>
    <div>
      <img src={require(`../images/games.jpg`)} alt='game' />
    </div>
    <div className="balance mt-4">
        <p><strong>Balance: ${balance}</strong></p>
      </div>

      <div className="bet-options mt-4">
        <p>Place your bet:</p>
        <div className="bet-buttons">
          {betOptions.map((bet) => (
            <button
              key={bet}
              onClick={() => handleBet(bet)}
              className="p-2 m-2 bg-blue-500 text-white rounded"
            >
              ${bet}
            </button>
          ))}
        </div>
      </div>

      {gameResult && (
        <div className="game-result mt-4">
          <p>{gameResult}</p>
        </div>
      )}

      {message && (
        <div className="message mt-4 text-red-500">
          <p>{message}</p>
        </div>
      )}
  </div>
)
};


export default GameContainer;
