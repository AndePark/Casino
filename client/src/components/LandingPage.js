import React from 'react';
import { Link, useNavigate} from 'react-router-dom';
import { FaUser } from 'react-icons/fa';



const LandingPage = ({ user }) => {
  let navigate = useNavigate();


  return (
    <div>
     {user ? (
      <>
      <h2>Welcome back, {user.username}</h2>
        <Link to="/games">
          <FaUser/>
            Go To Games Library</Link>
      </>
     ) : (
      <div>
        <h2>Welcome to the Mini Casino</h2>
        <Link to="/login">
          <FaUser /> Login / Sign Up
        </Link>
      </div>
     )}
    </div>
  );
};

export default LandingPage;
