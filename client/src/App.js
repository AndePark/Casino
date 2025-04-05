import React, { useState } from 'react';
// import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import LandingPage from './components/LandingPage';
import LoginPage from './components/LoginPage';
import GameLibrary from './components/GameLibrary';
import GameContainer from './components/GameContainer';
import SignupPage from './components/SignupPage';
import landingpagebg from './images/landingpagebg.jpg'

const App = () => {
  // const [username, setUsername] = useState(null);
  const [user, setUser] = useState(null);

  return (
    <main style={{
      backgroundImage: `url(${landingpagebg})`, backgroundSize: 'cover', width: '100vw', height: '100vh'
    }}>
      <Router>
        <Routes>
          <Route path='/' element={<LandingPage user={user} setUser={setUser} />}></Route>
          <Route path="/login" element={<LoginPage setUser={setUser} />}></Route>
          <Route path="/signup" element={<SignupPage />}></Route>
          <Route path="/games" element={<GameLibrary />}></Route>
          <Route path="/game/:id" element={<GameContainer user={user} />}></Route>
        </Routes>
      </Router> 
    </main>
  );
};

export default App;
