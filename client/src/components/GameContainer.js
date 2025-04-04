// import React, { useState } from 'react';
// import { useParams } from 'react-router-dom';

// const GameContainer = ({ username }) => {
//   const { gameId } = useParams();
//   const [betAmount, setBetAmount] = useState("");
//   const [message, setMessage] = useState("");

//   const placeBet = () => {
//     fetch("/api/bets/place", {
//       method: "POST",
//       headers: { "Content-Type": "application/json" },
//       body: JSON.stringify({ username, gameId, betAmount }),
//     })
//       .then((res) => res.json())
//       .then((data) => setMessage(data.message));
//   };

//   return (
//     <div className="p-4 text-center">
//       <h2 className="text-xl font-bold">Place Your Bet</h2>
//       <input type="number" placeholder="Bet Amount" className="p-2 border rounded w-full mt-2" value={betAmount} onChange={(e) => setBetAmount(e.target.value)} />
//       <button className="p-2 bg-red-500 text-white rounded mt-2 w-full" onClick={placeBet}>Place Bet</button>
//       {message && <p className="mt-4 text-lg font-bold">{message}</p>}
//     </div>
//   );
// };


// export default GameContainer;
