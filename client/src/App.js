// import React, { useState } from 'react';
// import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LandingPage from './components/LandingPage';
import LoginPage from './components/LoginPage';
import GameLibrary from './components/GameLibrary';
// import GameContainer from './components/GameContainer';
import SignupPage from './components/SignupPage';

const App = () => {
  // const [username, setUsername] = useState(null);

  return (
    <main className="p-4 max-w-lg mx-auto">
      <Router>
        <Routes>
          <Route path="/" element={<LandingPage />}></Route>
          <Route path="/login" element={<LoginPage />}></Route>
          <Route path="/signup" element={<SignupPage />}></Route>
          <Route path="/games" element={<GameLibrary />}></Route>
          {/* <Route path="/game/:gameId" element={username ? <GameContainer username={username} /> : <Navigate to="/login" />} /> */}
        </Routes>
      </Router> 
    </main>
  );
};

export default App;
