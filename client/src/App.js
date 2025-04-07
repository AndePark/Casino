import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LandingPage from './components/LandingPage';
import LoginPage from './components/LoginPage';
import GameLibrary from './components/GameLibrary';
import GameContainer from './components/GameContainer';
import SignupPage from './components/SignupPage';
import bg from './images/bg.png'

const App = () => {
  const [user, setUser] = useState(null);

    // On initial load, check if there is a user saved in localStorage
    useEffect(() => {
      const savedUser = localStorage.getItem('user');
      if (savedUser) {
        setUser(JSON.parse(savedUser));
      }
    }, []);
  
    // Whenever the user state changes, save it to localStorage
    useEffect(() => {
      if (user) {
        localStorage.setItem('user', JSON.stringify(user));
      } else {
        localStorage.removeItem('user');
      }
    }, [user]);


  return (
    <main style={{
      backgroundImage: `url(${bg})`, backgroundSize: 'cover', backgroundRepeat: 'no-repeat',
      backgroundPosition: 'center', width: '100vw', height: '100vh'
    }}>
      <Router>
        <Routes>
          <Route path='/' element={<LandingPage user={user} setUser={setUser} />}></Route>
          <Route path="/login" element={<LoginPage setUser={setUser} />}></Route>
          <Route path="/signup" element={<SignupPage setUser={setUser}/>}></Route>
          <Route path="/games" element={<GameLibrary user={user} setUser={setUser}/>}></Route>
          <Route path="/games/:id" element={<GameContainer user={user} setUser={setUser}  />}></Route>
        </Routes>
      </Router> 
    </main>
  );
};

export default App;
