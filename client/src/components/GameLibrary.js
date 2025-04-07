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
  };

  const filteredGames = games.filter((game) => 
    game.name.toLowerCase().includes(search.toLowerCase())
  );

  const visibleGames = filteredGames.slice(0, visibleCount);

 return (
  <div>
      <h2>Game Library</h2>
      <input type="text" placeholder="Search games" value={search} onChange={(e) => {
        setSearch(e.target.value);
        setVisibleCount(8);
        }} 
       />
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
          <button onClick={() => setVisibleCount((prev) => prev + 8)}>See More</button>
        </div>)}
    </div>
);
};


export default GameLibrary;
