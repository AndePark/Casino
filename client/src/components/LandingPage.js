import React from 'react';
import { Link, useNavigate} from 'react-router-dom';
import { FaUser } from 'react-icons/fa';
// import landingpagebg from '../images/landingpagebg.jpg'

// const LandingPage = () => (
//   <div className="p-4 text-center">
//    {/* <div style={{ display: 'flex', backgroundImage: `url(${landingpagebg})`, backgroundPosition: 'center', backgroundSize: 'cover', width: '100vw', height: '100vh'}}> */}
//     <h1 className="text-2xl font-bold">Welcome to Mini Casino</h1>
//     <Link to="/login" className="p-2 bg-blue-500 text-white rounded mt-4 inline-block">
//       <FaUser className="inline mr-2" /> Login / Sign Up
//     </Link>
//   </div>
// );


const LandingPage = ({ user, setUser }) => {
  let navigate = useNavigate();

  const handleLogout = () => {
    setUser(null);
    navigate('/login');
  };

  // useEffect(() => {
  //   if (!user) {
  //     navigate('/login');
  //     return null;
  //   }
  // },);

  return (
    <div className="p-6 max-w-3xl mx-auto text-center">
     {user ? (
      <>
      <h2>Welcome back, {user.username}</h2>
        <button
            onClick={handleLogout}
            className="mt-4 bg-red-500 text-white px-4 py-2 rounded"
          >
            Logout
        </button>
        <Link to="/games">
          <FaUser className="inline mr-2" />
            Go To Games Library</Link>
      </>
     ) : (
      <div>
        <h2>Welcome to the Mini Casino</h2>
        <Link to="/login" className="p-2 bg-blue-500 text-white rounded mt-4 inline-block">
          <FaUser className="inline mr-2" /> Login / Sign Up
        </Link>
      </div>
     )}
    </div>
  );
};

export default LandingPage;
