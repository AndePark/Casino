import React, { useState } from 'react';
// import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LandingPage from './components/LandingPage';
import LoginPage from './components/LoginPage';
import GameLibrary from './components/GameLibrary';
import GameContainer from './components/GameContainer';
import SignupPage from './components/SignupPage';
import bg from './images/bg.png'

const App = () => {
  const [user, setUser] = useState(null);



  return (
    <main style={{
      backgroundImage: `url(${bg})`, backgroundSize: 'cover', backgroundRepeat: 'no-repeat',
      backgroundPosition: 'center', width: '100vw', height: '100vh'
    }}>
      <Router>
        <Routes>
          <Route path='/' element={<LandingPage user={user} setUser={setUser} />}></Route>
          <Route path="/login" element={<LoginPage setUser={setUser} />}></Route>
          <Route path="/signup" element={<SignupPage />}></Route>
          <Route path="/games" element={<GameLibrary />}></Route>
          <Route path="/games/:id" element={<GameContainer user={user} />}></Route>
        </Routes>
      </Router> 
    </main>
  );
};

export default App;
