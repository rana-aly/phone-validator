import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import FormContainer from './components/FormContainer';
import PhoneList from './components/PhoneList';

class App extends Component {
  render() {
    return (
      <div className="col-md-6">
        <h3> Phone Validation App </h3>
        <FormContainer />
        <PhoneList/>
      </div>
    );
  }
}

export default App;
