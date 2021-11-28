import React, {Component} from 'react';  

/* Import Components */
import Input from './Input';  
import Select from './Select';
import Button from './Button'

class FormContainer extends Component {  
  constructor(props) {
    super(props);

    this.state = {
      newPhoneNumber: {
        name: '',
        phone: '',
        countryName: [],
      },
      countryNameOptions: ['Cameroon', 'Ethiopia', 'Morocco', 'Mozambique', 'Uganda'],
    }
    this.handleUserName = this.handleUserName.bind(this);
    this.handleNumber = this.handleNumber.bind(this);
    this.handleInput = this.handleInput.bind(this);
    this.handleFormSubmit = this.handleFormSubmit.bind(this);
    this.handleClearForm = this.handleClearForm.bind(this);
  }

  /* This lifecycle hook gets executed when the component mounts */
  
  handleUserName(e) {
   let value = e.target.value;
   this.setState( prevState => ({ newPhoneNumber : 
        {...prevState.newPhoneNumber, name: value
        }
      }), () => console.log(this.state.newPhoneNumber))
  }

  handleNumber(e) {
       let value = e.target.value;
   this.setState( prevState => ({ newPhoneNumber : 
        {...prevState.newPhoneNumber, phone: value
        }
      }), () => console.log(this.state.newPhoneNumber))
  }

  handleInput(e) {
    let value = e.target.value;
    let name = e.target.name;
  this.setState( prevState => ({ newPhoneNumber : 
    {...prevState.newPhoneNumber, [name]: value
    }
  }), () => console.log(this.state.newPhoneNumber))
  }


  handleFormSubmit(e) {
    e.preventDefault();
    let userData = this.state.newPhoneNumber;
    console.log(userData);
    this.handleClearForm(e);
    fetch('http://localhost:8888/numbers',{
        method: "POST",
        body: JSON.stringify(userData),
        headers: {
          'Accept':  "text/plain",
          'Content-Type': 'application/json'
        },
      }).then(response => {
        response.text().then(data =>{
          window.location.reload(false);
        })
    })

  }   

  handleClearForm(e) {
      e.preventDefault();
      this.setState({ 
        newPhoneNumber: {
          name: '',
          phone: '',
          countryName: [],
        }
      })
  }

  render() {
    return (
        <form className="container-fluid" onSubmit={this.handleFormSubmit}>
       
            <Input inputType={'text'}
                   title= {'Customer Name'} 
                   name= {'name'}
                   value={this.state.newPhoneNumber.name} 
                   placeholder = {'Enter name'}
                   handleChange = {this.handleUserName}
                   
                   /> {/* Name of the user */}
        
          <Input inputType={'text'} 
                name={'number'}
                 title= {'Phone Number'} 
                 value={this.state.newPhoneNumber.phone} 
                placeholder = {'Enter Phone number with Country code ()'}
                 handleChange={this.handleNumber} /> {/* number */} 

          <Select title={'Country'}
                  name={'countryName'}
                  options = {this.state.countryNameOptions} 
                  value = {this.state.newPhoneNumber.countryName}
                  placeholder = {'Select Country'}
                  handleChange = {this.handleInput}
                  /> {/* country Selection */}
        
          <Button 
              action = {this.handleFormSubmit}
              type = {'primary'} 
              title = {'Submit'} 
            style={buttonStyle}
          /> { /*Submit */ }
          
          <Button 
            action = {this.handleClearForm}
            type = {'secondary'}
            title = {'Clear'}
            style={buttonStyle}
          /> {/* Clear the form */}
          
        </form>
    );
  }
}

const buttonStyle = {
  margin : '5px 5px 5px 5px'
}

export default FormContainer;