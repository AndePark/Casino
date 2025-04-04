import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
// import Search from './Search';

const GameLibrary = () => {
  const [games, setGames] = useState([]);
  const [search, setSearch] = useState("");
  const [visibleCount, setVisibleCount] = useState(8);

  useEffect(() => {
    loadGames();
  },[]);


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
      // console.log(response.data[0].name);
      console.log(response.data);
      console.log(response);
      console.log(games);
      // console.log(Object.values(games));
    
  };

  // return (
  //   <section>
  //     <Search 
  //       search = {search}
  //       setSearch={setSearch}
  //       />
  //     <table>
  //       <thead>
  //         <tr>
  //           <th>Names of All Games</th>
  //       </tr>
  //       </thead>

  //     <tbody>
  //       {games.map((game, index) => (
  //         <tr key={game.id}>
  //           <th scope='row' key={index}> {index + 1} </th>
  //         {/* <td>ID: {game.id}</td> */}
  //         <td>Name: {game.name}</td>
  //         <td>Chance of Winning: {game.chanceOfWinning}</td>
  //         <td>Multiplier: {game.multiplier}</td>
  //         <td>Max Bet: {game.maxBet}</td>
  //         <td>Min Bet: {game.minBet}</td>
  //         <td>
  //         <Link to={"/games"}></Link>
  //         </td>
      
  //           </tr>
  //       ))}
  //       </tbody>
  //       </table>
  //   </section>
  // );

 return (
  <div className="p-4">
      <h2 className="text-xl font-bold">Game Library</h2>
      <input type="text" placeholder="Search games" className="p-2 border rounded w-full mb-2" value={search} onChange={(e) => setSearch(e.target.value)} />
      <div className="grid grid-cols-4 gap-4">
        {games.filter(game => game.name.toLowerCase().includes(search.toLowerCase())).slice(0, visibleCount).map(game => (
          <Link key={game.id} to={`/game/${game.id}`} className="p-4 border rounded bg-gray-100 text-center">
            <img src={game.image} alt={game.name} className="w-full h-24 object-cover" />
          </Link>
        ))}
      </div>
      {visibleCount < games.length && <button onClick={() => setVisibleCount(visibleCount + 8)} className="mt-4 bg-blue-500 text-white p-2 rounded">See More</button>}
    </div>
);
};


export default GameLibrary;
