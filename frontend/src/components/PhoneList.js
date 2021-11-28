import React, { Component } from "react";
import { Link } from "react-router-dom";
import Select from './Select';

export default class PhoneList extends Component {
  constructor(props) {
    super(props);
    this.retrieveNumbers = this.retrieveNumbers.bind(this);
    this.submitSearch = this.submitSearch.bind(this);
    this.handleCountryInput = this.handleCountryInput.bind(this);
    this.handleStateInput = this.handleStateInput.bind(this);

    this.state = {
      numbers: [],
      searchCountry: 'Cameroon',
      searchState: 'VALID',
      countryNameOptions: ['Cameroon', 'Ethiopia', 'Morocco', 'Mozambique', 'Uganda'],
      stateOptions: ['VALID', 'INVALID']
    };
  }

  componentDidMount() {
    this.retrieveNumbers();
  }

  retrieveNumbers() {
    fetch('http://localhost:8888/numbers',{
        method: "GET",
        headers: {
          'Accept':  "text/plain",
          'Content-Type': 'application/json'
        },
      }).then(response => response.json())
      .then(response => {
        console.log(response);
        this.setState({
            numbers: response
        });
          console.log("Successful" + response);
        })
      .catch(e => {
        console.log(e);
      });
  }
  
  handleCountryInput(e) {
    let value = e.target.value;
    this.setState( {searchCountry :value});
    console.log(this.state);
  }

  handleStateInput(e) {
    let value = e.target.value;
    this.setState( {searchState :value});
    console.log(this.state);
}

submitSearch() {
  let url = 'http://localhost:8888/numbers/' + this.state.searchState + "/" + this.state.searchCountry;
  fetch(url ,{
        method: "GET",
        headers: {
          'Accept':   'application/json',
          'Content-Type': 'application/json'
        },
      }).then(response => response.json())
      .then(response => {
        console.log(response);
        this.setState({
            numbers: response
        });
          console.log("Successful" + response);
        })
      .catch(e => {
        console.log(e);
    });
  }

  render() {
    const { numbers } = this.state;
    return (
      <div className="list row">
        <div className="col-md-8">
        <h2>Number List</h2>
          <div className="input-group mb-3">
          <table>
            <tr>
              <th> 
                <Select 
                  name={'countryName'}
                  options = {this.state.countryNameOptions} 
                  defaultValue={this.state.countryNameOptions[0]}
                  handleChange = {this.handleCountryInput}
                  placeholder = {'Select Country'}
                  /> {/* country Selection */} </th>
              <th> 
                <Select 
                  name={'State'}
                  options = {this.state.stateOptions} 
                  defaultValue={this.state.stateOptions[0]}
                  handleChange = {this.handleStateInput}
                  Placeholder = {'Select State'}
                  /> {/* state Selection */}
              </th>
              <th>
                <span/>
                <button
                  className="btn btn-outline-secondary"
                  type="button"
                  onClick={this.submitSearch}
                >
                 Search
              </button>
              </th>
              <th>
                 <button
                  className="m-3 btn btn-sm btn-danger"
                  onClick={this.retrieveNumbers}
          >
            Refresh List
          </button>
              </th>
            </tr>
            </table>
          </div>
        </div>
        <div className="col-md-6">
          <table>
            <tr>
              <th className="col-md-8">Name</th>
              <th className="col-md-8">Phone Number</th>
              <th className="col-md-8">Country</th>
              <th className="col-md-8">State</th>
            </tr>
            {numbers &&
              numbers.map((number, index) => (
            <tr>
                <td className="col-md-8"> {number.name}</td> 
                <td className="col-md-8"> {number.phone} </td>
                <td className="col-md-8"> {number.countryName} </td>
                <td className="col-md-8"> {number.state} </td>
            </tr>
              ))}
          </table>
        </div>
      </div>
    );
  }
}