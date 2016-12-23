import React, { Component } from 'react';
import GetBalance from './GetBalance.js';
import ShowResult from './ShowResult.js';
import logo from './logo.svg';
import './App.css';

class App extends Component {

constructor() {
  super();
  this.state = {
    accountNumber:'',
    balance:0,
    amount:0
  };
}

checkAccountNumber() {
  if(this.state.accountNumber === '' || this.state.accountNumber == null) {
    alert('please enter your account number');
    return false;
  } else {
    return true;
  }
}

sendHttpGetRequest(url, result) {
  fetch(url, {
  method: "GET"
  })
  .then((response) =>{
    return response.json();
  })
  .then((responseData) =>{
    result(responseData);
  });
}

sendHttpPostRequest(url, body, result) {
  fetch(url, {
  method: "POST",
  body: body,
  dataType: 'json',
  headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  }
  })
  .then((response) =>{
    return response.json();
  })
  .then((responseData) =>{
    result(responseData);
  })  ;
}

getBalance_onClick() {
  if(this.checkAccountNumber()) {
    var url = 'http://10.10.3.63:8080/balance/' + this.state.accountNumber;
    this.sendHttpGetRequest(url, (x => this.setState({balance : x})));
  }
}

deposit() {
  if(this.checkAccountNumber()) {
    var url = 'http://10.10.3.63:8080/deposit/' + this.state.accountNumber + '/' + this.state.amount;
    var data = {'accountNumber':this.state.accountNumber,
                'amount':this.state.amount};
    var body = JSON.stringify(data);
    this.sendHttpPostRequest(url, body, (x => {
      	if(x==="FINISHED") {
          alert("The deposit process succeeded!");
        } else {
          alert("Something went wrong, the deposit process failed!");
        }
    }));
  }
}

withdrawal() {
  if(this.checkAccountNumber()) {
    var url = 'http://10.10.3.63:8080/withdrawal/' + this.state.accountNumber + '/' + this.state.amount;
    var data = {'accountNumber':this.state.accountNumber,
                'amount':this.state.amount}
    var body = JSON.stringify(data);
    this.sendHttpPostRequest(url, body, (x => {
      	if(x==="FINISHED") {
          alert("The withdrawal process succeeded!");
        } else {
          alert("Something went wrong, the withdrawal process failed!");
        }
    }));
  }
}

onKeyUpBalance(event) {
  this.setState({accountNumber:event.currentTarget.value});
}

onKeyUpAmount(event) {
  this.setState({amount:event.currentTarget.value});
}

  render() {
    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to Super Bank</h2>
        </div>
        <div className="App-intro">
          <GetBalance ref="getBalance"
            getBalance_onClick={this.getBalance_onClick.bind(this)}
            onKeyUpAmount={this.onKeyUpAmount.bind(this)}
            onKeyUpBalance={this.onKeyUpBalance.bind(this)}
            deposit={this.deposit.bind(this)}
            withdrawal={this.withdrawal.bind(this)}/>
          <ShowResult ref="ShowResult"
            accountNumber={this.state.accountNumber}
            balance={this.state.balance}/>
        </div>
      </div>
    );
  }
}

export default App;
