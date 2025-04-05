import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import '../App.css'; 
// import games from '../images/games.jpg';


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
      console.log(response.data);
      console.log(response);
      console.log(games);
  };

  const filteredGames = games.filter((game) => 
    game.name.toLowerCase().includes(search.toLowerCase())
  );

  const visibleGames = filteredGames.slice(0, visibleCount);

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
      <input type="text" placeholder="Search games" className="p-2 border rounded w-full mb-2" value={search} onChange={(e) => {
        setSearch(e.target.value);
        setVisibleCount(8);
        }} 
       />
      <div className="container" >
        {visibleGames.map((game) => (
          <Link key={game.id} to={`/game/${game.id}`} className="p-4 border rounded bg-gray-100 text-center">
            <div className='image-wrapper'>
              <img src={require(`../images/games.jpg`)} alt={game.name} />
              <h1 className='image-text'>{game.name}</h1>
            </div>
          </Link>
        ))}
      </div>

      {visibleCount < filteredGames.length && (
        <div>
          <button onClick={() => setVisibleCount((prev) => prev + 8)} className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded">See More</button>
        </div>)}
    </div>
);
};


export default GameLibrary;
