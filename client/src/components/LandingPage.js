import React from 'react';
import {  Link  } from 'react-router-dom';
import { FaUser } from 'react-icons/fa';

const LandingPage = () => (
  <div className="p-4 text-center">
    <h1 className="text-2xl font-bold">Welcome to Mini Casino</h1>
    <Link to="/login" className="p-2 bg-blue-500 text-white rounded mt-4 inline-block">
      <FaUser className="inline mr-2" /> Login / Sign Up
    </Link>
    <Link to="/games" className="p-2 bg-green-500 text-white rounded mt-4 inline-block">
      🎮 Enter Game Library
    </Link>
  </div>
);

export default LandingPage;
