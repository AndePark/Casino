// import { render, screen } from '@testing-library/react';
import ReactDOM from "react-dom";
import React from "react";
import App from './App';


it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<App />, div);
  ReactDOM.unmountComponentAtNode(div);
});